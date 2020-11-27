/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.view.memory;

import application.controller.MemoryPanelController;
import application.view.processlist.ProcessDecorator;
import machine.memory.Register;
import os.memorymanagement.partition.Page;
import os.process.PagedProcess;

/**
 *
 * @author Luism
 */
public class PagingPainter implements MemoryPainter {

    @Override
    public void paint(ProcessDecorator process, MemoryPanelController mainMemoryController, MemoryPanelController diskMemoryController) {
        PagedProcess pProcess = (PagedProcess) process.getProcess();
        if(!pProcess.isLoaded()) {
            return;
        }
        for (Page page: pProcess.getPages()) {
            if (page.getFrame().isIsInMainMemory()) {
                for (Register register: page.getFrame().getMemory()) {
                    mainMemoryController.paintBorder(process.getColor(), register.getAddress().getOffset(), register.getAddress().getOffset());
                }
            } else {
                for (Register register: page.getFrame().getMemory()) {
                    diskMemoryController.paintBorder(process.getColor(), register.getAddress().getOffset(), register.getAddress().getOffset());
                }
            }
        }
        
    }
    
}
