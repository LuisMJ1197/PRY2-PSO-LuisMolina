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
    private Register[] memory;
    private boolean used = false;
    
    public Partition(int size, Register[] memory) {
        this.size = size;
        this.memory = memory;
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

    
    
}
