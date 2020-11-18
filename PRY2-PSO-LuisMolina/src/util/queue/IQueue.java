/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.queue;

import java.util.ArrayList;

/**
 *
 * @author Luism
 * @param <E>
 */
public interface IQueue<E> {
    
    public int size();
    
    public void enqueue(E element);
    
    public boolean dequeue(E element);
    
    public E dequeue();
    
    public E peek();
    
    public E tail();
    
    public E head();
    
    public ArrayList<E> getList();
}
