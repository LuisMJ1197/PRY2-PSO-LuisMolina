/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.memorymanagement;

import java.util.ArrayList;
import java.util.Arrays;
import machine.Machine;
import machine.memory.Register;
import os.memorymanagement.partition.Frame;
import os.memorymanagement.partition.Page;
import os.process.PagedProcess;
import os.process.Process;

/**
 *
 * @author Luism
 */
public class PagingMM extends MemoryManager {
    private int frameSize;
    private Frame[] frames;
    private ArrayList<Frame> availableFrames = new ArrayList<>();
    
    public PagingMM(int frameSize, int osSavedMemory) {
        super(osSavedMemory);
        this.frameSize = frameSize;
        this.frames = new Frame[
                Machine.getInstance().getMainMemory().getSize() / this.frameSize +
                Machine.getInstance().getSecMemory().getSize() / this.frameSize
                ];
    }
    
    public int getFrameSize() {
        return this.frameSize;
    }
    
    @Override
    public void init() {
        int mainMemoryFrames = Machine.getInstance().getMainMemory().getSize() / this.frameSize;
        for (int i = 0; i < this.frames.length; i++) {
            Register[] memory = new Register[this.frameSize];
            boolean isInMemory = true;
            for (int j = 0; j < this.frameSize; j++) {
                if (i > mainMemoryFrames - 1) {
                    memory[j] = Machine.getInstance().getSecMemory().getRegister((i - mainMemoryFrames) * this.frameSize + j);
                    isInMemory = false;
                } else {
                    memory[j] = Machine.getInstance().getMainMemory().getRegister(i * this.frameSize + j);
                }
            }
            Frame frame = new Frame(frameSize, memory, i);
            frame.setIsInMainMemory(isInMemory);
            this.frames[i] = frame;
            this.availableFrames.add(frame);
        }
        for (int i = 0; i < osSavedMemory / this.frameSize; i++) {
            this.frames[i].setUsed(true); // For OS
            this.availableFrames.remove(0);
        }

    }

    private void sortAvailableFrames() {
        int n = this.availableFrames.size(); 
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (this.availableFrames.get(j).getPartitionNumber() > 
                        this.availableFrames.get(j + 1).getPartitionNumber()) { 
                    // swap arr[j+1] and arr[j] 
                    Frame temp = this.availableFrames.get(j); 
                    this.availableFrames.set(j, this.availableFrames.get(j + 1));
                    this.availableFrames.set(j + 1, temp);
                } 
            }
        }
    }
    
    @Override
    public void loadProcess(Process process) {
        this.loadProcessInMemory(process);
        if (!process.isLoaded()) {
            this.processQueue.enqueue(process);
        }
    }

    @Override
    public void loadProcessInMemory(Process process) {
        if (this.availableFrames.size() >= ((PagedProcess) process).getPageCount()) {
            this.sortAvailableFrames();
            ArrayList<Register> savedMemory = new ArrayList<>();
            int frameCount = 0;
            ArrayList<Frame> toDelete = new ArrayList<>();
            for (Frame frame: this.availableFrames) {
                if (frameCount <= ((PagedProcess) process).getPageCount() - 1) {
                    frame.setUsed(true);
                    toDelete.add(frame);
                    savedMemory.addAll(Arrays.asList(frame.getMemory()));
                    ((PagedProcess) process).getPages()[frameCount].setFrame(frame);
                    frameCount++;
                } else {
                    break;
                }
            }
            process.allocateMemory(
                    savedMemory.toArray(new Register[
                            savedMemory.size()]
                    )
            );
            this.availableFrames.removeAll(toDelete);
        }
    }

    @Override
    public boolean verifyProgramSize(Process process) {
        return ((PagedProcess) process).getPageCount() <= this.frames.length;
    }

    @Override
    public void freeProcessMemory(Process process) {
        for (Page page: ((PagedProcess) process).getPages()) {
            page.getFrame().setUsed(false);
            this.availableFrames.add(page.getFrame());
            page.setFrame(null);
        }
        ArrayList<Process> toDelete = new ArrayList<>();
        for (Process processP: this.processQueue.getList()) {
            this.loadProcessInMemory(processP);
            if (processP.isLoaded()) {
                toDelete.add(processP);
                if (this.availableFrames.isEmpty()) {
                    break;
                }
            }
        }
        this.processQueue.getList().removeAll(toDelete);
    }

    @Override
    public int getOSMemorySaved() {
        return this.osSavedMemory;
    }

    @Override
    public LogicalAddress getNextAddress(Process process) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String load(LogicalAddress address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void store(LogicalAddress address, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
