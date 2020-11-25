/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.memorymanagement;

import machine.memory.Register;
import os.process.Process;

/**
 *
 * @author Luism
 */
public interface MemoryManager {
    public void init();
    public void loadProcess(Process process);
    public void loadProcessInMemory(Process process);
    public boolean verifyProgramSize(Process process);
    public void freeProcessMemory(Process process);
    public int getOSMemorySaved();
}
