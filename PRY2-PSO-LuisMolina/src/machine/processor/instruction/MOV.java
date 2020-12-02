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
public class MOV extends Instruction {
    private final Register reg1;
    private final Register reg2;
    
    public MOV(Core cpu, Register reg1, Register reg2) {
        this.height = IInstruction.MOV;
        this.reg1 = reg1;
        this.reg2 = reg2;
        this.core = cpu;
    }
    
    @Override
    public boolean execute() {
        reg1.setValue(reg2.getValue());
        this.movePc(1);
        return true;
    }
}
