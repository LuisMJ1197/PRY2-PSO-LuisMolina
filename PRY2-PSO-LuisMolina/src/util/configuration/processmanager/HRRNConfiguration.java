/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.configuration.processmanager;

import util.configuration.MethodConfiguration;

/**
 *
 * @author Luism
 */
public class HRRNConfiguration extends MethodConfiguration {
    public HRRNConfiguration(boolean activated) {
        super(activated);
        this.name = "Highest Response Ratio Next";
    }
}
