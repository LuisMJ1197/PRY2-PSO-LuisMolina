/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.memorymanagement;

import java.util.ArrayList;
import machine.Machine;
import machine.memory.IAddress;
import machine.memory.IMemory;
import machine.memory.Register;
import os.process.Process;

/**
 *
 * @author Luism
 */
public class DynamicPartitionMM extends MemoryManager {
    private IMemory mainMemory;
    
    public DynamicPartitionMM(int osSavedMemory) {
        super(osSavedMemory);
    }
    
    @Override
    public void init() {
        this.mainMemory = Machine.getInstance().getMainMemory();
    }

    @Override
    public void loadProcess(Process process) {
        this.loadProcessInMemory(process);
        if(!process.isLoaded()) {
            this.processQueue.enqueue(process);
        }
    }

    @Override
    public void loadProcessInMemory(Process process) {
        Register[] savedMemory = this.searchMemory(process.getProcessSize());
        if (savedMemory != null) {
            process.allocateMemory(savedMemory);
        }
    }
    
    private Register[] searchMemory(int size) {
        ArrayList<Register> res = new ArrayList<>();
        for (int i = this.osSavedMemory; i < this.mainMemory.getSize(); i++) {
            if (this.mainMemory.getRegister(i).getValue().equals(Register.EMPTY) 
                    && i + size <= this.mainMemory.getSize()) {
                for (int j = i; j < i + size; j++) {
                    if (this.mainMemory.getRegister(j).getValue().equals(Register.EMPTY)) {
                        res.add(this.mainMemory.getRegister(j));
                    } else {
                        res.clear();
                        break;
                    }
                }
                if (!res.isEmpty()) return res.toArray(new Register[res.size()]);
            }
        }
        return null;
    }

    @Override
    public boolean verifyProgramSize(Process process) {
        return process.getProcessSize() <= (this.mainMemory.getSize() - this.osSavedMemory);
    }

    @Override
    public void freeProcessMemory(Process process) {
        Register[] freeMemory = process.getSavedMemory();
        for (Register reg: freeMemory) {
            reg.clean();
        }
        Process selectedProcess = null;
        for (Process processP: this.processQueue.getList()) {
            this.loadProcessInMemory(processP);
            if(processP.isLoaded()) {
                selectedProcess = processP;
                break;
            }
        }
        if (selectedProcess != null) {
            this.processQueue.dequeue(process);
        }
    }

    @Override
    public int getOSMemorySaved() {
        return this.osSavedMemory;
    }

    @Override
    public LogicalAddress getNextAddress(Process process, int offset) {
        LogicalAddress address = new LogicalAddress(process.getPcb().getPcAddress().getOffset() + offset);
        return address;
    }

    @Override
    public String load(IAddress address) {
        return Machine.getInstance().getMainMemory().load(address);
    }

    @Override
    public void store(IAddress address, String value) {
        Machine.getInstance().getMainMemory().getRegister(address.getOffset());
    }

    @Override
    public boolean validateAddress(Process process, IAddress address) {
        return address.getOffset() >= process.getPcb().getBase() 
                && address.getOffset() <= (process.getPcb().getBase() + process.getPcb().getLimit() - 1);
    }
    
}
