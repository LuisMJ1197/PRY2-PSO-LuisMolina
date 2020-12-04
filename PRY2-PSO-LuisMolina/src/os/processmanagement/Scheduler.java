/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.processmanagement;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import machine.Machine;
import machine.processor.Core;
import machine.processor.Processor;
import os.OS;
import os.process.PCB;
import util.queue.MQueue;
import os.process.Process;

/**
 *
 * @author Luism
 */
public abstract class Scheduler {
    private MQueue<Process> generalProcessList = new MQueue<>();
    private MQueue<Process> processList = new MQueue<>();
    protected MQueue<Process> processQueue = new MQueue<>();
    protected final Processor processor;
    private boolean executing = false;
    protected int executionTime = 0;
    
    private IProcessorCoresObserver processorCoresObserver;
    private ISchedulerObserver schedulerObserver;
    
    public Scheduler() {
        processor = Machine.getInstance().getProcessor();
    }
    
    public void setProcessorCoresObserver(IProcessorCoresObserver processorCoresObserver){ 
        this.processorCoresObserver = processorCoresObserver;
    }
    
    public void setObserver(ISchedulerObserver schedulerObserver) {
        this.schedulerObserver = schedulerObserver;
    }
    
    public void insert(Process process) {
        this.generalProcessList.enqueue(process);
        this.processList.enqueue(process);
        for (Core core: this.processor.getCores()) {
            if (!core.isBusy()) {
                process.getPcb().setCpuNumber(core.getNumber());
                core.setProcess(process);
                //this.processQueue.enqueue(process);
                // Load process
                break;
            }
        }
    }
    
    public void finish(Process process) {
        process.getPcb().setStatus(PCB.TERMINATED);
    }
    
    public Process getNext() {
        Process p = null;
        for (Process process: this.processList.getList()) {
            if (process.getPcb().getCpuNumber() == -1) {
                //if (!process.isLoaded()) OS.getInstance().getMemoryManager().loadProcessInMemory(process);
                if (process.isLoaded()) {
                    p = process;
                    break;
                }
            }
        }
        return p;
    }
    
    public abstract Process chooseNextTurn();
    
    public void addProcess(Process newP) {
        this.insert(newP);
    }

    public ArrayList<Process> getProcessList() {
        return this.generalProcessList.getList();
    }
    
    public void start() throws InterruptedException {
        this.executing = true;
        this.executionTime = 1;
        boolean processInCycle = false; // If a process is in execution
        Process process = null; // Process in execution
        // Execution per second
        while(executing) {
            this.checkCoresBusy();
            checkForArrivingProcess(); // Checks for processes that have their arrival time at the same of execution time
            
            if(this.processorCoresObserver != null) this.processorCoresObserver.addExecutionSecond(this.executionTime);
            if(!processInCycle) {// If there is not process in execution, search for the next turn
                process = this.chooseNextTurn();
                if (process != null) {
                    process.getPcb().setStartTimeCal();
                    process.getPcb().setStartTime(executionTime);
                }
            }
            if (process != null) { // If there is a process in execution
                process.getPcb().setStatus(PCB.RUNNING);
                int cpuNumber = process.getPcb().getCpuNumber(); // gets the assigned cpu number
                Core core = processor.getCores()[cpuNumber - 1];
                if(!processInCycle)
                    this.setNextBurstTime(core, process); // Sets the next burst time of the cpu, depends of scheduling algorithm
                processInCycle = core.nextCycle(this.executionTime); // Executes next cycle (second) of the cpu             
                if (!processInCycle || process.getPcb().getStatus().equals(PCB.TERMINATED)) { // If the burst time has ended, checks for the process to be finished, if not, it enters the queue again
                    if (this.checkIfProcessHasFinished(process)) {
                        core.setProcess(null);
                    } else {
                        process.getPcb().setStatus(PCB.READY);
                        this.processQueue.enqueue(process);
                    }
                }
            }
            if (this.schedulerObserver != null) 
                this.schedulerObserver.update();
            if (this.processorCoresObserver != null) {
                this.processorCoresObserver.update();
            }
            this.incrementExecutionTime();
            sleep(1000);
            if (this.processList.getList().isEmpty() && this.processQueue.getList().isEmpty() && process == null) {
                this.executing = false;
                this.schedulerObserver.executionHasFinished();
            }
        }
    }
    
    public boolean checkIfProcessHasFinished(Process process) {
        if (process.getPcb().getExecutingTime() >= process.getBurstTime() 
                || process.getPcb().getStatus().equals(PCB.TERMINATED)) {
            process.getPcb().setStatus(PCB.TERMINATED);
            process.getPcb().setFinishTime(executionTime + 1);
            process.setLoaded(false);
            OS.getInstance().getMemoryManager().freeProcessMemory(process);
            return true;
        } else {
            return false;
        }
    }
    
    
    
    public void stopExecution() {
        this.executing = false;
    }
    
    public int getExecutionTime() {
        return this.executionTime;
    }
    
    public void incrementExecutionTime() {
        this.executionTime++;
    }
    
    public void checkCoresBusy() {
        for(Core core: this.processor.getCores()) {
            if(core.getPCB() == null) 
                this.checkCoreBusy(core);
        }
    }
    
    public void checkCoreBusy(Core core) {
        Process next = this.getNext();
        if (next != null) {
            next.getPcb().setCpuNumber(core.getNumber());
            core.setProcess(next);
            next.getPcb().setArrivalTime(executionTime);
        }
        core.setProcess(next);
    }
    
    private void checkForArrivingProcess() {
        ArrayList<Process> delete = new ArrayList<>();                                                                                                     
        for (Process process: this.processList.getList()) {
            if ((process.getPcb().getArrivalTime() <= this.executionTime) && process.getPcb().getArrivalTime() != -1) {
                //if(!process.isLoaded()) OS.getInstance().getMemoryManager().loadProcessInMemory(process);
                if(process.isLoaded()) {
                    this.processQueue.enqueue(process);
                    process.getPcb().setStatus(PCB.READY);
                    delete.add(process);
                }
            }
        }
        this.processList.getList().removeAll(delete);
    }

    public abstract void setNextBurstTime(Core core, Process process);
    
    public interface ISchedulerObserver {
        public void update();

        public void executionHasFinished();
    }
    
    public interface IProcessorCoresObserver {
        public void update();
        public void addExecutionSecond(int second);
        public void setTimeExecutionId(int time, int pid, int coreNumber);
    }
}
