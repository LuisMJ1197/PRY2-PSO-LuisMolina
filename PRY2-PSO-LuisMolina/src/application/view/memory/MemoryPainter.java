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
public interface MemoryPainter {
    public void paint(ProcessDecorator process, MemoryPanelController mainMemoryController, MemoryPanelController diskMemoryController);
}
