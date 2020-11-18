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
public interface IMemory {
    public static final int MAIN_MEMORY = 0;
    public static final int DISK_MEMORY = 1;
    
    public Register getRegister(int pos);
    
    public String load(int address);
    
    public void store(int address, String value);
    
    public String load(IAddress address);

    public void store(IAddress address, String value);
    
    public int getSize();
    
    public void clean();
    
}
