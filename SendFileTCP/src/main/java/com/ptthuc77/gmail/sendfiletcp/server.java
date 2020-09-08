package com.ptthuc77.gmail.sendfiletcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class server extends javax.swing.JFrame {
    
    public server() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnConnect = new javax.swing.JButton();
        btnReceive = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnConnect.setText("Open connect");
        btnConnect.setPreferredSize(new java.awt.Dimension(160, 40));
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnReceive.setText("Receive file");
        btnReceive.setPreferredSize(new java.awt.Dimension(160, 40));
        btnReceive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiveActionPerformed(evt);
            }
        });

        btnSend.setText("Send result");
        btnSend.setPreferredSize(new java.awt.Dimension(160, 40));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(btnReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    ServerSocket ssk;
    Socket sk;
    ArrayList listSend, listReceive;
    ObjectOutputStream oOutput;
    ObjectInputStream oInput;
    
    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        try {
            // Open connect
            ssk = new ServerSocket(1234);
            sk = ssk.accept();
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConnectActionPerformed
    
    private void btnReceiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceiveActionPerformed
        try {
            oInput = new ObjectInputStream(sk.getInputStream());
            listReceive = (ArrayList) oInput.readObject();
            // display jList
            DefaultListModel list = new DefaultListModel();
            listReceive.forEach(list::addElement);
            jList1.setModel(list);
        } catch(NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Please open the server connection");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReceiveActionPerformed

    private String readContent(String line) {
        return line.toUpperCase();
    }
    
    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        try {
            listSend = new ArrayList();
            oOutput = new ObjectOutputStream(sk.getOutputStream());
            // display jList
            listReceive.forEach((s) -> {
                listSend.add(readContent((String) s));
            });
            oOutput.writeObject(listSend);
            DefaultListModel list = new DefaultListModel();
            listSend.forEach(list::addElement);
            jList1.setModel(list);
        } catch(NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Please open the server connection");
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSendActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new server().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnReceive;
    private javax.swing.JButton btnSend;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
