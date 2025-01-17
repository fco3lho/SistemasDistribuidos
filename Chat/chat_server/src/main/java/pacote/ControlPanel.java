/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pacote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe Campos
 */
public class ControlPanel extends javax.swing.JFrame {

    /**
     * Creates new form ControlPanel
     */
    public ControlPanel() {
        initComponents();
    }
    
    Registry reg;
    rmiWebInterface objRmi;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPublicidade = new javax.swing.JLabel();
        lblWeb = new javax.swing.JLabel();
        btnPublicidade = new javax.swing.JToggleButton();
        btnWeb = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Control Panel");
        setName("frmControlPanel"); // NOI18N

        lblPublicidade.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPublicidade.setText("Publicidade");
        lblPublicidade.setName("lblPublicidade"); // NOI18N

        lblWeb.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblWeb.setText("Servidor");
        lblWeb.setName("lblWeb"); // NOI18N

        btnPublicidade.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnPublicidade.setText("Ativar");
        btnPublicidade.setName("btnPublicidade"); // NOI18N
        btnPublicidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublicidadeActionPerformed(evt);
            }
        });

        btnWeb.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnWeb.setText("Ativar");
        btnWeb.setName("btnWeb"); // NOI18N
        btnWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWebActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPublicidade)
                    .addComponent(lblWeb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPublicidade, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblWeb)
                    .addComponent(btnWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPublicidade)
                    .addComponent(btnPublicidade, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPublicidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublicidadeActionPerformed
        if(btnPublicidade.isSelected()){
            btnPublicidade.setText("Desativar");
        }else{
            btnPublicidade.setText("Ativar");
        }
    }//GEN-LAST:event_btnPublicidadeActionPerformed

    private void btnWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWebActionPerformed
        if(btnWeb.isSelected()){
            try{
                this.reg = LocateRegistry.createRegistry(6666);
                this.objRmi = new rmiWebImplementation();
                this.reg.rebind("servidorWebChat", objRmi);
                JOptionPane.showMessageDialog(null, "Servidor iniciado.");
                btnWeb.setText("Desativar");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ativando servidor RMI: " + e.getMessage());
                e.printStackTrace();
            }
        }
        else{
            try{
                this.reg.unbind("servidorWebChat");
                UnicastRemoteObject.unexportObject(this.objRmi, true);
                UnicastRemoteObject.unexportObject(this.reg, true);
                JOptionPane.showMessageDialog(null, "Servidor parado");
                btnWeb.setText("Ativar");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro parando servidor RMI: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnWebActionPerformed

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
            java.util.logging.Logger.getLogger(ControlPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ControlPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ControlPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ControlPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ControlPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnPublicidade;
    private javax.swing.JToggleButton btnWeb;
    private javax.swing.JLabel lblPublicidade;
    private javax.swing.JLabel lblWeb;
    // End of variables declaration//GEN-END:variables
}
