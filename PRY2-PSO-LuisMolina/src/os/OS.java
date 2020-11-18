/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import os.memorymanagement.FixedPartitionMM;
import os.memorymanagement.MemoryManager;

/**
 *
 * @author Luism
 */
public class OS {
    private static OS instance;

    private MemoryManager memoryManager;
    private Loader loader = new Loader();
    private MCompiler compiler = new MCompiler();
    
    public OS(MemoryManager memManager) {
        this.setMemoryManager(memManager);
    }
    
    public static OS getInstance() {
        return instance;
    }
    
    public static void startInstance(OS os) {
        instance = os;
    }
    
    private void setMemoryManager(MemoryManager memManager) {
        this.memoryManager = memManager;
        this.memoryManager.init();
    }
     
    public MemoryManager getMemoryManager() {
        return this.memoryManager;
    }
    
    public Loader getLoader() {
        return this.loader;
    }
    
    public String loadProgram(Program program) {
        String errMsg = "";
        this.compiler.compileProgram(program);
        if (!program.isAdmitted()) {
            errMsg = program.getError() + "\n";
        }
        errMsg += this.loader.loadProgram(program);
        return errMsg;
    }
}
