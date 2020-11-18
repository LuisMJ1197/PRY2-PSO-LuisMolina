/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import machine.memory.Register;
import util.stack.MStack;

/**
 *
 * @author Luism
 */
public class PCB {
    public static final String NEW = "New";
    public static final String READY = "Ready";
    public static final String RUNNING = "Running";
    public static final String WAITING = "Waiting";
    public static final String TERMINATED = "Terminated";
    public static final int STACK_SIZE = 5;
    public static final int PCB_SIZE = 14 + STACK_SIZE;
    public static final int ID_INDEX = 0;
    public static final int STATUS_INDEX = 1;
    public static final int BASE_INDEX = 2;
    public static final int LIMIT_INDEX = 3;
    public static final int PC_INDEX = 4;
    public static final int AC_INDEX = 5;
    public static final int AX_INDEX = 6;
    public static final int BX_INDEX = 7;
    public static final int CX_INDEX = 8;
    public static final int DX_INDEX = 9;
    public static final int STACK_INDEX = 10;
    public static final int CPUNUMBER_INDEX = STACK_INDEX + STACK_SIZE;
    public static final int STARTTIME_INDEX = STACK_INDEX + STACK_SIZE + 1;
    public static final int EXECUTIONTIME_INDEX = STACK_INDEX + STACK_SIZE + 2;
    public static final int IO_INDEX = STACK_INDEX + STACK_SIZE + 3;

    private Register status;
    private Register pc;
    private Register ac;
    private Register ax;
    private Register bx;
    private Register cx;
    private Register dx;
    
    private Register cpuNumber;
    private Register startTime;
    private Register executingTime;
    private Register pid;
    private Register listOfIO;
    private MStack stack;
    private Register base;
    private Register limit;
    // I/O info? TODO
    
    public PCB() {
        this.status.setValue(NEW);
        this.stack = new MStack(STACK_SIZE);
    }
    
    public PCB(Register[] array) {
        this.pid = array[ID_INDEX];
        this.status = array[STATUS_INDEX];
        this.status.setValue(NEW);
        this.pc = array[PC_INDEX];
        this.ac = array[AC_INDEX];
        this.ac.setValue(Register.ZERO);
        this.ax = array[AX_INDEX];
        this.ax.setValue(Register.ZERO);
        this.bx = array[BX_INDEX];
        this.bx.setValue(Register.ZERO);
        this.cx = array[CX_INDEX];
        this.cx.setValue(Register.ZERO);
        this.dx = array[DX_INDEX];
        this.dx.setValue(Register.ZERO);
        this.cpuNumber = array[CPUNUMBER_INDEX];
        this.cpuNumber.setValue("-1");
        this.startTime = array[STARTTIME_INDEX];
        this.executingTime = array[EXECUTIONTIME_INDEX];
        this.listOfIO = array[IO_INDEX];
        this.base = array[BASE_INDEX];
        this.limit = array[LIMIT_INDEX];
        this.startStack(array);
    }
    
    private void startStack(Register[] array) {
        this.stack = new MStack(STACK_SIZE);
        for (int i = STACK_INDEX + STACK_SIZE; i >= STACK_INDEX; i--) {
            this.stack.push(array[i]);
        }
    }
    
    public Register getStatus() {
        return status;
    }

    public int getSize() {
        return PCB.PCB_SIZE;
    }

    public Register getPc() {
        return pc;
    }

    public Register getAc() {
        return ac;
    }

    public Register getAx() {
        return ax;
    }

    public Register getBx() {
        return bx;
    }

    public Register getCx() {
        return cx;
    }

    public Register getDx() {
        return dx;
    }

    public Register getCpuNumber() {
        return cpuNumber;
    }

    public Register getStartTime() {
        return startTime;
    }

    public Register getExecutingTime() {
        return executingTime;
    }

    public Register getPid() {
        return pid;
    }

    public Register getListOfIO() {
        return listOfIO;
    }

    public MStack getStack() {
        return stack;
    }

    public Register getBase() {
        return base;
    }

    public Register getLimit() {
        return limit;
    }
    
    
}
