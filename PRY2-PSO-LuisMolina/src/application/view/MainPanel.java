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
        this.configuration.setLocationRelativeTo(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        configuration = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        loadFilesBT = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        runBT = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        settingsBT = new javax.swing.JButton();
        processListPanelParent = new application.view.processlist.ProcessListPanel();
        memoriesPanel = new application.view.memory.MemoriesPanel();
        statisticsPanel1 = new application.view.statistics.StatisticsPanel();
        displayPanel1 = new application.view.iodevices.DisplayPanel();
        processorPanel2 = new application.view.processor.ProcessorPanel();

        configuration.setTitle("Configuration");
        configuration.setAlwaysOnTop(true);
        configuration.setBackground(new java.awt.Color(255, 255, 255));
        configuration.setModal(true);
        configuration.setResizable(false);
        configuration.setSize(new java.awt.Dimension(520, 300));
        configuration.setType(java.awt.Window.Type.POPUP);
        configuration.getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(520, 300));
        jPanel2.setLayout(null);

        jPanel3.setLayout(null);

        jLabel2.setText("Configuration");
        jLabel2.setPreferredSize(new java.awt.Dimension(65, 24));
        jPanel3.add(jLabel2);
        jLabel2.setBounds(10, 0, 80, 24);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(10, 10, 500, 24);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.addTab("Memory", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.addTab("Scheduler", jPanel5);

        jPanel2.add(jTabbedPane1);
        jTabbedPane1.setBounds(10, 40, 500, 220);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(440, 270, 73, 23);

        configuration.getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 520, 300);

        setBackground(new java.awt.Color(241, 242, 242));
        setMinimumSize(new java.awt.Dimension(1360, 730));
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
        loadFilesBT.setActionCommand("loadFiles");
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
        runBT.setActionCommand("run");
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
        settingsBT.setActionCommand("settings");
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
        add(processListPanelParent);
        processListPanelParent.setBounds(10, 40, 1040, 220);
        add(memoriesPanel);
        memoriesPanel.setBounds(1060, 40, 290, 460);
        add(statisticsPanel1);
        statisticsPanel1.setBounds(10, 510, 810, 210);
        add(displayPanel1);
        displayPanel1.setBounds(830, 510, 520, 210);
        add(processorPanel2);
        processorPanel2.setBounds(10, 270, 1040, 232);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDialog configuration;
    private application.view.iodevices.DisplayPanel displayPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JButton loadFilesBT;
    public application.view.memory.MemoriesPanel memoriesPanel;
    private application.view.processlist.ProcessListPanel processListPanelParent;
    private application.view.processor.ProcessorPanel processorPanel2;
    public javax.swing.JButton runBT;
    public javax.swing.JButton settingsBT;
    private application.view.statistics.StatisticsPanel statisticsPanel1;
    // End of variables declaration//GEN-END:variables
}
