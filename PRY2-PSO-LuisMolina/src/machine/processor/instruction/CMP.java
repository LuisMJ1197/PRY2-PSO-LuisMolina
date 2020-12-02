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
public class CMP extends Instruction {
    private final Register reg1;
    private final Register reg2;
    
    public CMP(Core core, Register reg1, Register reg2) {
        this.core = core;
        this.reg1 = reg1;
        this.reg2 = reg2;
        this.height = IInstruction.CMP;
    }
    
    @Override
    public boolean execute() {
        if (Integer.parseInt(reg1.getValue()) == Integer.parseInt(reg2.getValue())) {
            this.core.setZeroFlag(1);
        } else {
            this.core.setZeroFlag(0);
        }
        this.movePc(1);
        return true;
    }
    
}
