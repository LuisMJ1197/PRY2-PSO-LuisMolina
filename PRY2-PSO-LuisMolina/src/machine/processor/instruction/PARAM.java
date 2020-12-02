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
public class PARAM extends Instruction {
    private String reg1;
    private String reg2;
    private String reg3;
    
    public PARAM(Core core, String reg1) {
        this.core = core;
        this.reg1 = reg1;
        this.height = IInstruction.PARAM;
    }
    
    public PARAM(Core core, String reg1, String reg2) {
        this.core = core;
        this.reg1 = reg1;
        this.reg2 = reg2;
        this.height = IInstruction.PARAM;
    }
    
    public PARAM(Core core, String reg1, String reg2, String reg3) {
        this.core = core;
        this.reg1 = reg1;
        this.reg2 = reg2;
        this.height = IInstruction.PARAM;
        this.reg3 = reg3;
    }

    @Override
    public boolean execute() {
        int count = 1;
        if (reg2 != null) count += 1;
        if (reg3 != null) count += 1;
        if (this.core.getPCB().getStack().getSize() + count > this.core.getPCB().getStack().getMaxSize()) {
            this.movePc(1);
            return false;
        } else {
            this.core.getPCB().getStack().push(reg1);
            if (reg2 != null) this.core.getPCB().getStack().push(reg2);
            if (reg3 != null) this.core.getPCB().getStack().push(reg3);
            this.movePc(1);
            return true;
        }
    }
}
