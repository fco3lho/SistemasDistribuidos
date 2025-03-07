/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pacote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
    QuizService objRmi;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblWeb = new javax.swing.JLabel();
        btnQuizz = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        lblWeb.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblWeb.setText("Chat WEB:");
        lblWeb.setName("lblWeb"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnQuizz.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnQuizz.setText("Ativar");
        btnQuizz.setName("btnQuizz"); // NOI18N
        btnQuizz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuizzActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Quizz Server");
        jLabel1.setName("lblQuizz"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(btnQuizz, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQuizz)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuizzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuizzActionPerformed
        if (btnQuizz.isSelected()) {
            try {
                this.reg = LocateRegistry.createRegistry(6789);
                this.objRmi = new QuizServer();
                this.reg.rebind("servidorQuiz", objRmi);
                JOptionPane.showMessageDialog(null, "Servidor iniciado.");
                btnQuizz.setText("Desativar");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ativando o servidor RMI: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try{
                this.reg.unbind("servidorQuiz");
                UnicastRemoteObject.unexportObject(this.objRmi, true);
                UnicastRemoteObject.unexportObject(this.reg, true);
                JOptionPane.showMessageDialog(null, "Servidor parado.");
                btnQuizz.setText("Ativar");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro parando servidor RMI: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnQuizzActionPerformed

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
    private javax.swing.JToggleButton btnQuizz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblWeb;
    // End of variables declaration//GEN-END:variables
}
