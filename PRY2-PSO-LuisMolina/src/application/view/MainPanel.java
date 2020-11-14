/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.view;

/**
 *
 * @author Luism
 */
public class MainPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainPanel
     */
    public MainPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        loadFilesBT = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        runBT = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        settingsBT = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        processListPanel1 = new application.view.processlist.ProcessListPanel();

        setBackground(new java.awt.Color(241, 242, 242));
        setPreferredSize(new java.awt.Dimension(1360, 730));
        setLayout(null);

        jToolBar1.setBackground(new java.awt.Color(51, 51, 51));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setPreferredSize(new java.awt.Dimension(1360, 35));

        loadFilesBT.setBackground(new java.awt.Color(51, 51, 51));
        loadFilesBT.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        loadFilesBT.setForeground(new java.awt.Color(241, 242, 242));
        loadFilesBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/open.png"))); // NOI18N
        loadFilesBT.setText("Load files");
        loadFilesBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loadFilesBT.setFocusable(false);
        loadFilesBT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jToolBar1.add(loadFilesBT);
        jToolBar1.add(jSeparator1);

        runBT.setBackground(new java.awt.Color(51, 51, 51));
        runBT.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        runBT.setForeground(new java.awt.Color(241, 242, 242));
        runBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/play.png"))); // NOI18N
        runBT.setText("Run");
        runBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        runBT.setFocusable(false);
        runBT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jToolBar1.add(runBT);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(null);

        settingsBT.setBackground(new java.awt.Color(51, 51, 51));
        settingsBT.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        settingsBT.setForeground(new java.awt.Color(241, 242, 242));
        settingsBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/settings.png"))); // NOI18N
        settingsBT.setText("Settings");
        settingsBT.setContentAreaFilled(false);
        settingsBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        settingsBT.setFocusable(false);
        settingsBT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        settingsBT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(settingsBT);
        settingsBT.setBounds(1104, 0, 110, 35);

        jToolBar1.add(jPanel1);

        add(jToolBar1);
        jToolBar1.setBounds(0, 0, 1360, 35);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        add(jPanel2);
        jPanel2.setBounds(830, 630, 520, 90);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        add(jPanel4);
        jPanel4.setBounds(10, 500, 810, 220);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        add(jPanel6);
        jPanel6.setBounds(10, 270, 1040, 220);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        add(jPanel3);
        jPanel3.setBounds(1060, 40, 290, 450);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        add(jPanel7);
        jPanel7.setBounds(830, 500, 520, 120);

        javax.swing.GroupLayout processListPanel1Layout = new javax.swing.GroupLayout(processListPanel1);
        processListPanel1.setLayout(processListPanel1Layout);
        processListPanel1Layout.setHorizontalGroup(
            processListPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        processListPanel1Layout.setVerticalGroup(
            processListPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        add(processListPanel1);
        processListPanel1.setBounds(10, 40, 1040, 220);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton loadFilesBT;
    private application.view.processlist.ProcessListPanel processListPanel1;
    private javax.swing.JButton runBT;
    private javax.swing.JButton settingsBT;
    // End of variables declaration//GEN-END:variables
}
