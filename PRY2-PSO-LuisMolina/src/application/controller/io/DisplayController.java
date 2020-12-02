/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller.io;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import machine.Machine;
import machine.io.IODevice;
import machine.io.IODeviceControllerListener;

/**
 *
 * @author Luism
 */
public class DisplayController implements IODeviceControllerListener {
    private JTextArea displayTA;
    private IODevice display;
    private JLabel processID;
    private boolean enabled = false;
    
    public DisplayController(JTextArea displayTA, JLabel processID) {
        this.displayTA = displayTA;
        this.processID = processID;
        this.display = Machine.getInstance().getDisplay();
        this.display.setControllerListener(this);
    }

    @Override
    public void start(int processID) {
        this.processID.setText(Integer.toString(processID));
        this.displayTA.setText(this.display.getEntry());
        this.enabled = true;
        this.display.reset();
    }
    
    @Override
    public void reset() {
        this.displayTA.setEnabled(false);
        this.enabled = false;
    }
}
