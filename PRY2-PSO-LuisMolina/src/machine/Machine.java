/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine;

import machine.io.Display;
import machine.memory.IMemory;
import machine.memory.Memory;
import machine.mmu.IMMU;
import machine.mmu.SimpleMMU;
import machine.processor.Processor;

/**
 *
 * @author Luism
 */
public class Machine {
    private static Machine instance;
    private final IMemory mainMemory;
    private final IMemory virtualMemory;
    private final Processor processor;
    private final Display display;
    private final IMMU mmu;
    
    public Machine(int mainMemorySize, int virtualMemorySize) {
        this.mainMemory = new Memory(mainMemorySize, IMemory.MAIN_MEMORY);
        this.virtualMemory = new Memory(virtualMemorySize, IMemory.DISK_MEMORY);
        this.processor = new Processor();
        this.display = new Display();
        this.mmu = new SimpleMMU(this.mainMemory, this.virtualMemory);
    }

    public static Machine getInstance() {
        return instance;
    }
    
    public IMemory getMainMemory() {
        return this.mainMemory;
    }
    
    public IMemory getSecMemory() {
        return this.virtualMemory;
    }
    
    public Processor getProcessor() {
        return this.processor;
    }
    
    public static void startInstance(Machine machine) {
       instance = machine;
    }

    public IMemory getVirtualMemory() {
        return virtualMemory;
    }

    public Display getDisplay() {
        return display;
    }
    
    public IMMU getMMU() {
        return this.mmu;
    }
    
}
