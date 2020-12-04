/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.memorymanagement;

import machine.Machine;
import machine.memory.IAddress;
import machine.memory.Memory;
import machine.memory.PhysicalAddress;
import machine.memory.Register;
import os.memorymanagement.partition.Partition;
import os.process.FixedProcess;
import os.process.Process;
import util.queue.MQueue;

/**
 *
 * @author Luism
 */
public class FixedPartitionMM extends MemoryManager {
    private Partition[] partitions;
    private int partitionSize;
    
    
    public FixedPartitionMM(int partitionSize, int osSavedMemory) {
        super(osSavedMemory);
        this.partitionSize = partitionSize;
        this.partitions = new Partition[Machine.getInstance().getMainMemory().getSize() / this.partitionSize];
    }
    
    @Override
    public void init() {
        for (int i = 0; i < this.partitions.length; i++) {
            Register[] memory = new Register[this.partitionSize];
            for (int j = 0; j < this.partitionSize; j++) {
                memory[j] = Machine.getInstance().getMainMemory().getRegister(i * this.partitionSize + j);
            }
            Partition partition = new Partition(partitionSize, memory, i);
            this.partitions[i] = partition;
        }
        for (int i = 0; i < osSavedMemory / this.partitionSize; i++) {
            this.partitions[i].setUsed(true); // For OS
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
        Partition partition = this.searchMemory(process.getProcessSize());
        if (partition != null) {
            ((FixedProcess) process).setAssignedPartition(partition);
            process.allocateMemory(partition.getMemory());
        }
    }    
    
    private Partition searchMemory(int size) {
        for (Partition partition : this.partitions) {
            if (!partition.isUsed()) {
                partition.setUsed(true);
                return partition;
            }
        }
        return null;
    }
    
    @Override
    public boolean verifyProgramSize(Process process) {
        return process.getProcessSize()<= this.partitionSize;
    }
    
    @Override
    public void freeProcessMemory(Process process) {
        FixedProcess fProcess = (FixedProcess) process;
        Process nextProcess = this.processQueue.dequeue();
        if (nextProcess != null) {
            nextProcess.allocateMemory(fProcess.getAssignedPartition().getMemory());
        } else {
            fProcess.getAssignedPartition().setUsed(false);
            Register[] freeMemory = process.getSavedMemory();
            for (Register reg: freeMemory) {
                reg.clean();
            }
        }
    }

    @Override
    public int getOSMemorySaved() {
        return this.osSavedMemory;
    }

    @Override
    public IAddress getNextAddress(Process process, int offset) {
        PhysicalAddress address = new PhysicalAddress(process.getPcb().getPcAddress().getOffset() + offset, Memory.MAIN_MEMORY);
        return address;
    }

    @Override
    public boolean validateAddress(Process process, IAddress address) {
        return address.getOffset() >= process.getPcb().getBase() 
                && address.getOffset() <= (process.getPcb().getBase() + process.getPcb().getLimit() - 1);
    }
}
