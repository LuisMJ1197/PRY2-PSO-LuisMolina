/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.view.processlist.ProcessDecorator;
import application.view.statistics.ProcessStatisticsPanel;
import application.view.statistics.StatisticsPanel;
import java.text.DecimalFormat;
import os.process.Process;
import java.util.ArrayList;

/**
 *
 * @author Luism
 */
public class StatisticsController {
    private StatisticsPanel panel;
    private ArrayList<ProcessDecorator> processDecorators;
    
    public StatisticsController(StatisticsPanel panel, ArrayList<ProcessDecorator> processDecorators) {
        this.panel = panel;
        this.processDecorators = processDecorators;
    }
    
    public void init() {
        this.panel.processesPanel.removeAll();
        if (this.processDecorators != null) {
            int i = 0;
            int turnaroundAll = 0;
            double tr_tsAll = 0;
            for (ProcessDecorator proc: this.processDecorators) {
                ProcessStatisticsPanel procPanel = new ProcessStatisticsPanel();
                while (i >= MColor.color.length) {
                   i = i - MColor.color.length;
                }
                proc.setColor(MColor.color[i]);
                procPanel.setProcessColor(proc.getColor());
                
                procPanel.nameLB.setText(proc.getName());
                procPanel.idLB.setText(Integer.toString(proc.getPid()));
                procPanel.arrivalTimeLB.setText(proc.getPcb().getStartTimeCal());
                procPanel.EndTimeTLB.setText(proc.getPcb().getFinishTimeCal());
                procPanel.startTimeLB.setText(Integer.toString(proc.getPcb().getStartTime()));
                procPanel.EndTime.setText(Integer.toString(proc.getPcb().getFinishTime()));
                int turnaround = proc.getPcb().getFinishTime() - proc.getPcb().getArrivalTime(); 
                procPanel.turnaroundTimeLB.setText(Integer.toString(turnaround));
                turnaroundAll += turnaround;
                
                double tr_ts = ((double) proc.getPcb().getFinishTime() - proc.getPcb().getArrivalTime())
                                / (((Process) proc.getProcess()).getBurstTime() * 1.00);
                procPanel.tr_tsLB.setText(
                        Double.toString(
                            Math.round((tr_ts) * 100.0) / 100.0
                        )
                );
                tr_tsAll += tr_ts;
                procPanel.setToolTipText(proc.getName());
                this.panel.processesPanel.add(procPanel);
                i++;
            }
            this.panel.turnaroundAvgLB.setText(
                    Double.toString(
                            Math.round(turnaroundAll / this.processDecorators.size() * 100.0) / 100.0
                    )
            );
            this.panel.tr_tsAvgLB.setText(
                    Double.toString(
                            Math.round(tr_tsAll / this.processDecorators.size() * 100.0) / 100.0
                    )
            );
        }
    }

    
}
