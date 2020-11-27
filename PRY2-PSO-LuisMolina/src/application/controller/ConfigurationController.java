/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.view.ConfigurationPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.configuration.Configuration;

/**
 *
 * @author Luism
 */
public class ConfigurationController implements ActionListener {
    private Configuration config;
    private ConfigurationPanel configPanel;
    
    public ConfigurationController(ConfigurationPanel configPanel, Configuration config) {
        this.config = config;
        this.configPanel = configPanel;
        this.start();
    }
    
    private void start() {
        setMainMemorySize();
        setDiskMemorySize();
        setOsSavedMemory();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setMainMemorySize() {
        this.configPanel.memorySizeSlider.setValue((int) (Math.log(config.getMainMemorySize()) / Math.log(2)));
    }
    
    private void setDiskMemorySize() {
        this.configPanel.diskMemorySlider.setValue((int) (Math.log(config.getDiskMemorySize()) / Math.log(2)));
    }

    private void setOsSavedMemory() {
        this.configPanel.osDedicatedMemorySlider.setValue((int) (Math.log(config.getOsSavedMemory()) / Math.log(2)));
    }
    
}
