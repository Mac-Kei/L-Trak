 
package FORMS;

import FORMS_DIALOG.Auto_success;
import FORMS_METHODS.Check_Database;
import FORMS_METHODS.Get_Database;
import FORMS_METHODS.Insert_Database;
import FORMS_METHODS.Table_Methods;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
 
public class A_Log_in extends javax.swing.JFrame {
    
    Table_Methods TM = new Table_Methods();
    Insert_Database ID = new Insert_Database();
    Get_Database GD = new Get_Database();
    Check_Database CD = new Check_Database();
        
    public A_Log_in() {
        initComponents();
        
        Password.setEchoChar('\u25cf');
         hide.setVisible(false); 
         incorrect_panel.setVisible(false);
         
    }
   Connection con; 
        PreparedStatement pst, ps;
        ResultSet rs, rst;
    public void Connect() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver"); 
    }
    public void incorrect_show(JPanel slider){
            new Thread (new Runnable(){
            @Override
            public void run(){ 
                 slider.setVisible(true);
                for (int i=0; i<=50; i++){
                    slider.setSize(520, i); 
                    try {
                       Thread.sleep(2);
                       
                    } catch (InterruptedException ex) {
                         
                    }
                }  
              
          }
        }).start() ; int start=0; 
       }
    
    public void incorrect_hide(JPanel slider){
            new Thread (new Runnable(){
            @Override
            public void run(){ 
                 slider.setVisible(true);
                for (int i=50; i>=0; i--){
                    slider.setSize(520, i); 
                    if(i==0){
                        slider.setVisible(false);
                    }
                    try {
                       Thread.sleep(2);
                       
                    } catch (InterruptedException ex) {
                         
                    }
                }  
              
          }
        }).start() ; int start=50; 
       }
     private Thread searchThread;
    
     
     
     void animate_incorrect(){
          incorrect_show(incorrect_panel);
         if (searchThread != null && searchThread.isAlive()) {
            searchThread.interrupt();
        }
        searchThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                incorrect_hide(incorrect_panel);
            } catch (InterruptedException e) {
            }
        });
        searchThread.start();
        
     }
     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        kGradientPanel1 = new keeptoo.KGradientPanel();
        panelRound1 = new Custom.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        panelRound3 = new Custom.PanelRound();
        panelRound2 = new Custom.PanelRound();
        email = new javax.swing.JTextField();
        sing_up_btn = new javax.swing.JLabel();
        panelRound4 = new Custom.PanelRound();
        panelRound5 = new Custom.PanelRound();
        Password = new javax.swing.JPasswordField();
        hide = new button.Button();
        view = new button.Button();
        jLabel4 = new javax.swing.JLabel();
        login_back2 = new Custom.PanelRound();
        login_back = new Custom.PanelRound();
        login = new button.Button();
        forgot_btn = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        incorrect_panel = new javax.swing.JPanel();
        text_incorrect = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel1.setkEndColor(new java.awt.Color(15, 0, 0));
        kGradientPanel1.setkGradientFocus(700);
        kGradientPanel1.setkStartColor(new java.awt.Color(161, 0, 0));
        kGradientPanel1.setLayout(new java.awt.GridBagLayout());

        panelRound1.setBackground(new java.awt.Color(227, 204, 204));
        panelRound1.setRoundBottomLeft(55);
        panelRound1.setRoundBottomRight(55);
        panelRound1.setRoundTopLeft(55);
        panelRound1.setRoundTopRight(55);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(110, 0, 2));
        jLabel1.setText("Email:");
        panelRound1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 170, 30));

        panelRound3.setBackground(new java.awt.Color(110, 0, 2));
        panelRound3.setRoundBottomLeft(60);
        panelRound3.setRoundBottomRight(60);
        panelRound3.setRoundTopLeft(60);
        panelRound3.setRoundTopRight(60);

        panelRound2.setBackground(new java.awt.Color(227, 204, 204));
        panelRound2.setRoundBottomLeft(50);
        panelRound2.setRoundBottomRight(50);
        panelRound2.setRoundTopLeft(50);
        panelRound2.setRoundTopRight(50);
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        email.setBackground(new java.awt.Color(227, 204, 204));
        email.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        email.setForeground(new java.awt.Color(110, 0, 2));
        email.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        email.setCaretColor(new java.awt.Color(110, 0, 2));
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        panelRound2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 0, 420, 40));

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        panelRound1.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        sing_up_btn.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        sing_up_btn.setForeground(new java.awt.Color(110, 0, 2));
        sing_up_btn.setText("Sign Up");
        sing_up_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sing_up_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sing_up_btnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sing_up_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sing_up_btnMouseExited(evt);
            }
        });
        panelRound1.add(sing_up_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 470, 91, 30));

        panelRound4.setBackground(new java.awt.Color(110, 0, 2));
        panelRound4.setRoundBottomLeft(60);
        panelRound4.setRoundBottomRight(60);
        panelRound4.setRoundTopLeft(60);
        panelRound4.setRoundTopRight(60);

        panelRound5.setBackground(new java.awt.Color(227, 204, 204));
        panelRound5.setRoundBottomLeft(50);
        panelRound5.setRoundBottomRight(50);
        panelRound5.setRoundTopLeft(50);
        panelRound5.setRoundTopRight(50);
        panelRound5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Password.setBackground(new java.awt.Color(227, 204, 204));
        Password.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Password.setForeground(new java.awt.Color(110, 0, 2));
        Password.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Password.setCaretColor(new java.awt.Color(110, 0, 2));
        Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordActionPerformed(evt);
            }
        });
        panelRound5.add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 2, 410, 40));

        hide.setBackground(new java.awt.Color(227, 204, 204));
        hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/BackGround/eye_hide.png"))); // NOI18N
        hide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideActionPerformed(evt);
            }
        });
        panelRound5.add(hide, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 20, 20));

        view.setBackground(new java.awt.Color(227, 204, 204));
        view.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/BackGround/eye.png"))); // NOI18N
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });
        panelRound5.add(view, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 20, 20));

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        panelRound1.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 480, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(110, 0, 2));
        jLabel4.setText("Password:");
        panelRound1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 410, 30));

        login_back2.setBackground(new java.awt.Color(255, 255, 255));
        login_back2.setRoundBottomLeft(50);
        login_back2.setRoundBottomRight(50);
        login_back2.setRoundTopLeft(50);
        login_back2.setRoundTopRight(50);

        login_back.setBackground(new java.awt.Color(110, 0, 2));
        login_back.setRoundBottomLeft(50);
        login_back.setRoundBottomRight(50);
        login_back.setRoundTopLeft(50);
        login_back.setRoundTopRight(50);

        login.setBackground(new java.awt.Color(110, 0, 2));
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("LOG IN");
        login.setDoubleBuffered(true);
        login.setEffectColor(new java.awt.Color(79, 0, 0));
        login.setFocusable(false);
        login.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
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
            .addGroup(login_backLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );
        login_backLayout.setVerticalGroup(
            login_backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_backLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout login_back2Layout = new javax.swing.GroupLayout(login_back2);
        login_back2.setLayout(login_back2Layout);
        login_back2Layout.setHorizontalGroup(
            login_back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_back2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(login_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        login_back2Layout.setVerticalGroup(
            login_back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_back2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(login_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        panelRound1.add(login_back2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 480, -1));

        forgot_btn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        forgot_btn.setForeground(new java.awt.Color(110, 0, 2));
        forgot_btn.setText("Forgot Password?");
        forgot_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forgot_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgot_btnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forgot_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                forgot_btnMouseExited(evt);
            }
        });
        panelRound1.add(forgot_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 211, 30));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(110, 0, 2));
        jLabel7.setText("Don't have an account? ");
        panelRound1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 298, 30));

        incorrect_panel.setBackground(new java.awt.Color(214, 103, 103));
        incorrect_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        text_incorrect.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        text_incorrect.setForeground(new java.awt.Color(255, 255, 255));
        text_incorrect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text_incorrect.setText("Password or Email is incorrect.");
        incorrect_panel.add(text_incorrect, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 520, 30));

        panelRound1.add(incorrect_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 520, 50));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 40)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(110, 0, 2));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LOG IN");
        panelRound1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 580, 70));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.ipady = 55;
        gridBagConstraints.insets = new java.awt.Insets(-200, 0, 0, 0);
        kGradientPanel1.add(panelRound1, gridBagConstraints);

        getContentPane().add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1560, 930));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        Auto_success AS = new Auto_success(this); 

    String Email = String.valueOf(email.getText());
    String Pass = String.valueOf(Password.getPassword());
    
    if(String.valueOf(email.getText()).equals("")){
        //AS.showMessage("Please enter your Email.");
        text_incorrect.setText("Please enter your email first.");
        animate_incorrect();
    }
    
    else if(String.valueOf(Password.getPassword()).equals("")){
        //AS.showMessage("Please enter your Password.");
        text_incorrect.setText("Please enter your password.");
        animate_incorrect();
    }
    
    else if(!CD.check_account(Email, Pass)){
        text_incorrect.setText("Password or Email is incorrect.");
        animate_incorrect();
    }else{
        
        
        String  username= String.valueOf(email.getText());
        String pass = String.valueOf(Password.getPassword());
         try {
            Class.forName("com.mysql.jdbc.Driver");
             con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
            pst= (PreparedStatement) con.prepareStatement("SELECT * FROM `accounts` WHERE `email`=? AND `password`=?");
            pst.setString(1, username);
            pst.setString(2, pass); 
       
           rs=pst.executeQuery();
            if(rs.next()){
                 
            AS.showMessage("Logged in successfully");
            history();
            B_dashboard ad =new B_dashboard();
            ad.setVisible(true);
            ad.pack();
            ad.setLocationRelativeTo(null);
             
            ad.profile_Name1.setText(String.valueOf(rs.getString("name")));
            ad.profile_Status1.setText(String.valueOf(rs.getString("status"))); 
            ad.profile_Name.setText(String.valueOf(rs.getString("name")));
            ad.profile_Email.setText(String.valueOf(rs.getString("email")));
            ad.profile_ID.setText(String.valueOf(rs.getString("id_no")));
            ad.profile_Number.setText(String.valueOf(rs.getString("number")));
            ad.profile_Status.setText(String.valueOf(rs.getString("status")));
            
            ad.Head_name.setText(String.valueOf(rs.getString("name")));
            ad.Head_status.setText(String.valueOf(rs.getString("status"))); 
            
            
            
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
                
            }} 
        catch (SQLException ex) {
            Logger.getLogger(Check_Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Check_Database.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        
        
        
        
        
        
    }
     
        
        
       
        
    }//GEN-LAST:event_loginActionPerformed

    void history(){ 
        String user = String.valueOf(email.getText());
        if(ID.insert_history("was logged in at", user)){
            
        }
    }
    
    
    
    
    
    private void forgot_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgot_btnMouseEntered
         forgot_btn.setForeground(new Color(252, 174, 96));
    }//GEN-LAST:event_forgot_btnMouseEntered

    private void forgot_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgot_btnMouseExited
        forgot_btn.setForeground(new Color(110,0,2));
    }//GEN-LAST:event_forgot_btnMouseExited

    private void loginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseEntered
        login.setBackground( new Color(255,255,255));
        login.setForeground( new Color(110,0,2));
        login_back.setBackground( new Color(255,255,255));
        login_back2.setBackground( new Color(110,0,2));
        
    }//GEN-LAST:event_loginMouseEntered

    private void loginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseExited
        login.setBackground( new Color(110,0,2));
        login.setForeground( new Color(255,255,255));
        login_back.setBackground( new Color(110,0,2));
        login_back2.setBackground( new Color(255,255,255));
    }//GEN-LAST:event_loginMouseExited

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
         hide.setVisible(true);
         view.setVisible(false);
         incorrect_panel.setVisible(false);
         Password.setEchoChar((char)0);
    }//GEN-LAST:event_viewActionPerformed

    private void hideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideActionPerformed
        view.setVisible(true);
        hide.setVisible(false);
        incorrect_panel.setVisible(false);
        Password.setEchoChar('\u25cf');
    }//GEN-LAST:event_hideActionPerformed

    private void PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordActionPerformed

    private void sing_up_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sing_up_btnMouseExited
        sing_up_btn.setForeground(new Color(110,0,2));
    }//GEN-LAST:event_sing_up_btnMouseExited

    private void sing_up_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sing_up_btnMouseEntered
        sing_up_btn.setForeground(new Color(252, 174, 96));
    }//GEN-LAST:event_sing_up_btnMouseEntered

    private void sing_up_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sing_up_btnMouseClicked
         //private Thread searchThread;
       
       
        
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
            
    }//GEN-LAST:event_sing_up_btnMouseClicked

    private void forgot_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgot_btnMouseClicked
            B_resetPassword ad = new B_resetPassword();
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
    }//GEN-LAST:event_forgot_btnMouseClicked

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed
 
    public static void main(String args[]) {
         
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new A_Log_in().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Password;
    private javax.swing.JTextField email;
    private javax.swing.JLabel forgot_btn;
    private button.Button hide;
    private javax.swing.JPanel incorrect_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private keeptoo.KGradientPanel kGradientPanel1;
    private button.Button login;
    private Custom.PanelRound login_back;
    private Custom.PanelRound login_back2;
    private Custom.PanelRound panelRound1;
    private Custom.PanelRound panelRound2;
    private Custom.PanelRound panelRound3;
    private Custom.PanelRound panelRound4;
    private Custom.PanelRound panelRound5;
    private javax.swing.JLabel sing_up_btn;
    private javax.swing.JLabel text_incorrect;
    private button.Button view;
    // End of variables declaration//GEN-END:variables
}
