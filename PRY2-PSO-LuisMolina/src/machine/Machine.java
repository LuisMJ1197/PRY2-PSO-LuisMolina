/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine;

import machine.memory.IMemory;
import machine.memory.Memory;

/**
 *
 * @author Luism
 */
public class Machine {
    public static Machine instance;
    private final IMemory mainMemory;
    private final IMemory virtualMemory;
    
    public Machine(int mainMemorySize, int virtualMemorySize) {
        this.mainMemory = new Memory(mainMemorySize, IMemory.MAIN_MEMORY);
        this.virtualMemory = new Memory(virtualMemorySize, IMemory.DISK_MEMORY);
    }

    public IMemory getMainMemory() {
        return this.mainMemory;
    }
    
    public IMemory getSecMemory() {
        return this.virtualMemory;
    }
}
