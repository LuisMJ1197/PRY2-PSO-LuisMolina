/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.view.memory;

import application.controller.MemoryPanelController;
import application.view.processlist.ProcessDecorator;
import os.Process;

/**
 *
 * @author Luism
 */
public class FixedPartitionPainter implements MemoryPainter {

    @Override
    public void paint(ProcessDecorator process, MemoryPanelController mainMemoryController, MemoryPanelController diskMemoryController) {
        int initPos = process.getPcb().getPid().getAddress().getOffset();
        int endPos = initPos + ((Process) process.getProcess()).getSavedMemory().length - 1;
        mainMemoryController.paintBorder(process.getColor(), initPos, endPos);
    }
    
}
