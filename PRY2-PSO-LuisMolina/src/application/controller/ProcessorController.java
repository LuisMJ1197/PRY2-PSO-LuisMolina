/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.MiniPC;
import application.view.processlist.ProcessDecorator;
import application.view.processor.ProcessorCorePanel;
import application.view.processor.ProcessorPanel;
import application.view.processor.TimeSecondPanel;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JLabel;
import machine.Machine;
import machine.processor.Core;
import machine.processor.Core.ICoreObserver;
import machine.processor.Processor;
import os.OS;
import os.process.PCB;
import os.processmanagement.Scheduler.IProcessorCoresObserver;

/**
 *
 * @author Luism
 */
public class ProcessorController implements IProcessorCoresObserver, ICoreObserver {
    private final Processor processor;
    private final ProcessorPanel view;
    private ArrayList<ProcessDecorator> decorators;
    
    public ProcessorController(Processor processor, ProcessorPanel view, ArrayList<ProcessDecorator> decorators) {
        this.processor = processor;
        this.view = view;
        this.decorators = decorators;
        this.initView();
    }
    
    private void initView() {
        int i = 0;
        for (ProcessorCorePanel cP: this.view.corePanels) {
            cP.coreNumber.setText(Integer.toString(++i));
        }
        for (Core core: Machine.getInstance().getProcessor().getCores()) {
            core.setCoreObserver(this);
        }
        OS.getInstance().getScheduler().setProcessorCoresObserver(this);
    }

    @Override
    public void update() {
        this.view.executionTimeLB.setText(Integer.toString(OS.getInstance().getScheduler().getExecutionTime()));
        for (int i = 0; i < this.view.corePanels.length; i++) {
            PCB pcb = this.processor.getCores()[i].getPCB();
            String id = "-1";
            if (pcb != null) {
                id = Integer.toString(pcb.getPid());
            }
            this.view.corePanels[i].processID.setText(id);
            this.view.corePanels[i].irLB.setText(this.processor.getCores()[i].getIr());
        }
        
    }

    @Override
    public void addExecutionSecond(int time) {
        int width = time > 16 ? 50 * time : 800;
        int cols = time > 16 ? time : 16;
        this.view.executionTimesPanel.setLayout(new java.awt.GridLayout(0, cols, 0, 0));
        this.view.executionTimesPanel.setSize(width, 25);
        this.view.executionTimesPanel.setPreferredSize(new Dimension(width, 25));
        TimeSecondPanel panel = new TimeSecondPanel();
        panel.number.setText(Integer.toString(time));
        this.view.executionTimesPanel.add(panel); 
        
        this.view.CoreListPanel.setSize(220 + width, 140);
        this.view.CoreListPanel.setPreferredSize(new Dimension(220 + width, 140));
        
        for (ProcessorCorePanel corePanel : this.view.corePanels) {
            corePanel.setSize(220 + width, 25);
            corePanel.setPreferredSize(new Dimension(220 + width, 25));

            corePanel.executionTimesPanel.setLayout(new java.awt.GridLayout(0, cols, 0, 0));
            corePanel.executionTimesPanel.setSize(width, 25);
            corePanel.executionTimesPanel.setPreferredSize(new Dimension(width, 25));
            
            TimeSecondPanel panel1 = new TimeSecondPanel();
            panel1.number.setText("");
            corePanel.executionTimesPanel.add(panel1);
        }
        
        this.view.jScrollPane1.getHorizontalScrollBar().setValue(this.view.jScrollPane1.getHorizontalScrollBar().getMaximum());
    }

    @Override
    public void setTimeExecutionId(int time, int pid, int coreNumber) {
        JLabel number = ((TimeSecondPanel) this.view.corePanels[coreNumber - 1].executionTimesPanel.getComponents()[time - 1])
                .number;
        number.setText(Integer.toString(pid));
        number.setBackground(this.decorators.get(pid).getColor());
    }
}
