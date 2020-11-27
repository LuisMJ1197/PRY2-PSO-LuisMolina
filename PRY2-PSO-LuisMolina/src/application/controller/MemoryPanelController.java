/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.view.memory.MemoryPanel;
import application.view.memory.MemoryRegisterPanel;
import java.awt.Color;
import machine.memory.IMemory;
import util.Luminance;

/**
 *
 * @author Luism
 */
public class MemoryPanelController implements IController {
    private MemoryPanel memoryPanel;
    private IMemory memory;
    private MemoryRegisterPanel[] memoryRegisterPanels;
    
    public MemoryPanelController(IMemory memory, MemoryPanel memoryPanel) {
        this.memory = memory;
        this.memoryPanel = memoryPanel;
        this.memoryRegisterPanels = new MemoryRegisterPanel[this.memory.getSize()];
        this.initMemoryView();
        this.update();
    }
    
    private void initMemoryView() {
        this.memoryPanel.memorySize.setText(Integer.toString(this.memory.getSize()));
        this.memoryPanel.setGridLayoutRowsCount(this.memory.getSize());
        for (int i = 0; i < this.memory.getSize(); i++) {
            this.memoryRegisterPanels[i] = new MemoryRegisterPanel();
            this.memoryRegisterPanels[i].addressLB.setText(this.pad(Integer.toString(this.memory.getRegister(i).getAddress().getOffset()), "0", 16, true));
            this.memoryPanel.memoryRegisters.add(this.memoryRegisterPanels[i]);
        }
    }
    
    private String pad(String value, String charP, int count, boolean leftPad) {
        String res = "";
        if (!leftPad) res += value;
        for (int i = 0; i < count - value.length(); i++) {
            res += charP;
        }
        if (leftPad) res += value;
        return res;
    }
    
    @Override
    public void update() {
        int i = 0;
        for (MemoryRegisterPanel memRegPanel: this.memoryRegisterPanels) {
            memRegPanel.dataLB.setText(this.memory.load(i));
            i++;
        }
    }
    
    public void paintBorder(Color color, int initPos, int count) {
        for (int i = 0; i <= count - initPos; i++) {
            this.memoryRegisterPanels[initPos + i].setBorder(javax.swing.BorderFactory.createLineBorder(color));
            this.memoryRegisterPanels[initPos + i].setBackground(color);
            if (Luminance.intensity(color) >= 128.0) {
                this.memoryRegisterPanels[initPos + i].addressLB.setForeground(Color.black);
                this.memoryRegisterPanels[initPos + i].dataLB.setForeground(Color.black);
            } else {
                this.memoryRegisterPanels[initPos + i].addressLB.setForeground(Color.white);
                this.memoryRegisterPanels[initPos + i].dataLB.setForeground(Color.white);
            }
        }
    }
    
}
