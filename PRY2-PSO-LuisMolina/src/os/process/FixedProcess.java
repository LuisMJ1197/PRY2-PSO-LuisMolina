/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.process;

import os.memorymanagement.partition.Partition;

/**
 *
 * @author Luism
 */
public class FixedProcess extends Process {
    private Partition assignedPartition = null;
    
    public FixedProcess(String name, String[] code) {
        super(name, code);
    }
    
    public void setAssignedPartition(Partition partition) {
        this.assignedPartition = partition;
    }
    
    public Partition getAssignedPartition() {
        return this.assignedPartition;
    }
}
