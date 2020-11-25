/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.process;

import machine.memory.Register;
import os.PCB;

/**
 *
 * @author Luism
 */
public class Process implements IProcess {
    protected static int sCantProcess = 0;
    protected final int pid;
    protected boolean loaded = false;
    protected String name;
    protected PCB pcb;
    protected int burstTime;
    
    protected Register[] savedMemory;
    protected final String[] code;
    
    public Process(String name, String[] code) {
        this.name = name;
        this.pid = sCantProcess++;
        this.code = code;
    }

    public static int getsCantProcess() {
        return sCantProcess;
    }

    public static void setsCantProcess(int sCantProcess) {
        Process.sCantProcess = sCantProcess;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPid() {
        return pid;
    }

    @Override
    public int getProgramSize() {
        return this.code.length;
    }
    
    @Override
    public int getProcessSize() {
        return this.getProgramSize();
    }

    @Override
    public PCB getPcb() {
        return this.pcb;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void createPCB() {
        this.pcb = new PCB(this.pid);
    }  
    
    public String[] getProcessCode() {
        return this.code;
    }
    
    public Register[] getSavedMemory() {
        return this.savedMemory;
    }

    public int getBurstTime() {
        return this.burstTime;
    }
    
    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setAllocatedMemory(Register[] savedMemory) {
        this.savedMemory = savedMemory;
    }

    public void allocateMemory(Register[] savedMemory) {
        this.setAllocatedMemory(savedMemory);
        this.setLoaded(true);
        this.getPcb().setPc(savedMemory[0].getAddress());
        this.getPcb().setBase(savedMemory[0].getAddress());
        for (int i = 0; i < this.getProgramSize(); i++) {
            savedMemory[i].setValue(this.code[i]);
        }
    }
}
