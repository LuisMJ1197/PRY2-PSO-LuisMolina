/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.process;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    
    private static final int STACK_SIZE = 5;
    
    private Register status;
    private IAddress pc;
    private Register ac;
    private Register ax;
    private Register bx;
    private Register cx;
    private Register dx;
    
    private Register cpuNumber;
    // Time
    private Calendar startTimeCalendar;
    private Register startTime;
    private Register arrivalTime;
    private Register executingTime;
    private Register finishTime;
    private Register pid;
    private MStack stack;
    private Register base;
    private Register limit;
    // I/O info? TODO

    public PCB(int pid) {
        this.pid = new Register(Integer.toString(pid));
        this.cpuNumber = new Register("-1");
        this.ac = new Register("0");
        this.ax = new Register("0");
        this.bx = new Register("0");
        this.cx = new Register("0");
        this.dx = new Register("0");
        this.startTime = new Register("-1");
        this.arrivalTime = new Register("-1");
        this.executingTime = new Register("0");
        this.finishTime = new Register("-1");
        this.limit = new Register("0");
        this.pc = null;
        this.base = new Register(Register.EMPTY);
        this.status = new Register(PCB.NEW);
        this.startStack();
    }
    
    private void startStack() {
        this.stack = new MStack(STACK_SIZE);
        for (int i = 0; i < STACK_SIZE; i++) {
            this.stack.push(new Register(Register.EMPTY));
        }
    }
    
    public void setStartTimeCal() {
        this.startTimeCalendar = Calendar.getInstance();
    }
    
    public String getStartTimeCal() {
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm:ss");
        String date1 = format1.format(this.startTimeCalendar.getTime()); 
        return date1;
    }
    
    public String getFinishTimeCal() {
        Calendar cl = (Calendar) this.startTimeCalendar.clone();
        cl.add(Calendar.SECOND, this.getFinishTime() - this.getStartTime());
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm:ss");
        String date1 = format1.format(cl.getTime()); 
        return date1;
    }
    
    public void setStartTime(int time) {
        if (this.getStartTime() == -1)
            this.startTime.setValue(Integer.toString(time));
    }
    
    public int getStartTime() {
        return Integer.parseInt(this.startTime.getValue());
    }

    public String getStatus() {
        return status.getValue();
    }

    public void setStatus(String status) {
        this.status.setValue(status);
    }

    public String getPc() {
        return pc != null ? pc.getAddressString() : "";
    }
    
    public IAddress getPcAddress() {
        return pc;
    }

    public void setPc(IAddress pc) {
        this.pc = pc;
    }

    public int getAc() {
        return Integer.parseInt(ac.getValue());
    }

    public void setAc(int ac) {
        this.ac.setValue(Integer.toString(ac));
    }
    
    public void setAc(String ac) {
        this.ac.setValue(ac);
    }

    public int getAx() {
        return Integer.parseInt(ax.getValue());
    }

    public void setAx(int ax) {
        this.ax.setValue(Integer.toString(ax));
    }

    public int getBx() {
        return Integer.parseInt(bx.getValue());
    }

    public void setBx(int bx) {
        this.bx.setValue(Integer.toString(bx));
    }

    public int getCx() {
        return Integer.parseInt(cx.getValue());
    }

    public void setCx(int cx) {
        this.cx.setValue(Integer.toString(cx));
    }

    public int getDx() {
        return Integer.parseInt(dx.getValue());
    }

    public void setDx(int dx) {
        this.dx.setValue(Integer.toString(dx));
    }

    public int getCpuNumber() {
        return Integer.parseInt(cpuNumber.getValue());
    }

    public void setCpuNumber(int cpuNumber) {
        this.cpuNumber.setValue(Integer.toString(cpuNumber));
    }

    public int getArrivalTime() {
        return Integer.parseInt(arrivalTime.getValue());
    }
    
    public int getFinishTime() {
        return Integer.parseInt(finishTime.getValue());
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime.setValue(Integer.toString(arrivalTime));
    }
    
    public void setFinishTime(int finishTime) {
        this.finishTime.setValue(Integer.toString(finishTime));
    }

    public int getExecutingTime() {
        return Integer.parseInt(executingTime.getValue());
    }

    public void setExecutingTime(int executingTime) {
        this.executingTime.setValue(Integer.toString(executingTime));
    }

    public int getPid() {
        return Integer.parseInt(pid.getValue());
    }

    public void setPid(int pid) {
        this.pid.setValue(Integer.toString(pid));
    }

    public MStack getStack() {
        return stack;
    }

    public void setStack(MStack stack) {
        this.stack = stack;
    }

    public int getBase() {
        return Integer.parseInt(base.getValue().trim());
    }

    public void setBase(IAddress base) {
        this.base.setValue(base.getAddressString());
    }

    public int getLimit() {
        return Integer.parseInt(limit.getValue());
    }

    public void setLimit(int limit) {
        this.limit.setValue(Integer.toString(limit));
    }

    public Register getAcRegister() {
        return this.ac;
    }

    public Register getAxRegister() {
        return this.ax;
    }

    public Register getBxRegister() {
        return this.bx;
    }

    public Register getCxRegister() {
        return this.cx;
    }

    public Register getDxRegister() {
        return this.dx;
    }
}
