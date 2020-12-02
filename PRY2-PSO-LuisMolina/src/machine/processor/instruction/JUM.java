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
public class JUM extends Instruction {
    private final int offset;
    
    public JUM(Core core, int offset) {
        this.core = core;
        this.offset = offset;
        this.height = IInstruction.JUM;
    }
    
    @Override
    public boolean execute() {
        this.movePc(offset);
        return true;
    }
    
}
