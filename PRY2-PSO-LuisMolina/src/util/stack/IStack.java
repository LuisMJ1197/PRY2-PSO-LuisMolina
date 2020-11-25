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
    
    public void push(E element);
    
    public E pop();
    
}
