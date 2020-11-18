/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.mmu;

import machine.memory.IAddress;
import machine.memory.Register;

/**
 *
 * @author Luism
 */
public interface IMMU {
    public Register[] saveMemory(int size);
    public String loadFromMemory(IAddress address);
    public void storeToMemory(IAddress address, String value);
}
