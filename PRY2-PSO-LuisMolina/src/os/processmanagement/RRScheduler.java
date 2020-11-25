/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.processmanagement;

import machine.processor.Core;
import os.process.Process;

/**
 *
 * @author Luism
 */
public class RRScheduler extends Scheduler {
    private int cycleClockAmount = 0;
    
    public RRScheduler(int cycleClockAmount) {
        this.cycleClockAmount = cycleClockAmount;
    }
    
    @Override
    public Process chooseNextTurn() {
        Process next = this.processQueue.dequeue();
        return next;
    }

    @Override
    public void setNextBurstTime(Core core, Process process) {
        if (process.getBurstTime() - process.getPcb().getExecutingTime() < this.cycleClockAmount) {
            core.setBurstTime(process.getBurstTime() - process.getPcb().getExecutingTime());
        } else {
            core.setBurstTime(this.cycleClockAmount);
        }
    }
    
}
