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
public class Memory implements IMemory {
    
    private final Register[] registers;
    private final int size;
    private final int kind;
    
    public Memory(int size, int kind) {
        this.size = size;
        this.kind = kind;
        registers = new Register[size];
        this.initMemory();
    }
    
    @Override
    public Register getRegister(int pos) {
        return this.registers[pos];
    }
    
    @Override
    public String load(int address) {
        return this.registers[address].getValue();
    }

    @Override
    public void store(int address, String value) {
        this.registers[address].setValue(value);
    }
    
    @Override
    public String load(IAddress address) {
        return this.registers[address.getOffset()].getValue();
    }

    @Override
    public void store(IAddress address, String value) {
        this.registers[address.getOffset()].setValue(value);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    private void initMemory() {
        for (int i = 0; i < this.size; i++) {
            IAddress address;
            address = new PhysicalAddress(this.kind, i);
            this.registers[i] = new Register(address);
        }
    }
    
    public void clean() {
        for (Register reg: this.registers) {
            reg.clean();
        }
    }
}
