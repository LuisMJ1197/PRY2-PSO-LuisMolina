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
public class PagingConfiguration extends MethodConfiguration {
    private int frameSize;
    
    public PagingConfiguration(boolean activated, int frameSize) {
        super(activated);
        this.frameSize = frameSize;
    }

    public int getFrameSize() {
        return frameSize;
    }
    
    
    
}
