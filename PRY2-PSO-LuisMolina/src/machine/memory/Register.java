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
public class Register {
    public static final String EMPTY = "----------------";
    public static final String ZERO = "0";
    private final int address;
    private String value = EMPTY;
    
    public Register(int address) {
        this.address = address;
    }
    
    public int getAddress() {
        return this.address;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public void clean() {
        this.value = EMPTY;
    }
}
