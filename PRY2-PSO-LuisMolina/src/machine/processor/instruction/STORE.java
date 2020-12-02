/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor.instruction;

import machine.memory.Register;
import machine.processor.Core;

/**
 *
 * @author Luism
 */
public class STORE extends Instruction {
    private final Register reg;
    
    public STORE(Core cpu, Register reg) {
        this.height = IInstruction.STORE;
        this.reg = reg;
        this.core = cpu;
        this.height = IInstruction.STORE;
    }
    
    @Override
    public boolean execute() {
        reg.setValue(Integer.toString(this.core.getPCB().getAc()));
        this.movePc(1);
        return true;
    }
}
