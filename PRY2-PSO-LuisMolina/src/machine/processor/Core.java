/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor;

import machine.Machine;
import machine.processor.instruction.IInstruction;
import os.OS;
import os.process.PCB;
import os.process.Process;

/**
 *
 * @author Luism
 */
public class Core {
    private final int coreNumber;
    private Process process;
    private boolean busy = false;
    private int burstTime = 0;
    private ICoreObserver observer;
    private int zeroFlag = 0;
    private String ir = "-";
    
    public Core(int coreNumber) {
        this.coreNumber = coreNumber;
    }
    
    public int getNumber() {
        return this.coreNumber;
    }

    public boolean nextCycle(int time) {
        this.burstTime--;
        if (this.observer != null && this.process != null) {
            this.observer.setTimeExecutionId(time, this.process.getPid(), this.coreNumber);
        }
        if (this.process != null) {
            this.process.getPcb().setExecutingTime(this.process.getPcb().getExecutingTime() + 1);
            IInstruction instruction = this.nextInstruction();
            if (instruction !=  null) {
                if (!instruction.execute()) {
                    this.abortProcess();
                }
            }
        }
        return this.burstTime > 0;
    }
    
    private IInstruction nextInstruction() {
        if (OS.getInstance().getMemoryManager().validateAddress(process, process.getPcb().getPcAddress())) {
            String nextInstruction = Machine.getInstance().getMainMemory().load(this.getPCB().getPcAddress());
            this.ir = nextInstruction;
            return InstructionDecoder.decode(this, nextInstruction);
        } else {
            this.ir = "-";
            this.abortProcess();
            return null;
        }
    }

    public void setProcess(Process next) {
        this.process = next;
        this.busy = true;
    }

    public boolean isBusy() {
        return this.busy;
    }

    public PCB getPCB() {
        if (this.process != null) {
            return this.process.getPcb();
        } else {
            return null;
        }
    }
    
    public Process getProcess() {
        return this.process;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }
    
    public void setCoreObserver(ICoreObserver observer){ 
        this.observer = observer;
    }

    public void setZeroFlag(int flag) {
        this.zeroFlag = flag;
    }

    public int getZeroFlag() {
        return this.zeroFlag;
    }

    public void abortProcess() {
        this.process.getPcb().setStatus(PCB.TERMINATED);
    }
    
    public String getIr() {
        return this.ir;
    }
    
    public interface ICoreObserver {
        public void setTimeExecutionId(int time, int pid, int coreNumber);
    }
    
}
