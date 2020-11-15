/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.view.MainPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import machine.Machine;
/**
 *
 * @author Luism
 */
public class MainPanelController implements ActionListener {
    private final MainPanel mainPanel;
    private final MemoryPanelController mainMemoryPanelController;
    private final MemoryPanelController virtualMemoryPanelController;
    
    public MainPanelController(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.mainPanel.loadFilesBT.addActionListener(this);
        this.mainPanel.runBT.addActionListener(this);
        this.mainPanel.settingsBT.addActionListener(this);
        this.mainMemoryPanelController = new MemoryPanelController(Machine.instance.getMainMemory(), this.mainPanel.memoriesPanel.mainMemoryPanel);
        this.virtualMemoryPanelController = new MemoryPanelController(Machine.instance.getSecMemory(), this.mainPanel.memoriesPanel.virtualMemoryPanel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "loadFiles":
                break;
            case "run":
                break;
            case "settings":
                this.mainPanel.configuration.setVisible(true);
                break;
        }
    }
    
}
