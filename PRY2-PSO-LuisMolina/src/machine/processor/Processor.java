/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor;


/**
 *
 * @author Luism
 */
public class Processor {
    private final Core[] cores = new Core[5];
    
    public Processor() {
        initCores();
    }
    
    public Core[] getCores() {
        return this.cores;
    }
    

    private void initCores() {
        for (int i = 0; i < 5; i++) {
            cores[i] = new Core(i + 1);
        }
    }
}
