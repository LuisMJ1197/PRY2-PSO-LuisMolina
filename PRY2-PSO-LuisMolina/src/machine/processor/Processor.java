/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor;

import java.util.ArrayList;
import os.Process;
/**
 *
 * @author Luism
 */
public class Processor {
    ArrayList<Process> processList = new ArrayList<>();
    
    public Processor() {
        
    }
    
    public void addProcess(Process newP) {
        this.processList.add(newP);
    }

    public ArrayList<Process> getProcessList() {
        return this.processList;
    }
    
    
}
