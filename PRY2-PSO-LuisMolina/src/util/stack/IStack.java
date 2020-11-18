/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.stack;

/**
 *
 * @author Luism
 */
public interface IStack<E> {
    
    public int getSize();
    
    public int getMaxSize();
    
    public boolean push(E element);
    
    public boolean push(String value);
    
    public E pop();
    
}
