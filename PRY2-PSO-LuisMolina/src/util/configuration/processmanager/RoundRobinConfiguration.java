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
public class RoundRobinConfiguration extends MethodConfiguration {
    private int cycleClockAmount = 0;
    public RoundRobinConfiguration(boolean activated, int cycleClockAmount) {
        super(activated);
        this.cycleClockAmount = cycleClockAmount;
    }

    public int getCycleClockAmount() {
        return cycleClockAmount;
    }

    public void setCycleClockAmount(int cycleClockAmount) {
        this.cycleClockAmount = cycleClockAmount;
    }
}
