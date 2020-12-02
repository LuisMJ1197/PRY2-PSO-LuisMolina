/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor.instruction;

import machine.Machine;
import machine.io.Display;
import machine.io.IODeviceListener;
import machine.processor.Core;
import os.process.PCB;

/**
 *
 * @author Luism
 */
public class INT extends Instruction implements IODeviceListener {
    private static final int PRINT = 9;
    private static final int FINISH = 20;
    private static final int READ = 10;
    private final int value;
    private boolean done = false;
    
    public INT(Core cpu, int value) {
        this.height = -1;
        this.value = value;
        this.core = cpu;
    }
    
    public String getProcessID() {
        return Integer.toString(this.core.getPCB().getPid());
    }
    
    @Override
    public boolean execute() {
        switch (value) {
            case PRINT:
                // this.core.getPCB().getListOfIO().setValue("00010000");
                ((Display) Machine.getInstance().getDisplay()).addKey(Integer.toString(core.getPCB().getDx()));
                Machine.getInstance().getDisplay().setBusy(core.getPCB().getPid(), this);
                break;
            case FINISH:
                this.core.abortProcess();
                done = true;
                break;
        }
        return true;
    }

    @Override
    public void callback(String result) {
        switch (value) {
            case PRINT:
                done = true;
                this.core.getPCB().setStatus(PCB.RUNNING);
                this.movePc(1);
                break;
        }
    }
    
    @Override
    public boolean isDone() {
        return this.done;
    }
}
