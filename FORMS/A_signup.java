 
package FORMS;

import FORMS_DIALOG.Auto_success;
import FORMS_METHODS.Check_Database;
import FORMS_METHODS.Get_Database;
import FORMS_METHODS.Insert_Database;
import FORMS_METHODS.Table_Methods;
import java.awt.Color;
import javax.swing.JPanel;
 
public class A_signup extends javax.swing.JFrame {
    Table_Methods TM = new Table_Methods();
    Insert_Database ID = new Insert_Database();
    Get_Database GD = new Get_Database();
    Check_Database CD = new Check_Database();
    public A_signup() {
        initComponents(); 
        Password.setEchoChar('\u25cf');
        hide1.setVisible(false); 
        incorrect_panel.setVisible(false);
        
        IDnumber.setDocument(new number_only());
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
                       Thread.sleep(3);
                       
                    } catch (InterruptedException ex) {
                         
                    }
                }  
              
          }
        }).start() ; int start=50; 
       }
     private Thread searchThread;
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
        Fullname = new javax.swing.JTextField();
        login_back2 = new Custom.PanelRound();
        login_back = new Custom.PanelRound();
        login = new button.Button();
        panelRound6 = new Custom.PanelRound();
        panelRound7 = new Custom.PanelRound();
        Password = new javax.swing.JPasswordField();
        hide1 = new button.Button();
        view1 = new button.Button();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelRound8 = new Custom.PanelRound();
        panelRound9 = new Custom.PanelRound();
        IDnumber = new javax.swing.JTextField();
        panelRound14 = new Custom.PanelRound();
        panelRound15 = new Custom.PanelRound();
        Email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        incorrect_panel = new javax.swing.JPanel();
        text_incorrect = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        sing_up_btn = new javax.swing.JLabel();

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
        jLabel1.setText("Full Name:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(110, 0, 2));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SIGN UP");

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

        Fullname.setBackground(new java.awt.Color(227, 204, 204));
        Fullname.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Fullname.setForeground(new java.awt.Color(110, 0, 2));
        Fullname.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Fullname.setCaretColor(new java.awt.Color(110, 0, 2));

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Fullname, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fullname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

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
        login.setText("REGISTER");
        login.setDoubleBuffered(true);
        login.setEffectColor(new java.awt.Color(204, 204, 204));
        login.setFocusable(false);
        login.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
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
                .addGap(23, 23, 23)
                .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        login_backLayout.setVerticalGroup(
            login_backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, login_backLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
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
        panelRound7.add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1, 438, 38));

        hide1.setBackground(new java.awt.Color(227, 204, 204));
        hide1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/BackGround/eye_hide.png"))); // NOI18N
        hide1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hide1ActionPerformed(evt);
            }
        });
        panelRound7.add(hide1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, 20));

        view1.setBackground(new java.awt.Color(227, 204, 204));
        view1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/BackGround/eye.png"))); // NOI18N
        view1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view1ActionPerformed(evt);
            }
        });
        panelRound7.add(view1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, 20));

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(110, 0, 2));
        jLabel5.setText("Password:");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(110, 0, 2));
        jLabel3.setText("ID No:");

        panelRound8.setBackground(new java.awt.Color(110, 0, 2));
        panelRound8.setRoundBottomLeft(60);
        panelRound8.setRoundBottomRight(60);
        panelRound8.setRoundTopLeft(60);
        panelRound8.setRoundTopRight(60);

        panelRound9.setBackground(new java.awt.Color(227, 204, 204));
        panelRound9.setRoundBottomLeft(50);
        panelRound9.setRoundBottomRight(50);
        panelRound9.setRoundTopLeft(50);
        panelRound9.setRoundTopRight(50);

        IDnumber.setBackground(new java.awt.Color(227, 204, 204));
        IDnumber.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        IDnumber.setForeground(new java.awt.Color(110, 0, 2));
        IDnumber.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        IDnumber.setCaretColor(new java.awt.Color(110, 0, 2));

        javax.swing.GroupLayout panelRound9Layout = new javax.swing.GroupLayout(panelRound9);
        panelRound9.setLayout(panelRound9Layout);
        panelRound9Layout.setHorizontalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(IDnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelRound9Layout.setVerticalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IDnumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelRound9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound8Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelRound9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        panelRound14.setBackground(new java.awt.Color(110, 0, 2));
        panelRound14.setRoundBottomLeft(60);
        panelRound14.setRoundBottomRight(60);
        panelRound14.setRoundTopLeft(60);
        panelRound14.setRoundTopRight(60);

        panelRound15.setBackground(new java.awt.Color(227, 204, 204));
        panelRound15.setRoundBottomLeft(50);
        panelRound15.setRoundBottomRight(50);
        panelRound15.setRoundTopLeft(50);
        panelRound15.setRoundTopRight(50);

        Email.setBackground(new java.awt.Color(227, 204, 204));
        Email.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Email.setForeground(new java.awt.Color(110, 0, 2));
        Email.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Email.setCaretColor(new java.awt.Color(110, 0, 2));
        Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound15Layout = new javax.swing.GroupLayout(panelRound15);
        panelRound15.setLayout(panelRound15Layout);
        panelRound15Layout.setHorizontalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelRound15Layout.setVerticalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound14Layout = new javax.swing.GroupLayout(panelRound14);
        panelRound14.setLayout(panelRound14Layout);
        panelRound14Layout.setHorizontalGroup(
            panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound14Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelRound15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelRound14Layout.setVerticalGroup(
            panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound14Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelRound15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(110, 0, 2));
        jLabel7.setText("Email:");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(110, 0, 2));
        jLabel8.setText("  ");

        incorrect_panel.setBackground(new java.awt.Color(214, 103, 103));
        incorrect_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        text_incorrect.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        text_incorrect.setForeground(new java.awt.Color(255, 255, 255));
        text_incorrect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text_incorrect.setText("Email already existed. Please try another.");
        incorrect_panel.add(text_incorrect, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 520, 30));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(110, 0, 2));
        jLabel9.setText("Already have an account? ");

        sing_up_btn.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        sing_up_btn.setForeground(new java.awt.Color(110, 0, 2));
        sing_up_btn.setText("Log In");
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

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelRound1Layout.createSequentialGroup()
                                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(panelRound14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(incorrect_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sing_up_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(login_back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(incorrect_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(panelRound14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(login_back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sing_up_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(-105, 21, 0, 0);
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

       String email=  String.valueOf(Email.getText()); 
       String pass1 = String.valueOf(Password.getPassword());
       
        String lowercasePattern = ".*[a-z].*";
        String uppercasePattern = ".*[A-Z].*";
        String digitPattern = ".*[0-9].*";
        String specialCharacterPattern = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*";

         // Check each condition
        boolean hasLowercase = pass1.matches(lowercasePattern);
        boolean hasUppercase = pass1.matches(uppercasePattern);
        boolean hasDigit = pass1.matches(digitPattern);
        boolean hasSpecialChar = pass1.matches(specialCharacterPattern);
               
               
     if (String.valueOf(Fullname.getText()).equals("")) {
        text_incorrect.setText("Please enter your Name.");
        incorrect_animate();
    } 
    else if (String.valueOf(IDnumber.getText()).equals("")) {
        text_incorrect.setText("Please enter your ID number.");
        incorrect_animate();
    } 
    else if (String.valueOf(Email.getText()).equals("")) {
        text_incorrect.setText("Please enter your Email.");
        incorrect_animate();
    } 
    else if (!email.contains("@")||!email.contains("gmail.com") ) {
        text_incorrect.setText("Email must contain \"@gmail.com\".");
        incorrect_animate();
    }
    else if (String.valueOf(Password.getPassword()).equals("")) {
        text_incorrect.setText("Please enter your Password.");
        incorrect_animate();
    } 
    else if (pass1.length() < 8) {
        text_incorrect.setText("Password should at least 8 characters.");
        incorrect_animate();
            } 
    else if (!hasUppercase) { 
        text_incorrect.setText("Password must contain at least one uppercase letter.");
        incorrect_animate();
    }

    else if (!hasLowercase) { 
         text_incorrect.setText("Password must contain at least one lowercase letter."); 
         incorrect_animate();
    }

    else if (!hasDigit) { 
        text_incorrect.setText("Password must contain at least one digit."); 
        incorrect_animate();
    }

    else if (!hasSpecialChar) { 
         text_incorrect.setText("Password must contain at least one special character."); 
         incorrect_animate();
    }
    
    
    
    
    
    
    
    
    else {
        int id;
        try {
            id = Integer.parseInt(IDnumber.getText());
        } catch (NumberFormatException e) {
            text_incorrect.setText("ID number must be a valid number.");
            incorrect_animate();
            return;
        }
          if (CD.check_id(id)) {
            text_incorrect.setText("ID number already existed. Please try another.");
            incorrect_animate();
        } 
        else if (CD.check_email(String.valueOf(Email.getText()))) {
            text_incorrect.setText("Email already existed. Please try another.");
            incorrect_animate();
        } 
        
        else {
            if (ID.insert_account(Fullname, IDnumber, Email, Password)) {
                AS.showMessage("New account added successfully");
                Fullname.setText("");
                Email.setText("");
                IDnumber.setText("");
                Password.setText("");

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
    }
        
    }//GEN-LAST:event_loginActionPerformed

    private void PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordActionPerformed

    private void hide1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hide1ActionPerformed
        view1.setVisible(true);
        hide1.setVisible(false);
        incorrect_panel.setVisible(false);
        Password.setEchoChar('\u25cf');
    }//GEN-LAST:event_hide1ActionPerformed

    private void view1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view1ActionPerformed
        hide1.setVisible(true);
        view1.setVisible(false);
        incorrect_panel.setVisible(false);
        Password.setEchoChar((char)0);
    }//GEN-LAST:event_view1ActionPerformed

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailActionPerformed

    private void sing_up_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sing_up_btnMouseClicked
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
    }//GEN-LAST:event_sing_up_btnMouseClicked

    private void sing_up_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sing_up_btnMouseEntered
        sing_up_btn.setForeground(new Color(252, 174, 96));
    }//GEN-LAST:event_sing_up_btnMouseEntered

    private void sing_up_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sing_up_btnMouseExited
        sing_up_btn.setForeground(new Color(110,0,2));
    }//GEN-LAST:event_sing_up_btnMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new A_signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Email;
    private javax.swing.JTextField Fullname;
    private javax.swing.JTextField IDnumber;
    private javax.swing.JPasswordField Password;
    private button.Button hide1;
    private javax.swing.JPanel incorrect_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private keeptoo.KGradientPanel kGradientPanel1;
    private button.Button login;
    private Custom.PanelRound login_back;
    private Custom.PanelRound login_back2;
    private Custom.PanelRound panelRound1;
    private Custom.PanelRound panelRound14;
    private Custom.PanelRound panelRound15;
    private Custom.PanelRound panelRound2;
    private Custom.PanelRound panelRound3;
    private Custom.PanelRound panelRound6;
    private Custom.PanelRound panelRound7;
    private Custom.PanelRound panelRound8;
    private Custom.PanelRound panelRound9;
    private javax.swing.JLabel sing_up_btn;
    private javax.swing.JLabel text_incorrect;
    private button.Button view1;
    // End of variables declaration//GEN-END:variables
}
