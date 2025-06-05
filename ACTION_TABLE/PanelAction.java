 
package ACTION_TABLE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 
public class PanelAction extends javax.swing.JPanel {

    
    public PanelAction() {
        initComponents();
    }

    public void initEvent(TableActionEvent event, int row){
        cmdDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                 event.onDelete(row);
            }
            
        });
        
        cmdEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                 event.onEdit(row);
            }
            
        });
        
        
        
        
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmdEdit = new ACTION_TABLE.ActionButton();
        cmdDelete = new ACTION_TABLE.ActionButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cmdEdit.setBackground(new java.awt.Color(110, 0, 2));
        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-edit-25.png"))); // NOI18N
        cmdEdit.setText("EDIT");
        cmdEdit.setFocusable(false);
        cmdEdit.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        cmdEdit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        cmdDelete.setBackground(new java.awt.Color(255, 48, 48));
        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-delete-25.png"))); // NOI18N
        cmdDelete.setText("DELETE");
        cmdDelete.setFocusable(false);
        cmdDelete.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        cmdDelete.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(jPanel1, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ACTION_TABLE.ActionButton cmdDelete;
    private ACTION_TABLE.ActionButton cmdEdit;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
