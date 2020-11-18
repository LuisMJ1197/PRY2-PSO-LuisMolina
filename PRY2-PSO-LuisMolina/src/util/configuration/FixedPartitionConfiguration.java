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
public class FixedPartitionConfiguration {
    private boolean activated;
    private int partitionSize;
    
    public FixedPartitionConfiguration(boolean activated, int partitionSize) {
        this.activated = activated;
        this.partitionSize = partitionSize;
    }

    public boolean isActivated() {
        return activated;
    }

    public int getPartitionSize() {
        return partitionSize;
    }
}
