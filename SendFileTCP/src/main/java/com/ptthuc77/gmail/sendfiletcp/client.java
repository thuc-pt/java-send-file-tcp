package com.ptthuc77.gmail.sendfiletcp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class client extends javax.swing.JFrame {

    public client() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPath = new javax.swing.JTextField();
        btnChoose = new javax.swing.JButton();
        btnConnect = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();
        btnReceive = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(640, 480));

        txtPath.setPreferredSize(new java.awt.Dimension(11, 40));

        btnChoose.setText("Choose file");
        btnChoose.setPreferredSize(new java.awt.Dimension(160, 40));
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        btnConnect.setText("Connect server");
        btnConnect.setPreferredSize(new java.awt.Dimension(160, 40));
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnSend.setText("Send file");
        btnSend.setPreferredSize(new java.awt.Dimension(160, 40));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnReceive.setText("Receive result");
        btnReceive.setPreferredSize(new java.awt.Dimension(160, 40));
        btnReceive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiveActionPerformed(evt);
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
                        .addComponent(txtPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btnReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    Socket sk;
    ArrayList listSend, listReceive;
    ObjectOutputStream oOutput;
    ObjectInputStream oInput;
    
    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        JFileChooser file = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int isClick = file.showSaveDialog(null);
        if (isClick == JFileChooser.APPROVE_OPTION) {
            txtPath.setText(file.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnChooseActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        try {
            sk = new Socket("localhost", 1234);
            DefaultListModel list = new DefaultListModel();
            list.addElement("Connected to the server");
            jList1.setModel(list);
        } catch (ConnectException e) {
            JOptionPane.showMessageDialog(null, "Can not connect to the server"); 
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        try {
            oOutput = new ObjectOutputStream(sk.getOutputStream());
            listSend = new ArrayList();
            // read file and write to ArrayList
            File f = new File(txtPath.getText());
            if (f.exists()) {
                FileReader fr = new FileReader(f, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    listSend.add(line);
                }
                // display jList
                oOutput.writeObject(listSend);
                DefaultListModel list = new DefaultListModel();
                listSend.forEach(list::addElement);
                jList1.setModel(list);
            } else {
                JOptionPane.showMessageDialog(null, "The path was not found");
            }
        } catch(ConnectException ex) {
            JOptionPane.showMessageDialog(null, "Can not connect to the server");
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnReceiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceiveActionPerformed
        try {
            // get object (file) from server
            listReceive = new ArrayList();
            oInput = new ObjectInputStream(sk.getInputStream());
            listReceive = (ArrayList) oInput.readObject();
            
            // display jList
            DefaultListModel list = new DefaultListModel();
            listReceive.forEach(list::addElement);
            jList1.setModel(list);
        } catch(ConnectException ex) {
            JOptionPane.showMessageDialog(null, "Can not connect to the server");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReceiveActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new client().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnReceive;
    private javax.swing.JButton btnSend;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtPath;
    // End of variables declaration//GEN-END:variables
}
