/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import machine.Machine;

/**
 *
 * @author Luism
 */
public class Loader {
    
    public String loadProgram(Program program) {
        Process newP = new Process(program.getName(), program.getCompiledLines());
        newP.createPCB();
        /*OS.getInstance().getMemoryManager().loadProcess(newP);
        if (!newP.isLoaded()) {
            return String.format("Program %s is too big.\n", program.getName());
        }*/
        Machine.getInstance().getProcessor().addProcess(newP);
        return "";
    }
}
