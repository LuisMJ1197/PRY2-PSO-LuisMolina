/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor;

import os.PCB;

/**
 *
 * @author Luism
 */
public class Core {
    private final int coreNumber;
    private PCB process;
    private boolean busy = false;
    private int burstTime = 0;
    private ICoreObserver observer;
    
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
            this.process.setExecutingTime(this.process.getExecutingTime() + 1);
        }
        return this.burstTime > 0;
    }

    public void setProcess(PCB next) {
        this.process = next;
        this.busy = true;
    }

    public boolean isBusy() {
        return this.busy;
    }

    public PCB getPCB() {
        return this.process;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }
    
    public void setCoreObserver(ICoreObserver observer){ 
        this.observer = observer;
    }
    
    public interface ICoreObserver {
        public void setTimeExecutionId(int time, int pid, int coreNumber);
    }
}
