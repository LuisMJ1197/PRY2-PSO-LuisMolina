/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.util.Date;
import machine.memory.IAddress;
import machine.memory.Register;
import os.memorymanagement.LogicalAddress;
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
    
    private String status;
    private LogicalAddress pc;
    private int ac;
    private int ax;
    private int bx;
    private int cx;
    private int dx;
    
    private int cpuNumber;
    private int arrivalTime;
    private int executingTime;
    private int pid;
    private MStack<Integer> stack;
    private IAddress base;
    private int limit;
    // I/O info? TODO

    public PCB(int pid) {
        this.pid = pid;
        this.cpuNumber = -1;
        this.ac = 0;
        this.ax = 0;
        this.bx = 0;
        this.cx = 0;
        this.dx = 0;
        this.arrivalTime = -1;
        this.executingTime = 0;
        this.limit = 0;
        this.pc = null;
        this.base = null;
        this.status = PCB.NEW;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPc() {
        return pc != null ? pc.getAddressString() : "";
    }
    
    public IAddress getPcAddress() {
        return pc;
    }

    public void setPc(LogicalAddress pc) {
        this.pc = pc;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getAx() {
        return ax;
    }

    public void setAx(int ax) {
        this.ax = ax;
    }

    public int getBx() {
        return bx;
    }

    public void setBx(int bx) {
        this.bx = bx;
    }

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getCpuNumber() {
        return cpuNumber;
    }

    public void setCpuNumber(int cpuNumber) {
        this.cpuNumber = cpuNumber;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getExecutingTime() {
        return executingTime;
    }

    public void setExecutingTime(int executingTime) {
        this.executingTime = executingTime;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public MStack<Integer> getStack() {
        return stack;
    }

    public void setStack(MStack<Integer> stack) {
        this.stack = stack;
    }

    public IAddress getBase() {
        return base;
    }

    public void setBase(IAddress base) {
        this.base = base;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
    
    
}
