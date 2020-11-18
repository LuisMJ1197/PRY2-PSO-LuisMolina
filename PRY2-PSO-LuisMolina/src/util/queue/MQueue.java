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
 */
public class MQueue<E> implements IQueue<E> {
    private ArrayList<E> queue = new ArrayList<>();
    
    @Override
    public int size() {
        return this.queue.size();
    }

    @Override
    public void enqueue(E element) {
        queue.add(element);
    }

    @Override
    public boolean dequeue(E element) {
        return queue.remove(element);
    }

        @Override
    public E dequeue() {
        if (this.queue.size() > 0)
            return this.queue.remove(0);
        else
            return null;
    }

    @Override
    public E peek() {
        return this.queue.get(0);
    }

    @Override
    public E tail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E head() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<E> getList() {
        return this.queue;
    }
    
}