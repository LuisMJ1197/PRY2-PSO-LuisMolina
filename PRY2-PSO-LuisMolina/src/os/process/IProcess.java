/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.process;

import util.stack.MStack;

/**
 *
 * @author Luism
 */
public interface IProcess {
    
    public int getProgramSize();
    
    public int getProcessSize();
    
    public String getName();

    public int getPid();

    public PCB getPcb();
    
    public MStack getStack();
}
