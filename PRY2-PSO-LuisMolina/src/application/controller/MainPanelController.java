/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.MiniPC;
import application.view.ArrivalTimeProcessPanel;
import application.view.MainPanel;
import application.view.memory.FixedPartitionPainter;
import application.view.memory.MemoryPainter;
import application.view.processlist.ProcessDecorator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import machine.Machine;
import os.OS;
import os.Program;
import os.Process;
import util.FileBrowser;
/**
 *
 * @author Luism
 */
public class MainPanelController implements ActionListener {
    private final MainPanel mainPanel;
    private final MemoryPanelController mainMemoryPanelController;
    private final MemoryPanelController virtualMemoryPanelController;
    private ProcessPanelController processPanelController;
    
    public MainPanelController(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.mainPanel.loadFilesBT.addActionListener(this);
        this.mainPanel.runBT.addActionListener(this);
        this.mainPanel.settingsBT.addActionListener(this);
        this.mainMemoryPanelController = new MemoryPanelController(
                Machine.getInstance().getMainMemory(), 
                this.mainPanel.memoriesPanel.mainMemoryPanel);
        this.virtualMemoryPanelController = new MemoryPanelController(
                Machine.getInstance().getSecMemory(), 
                this.mainPanel.memoriesPanel.virtualMemoryPanel);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "loadFiles":
                this.openFiles();
                break;
            case "run":
                break;
            case "settings":
                this.mainPanel.configuration.setVisible(true);
                break;
            case "accept":
                saveArrivalTime();
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
                Machine.getInstance().getProcessor().getProcessList(), 
                this.mainPanel.processListPanelParent);
        this.processPanelController.init();
        this.startArrivalTimeAssignation();
        //this.mainMemoryPanelController.update();
        //this.virtualMemoryPanelController.update();
        //this.paintMemories();
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
        }
        if (painter != null) {
            for(ProcessDecorator proc: this.processPanelController.getProcessDecorators()) {
                painter.paint(proc, mainMemoryPanelController, virtualMemoryPanelController);
            }
        }
    }
    
    private void startArrivalTimeAssignation() {
        this.mainPanel.processList.removeAll();
        for (int i = 0; i < Machine.getInstance().getProcessor().getProcessList().size() && i < 5; i++) {
            ArrivalTimeProcessPanel arr = new ArrivalTimeProcessPanel();
            arr.idLB.setText(Integer.toString(Machine.getInstance().getProcessor().getProcessList().get(i).getPid()));
            arr.nameLB.setText(Machine.getInstance().getProcessor().getProcessList().get(i).getName());
            this.mainPanel.processList.add(arr);
            if (i == 0) {
                ((ArrivalTimeProcessPanel)this.mainPanel.processList.getComponents()[0]).spinner.setEnabled(false);
                ((ArrivalTimeProcessPanel)this.mainPanel.processList.getComponents()[0]).spinner.setValue(0);
            }
        }
        this.mainPanel.arrivaltimeassignationpanel.setVisible(true);
    }
    
    private void saveArrivalTime() {
        for (int i = 0; i < Machine.getInstance().getProcessor().getProcessList().size() && i < 5; i++) {
            Machine.getInstance().getProcessor().getProcessList().get(i).getPcb().getStartTime().setValue(
                    ((ArrivalTimeProcessPanel)this.mainPanel.processList.getComponents()[i]).spinner.getValue().toString()
            );
        }
        this.mainPanel.arrivaltimeassignationpanel.setVisible(false);
    }
}
