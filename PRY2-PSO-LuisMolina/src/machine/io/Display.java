/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.io;

import machine.processor.instruction.INT;
import util.queue.MQueue;

/**
 *
 * @author Luism
 */
public class Display implements IODevice {
    private String entry = "";
    private String id = "0002";
    private boolean busy = false;
    private IODeviceControllerListener controllerListener;
    private int processID;
    private IODeviceListener listener;
    private MQueue<INT> queue = new MQueue<>();
    
    public Display() {
    }
    
    @Override
    public boolean isBusy() {
        return this.busy;
    }
    
    @Override
    public void setBusy(int processID, INT instr) {
        if (this.busy) {
            this.queue.enqueue(instr);
        } else {
            this.listener = instr;
            this.processID = processID;
            this.busy = true;
            if(this.controllerListener != null) {
                this.controllerListener.start(processID);
            }
        }
    }
    
    @Override
    public void reset() {
        if (listener != null) {
            this.listener.callback(entry);
            if (this.queue.getList().isEmpty()) {
                this.busy = false;
                this.processID = -1;
                if(this.controllerListener != null) {
                    this.controllerListener.reset();
                }
            } else {
                INT instr = this.queue.dequeue();
                this.processID = Integer.parseInt(instr.getProcessID());
                this.listener = instr;
                if(this.controllerListener != null) {
                    this.controllerListener.start(processID);
                }
                this.busy = true;
            }
        }
    }
    
    public void addKey(String ch) {
        this.entry = ch + "\n";
    }
    
    public String getText() {
        return this.entry;
    }

    @Override
    public void setControllerListener(IODeviceControllerListener listener) {
        this.controllerListener = listener;
    }

    @Override
    public void setListener(IODeviceListener listener) {
        this.listener = listener;
    }

    @Override
    public String getEntry() {
        return this.entry;
    }
}
