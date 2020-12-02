/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor.instruction;

import machine.processor.Core;

/**
 *
 * @author Luism
 */
public class JE extends Instruction {
    private final int offset;
    
    public JE(Core core, int offset) {
        this.core = core;
        this.offset = offset;
        this.height = IInstruction.JE;
    }
    
    @Override
    public boolean execute() {
        if (this.core.getZeroFlag() == 1) {
            this.movePc(offset);
        } else {
            this.movePc(1);
        }
        return true;
    }
}
