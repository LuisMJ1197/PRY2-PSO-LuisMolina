/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.configuration.memorymanager;

import util.configuration.MethodConfiguration;

/**
 *
 * @author Luism
 */
public class FixedPartitionConfiguration extends MethodConfiguration {
    private int partitionSize;
    
    public FixedPartitionConfiguration(boolean activated, int partitionSize) {
        super(activated);
        this.partitionSize = partitionSize;
        this.name = "Fixed partition";
    }

    public int getPartitionSize() {
        return partitionSize;
    }

    public void setPartitionSize(int i) {
        this.partitionSize = i;
    }
}
