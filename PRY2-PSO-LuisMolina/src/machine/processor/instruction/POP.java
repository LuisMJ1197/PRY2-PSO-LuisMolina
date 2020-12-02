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
public class POP extends Instruction {
    private Register reg;
    
    public POP(Core core, Register reg) {
        this.core = core;
        this.reg = reg;
        this.height = IInstruction.POP;
    }
    
    @Override
    public boolean execute() {
        if (this.core.getPCB().getStack().isEmpty()) return false;
        this.reg.setValue(this.core.getPCB().getStack().pop());
        this.movePc(1);
        return true;
    }
}
