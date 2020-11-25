/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import application.controller.MainFrameController;
import application.view.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import machine.Machine;
import os.OS;
import os.memorymanagement.FixedPartitionMM;
import os.memorymanagement.MemoryManager;
import os.processfactory.FixedPartitionProcessFactory;
import os.processfactory.ProcessFactory;
import os.processmanagement.FCFSScheduler;
import os.processmanagement.HRRNScheduler;
import os.processmanagement.RRScheduler;
import os.processmanagement.SJFScheduler;
import os.processmanagement.SRTScheduler;
import os.processmanagement.Scheduler;
import util.configuration.Configuration;
import util.configuration.ConfigurationReader;

/**
 *
 * @author Luism
 */
public class MiniPC {
    public static Configuration config;
    
     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        MiniPC miniPC = new MiniPC();
        miniPC.startMachine();
        miniPC.startView();
    }
    
    public void startMachine() {
        try {
            config = ConfigurationReader.getInstance().getConfiguration();
            Machine.startInstance(
                    new Machine(
                            config.getMainMemorySize(), 
                            config.getDiskMemorySize())
            );
            MemoryManager memManager = null;
            ProcessFactory factory = null;
            
            /* Process factory policy */
            if (config.getFixedPartitionConfiguration().isActivated()) {
                memManager = new FixedPartitionMM(config.getFixedPartitionConfiguration().getPartitionSize(), config.getOsSavedMemory());
                factory = new FixedPartitionProcessFactory();
            }
            
            /* Scheduler algorithm */
            Scheduler scheduler = null;
            if (config.getFcfsConfiguration().isActivated()) {
                scheduler = new FCFSScheduler();    
            } else  if (config.getRoundRobinConfiguration().isActivated()) {
                scheduler = new RRScheduler(config.getRoundRobinConfiguration().getCycleClockAmount());
            } else if (config.getSrtConfiguration().isActivated()) {
                scheduler = new SRTScheduler();
            } else if (config.getSjfConfiguration().isActivated()) {
                scheduler = new SJFScheduler();
            } else if (config.getHrrnConfiguration().isActivated()) {
                scheduler = new HRRNScheduler();
            }
            
            OS.startInstance(
                new OS(memManager, scheduler, factory)
            );
            
        } catch (Exception ex) {
            Logger.getLogger(MiniPC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startView() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrameController.getInstance();
            }
        });
    }
}
