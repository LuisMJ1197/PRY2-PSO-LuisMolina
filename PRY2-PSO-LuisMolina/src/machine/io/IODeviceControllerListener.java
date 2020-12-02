/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.io;

/**
 *
 * @author Luism
 */
public interface IODeviceControllerListener {
    public void start(int processID);
    public void reset();
}
