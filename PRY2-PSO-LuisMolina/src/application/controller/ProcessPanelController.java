/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.MiniPC;
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
                while (i >= MColor.color.length) {
                   i = i - MColor.color.length;
                }
                proc.setColor(MColor.color[i]);
                procPanel.setProcessColor(proc.getColor());
                procPanel.nameLB.setText(proc.getName());
                procPanel.idLB.setText(Integer.toString(proc.getPid()));
                procPanel.idMLB.setText(Integer.toString(proc.getPid()));
                procPanel.setToolTipText(proc.getName());
                this.processListPanelParent.processListPanel.add(procPanel);
                i++;
            }
        }
        this.update();
    }
    
    @Override
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
            for (int j = 0; j < procPanel.stack.length; j++) {
                String n = proc.getStack().get(j);
                procPanel.stack[j].setText(proc.getStack().get(j));
            }
        }
    }
    
    private void shuffleColor() {
        List<Color> intList = Arrays.asList(MColor.color);
        Collections.shuffle(intList);
	intList.toArray(MColor.color);
    }
    
}
