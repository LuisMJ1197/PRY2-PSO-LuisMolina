/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import machine.mmu.FixedPartitionMMU;
import machine.mmu.IMMU;

/**
 *
 * @author Luism
 */
public class OS {
    private static OS instance = new OS();
    private IMMU memoryManager = new FixedPartitionMMU();
    
    private OS() {
    }
    
    public static OS getInstance() {
        return instance;
    }
    
    public IMMU getMemoryManager() {
        return this.memoryManager;
    }
}
