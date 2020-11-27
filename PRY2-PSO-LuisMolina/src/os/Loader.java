/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import machine.Machine;
import os.process.Program;
import os.process.Process;

/**
 *
 * @author Luism
 */
public class Loader {
    
    public String loadProgram(Program program) {
        Process newP = OS.getInstance().createProcess(program.getName(), program.getCompiledLines());
        newP.setBurstTime(newP.getProgramSize());
        newP.createPCB();
        if (OS.getInstance().getMemoryManager().verifyProgramSize(newP)) {
            if (OS.getInstance().getScheduler().getProcessList().size() < OS.getInstance().getMemoryManager().getOSMemorySaved()) {
                OS.getInstance().getMemoryManager().loadProcess(newP);
                OS.getInstance().getScheduler().addProcess(newP);
                Machine.getInstance().getMainMemory().store(newP.getPid(), Integer.toString(newP.getPid()));
            } else {
                return "The OS can't handle more processes.\n";
            }
        } else {
            return String.format("Program %s is too big.\n", program.getName());
        }
        return "";
    }
}
