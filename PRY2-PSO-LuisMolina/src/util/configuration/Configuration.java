/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.configuration;

/**
 *
 * @author Luism
 */
public class Configuration {
    private int mainMemorySize = 0;
    private int diskMemorySize = 0;
    private FixedPartitionConfiguration fixedPartitionConfiguration;
    
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
    
    public void setFixedPartitionConfiguration(boolean activated, int partitionSize) {
        this.fixedPartitionConfiguration = new FixedPartitionConfiguration(activated, partitionSize);
    }

    public FixedPartitionConfiguration getFixedPartitionConfiguration() {
        return fixedPartitionConfiguration;
    }
    
    
}
