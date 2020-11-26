/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.memorymanagement;

import machine.memory.IAddress;

/**
 *
 * @author Luism
 */
public class LogicalAddress implements IAddress {
    private int pageNumber;
    private int offset;
    
    /**
     * 
     * @param pageNumber 
     * @param offset 
     */
    public LogicalAddress(int pageNumber, int offset) {
        this.pageNumber = pageNumber;
        this.offset = offset;
    }

    public LogicalAddress(int offset) {
        this.offset = offset;
    }
    
    @Override
    public int getOffset() {
        return this.offset;
    }

    @Override
    public String getAddressString() {
        return Integer.toString(this.pageNumber) + Integer.toString(offset);
    }
    
}
