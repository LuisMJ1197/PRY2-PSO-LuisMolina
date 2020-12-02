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
public class PUSH extends Instruction {
    private Register reg;
    
    public PUSH(Core core, Register reg) {
        this.core = core;
        this.reg = reg;
        this.height = IInstruction.PUSH;
    }
    
    @Override
    public boolean execute() {
        if (this.core.getPCB().getStack().isFull()) return false;
        this.core.getPCB().getStack().push(reg.getValue());
        this.movePc(1);
        return true;
    }
}
