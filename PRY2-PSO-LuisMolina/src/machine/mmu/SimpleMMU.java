/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.mmu;

import java.util.ArrayList;
import machine.memory.IAddress;
import machine.memory.IMemory;
import machine.memory.PhysicalAddress;
import machine.memory.Register;

/**
 *
 * @author Luism
 */
public class SimpleMMU implements IMMU {
    private IMemory mainMemory;
    private IMemory diskMemory;
    
    public SimpleMMU(IMemory mainMemory, IMemory diskMemory) {
        this.mainMemory = mainMemory;
        this.diskMemory = diskMemory;
    }
    
    /**
     *
     * @param address
     * @return
     */
    @Override
    public String loadFromMemory(IAddress address) {
        return null;
    }
    
    @Override
    public void storeToMemory(IAddress address, String value) {
        
    }
    
    @Override
    public Register[] saveMemory(int size) {
        ArrayList<Register> res = this.lookForMemory(mainMemory, size);
        if (res.isEmpty()) {
            res = this.lookForMemory(diskMemory, size);
        }
        if (res.isEmpty()) return null;
        else {
            Register[] result = new Register[size];
            res.toArray(result);
            return result;
        }
    }
    
    private ArrayList<Register> lookForMemory(IMemory memory, int size) {
        ArrayList<Register> res = new ArrayList<>();
        for (int i = 0; i < memory.getSize(); i++) {
            if (memory.getRegister(i).getValue().equals(Register.EMPTY ) && i + size <= memory.getSize()) {
                for (int j = i; j < i + size; j++) {
                    if (memory.getRegister(j).getValue().equals(Register.EMPTY)) {
                        res.add(memory.getRegister(j));
                    } else {
                        res.clear();
                        break;
                    }
                }
                if (!res.isEmpty()) return res;
            }
        }
        return res;
    }
}
