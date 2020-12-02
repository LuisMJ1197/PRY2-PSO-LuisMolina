/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.view.ConfigurationPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import util.configuration.Configuration;
import util.configuration.ConfigurationReader;

/**
 *
 * @author Luism
 */
public class ConfigurationController implements ActionListener {
    private Configuration config;
    private ConfigurationPanel configPanel;
    private MainPanelController listener;
    
    public ConfigurationController(ConfigurationPanel configPanel, Configuration config) {
        this.config = config;
        this.configPanel = configPanel;
        this.configPanel.saveBT.addActionListener(this);
        this.start();
    }
    
    private void start() {
        setMainMemorySize();
        setDiskMemorySize();
        setOsSavedMemory();
        this.setFrameSize();
        this.setPartitionSize();
        if (config.getFixedPartitionConfiguration().isActivated()) {
            this.configPanel.activateFixed();
        } else if (config.getDynamicPartitionConfiguration().isActivated()) {
            this.configPanel.activateDynamic();
        } else if (config.getPagingConfiguration().isActivated()) {
            this.configPanel.activatePaging();
        } else if (config.getSegmentationConfiguration().isActivated()) {
            this.configPanel.activateSegmentation();
        }
        if (config.getFcfsConfiguration().isActivated()) {
            this.configPanel.activateFCFS();
        } else if (config.getSjfConfiguration().isActivated()) {
            this.configPanel.activateSJF();
        } else if (config.getSrtConfiguration().isActivated()) {
            this.configPanel.activateSRT();
        } else if (config.getHrrnConfiguration().isActivated()) {
            this.configPanel.activateHRRN();
        } else if (config.getRoundRobinConfiguration().isActivated()) {
            this.configPanel.activateRR();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Save":
                saveChanges();
                break;
        }
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
    
    private void setPartitionSize() {
        this.configPanel.partitionSizeSlider.setValue((int) (Math.log(config.getFixedPartitionConfiguration().getPartitionSize()) / Math.log(2)));
    }
    
    private void setFrameSize() {
        this.configPanel.frameSizeSlider.setValue((int) (Math.log(config.getPagingConfiguration().getFrameSize()) / Math.log(2)));
    }
    
    private void saveChanges() {
        this.config.setDiskMemorySize((int) Math.pow(2, this.configPanel.diskMemorySlider.getValue()));
        this.config.setMainMemorySize((int) Math.pow(2, this.configPanel.memorySizeSlider.getValue()));
        this.config.setOsSavedMemory((int) Math.pow(2, this.configPanel.osDedicatedMemorySlider.getValue()));
        this.config.getFixedPartitionConfiguration().setActivated(this.configPanel.fixedPartitionActivateBT.getText().equals("Activated"));
        this.config.getFixedPartitionConfiguration().setPartitionSize((int) Math.pow(2, this.configPanel.partitionSizeSlider.getValue()));
        this.config.getDynamicPartitionConfiguration().setActivated(this.configPanel.dynamicPartitionActivateBT.getText().equals("Activated"));
        this.config.getPagingConfiguration().setActivated(this.configPanel.pagingActivateBT.getText().equals("Activated"));
        this.config.getPagingConfiguration().setPageSize((int) Math.pow(2, this.configPanel.partitionSizeSlider.getValue()));
        this.config.getSegmentationConfiguration().setActivated(this.configPanel.fixedPartitionActivateBT.getText().equals("Activated"));
        this.config.getFcfsConfiguration().setActivated(this.configPanel.fcfsActivateBT.getText().equals("Activated"));
        this.config.getSrtConfiguration().setActivated(this.configPanel.srtActivateBT.getText().equals("Activated"));
        this.config.getSjfConfiguration().setActivated(this.configPanel.sjfActivateBT.getText().equals("Activated"));
        this.config.getHrrnConfiguration().setActivated(this.configPanel.hrrnActivateBT.getText().equals("Activated"));
        this.config.getRoundRobinConfiguration().setActivated(this.configPanel.rrActivateBT.getText().equals("Activated"));
        this.config.getRoundRobinConfiguration().setQuantum((int) Math.pow(2, this.configPanel.quantumSlider.getValue()));
        try {
            ConfigurationReader.getInstance().saveConfig(config);
            this.listener.updateconfig();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    void setListener(MainPanelController aThis) {
        this.listener = aThis;
    }
}
