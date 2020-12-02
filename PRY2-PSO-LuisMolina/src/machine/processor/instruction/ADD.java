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
public class ADD extends Instruction {
    private final Register reg;
    
    public ADD(Core cpu, Register reg) {
        this.height = IInstruction.ADD;
        this.reg = reg;
        this.core = cpu;
    }
    
    @Override
    public boolean execute() {
        this.core.getPCB().setAc(
                this.core.getPCB().getAc() + 
                Integer.parseInt(reg.getValue())
            );
        this.movePc(1);
        return true;
    }
}
