/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.stack;

import java.util.ArrayList;

public class MStack<T> {
    private final ArrayList<T> stack;
    private boolean limited = false;
    private int maxSize = 0;
    
    public MStack (int maxSize) {
        this.stack = new ArrayList<>();
        this.maxSize = maxSize;
        this.limited = true;
    }
    
    public int getSize() {
        return this.stack.size();
    }

    public boolean push(T element) {
        if(this.isFull()) {
            return false;
        } else {
            this.stack.add(element);
            return true;
        }
    }

    public T pop() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.stack.get(this.stack.size() - 1);
        }
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }   
    
    public boolean isFull() {
        return this.stack.size() >= this.maxSize;
    }
}