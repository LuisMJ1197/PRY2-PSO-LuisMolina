/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.configuration;

/**
 *
 * @author Luism
 */
public class MethodConfiguration {
    private boolean activated;
    protected String name;
    
    public MethodConfiguration(boolean activated) {
        this.activated = activated;
    }
    
    public String getName() {
        return this.name;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    
    
}
