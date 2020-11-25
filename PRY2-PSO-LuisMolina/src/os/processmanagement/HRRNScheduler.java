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
public class HRRNScheduler extends Scheduler {

    @Override
    public Process chooseNextTurn() {
        if (this.processQueue.size() > 0) {
            double[] ratios = new double[this.processQueue.size()];
            int i = 0;
            for (Process process: this.processQueue.getList()) {
                // ((actualtime - arrivaltime) + s) / s = (w + s) / s
                ratios[i++] = 
                        ((this.executionTime - process.getPcb().getArrivalTime()) + process.getBurstTime()) 
                        / process.getBurstTime();
            }
            return this.processQueue.dequeue(this.getIndexOfMaxValue(ratios));
        } else {
            return null;
        }
    }
    
    public int getIndexOfMaxValue (double[] array) {
        if (array.length == 0) return -1;
        double max = array[0];
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                index = i;
            }
        }
        return index;
    }

    @Override
    public void setNextBurstTime(Core core, Process process) {
        core.setBurstTime(process.getBurstTime());
    }
    
}
