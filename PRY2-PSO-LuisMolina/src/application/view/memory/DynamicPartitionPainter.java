/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.view.memory;

import application.controller.MemoryPanelController;
import application.view.processlist.ProcessDecorator;

/**
 *
 * @author Luism
 */
public class DynamicPartitionPainter implements MemoryPainter {

    @Override
    public void paint(ProcessDecorator process, MemoryPanelController mainMemoryController, MemoryPanelController diskMemoryController) {
        os.process.Process p = (os.process.Process) process.getProcess();
        
        int initPos = process.getPcb().getPcAddress().getOffset();
        int endPos = initPos + p.getSavedMemory().length - 1;
        if (p.getSavedMemory() != null && p.isLoaded()) {
            mainMemoryController.paintBorder(process.getColor(), initPos, endPos);
        } 
        if (p.getSavedMemory() != null && !p.isLoaded()) {
            diskMemoryController.paintBorder(process.getColor(), initPos, endPos);
        }
    }
    
}
