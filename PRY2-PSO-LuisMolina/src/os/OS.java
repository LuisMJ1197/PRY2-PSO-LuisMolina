/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import os.process.Program;
import os.memorymanagement.MemoryManager;
import os.processfactory.ProcessFactory;
import os.processmanagement.Scheduler;
import os.process.Process;
/**
 *
 * @author Luism
 */
public class OS {
    private static OS instance;

    private MemoryManager memoryManager;
    private Loader loader = new Loader();
    private MCompiler compiler = new MCompiler();
    private Scheduler scheduler;
    private ProcessFactory processFactory;
    
    public OS(MemoryManager memManager, Scheduler scheduler, ProcessFactory factory) {
        this.setMemoryManager(memManager);
        this.setScheduler(scheduler);
        this.processFactory = factory;
    }
    
    public static OS getInstance() {
        return instance;
    }
    
    public static void startInstance(OS os) {
        instance = os;
    }
    
    public Process createProcess(String name, String[] code) {
        return this.processFactory.createProcess(name, code);
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

    private void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
    
    public Scheduler getScheduler() {
        return this.scheduler;
    }
}
