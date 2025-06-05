 
package FORMS;

import java.awt.Color;
 
public class A_front_page extends javax.swing.JFrame {

    /**
     * Creates new form Log_in
     */
    public A_front_page() {
        initComponents();
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        kGradientPanel1 = new keeptoo.KGradientPanel();
        panelRound6 = new Custom.PanelRound();
        login_back = new Custom.PanelRound();
        login = new button.Button();
        panelRound8 = new Custom.PanelRound();
        signup_back = new Custom.PanelRound();
        signup = new button.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        imageAvatar1 = new Swing.ImageAvatar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel1.setkEndColor(new java.awt.Color(15, 0, 0));
        kGradientPanel1.setkGradientFocus(700);
        kGradientPanel1.setkStartColor(new java.awt.Color(161, 0, 0));
        kGradientPanel1.setLayout(new java.awt.GridBagLayout());

        panelRound6.setBackground(new java.awt.Color(255, 255, 255));
        panelRound6.setRoundBottomLeft(80);
        panelRound6.setRoundBottomRight(80);
        panelRound6.setRoundTopLeft(80);
        panelRound6.setRoundTopRight(80);

        login_back.setBackground(new java.awt.Color(110, 0, 2));
        login_back.setRoundBottomLeft(80);
        login_back.setRoundBottomRight(80);
        login_back.setRoundTopLeft(80);
        login_back.setRoundTopRight(80);

        login.setBackground(new java.awt.Color(110, 0, 2));
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("LOG IN");
        login.setFocusable(false);
        login.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginMouseExited(evt);
            }
        });
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout login_backLayout = new javax.swing.GroupLayout(login_back);
        login_back.setLayout(login_backLayout);
        login_backLayout.setHorizontalGroup(
            login_backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, login_backLayout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        login_backLayout.setVerticalGroup(
            login_backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_backLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(login_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(login_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(77, 503, 0, 0);
        kGradientPanel1.add(panelRound6, gridBagConstraints);

        panelRound8.setBackground(new java.awt.Color(255, 255, 255));
        panelRound8.setRoundBottomLeft(80);
        panelRound8.setRoundBottomRight(80);
        panelRound8.setRoundTopLeft(80);
        panelRound8.setRoundTopRight(80);

        signup_back.setBackground(new java.awt.Color(110, 0, 2));
        signup_back.setRoundBottomLeft(80);
        signup_back.setRoundBottomRight(80);
        signup_back.setRoundTopLeft(80);
        signup_back.setRoundTopRight(80);

        signup.setBackground(new java.awt.Color(110, 0, 2));
        signup.setForeground(new java.awt.Color(255, 255, 255));
        signup.setText("SIGN UP");
        signup.setFocusable(false);
        signup.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        signup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signupMouseExited(evt);
            }
        });
        signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout signup_backLayout = new javax.swing.GroupLayout(signup_back);
        signup_back.setLayout(signup_backLayout);
        signup_backLayout.setHorizontalGroup(
            signup_backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signup_backLayout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addComponent(signup, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        signup_backLayout.setVerticalGroup(
            signup_backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signup_backLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(signup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(signup_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound8Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(signup_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(42, 502, 238, 503);
        kGradientPanel1.add(panelRound8, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 52)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("L-TRAK");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 692, 0, 0);
        kGradientPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Lab Tracking and Record-Keeping");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 572, 0, 0);
        kGradientPanel1.add(jLabel2, gridBagConstraints);

        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/bottle.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 179;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(41, 670, 0, 0);
        kGradientPanel1.add(imageAvatar1, gridBagConstraints);

        getContentPane().add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1546, 910));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
            A_Log_in ad =new A_Log_in();
            ad.setVisible(true);
            ad.pack();
            ad.setLocationRelativeTo(null);
             if (searchThread != null && searchThread.isAlive()) {
            searchThread.interrupt();
        }
        searchThread = new Thread(() -> {
            try {
                Thread.sleep(500);
                this.dispose();
            } catch (InterruptedException e) {
            }
        });
        searchThread.start(); 
    }//GEN-LAST:event_loginActionPerformed
private Thread searchThread;
    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed
            A_signup ad =new A_signup();
            ad.setVisible(true);
            ad.pack();
            ad.setLocationRelativeTo(null);
             if (searchThread != null && searchThread.isAlive()) {
            searchThread.interrupt();
        }
        searchThread = new Thread(() -> {
            try {
                Thread.sleep(500);
                this.dispose();
            } catch (InterruptedException e) {
            }
        });
        searchThread.start(); 
    }//GEN-LAST:event_signupActionPerformed

    private void loginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseEntered
        login.setBackground( new Color(255,255,255));
        login.setForeground( new Color(110,0,2));
        login_back.setBackground( new Color(255,255,255));
    }//GEN-LAST:event_loginMouseEntered

    private void loginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseExited
        login.setBackground( new Color(110,0,2));
        login.setForeground( new Color(255,255,255));
        login_back.setBackground( new Color(110,0,2));
    }//GEN-LAST:event_loginMouseExited

    private void signupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signupMouseExited
        signup.setBackground( new Color(110,0,2));
        signup.setForeground( new Color(255,255,255));
        signup_back.setBackground( new Color(110,0,2));
    }//GEN-LAST:event_signupMouseExited

    private void signupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signupMouseEntered
        signup.setBackground( new Color(255,255,255));
        signup.setForeground( new Color(110,0,2));
        signup_back.setBackground( new Color(255,255,255));
    }//GEN-LAST:event_signupMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new A_front_page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private button.Button login;
    private Custom.PanelRound login_back;
    private Custom.PanelRound panelRound6;
    private Custom.PanelRound panelRound8;
    private button.Button signup;
    private Custom.PanelRound signup_back;
    // End of variables declaration//GEN-END:variables
}
