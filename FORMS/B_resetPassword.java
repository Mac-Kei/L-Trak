 
package FORMS;

import FORMS_DIALOG.Auto_success;
import FORMS_METHODS.Check_Database;
import FORMS_METHODS.Delete_Database;
import FORMS_METHODS.Get_Database;
import FORMS_METHODS.Insert_Database;
import FORMS_METHODS.Table_Methods;
import FORMS_METHODS.Update_Database;
import java.awt.Color;
import javax.swing.JPanel;
 
public class B_resetPassword extends javax.swing.JFrame {
 
    Table_Methods TM = new Table_Methods();
    Insert_Database ID = new Insert_Database();
    Get_Database GD = new Get_Database();
    Check_Database CD = new Check_Database();
    Update_Database UD = new Update_Database();
    Delete_Database DD = new Delete_Database();
    
    public B_resetPassword() {
        initComponents();
        Password.setEchoChar('\u25cf');
        hide.setVisible(false); 
        Password2.setEchoChar('\u25cf');
        hide1.setVisible(false); 
        
        incorrect_panel.setVisible(false);
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
                    delay_hide();
                    try {
                       Thread.sleep(2);
                       
                    } catch (InterruptedException ex) {
                         
                    }
                }  
              
          }
        }).start() ; int start=50; 
       }
          void delay_hide(){
          if (searchThread != null && searchThread.isAlive()) {
            searchThread.interrupt();
        }
        searchThread = new Thread(() -> {
            try {
                Thread.sleep(100);
                incorrect_panel.setVisible(false);
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
        jLabel2 = new javax.swing.JLabel();
        panelRound3 = new Custom.PanelRound();
        panelRound2 = new Custom.PanelRound();
        Email = new javax.swing.JTextField();
        panelRound4 = new Custom.PanelRound();
        panelRound5 = new Custom.PanelRound();
        Password = new javax.swing.JPasswordField();
        view = new button.Button();
        hide = new button.Button();
        jLabel4 = new javax.swing.JLabel();
        login_back2 = new Custom.PanelRound();
        login_back = new Custom.PanelRound();
        login = new button.Button();
        jLabel6 = new javax.swing.JLabel();
        panelRound6 = new Custom.PanelRound();
        panelRound7 = new Custom.PanelRound();
        Password2 = new javax.swing.JPasswordField();
        hide1 = new button.Button();
        view1 = new button.Button();
        jLabel5 = new javax.swing.JLabel();
        incorrect_panel = new javax.swing.JPanel();
        text_incorrect = new javax.swing.JLabel();
        back_btn = new button.Button();
        back_underline1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        kGradientPanel1.setkEndColor(new java.awt.Color(15, 0, 0));
        kGradientPanel1.setkGradientFocus(700);
        kGradientPanel1.setkStartColor(new java.awt.Color(161, 0, 0));
        kGradientPanel1.setLayout(new java.awt.GridBagLayout());

        panelRound1.setBackground(new java.awt.Color(227, 204, 204));
        panelRound1.setRoundBottomLeft(55);
        panelRound1.setRoundBottomRight(55);
        panelRound1.setRoundTopLeft(55);
        panelRound1.setRoundTopRight(55);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(110, 0, 2));
        jLabel1.setText("Email:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(110, 0, 2));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Reset Password");

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

        Email.setBackground(new java.awt.Color(227, 204, 204));
        Email.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Email.setForeground(new java.awt.Color(110, 0, 2));
        Email.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Email.setCaretColor(new java.awt.Color(110, 0, 2));

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

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
        panelRound5.add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 438, 40));

        view.setBackground(new java.awt.Color(227, 204, 204));
        view.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/BackGround/eye.png"))); // NOI18N
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });
        panelRound5.add(view, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, 20));

        hide.setBackground(new java.awt.Color(227, 204, 204));
        hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/BackGround/eye_hide.png"))); // NOI18N
        hide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideActionPerformed(evt);
            }
        });
        panelRound5.add(hide, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, 20));

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(110, 0, 2));
        jLabel4.setText("New Password:");

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
        login.setText("CONFIRM");
        login.setDoubleBuffered(true);
        login.setEffectColor(new java.awt.Color(204, 204, 204));
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, login_backLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        login_backLayout.setVerticalGroup(
            login_backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_backLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout login_back2Layout = new javax.swing.GroupLayout(login_back2);
        login_back2.setLayout(login_back2Layout);
        login_back2Layout.setHorizontalGroup(
            login_back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_back2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(login_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        login_back2Layout.setVerticalGroup(
            login_back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_back2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(login_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(227, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(".                                         .");

        panelRound6.setBackground(new java.awt.Color(110, 0, 2));
        panelRound6.setRoundBottomLeft(60);
        panelRound6.setRoundBottomRight(60);
        panelRound6.setRoundTopLeft(60);
        panelRound6.setRoundTopRight(60);

        panelRound7.setBackground(new java.awt.Color(227, 204, 204));
        panelRound7.setRoundBottomLeft(50);
        panelRound7.setRoundBottomRight(50);
        panelRound7.setRoundTopLeft(50);
        panelRound7.setRoundTopRight(50);
        panelRound7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Password2.setBackground(new java.awt.Color(227, 204, 204));
        Password2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Password2.setForeground(new java.awt.Color(110, 0, 2));
        Password2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Password2.setCaretColor(new java.awt.Color(110, 0, 2));
        Password2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Password2ActionPerformed(evt);
            }
        });
        panelRound7.add(Password2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 438, 40));

        hide1.setBackground(new java.awt.Color(227, 204, 204));
        hide1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/BackGround/eye_hide.png"))); // NOI18N
        hide1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hide1ActionPerformed(evt);
            }
        });
        panelRound7.add(hide1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, 20));

        view1.setBackground(new java.awt.Color(227, 204, 204));
        view1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/BackGround/eye.png"))); // NOI18N
        view1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view1ActionPerformed(evt);
            }
        });
        panelRound7.add(view1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, 20));

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(110, 0, 2));
        jLabel5.setText("Confirm Password:");

        incorrect_panel.setBackground(new java.awt.Color(214, 103, 103));
        incorrect_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        text_incorrect.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        text_incorrect.setForeground(new java.awt.Color(255, 255, 255));
        text_incorrect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text_incorrect.setText("Invalid email. Please try again.");
        incorrect_panel.add(text_incorrect, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 520, 30));

        back_btn.setBackground(new java.awt.Color(227, 204, 204));
        back_btn.setForeground(new java.awt.Color(110, 0, 2));
        back_btn.setText("Back");
        back_btn.setToolTipText("");
        back_btn.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        back_btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        back_btn.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        back_btn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });

        back_underline1.setBackground(new java.awt.Color(110, 0, 2));

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(back_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back_underline1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(login_back2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(incorrect_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40))))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(incorrect_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(login_back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(back_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(back_underline1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(-145, 26, 0, 0);
        kGradientPanel1.add(panelRound1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1546, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void view1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view1ActionPerformed
        hide1.setVisible(true);
        view1.setVisible(false);
        Password2.setEchoChar((char)0);
    }//GEN-LAST:event_view1ActionPerformed

    private void hide1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hide1ActionPerformed
        view1.setVisible(true);
        hide1.setVisible(false);
        Password2.setEchoChar('\u25cf');
    }//GEN-LAST:event_hide1ActionPerformed

    private void Password2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Password2ActionPerformed
        
    }//GEN-LAST:event_Password2ActionPerformed

     private Thread searchThread;
    void incorrect_animate(){
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
    
    
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        Auto_success AS = new Auto_success(this);
        
        
        if (String.valueOf(Email.getText()).equals("")) {
            text_incorrect.setText("Please enter your email.");
            incorrect_animate();
            } 
         else if(String.valueOf(Password.getPassword()).equals("")) {
            text_incorrect.setText("Please enter your new password.");
            incorrect_animate();
            } 
         else if(String.valueOf(Password2.getPassword()).equals("")) {
            text_incorrect.setText("Enter confirm password.");
            incorrect_animate();
            } 
         else if(!String.valueOf(Password.getPassword()).equals(String.valueOf(Password2.getPassword()))) {
            text_incorrect.setText("Password not match.");
            incorrect_animate();
            } 
         else if (!CD.check_email(String.valueOf(Email.getText()))) {
            text_incorrect.setText("Invalid email. Please try again.");
            incorrect_animate();
        } else{
             if(UD.update_password(Email, Password)){
                 AS.showMessage("Password update successful.");
                 
                 
                 
                  A_Log_in ad = new A_Log_in();
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
             }
         }
         
         
    }//GEN-LAST:event_loginActionPerformed

    private void loginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseExited
        login.setBackground( new Color(110,0,2));
        login.setForeground( new Color(255,255,255));
        login_back.setBackground( new Color(110,0,2));
        login_back2.setBackground( new Color(255,255,255));
    }//GEN-LAST:event_loginMouseExited

    private void loginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseEntered
        login.setBackground( new Color(255,255,255));
        login.setForeground( new Color(110,0,2));
        login_back.setBackground( new Color(255,255,255));
        login_back2.setBackground( new Color(110,0,2));

    }//GEN-LAST:event_loginMouseEntered

    private void hideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideActionPerformed
        view.setVisible(true);
        hide.setVisible(false);
        Password.setEchoChar('\u25cf');
    }//GEN-LAST:event_hideActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        hide.setVisible(true);
        view.setVisible(false);
        Password.setEchoChar((char)0);
    }//GEN-LAST:event_viewActionPerformed

    private void PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordActionPerformed

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
                
        A_Log_in ad = new A_Log_in();
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
    }//GEN-LAST:event_back_btnActionPerformed

    
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new B_resetPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Email;
    private javax.swing.JPasswordField Password;
    private javax.swing.JPasswordField Password2;
    private button.Button back_btn;
    private javax.swing.JPanel back_underline1;
    private button.Button hide;
    private button.Button hide1;
    private javax.swing.JPanel incorrect_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private keeptoo.KGradientPanel kGradientPanel1;
    private button.Button login;
    private Custom.PanelRound login_back;
    private Custom.PanelRound login_back2;
    private Custom.PanelRound panelRound1;
    private Custom.PanelRound panelRound2;
    private Custom.PanelRound panelRound3;
    private Custom.PanelRound panelRound4;
    private Custom.PanelRound panelRound5;
    private Custom.PanelRound panelRound6;
    private Custom.PanelRound panelRound7;
    private javax.swing.JLabel text_incorrect;
    private button.Button view;
    private button.Button view1;
    // End of variables declaration//GEN-END:variables
}
