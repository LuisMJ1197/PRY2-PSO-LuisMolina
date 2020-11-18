/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.memorymanagement;

import machine.Machine;
import machine.memory.Register;
import os.PCB;
import os.memorymanagement.partition.Partition;
import os.Process;
import util.queue.MQueue;

/**
 *
 * @author Luism
 */
public class FixedPartitionMM implements MemoryManager {
    private Partition[] partitions;
    private int partitionSize;
    private MQueue<Process> processQueue = new MQueue<>();
    
    public FixedPartitionMM(int partitionSize) {
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
            Partition partition = new Partition(partitionSize, memory);
            this.partitions[i] = partition;
        }
        this.partitions[0].setUsed(true); // For OS
    }

    @Override
    public void loadProcess(Process process) {
        if (process.getTotalSize() <= this.partitionSize) {
            for (Partition partition : this.partitions) {
                if (!partition.isUsed()) {
                    partition.setUsed(true);
                    process.createPCB(partition.getMemory());
                    for (int i = PCB.PCB_SIZE; i < PCB.PCB_SIZE + process.getProgramSize(); i++) {
                        partition.getMemory()[i].setValue(process.getProcessCode()[i - PCB.PCB_SIZE]);
                    }
                    process.getPcb().getStatus().setValue(PCB.READY);
                    return;
                }
            }
            if (!process.getPcb().getStatus().getValue().equals(PCB.READY)) {
                this.processQueue.enqueue(process);
            }
        } else {
            process.setLoaded(false);
        }
    }
}
