/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.stack;

import java.util.ArrayList;
import machine.memory.Register;

public class MStack {
    private int size;
    private final int maxSize;
    private final ArrayList<Register> stack;
    
    public MStack (int size) {
        this.maxSize = size;
        this.size = 0;
        this.stack = new ArrayList<>();
    }
    
    public int getMaxSize() {
        return this.maxSize;
    }
    
    public int getSize() {
        return this.size;
    }

    public boolean push(Register element) {
        if (this.maxSize >= this.stack.size()) {
            this.stack.add(0, element);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean push(String value) {
        if (this.size >= this.maxSize) {
            return false;
        } else {
            for (int i = size + 1; i > 0; i--) {
                this.stack.get(i).setValue(this.stack.get(i - 1).getValue());
            }
            this.stack.get(0).setValue(value);
            this.size++;
            return true;
        }
    }

    public String pop() {
        if (this.size > 0) {
            String value = this.stack.get(0).getValue();
            if (this.size == 1) {
                this.stack.get(0).setValue(Register.EMPTY);
            }
            for (int i = 0; i < size - 1; i++) {
                this.stack.get(i).setValue(this.stack.get(i + 1).getValue());
            }
            this.size--;
            return value;
        } else {
            return null;
        }
    }
    
    public boolean isFull() {
        return this.size == this.maxSize;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
    
}