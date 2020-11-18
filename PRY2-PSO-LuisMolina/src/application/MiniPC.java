/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import application.controller.MainFrameController;
import application.view.MainFrame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import machine.Machine;
import org.xml.sax.SAXException;
import os.OS;
import os.memorymanagement.FixedPartitionMM;
import os.memorymanagement.MemoryManager;
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
            if (config.getFixedPartitionConfiguration().isActivated()) {
                memManager = new FixedPartitionMM(config.getFixedPartitionConfiguration().getPartitionSize());
            }
            OS.startInstance(
                new OS(memManager)
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
