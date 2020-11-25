/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.view.processlist.ProcessDecorator;
import application.view.processlist.ProcessListPanel;
import application.view.processlist.ProcessPanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import os.process.Process;

/**
 *
 * @author Luism
 */
public class ProcessPanelController implements IController {
    public static Color[] color = {
        new Color(26, 188, 156),
        new Color(46, 236, 113),
        new Color(52, 152, 219),
        new Color(52, 73, 94),
        new Color(22, 160, 133),
        new Color(39, 174, 96),
        new Color(41, 128, 185),
        new Color(142, 68, 173),
        new Color(44, 62, 80),
        new Color(241, 196, 15),
        new Color(230, 126, 34),
        new Color(231, 76, 60),
        new Color(2, 128, 144),
        new Color(149, 165, 166),
        new Color(243, 156, 18),
        new Color(211, 84, 0),
        new Color(192, 57, 43),
        new Color(189, 195, 199),
        new Color(127, 140, 141)
    };
    
    private ArrayList<ProcessDecorator> processList;
    private ProcessListPanel processListPanelParent;

    public ProcessPanelController(ArrayList<Process> processList, ProcessListPanel processListPanelParent) {
        this.processListPanelParent = processListPanelParent;
        this.processList = new ArrayList<>();
        for (Process process: processList) {
            this.processList.add(new ProcessDecorator(process));
        }
        this.setGridLayoutRowsCount(this.processList.size());
    }
    
    public ArrayList<ProcessDecorator> getProcessDecorators() {
        return this.processList;
    }

    private void setGridLayoutRowsCount(int count) {
        this.processListPanelParent.processListPanel.setLayout(new java.awt.GridLayout(6 + count, 0));
    }
    
    public void init() {
        this.processListPanelParent.processCountLB.setText(Integer.toString(this.processList.size()));
        //this.shuffleColor();
        if (this.processList != null) {
            int i = 0;
            for (ProcessDecorator proc: this.processList) {
                ProcessPanel procPanel = new ProcessPanel();
                while (i >= ProcessPanelController.color.length) {
                   i = i - ProcessPanelController.color.length;
                }
                proc.setColor(ProcessPanelController.color[i]);
                procPanel.setProcessColor(proc.getColor());
                procPanel.nameLB.setText(proc.getName());
                procPanel.idLB.setText(Integer.toString(proc.getPid()));
                procPanel.setToolTipText(proc.getName());
                this.processListPanelParent.processListPanel.add(procPanel);
                i++;
            }
        }
        this.update();
    }
    
    public void update() {
        for (int i = 0; i < this.processList.size(); i++) {
            ProcessPanel procPanel = (ProcessPanel) this.processListPanelParent.processListPanel.getComponent(i);
            ProcessDecorator proc = this.processList.get(i);
            procPanel.statusLB.setText(proc.getPcb().getStatus());
            procPanel.burstTimeLB.setText(Integer.toString(proc.getProgramSize()));
            procPanel.pcLB.setText(proc.getPcb().getPc());
            procPanel.acLB.setText(Integer.toString(proc.getPcb().getAc()));
            procPanel.axLB.setText(Integer.toString(proc.getPcb().getAx()));
            procPanel.bxLB.setText(Integer.toString(proc.getPcb().getBx()));
            procPanel.cxLB.setText(Integer.toString(proc.getPcb().getCx()));
            procPanel.dxLB.setText(Integer.toString(proc.getPcb().getDx()));
            procPanel.cpuLB.setText(Integer.toString(proc.getPcb().getCpuNumber()));
        }
    }
    
    private void shuffleColor() {
        List<Color> intList = Arrays.asList(ProcessPanelController.color);
        Collections.shuffle(intList);
	intList.toArray(ProcessPanelController.color);
    }
    
}
