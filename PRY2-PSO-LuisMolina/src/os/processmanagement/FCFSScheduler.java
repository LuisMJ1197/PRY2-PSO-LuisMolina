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
public class FCFSScheduler extends Scheduler {

    public FCFSScheduler() {
        
    }
    
    @Override
    public Process chooseNextTurn() {
        Process next = this.processQueue.dequeue();
        return next;
    }

    @Override
    public void setNextBurstTime(Core core, Process process) {
        core.setBurstTime(process.getBurstTime());
    }
    
}
