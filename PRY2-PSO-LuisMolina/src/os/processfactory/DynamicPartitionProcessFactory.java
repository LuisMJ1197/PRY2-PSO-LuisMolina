/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.processfactory;

import os.process.Process;

/**
 *
 * @author Luism
 */
public class DynamicPartitionProcessFactory implements ProcessFactory {

    @Override
    public Process createProcess(String name, String[] code) {
        return new Process(name, code);
    }
    
}
