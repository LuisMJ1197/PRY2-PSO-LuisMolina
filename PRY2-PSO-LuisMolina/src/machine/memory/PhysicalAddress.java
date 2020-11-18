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
    private int memoryKind;
    private int offset;
    
    public PhysicalAddress (int memoryKind, int offset) {
        this.memoryKind = memoryKind;
        this.offset = offset;
    }

    public PhysicalAddress(String value) {
        this.memoryKind = IMemory.MAIN_MEMORY;
        if (value.substring(0, 1).equals("1")) {
            this.memoryKind = IMemory.DISK_MEMORY;
        }
        this.offset = Integer.parseInt(value.substring(4));
    }

    public int getMemoryKind() {
        return memoryKind;
    }

    @Override
    public int getOffset() {
        return offset;
    }
    
    @Override
    public String getAddressString() {
        String address = "";
        //address += IAddress.pad(Integer.toString(this.memoryKind), "0", 8, false);
        address += IAddress.pad(Integer.toString(this.offset), "0", 16, true);
        return address;
    }
    
    
}
