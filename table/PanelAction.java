/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package table;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author RAVEN
 */
public class PanelAction extends javax.swing.JPanel {

    /**
     * Creates new form PanelAction
     */
    public PanelAction() {
        initComponents();
    }

    public void initEvent(TableActionEvent event, int row) {
        cmdEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onEdit(row);
            }
        });
        cmdDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onDelete(row);
            }
        });
        cmdView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onView(row);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdEdit = new button.Button();
        cmdDelete = new button.Button();
        cmdView = new button.Button();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmdEdit.setBackground(new java.awt.Color(204, 204, 204));
        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        add(cmdEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 30));

        cmdDelete.setBackground(new java.awt.Color(204, 204, 204));
        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        add(cmdDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 30, 30));

        cmdView.setBackground(new java.awt.Color(204, 204, 204));
        cmdView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/view.png"))); // NOI18N
        add(cmdView, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 30, 30));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button cmdDelete;
    private button.Button cmdEdit;
    private button.Button cmdView;
    // End of variables declaration//GEN-END:variables
}
