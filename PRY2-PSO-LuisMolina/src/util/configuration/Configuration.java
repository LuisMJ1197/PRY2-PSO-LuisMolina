/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.configuration;

import util.configuration.memorymanager.FixedPartitionConfiguration;
import util.configuration.processmanager.FCFSConfiguration;
import util.configuration.processmanager.HRRNConfiguration;
import util.configuration.processmanager.RoundRobinConfiguration;
import util.configuration.processmanager.SJFConfiguration;
import util.configuration.processmanager.SRTConfiguration;

/**
 *
 * @author Luism
 */
public class Configuration {
    private int mainMemorySize = 0;
    private int diskMemorySize = 0;
    private int osSavedMemory = 0;
    
    /* Memory manager */
    private FixedPartitionConfiguration fixedPartitionConfiguration;
    
    /* Process manager */
    private FCFSConfiguration fcfsConfiguration;
    private RoundRobinConfiguration roundRobinConfiguration;
    private SRTConfiguration srtConfiguration;
    private SJFConfiguration sjfConfiguration;
    private HRRNConfiguration hrrnConfiguration;
    
    public Configuration() {
    }

    public void setMainMemorySize(int mainMemorySize) {
        this.mainMemorySize = mainMemorySize;
    }

    public void setDiskMemorySize(int diskMemorySize) {
        this.diskMemorySize = diskMemorySize;
    }
    
    public int getMainMemorySize() {
        return mainMemorySize;
    }

    public int getDiskMemorySize() {
        return diskMemorySize;
    }

    public int getOsSavedMemory() {
        return osSavedMemory;
    }

    public void setOsSavedMemory(int osSavedMemory) {
        this.osSavedMemory = osSavedMemory;
    }
    
    public void setFixedPartitionConfiguration(boolean activated, int partitionSize) {
        this.fixedPartitionConfiguration = new FixedPartitionConfiguration(activated, partitionSize);
    }

    public FixedPartitionConfiguration getFixedPartitionConfiguration() {
        return fixedPartitionConfiguration;
    }
    
    public void setFcfsConfiguration(boolean activated) {
        this.fcfsConfiguration = new FCFSConfiguration(activated);
    }
    
    public FCFSConfiguration getFcfsConfiguration() {
        return this.fcfsConfiguration;
    }
    
    public void setRoundRobinConfiguration(boolean activated, int cycleClockAmount) {
        this.roundRobinConfiguration = new RoundRobinConfiguration(activated, cycleClockAmount);
    }
    
    public RoundRobinConfiguration getRoundRobinConfiguration() {
        return this.roundRobinConfiguration;
    }

    void setSRTConfiguration(boolean activated) {
        this.srtConfiguration = new SRTConfiguration(activated);
    }

    public SRTConfiguration getSrtConfiguration() {
        return srtConfiguration;
    }
    
    void setSJFConfiguration(boolean activated) {
        this.sjfConfiguration = new SJFConfiguration(activated);
    }

    public SJFConfiguration getSjfConfiguration() {
        return sjfConfiguration;
    }

    void setHRRNConfiguration(boolean activated) {
        this.hrrnConfiguration = new HRRNConfiguration(activated);
    }

    public HRRNConfiguration getHrrnConfiguration() {
        return hrrnConfiguration;
    }
    
}
