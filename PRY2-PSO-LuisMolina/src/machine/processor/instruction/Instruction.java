/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor.instruction;

import machine.memory.IAddress;
import machine.processor.Core;
import os.OS;

/**
 *
 * @author Luism
 */
public abstract class Instruction implements IInstruction {
    protected int height;
    private int executionTime = 0;
    protected Core core;
    
    @Override
    public abstract boolean execute();

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int incrementExecutionTime() {
        return ++this.executionTime;
    }
    
    @Override
    public int getExecutionTime() {
        return this.executionTime;
    }
    
    @Override
    public boolean isDone() {
        return this.executionTime == this.height;
    }
    
    @Override 
    public void movePc(int offset) { 
        IAddress pc = OS.getInstance().getMemoryManager().getNextAddress(this.core.getProcess(), offset);
        this.core.getPCB().setPc(pc);
    }
}
