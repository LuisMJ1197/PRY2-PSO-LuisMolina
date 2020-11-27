/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.MiniPC;
import application.view.ArrivalTimeProcessPanel;
import application.view.MainPanel;
import application.view.memory.DynamicPartitionPainter;
import application.view.memory.FixedPartitionPainter;
import application.view.memory.MemoryPainter;
import application.view.memory.PagingPainter;
import application.view.processlist.ProcessDecorator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import machine.Machine;
import os.OS;
import os.process.Program;
import os.processmanagement.Scheduler.ISchedulerObserver;
import util.FileBrowser;
/**
 *
 * @author Luism
 */
public class MainPanelController implements ActionListener, ISchedulerObserver {
    private final MainPanel mainPanel;
    private final MemoryPanelController mainMemoryPanelController;
    private final MemoryPanelController virtualMemoryPanelController;
    private ProcessPanelController processPanelController;
    private ProcessorController processorController;
    
    public MainPanelController(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.mainPanel.loadFilesBT.addActionListener(this);
        this.mainPanel.runBT.addActionListener(this);
        this.mainPanel.settingsBT.addActionListener(this);
        this.mainPanel.acceptBT.addActionListener(this);
        this.mainMemoryPanelController = new MemoryPanelController(
                Machine.getInstance().getMainMemory(), 
                this.mainPanel.memoriesPanel.mainMemoryPanel);
        this.virtualMemoryPanelController = new MemoryPanelController(
                Machine.getInstance().getSecMemory(), 
                this.mainPanel.memoriesPanel.virtualMemoryPanel);
        OS.getInstance().getScheduler().setObserver(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "loadFiles":
                this.openFiles();
                break;
            case "run":
                this.run();
                break;
            case "settings":
                initConfig();
                break;
            case "accept":
                this.saveArrivalTime();
                break;
        }
    }
    
    public void openFiles() {
        FileBrowser fileBrowser = new FileBrowser();
        File[] selectedFiles = fileBrowser.browseMultiple(MainFrameController.getInstance().getScreen());
        if (selectedFiles == null) return;
        ArrayList<Program> programList = this.createProgramList(selectedFiles, fileBrowser);
        if (programList.isEmpty()) return;
        String result = this.loadPrograms(programList);
        if (!result.isEmpty()) {
            JOptionPane.showMessageDialog(this.mainPanel, result);
        }
        this.processPanelController = new ProcessPanelController(
                OS.getInstance().getScheduler().getProcessList(), 
                this.mainPanel.processListPanelParent);
        this.processPanelController.init();
        this.startArrivalTimeAssignation();
        
        this.processorController = new ProcessorController(Machine.getInstance().getProcessor(), this.mainPanel.processorPanel, this.processPanelController.getProcessDecorators());
        this.update();
        this.mainPanel.loadFilesBT.setEnabled(false);
        this.mainPanel.runBT.setEnabled(true);
    }
    
    private ArrayList<Program> createProgramList(File[] files, FileBrowser fileBrowser) {
        ArrayList<Program> programList = new ArrayList<>();
        for (File file: files) {
            try {
                Program program = new Program(file.getName(), fileBrowser.extractFileInfo(file).split("\\r?\\n"));
                programList.add(program);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(mainPanel, String.format("Error loading program %s.", file.getName()));
                Logger.getLogger(MainPanelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return programList;
    }

    private String loadPrograms(ArrayList<Program> programList) {
        String errMsg = "";
        for (Program program: programList) {
            errMsg += OS.getInstance().loadProgram(program);
        }
        return errMsg;
    }
    
    private void paintMemories() {
        MemoryPainter painter = null;
        if (MiniPC.config.getFixedPartitionConfiguration().isActivated()) {
            painter = new FixedPartitionPainter();
        } else if (MiniPC.config.getDynamicPartitionConfiguration().isActivated()) {
            painter = new DynamicPartitionPainter();
        } else if (MiniPC.config.getPagingConfiguration().isActivated()) {
            painter = new PagingPainter();
        }
        if (painter != null) {
            this.mainMemoryPanelController.paintBorder(new Color(240, 240, 240), 0, Machine.getInstance().getMainMemory().getSize() - 1);
            this.virtualMemoryPanelController.paintBorder(new Color(240, 240, 240), 0, Machine.getInstance().getSecMemory().getSize() - 1);
            for(ProcessDecorator proc: this.processPanelController.getProcessDecorators()) {
                painter.paint(proc, mainMemoryPanelController, virtualMemoryPanelController);
            }
        }
    }
    
    private void startArrivalTimeAssignation() {
        this.mainPanel.processList.removeAll();
        for (int i = 0; i < OS.getInstance().getScheduler().getProcessList().size() && i < 5; i++) {
            ArrivalTimeProcessPanel arr = new ArrivalTimeProcessPanel();
            arr.idLB.setText(Integer.toString(OS.getInstance().getScheduler().getProcessList().get(i).getPid()));
            arr.nameLB.setText(OS.getInstance().getScheduler().getProcessList().get(i).getName());
            this.mainPanel.processList.add(arr);
            arr.spinner.setValue(i + 1);
            if (i == 0) {
                ((ArrivalTimeProcessPanel)this.mainPanel.processList.getComponents()[0]).spinner.setEnabled(false);
                // ((ArrivalTimeProcessPanel)this.mainPanel.processList.getComponents()[0]).spinner.setValue(1);
            }
        }
        this.mainPanel.arrivaltimeassignationpanel.setVisible(true);
    }
    
    private void saveArrivalTime() {
        for (int i = 0; i < OS.getInstance().getScheduler().getProcessList().size() && i < 5; i++) {
            OS.getInstance().getScheduler().getProcessList().get(i).getPcb().setArrivalTime(
                    Integer.parseInt(((ArrivalTimeProcessPanel)this.mainPanel.processList.getComponents()[i]).spinner.getValue().toString())
            );
        }
        this.mainPanel.arrivaltimeassignationpanel.setVisible(false);
    }

    private void run() {
        new Thread() {
            public void run () {
                try {
                    OS.getInstance().getScheduler().start();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainPanelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }

    @Override
    public void update() {
        this.processPanelController.update();
        this.mainMemoryPanelController.update();
        this.virtualMemoryPanelController.update();
        this.paintMemories();
    }

    private void initConfig() {
        this.mainPanel.configuration.setVisible(true);
        ConfigurationController c = new ConfigurationController(this.mainPanel.configurationPanel1, MiniPC.config);
        
    }
}
