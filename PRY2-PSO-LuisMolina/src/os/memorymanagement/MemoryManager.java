/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.memorymanagement;

import os.process.Process;
import util.queue.MQueue;

/**
 *
 * @author Luism
 */
public abstract class MemoryManager {
    protected int osSavedMemory;
    protected MQueue<Process> processQueue = new MQueue<>();
    
    public MemoryManager(int osSavedMemory) {
        this.osSavedMemory = osSavedMemory;
    }
    
    public abstract void init();
    public abstract void loadProcess(Process process);
    public abstract void loadProcessInMemory(Process process);
    public abstract boolean verifyProgramSize(Process process);
    public abstract void freeProcessMemory(Process process);
    public abstract int getOSMemorySaved();
    public abstract LogicalAddress getNextAddress(Process process);
    public abstract String load(LogicalAddress address);
    public abstract void store(LogicalAddress address, String value);
}
