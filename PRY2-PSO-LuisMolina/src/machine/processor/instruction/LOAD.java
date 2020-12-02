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
public class LOAD extends Instruction {
    private final Register reg;
    
    public LOAD(Core cpu, Register reg) {
        this.height = IInstruction.LOAD;
        this.reg = reg;
        this.core = cpu;
    }
    
    @Override
    public boolean execute() {
        this.core.getPCB().setAc(reg.getValue());
        this.movePc(1);
        return true;
    }
}
