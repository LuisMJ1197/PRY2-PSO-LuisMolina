/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

/**
 *
 * @author Luism
 */
public interface IProcess {
    
    public int getProgramSize();
    
    public int getTotalSize();
    
    public String getName();

    public int getPid();

    public PCB getPcb();
}
