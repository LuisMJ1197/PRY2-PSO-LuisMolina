/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.view.processlist;

/**
 *
 * @author Luism
 */
public class ProcessPanel extends javax.swing.JPanel {

    /**
     * Creates new form ProcessPanel
     */
    public ProcessPanel() {
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

        colorPanel = new javax.swing.JPanel();
        dxLB = new javax.swing.JLabel();
        cxLB = new javax.swing.JLabel();
        axLB = new javax.swing.JLabel();
        bxLB = new javax.swing.JLabel();
        acLB = new javax.swing.JLabel();
        pcLB = new javax.swing.JLabel();
        statusLB = new javax.swing.JLabel();
        cpuLB = new javax.swing.JLabel();
        nameLB = new javax.swing.JLabel();
        idLB = new javax.swing.JLabel();

        setBackground(new java.awt.Color(249, 249, 249));
        setPreferredSize(new java.awt.Dimension(1002, 24));
        setLayout(null);

        colorPanel.setBackground(new java.awt.Color(255, 204, 0));

        javax.swing.GroupLayout colorPanelLayout = new javax.swing.GroupLayout(colorPanel);
        colorPanel.setLayout(colorPanelLayout);
        colorPanelLayout.setHorizontalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );
        colorPanelLayout.setVerticalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );

        add(colorPanel);
        colorPanel.setBounds(6, 5, 14, 14);

        dxLB.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        dxLB.setText("DX");
        add(dxLB);
        dxLB.setBounds(872, 5, 120, 14);

        cxLB.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cxLB.setText("CX");
        add(cxLB);
        cxLB.setBounds(752, 5, 120, 14);

        axLB.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        axLB.setText("AX");
        add(axLB);
        axLB.setBounds(512, 5, 120, 14);

        bxLB.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        bxLB.setText("BX");
        add(bxLB);
        bxLB.setBounds(632, 5, 120, 14);

        acLB.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        acLB.setText("AC");
        add(acLB);
        acLB.setBounds(392, 5, 120, 14);

        pcLB.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        pcLB.setText("PC");
        add(pcLB);
        pcLB.setBounds(272, 5, 120, 13);

        statusLB.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        statusLB.setText("Status");
        add(statusLB);
        statusLB.setBounds(192, 5, 74, 14);

        cpuLB.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cpuLB.setText("CPU");
        add(cpuLB);
        cpuLB.setBounds(156, 5, 30, 14);

        nameLB.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        nameLB.setText("Name");
        add(nameLB);
        nameLB.setBounds(62, 5, 88, 14);

        idLB.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        idLB.setText("ID");
        add(idLB);
        idLB.setBounds(30, 5, 26, 14);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acLB;
    private javax.swing.JLabel axLB;
    private javax.swing.JLabel bxLB;
    private javax.swing.JPanel colorPanel;
    private javax.swing.JLabel cpuLB;
    private javax.swing.JLabel cxLB;
    private javax.swing.JLabel dxLB;
    private javax.swing.JLabel idLB;
    private javax.swing.JLabel nameLB;
    private javax.swing.JLabel pcLB;
    private javax.swing.JLabel statusLB;
    // End of variables declaration//GEN-END:variables
}
