/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.memorymanagement;

import os.Process;

/**
 *
 * @author Luism
 */
public interface MemoryManager {
    public void init();
    public void loadProcess(Process process);
}
