/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author LaNo
 */
public class IJVM extends javax.swing.JDialog {

    /**
     * Creates new form IJVM
     */
    
    public String caminhoArqIJVM;
    public String nomeArqSaida;
    
    public IJVM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.caminhoArqIJVM = null;
        this.nomeArqSaida = "hakai.bin";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSelecArqijvm = new javax.swing.JButton();
        lbNomeArqijvm = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNomeArqBinSaida = new javax.swing.JTextField();
        btnExportarArqBin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 51));
        jLabel1.setText("IJVM");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel2.setText("Selecione o arquivo de código IJVM e defina o nome do arquivo para exportar em código binário");

        btnSelecArqijvm.setBackground(new java.awt.Color(204, 102, 255));
        btnSelecArqijvm.setFont(new java.awt.Font("Wide Latin", 0, 11)); // NOI18N
        btnSelecArqijvm.setForeground(new java.awt.Color(51, 0, 51));
        btnSelecArqijvm.setText("Selecione Arquivo");
        btnSelecArqijvm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecArqijvmActionPerformed(evt);
            }
        });

        lbNomeArqijvm.setFont(new java.awt.Font("Sitka Small", 0, 12)); // NOI18N
        lbNomeArqijvm.setForeground(new java.awt.Color(102, 0, 0));
        lbNomeArqijvm.setText("Nenhum arquivo selecionado");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nome do arquivo binário para exportar");

        txtNomeArqBinSaida.setFont(new java.awt.Font("Simplified Arabic Fixed", 0, 12)); // NOI18N
        txtNomeArqBinSaida.setForeground(new java.awt.Color(51, 0, 51));
        txtNomeArqBinSaida.setText("hakai.bin");

        btnExportarArqBin.setBackground(new java.awt.Color(204, 204, 255));
        btnExportarArqBin.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        btnExportarArqBin.setForeground(new java.awt.Color(51, 0, 51));
        btnExportarArqBin.setText("Exportar Arq. binário");
        btnExportarArqBin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarArqBinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExportarArqBin)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(262, 262, 262)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnSelecArqijvm))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lbNomeArqijvm))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel3))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(txtNomeArqBinSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(btnSelecArqijvm)
                .addGap(18, 18, 18)
                .addComponent(lbNomeArqijvm)
                .addGap(43, 43, 43)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNomeArqBinSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExportarArqBin)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecArqijvmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecArqijvmActionPerformed
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i= file.showSaveDialog(null);
        if (i==1) {
            this.caminhoArqIJVM = null;
            this.lbNomeArqijvm.setText("Nenhum arquivo selecionado");
            this.txtNomeArqBinSaida.setText("hakai.bin");
        }
        else {
           File arquivo = file.getSelectedFile();
           this.caminhoArqIJVM = arquivo.getPath();
           this.lbNomeArqijvm.setText(arquivo.getName());
           //this.nomeArqSaida = this.txtNomeArqBinSaida.getText();
        }
    }//GEN-LAST:event_btnSelecArqijvmActionPerformed

    private void btnExportarArqBinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarArqBinActionPerformed
        if(this.caminhoArqIJVM==null) 
            JOptionPane.showMessageDialog(null, "Não há arquivo selecionado", "ei mah, se liga", JOptionPane.WARNING_MESSAGE);
        else {
            this.nomeArqSaida = this.txtNomeArqBinSaida.getText();
            this.dispose();
        }
        
    }//GEN-LAST:event_btnExportarArqBinActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IJVM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IJVM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IJVM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IJVM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IJVM dialog = new IJVM(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportarArqBin;
    private javax.swing.JButton btnSelecArqijvm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbNomeArqijvm;
    private javax.swing.JTextField txtNomeArqBinSaida;
    // End of variables declaration//GEN-END:variables
}
