/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.view.processor;

/**
 *
 * @author Luism
 */
public class ProcessorPanel extends javax.swing.JPanel {
    public ProcessorCorePanel[] corePanels = new ProcessorCorePanel[5];
    
    /**
     * Creates new form ProcessorPanel
     */
    public ProcessorPanel() {
        initComponents();
        corePanels[0] = this.processorCore1;
        corePanels[1] = this.processorCore2;
        corePanels[2] = this.processorCore3;
        corePanels[3] = this.processorCore4;
        corePanels[4] = this.processorCore5;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        executionTimeLB = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        schedulingMethodLB = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        executionTimesPanel = new javax.swing.JPanel();
        CoreListPanel = new javax.swing.JPanel();
        processorCore1 = new application.view.processor.ProcessorCorePanel();
        processorCore2 = new application.view.processor.ProcessorCorePanel();
        processorCore3 = new application.view.processor.ProcessorCorePanel();
        processorCore4 = new application.view.processor.ProcessorCorePanel();
        processorCore5 = new application.view.processor.ProcessorCorePanel();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1040, 232));
        setLayout(null);

        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Execution time:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(880, 2, 80, 20);

        executionTimeLB.setBackground(new java.awt.Color(242, 98, 99));
        executionTimeLB.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        executionTimeLB.setForeground(new java.awt.Color(255, 255, 255));
        executionTimeLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        executionTimeLB.setToolTipText("Tiempo de ejecución");
        executionTimeLB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        executionTimeLB.setOpaque(true);
        jPanel1.add(executionTimeLB);
        executionTimeLB.setBounds(962, 0, 58, 25);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Processor #1");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 5, 70, 14);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Scheduling algorithm:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(100, 0, 140, 24);

        schedulingMethodLB.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        schedulingMethodLB.setForeground(new java.awt.Color(51, 51, 51));
        schedulingMethodLB.setText("mm");
        jPanel1.add(schedulingMethodLB);
        schedulingMethodLB.setBounds(220, 0, 210, 24);

        add(jPanel1);
        jPanel1.setBounds(10, 11, 1020, 24);

        jScrollPane1.setBorder(null);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setAutoscrolls(true);

        jLabel5.setBackground(new java.awt.Color(60, 61, 61));
        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Core");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setOpaque(true);

        jLabel6.setBackground(new java.awt.Color(160, 176, 167));
        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Process");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setOpaque(true);

        executionTimesPanel.setAutoscrolls(true);
        executionTimesPanel.setLayout(new java.awt.GridLayout(0, 17));

        CoreListPanel.setPreferredSize(new java.awt.Dimension(1020, 125));
        CoreListPanel.setLayout(new java.awt.GridLayout(5, 1));
        CoreListPanel.add(processorCore1);
        CoreListPanel.add(processorCore2);
        CoreListPanel.add(processorCore3);
        CoreListPanel.add(processorCore4);
        CoreListPanel.add(processorCore5);

        jLabel7.setBackground(new java.awt.Color(195, 202, 203));
        jLabel7.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("IR");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(executionTimesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(CoreListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(executionTimesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(CoreListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setViewportView(jPanel3);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 40, 1020, 190);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel CoreListPanel;
    public javax.swing.JLabel executionTimeLB;
    public javax.swing.JPanel executionTimesPanel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JScrollPane jScrollPane1;
    public application.view.processor.ProcessorCorePanel processorCore1;
    public application.view.processor.ProcessorCorePanel processorCore2;
    public application.view.processor.ProcessorCorePanel processorCore3;
    public application.view.processor.ProcessorCorePanel processorCore4;
    public application.view.processor.ProcessorCorePanel processorCore5;
    public javax.swing.JLabel schedulingMethodLB;
    // End of variables declaration//GEN-END:variables
}
