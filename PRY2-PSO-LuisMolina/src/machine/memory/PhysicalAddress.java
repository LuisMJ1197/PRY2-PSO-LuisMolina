/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.memory;


/**
 *
 * @author Luism
 */
public class PhysicalAddress implements IAddress {
    private int offset;
    
    public PhysicalAddress (int offset) {
        this.offset = offset;
    }

    public PhysicalAddress(String value) {
        this.offset = Integer.parseInt(value);
    }

    @Override
    public int getOffset() {
        return offset;
    }
    
    @Override
    public String getAddressString() {
        String address = "";
        address += IAddress.pad(Integer.toString(this.offset), "0", 16, true);
        return address;
    }
    
    
}
