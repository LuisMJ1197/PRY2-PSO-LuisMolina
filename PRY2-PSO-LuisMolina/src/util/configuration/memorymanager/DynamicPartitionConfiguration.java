/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.configuration.memorymanager;

import util.configuration.MethodConfiguration;

/**
 *
 * @author Luism
 */
public class DynamicPartitionConfiguration extends MethodConfiguration {
    public DynamicPartitionConfiguration(boolean activated) {
        super(activated);
        this.name = "Dynamic Partition";
    }
}
