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
public class Frame extends Partition {
    private boolean isInMainMemory = true;
    
    public Frame(int size, Register[] memory, int partitionNumber) {
        super(size, memory, partitionNumber);
    }

    public boolean isIsInMainMemory() {
        return isInMainMemory;
    }

    public void setIsInMainMemory(boolean isInMainMemory) {
        this.isInMainMemory = isInMainMemory;
    }
    
    
}
