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
public class SegmentationConfiguration extends MethodConfiguration {
    
    public SegmentationConfiguration(boolean activated) {
        super(activated);
        this.name = "Segmentation";
    }
    
}
