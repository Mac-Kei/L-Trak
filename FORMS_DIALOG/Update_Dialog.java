 
package FORMS_DIALOG;
 
import FORMS_METHODS.Insert_Database;
import FORMS_METHODS.Update_Database;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import messageSwing.Glass;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Update_Dialog extends javax.swing.JDialog {

    private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;
    Update_Database UD = new Update_Database(); 
    Insert_Database ID = new Insert_Database();
    public Update_Dialog(JFrame fram) {
        super(fram, true);
        this.fram = fram;
        initComponents();
        init();
        incorrect_panel.setVisible(false);
        Status.setSelectedItem("CMB (ADMIN/USER)");
    }

    private void init() {
        
        
        setBackground(new Color(0, 0, 0, 0));
        StyledDocument doc = txt.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        txt.setOpaque(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeMessage();
            }
        });
        animator = new Animator(350, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float f = show ? fraction : 1f - fraction;
                glass.setAlpha(f - f * 0.3f);
                setOpacity(f);
            }

            @Override
            public void end() {
                if (show == false) {
                    dispose();
                    glass.setVisible(false);
                }
            }
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        setOpacity(0f);
        glass = new Glass();
    }

    private void startAnimator(boolean show) {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
        this.show = show;
        animator.start();
    }

  
    public void showMessage(String message, String id, String editor, String user) {
        fram.setGlassPane(glass);
        glass.setVisible(true); 
        txt.setText(message);
         User_id.setText(id);
         editor_label.setText(editor);
         user_label.setText(user);
        setLocationRelativeTo(fram);
        startAnimator(true);
        setVisible(true);
    }

    public void closeMessage() {
        startAnimator(false);
        this.dispose();
        
    }

    public MessageType getMessageType() {
        return messageType;
    }
    
    void cancel(){
       new Thread (new Runnable(){
            @Override
            public void run(){
                for (int i=0; i<=100; i++){  
                     if(i==100){
                          messageType = MessageType.CANCEL;
                          closeMessage();
                    } 
                    try {
                          Thread.sleep(5);
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Update_Dialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
             }
        }).start();int a=0; 
        
    }
    void update(){
        messageType = MessageType.OK;
       new Thread (new Runnable(){
            @Override
            public void run(){
                for (int i=0; i<=100; i++){  
                     if(i==100){
                           messageType = MessageType.OK;
                           closeMessage();
                    } 
                    try {
                          Thread.sleep(5);
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Update_Dialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
             }
        }).start();int a=0; 
        
    }     
    
    
    
    private Thread searchThread;
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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new Custom.PanelRound();
        txt = new javax.swing.JTextPane();
        cmdNO = new button.Button();
        cmdOK = new button.Button();
        jLabel1 = new javax.swing.JLabel();
        Status = new javax.swing.JComboBox<>();
        incorrect_panel = new javax.swing.JPanel();
        text_incorrect = new javax.swing.JLabel();
        User_id = new javax.swing.JLabel();
        user_label = new javax.swing.JLabel();
        editor_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224), 6));
        panelRound1.setRoundBottomLeft(22);
        panelRound1.setRoundBottomRight(22);
        panelRound1.setRoundTopLeft(22);
        panelRound1.setRoundTopRight(22);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt.setEditable(false);
        txt.setBackground(new java.awt.Color(17, 19, 25));
        txt.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        txt.setText("CHANGE USER’S STATUS");
        txt.setFocusable(false);
        txt.setOpaque(false);
        panelRound1.add(txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 400, 50));

        cmdNO.setBackground(new java.awt.Color(110, 0, 2));
        cmdNO.setBorder(null);
        cmdNO.setForeground(new java.awt.Color(255, 255, 255));
        cmdNO.setText("BACK");
        cmdNO.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmdNO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdNOMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdNOMouseExited(evt);
            }
        });
        cmdNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNOActionPerformed(evt);
            }
        });
        panelRound1.add(cmdNO, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 110, 40));

        cmdOK.setBackground(new java.awt.Color(110, 0, 2));
        cmdOK.setBorder(null);
        cmdOK.setForeground(new java.awt.Color(255, 255, 255));
        cmdOK.setText("UPDATE");
        cmdOK.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmdOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdOKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdOKMouseExited(evt);
            }
        });
        cmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOKActionPerformed(evt);
            }
        });
        panelRound1.add(cmdOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 110, 40));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Status:");
        panelRound1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 110, 20));

        Status.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Status", "ADMIN", "USER" }));
        Status.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224), 5));
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });
        panelRound1.add(Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 260, 40));

        incorrect_panel.setBackground(new java.awt.Color(214, 103, 103));
        incorrect_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        text_incorrect.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        text_incorrect.setForeground(new java.awt.Color(255, 255, 255));
        text_incorrect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text_incorrect.setText("Please select new status.");
        incorrect_panel.add(text_incorrect, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 440, 30));

        panelRound1.add(incorrect_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 50));

        User_id.setForeground(new java.awt.Color(255, 255, 255));
        panelRound1.add(User_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 160, 30));

        user_label.setForeground(new java.awt.Color(255, 255, 255));
        panelRound1.add(user_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, 30));

        editor_label.setForeground(new java.awt.Color(255, 255, 255));
        panelRound1.add(editor_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 160, 30));

        getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed

        String status = String.valueOf(Status.getSelectedItem()); 
        String user = String.valueOf(user_label.getText());
        String editor = String.valueOf(editor_label.getText()); 
        
        if(status == "CMB (ADMIN/USER)"){
            text_incorrect.setText("Please select new status.");
            incorrect_animate();
        }else{
            if(UD.update_user_status(Status, User_id)){ 
                    update();   
                  
                }
            
            if(ID.insert_history("was edited User "+user+" to "+status+" at", editor)){

                    }
        }
        
        
        
        
         
    }//GEN-LAST:event_cmdOKActionPerformed

    private void cmdOKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMouseEntered
//        cmdOK.setBackground(new Color(255, 48, 48));
//        cmdOK.setForeground(new Color(255, 255, 255));
//        YES_panel1.setBackground(new Color(255, 48, 48));
//        YES_panel.setBackground(new Color(255, 48, 48));
        
    }//GEN-LAST:event_cmdOKMouseEntered

    private void cmdOKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMouseExited
       
//        cmdOK.setBackground(new Color(255, 255, 255));
//        cmdOK.setForeground(new Color(0,0,0));
//        YES_panel1.setBackground(new Color(0,0,0));
//        YES_panel.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_cmdOKMouseExited

    private void cmdNOMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdNOMouseEntered
//        cmdNO.setBackground(new Color(255, 48, 48));
//        cmdNO.setForeground(new Color(255, 255, 255));
//        NO_panel1.setBackground(new Color(255, 48, 48));
//        NO_panel.setBackground(new Color(255, 48, 48));
    }//GEN-LAST:event_cmdNOMouseEntered

    private void cmdNOMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdNOMouseExited
//        cmdNO.setBackground(new Color(255, 255, 255));
//        cmdNO.setForeground(new Color(0,0,0));
//        NO_panel1.setBackground(new Color(0,0,0));
//        NO_panel.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_cmdNOMouseExited

    private void cmdNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNOActionPerformed
      cancel();
    }//GEN-LAST:event_cmdNOActionPerformed

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusActionPerformed
   
    public static enum MessageType {
        CANCEL, OK
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Status;
    private javax.swing.JLabel User_id;
    private button.Button cmdNO;
    private button.Button cmdOK;
    private javax.swing.JLabel editor_label;
    private javax.swing.JPanel incorrect_panel;
    private javax.swing.JLabel jLabel1;
    private Custom.PanelRound panelRound1;
    private javax.swing.JLabel text_incorrect;
    private javax.swing.JTextPane txt;
    private javax.swing.JLabel user_label;
    // End of variables declaration//GEN-END:variables
}
