/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.view.MainFrame;
import application.view.MainPanel;
import javax.swing.JPanel;

/**
 *
 * @author Luism
 */
public class MainFrameController {
    private final MainFrame screen;
    private static MainFrameController instance;
    
    private MainFrameController() {
        this.screen = new MainFrame();
        this.screen.setVisible(true);
        this.screen.setContentPane(new MainPanel());
    }
    
    public static MainFrameController getInstance() {
        if (MainFrameController.instance == null) {
            MainFrameController.instance = new MainFrameController();
        }
        return MainFrameController.instance;
    }
    
    public void changeView(JPanel panel) {
        this.screen.setContentPane(panel);
    }
    
}
