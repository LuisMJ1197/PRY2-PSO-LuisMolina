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
public class SJFScheduler extends Scheduler {

    @Override
    public Process chooseNextTurn() {
        Process next = this.processQueue.peek();
        if (next != null) {
            for (Process process: this.processQueue.getList()) {
                if (process.getBurstTime() < next.getBurstTime()) {
                    next = process;
                }
            }
            this.processQueue.dequeue(next);
        }
        return next;
    }

    @Override
    public void setNextBurstTime(Core core, Process process) {
        core.setBurstTime(process.getBurstTime());
    }
    
}
