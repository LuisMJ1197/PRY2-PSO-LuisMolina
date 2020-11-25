/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.memorymanagement.partition;

import machine.memory.Register;

/**
 *
 * @author Luism
 */
public class Partition {
    private int size;
    private int partitionNumber;
    private Register[] memory;
    private boolean used = false;
    
    public Partition(int size, Register[] memory, int partitionNumber) {
        this.size = size;
        this.memory = memory;
        this.partitionNumber = partitionNumber;
    }
    
    public int getPartitionNumber() {
        return this.partitionNumber;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Register[] getMemory() {
        return memory;
    }

    public boolean equals(Partition partition) {
        return partition.getPartitionNumber() == this.partitionNumber;
    }
    
}
