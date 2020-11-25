/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.view.processlist;

import java.awt.Color;
import os.process.IProcess;
import os.PCB;

/**
 *
 * @author Luism
 */
public class ProcessDecorator implements IProcess {
    private IProcess process;
    private Color color;
    
    public ProcessDecorator(IProcess process) {
        this.process = process;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public IProcess getProcess() {
        return this.process;
    }
    
    @Override
    public int getProgramSize() {
        return this.process.getProgramSize();
    }
    
    @Override
    public int getProcessSize() {
        return this.process.getProcessSize();
    }
    
    @Override
    public String getName() {
        return this.process.getName();
    }

    @Override
    public int getPid() {
        return this.process.getPid();
    }

    @Override
    public PCB getPcb() {
        return this.process.getPcb();
    }

}
