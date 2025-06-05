 
package FORMS_DIALOG;
 
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import messageSwing.Glass;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Delete_Dialog extends javax.swing.JDialog {

    private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;

    public Delete_Dialog(JFrame fram) {
        super(fram, true);
        this.fram = fram;
        initComponents();
        init();
        
        NO_panel1.setBackground(new Color(0,0,0));
        YES_panel1.setBackground(new Color(0,0,0));
        
        
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

    public void showMessage(String message) {
        fram.setGlassPane(glass);
        glass.setVisible(true); 
        txt.setText(message);
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
                        Logger.getLogger(Delete_Dialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
             }
        }).start();int a=0; 
        
    }
    void delete(){
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
                        Logger.getLogger(Delete_Dialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
             }
        }).start();int a=0; 
        
    }     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new Custom.PanelRound();
        txt = new javax.swing.JTextPane();
        YES_panel1 = new Custom.PanelRound();
        YES_panel = new Custom.PanelRound();
        cmdOK = new button.Button();
        NO_panel1 = new Custom.PanelRound();
        NO_panel = new Custom.PanelRound();
        cmdNO = new button.Button();

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
        txt.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txt.setForeground(new java.awt.Color(0, 0, 0));
        txt.setText("ARE YOU SURE YOU WANT TO DELETE THIS USER?");
        txt.setFocusable(false);
        txt.setOpaque(false);
        panelRound1.add(txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, 100));

        YES_panel1.setRoundBottomLeft(40);
        YES_panel1.setRoundBottomRight(40);
        YES_panel1.setRoundTopLeft(40);
        YES_panel1.setRoundTopRight(40);

        YES_panel.setBackground(new java.awt.Color(255, 255, 255));
        YES_panel.setRoundBottomLeft(40);
        YES_panel.setRoundBottomRight(40);
        YES_panel.setRoundTopLeft(40);
        YES_panel.setRoundTopRight(40);

        cmdOK.setForeground(new java.awt.Color(0, 0, 0));
        cmdOK.setText("YES");
        cmdOK.setFocusable(false);
        cmdOK.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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

        javax.swing.GroupLayout YES_panelLayout = new javax.swing.GroupLayout(YES_panel);
        YES_panel.setLayout(YES_panelLayout);
        YES_panelLayout.setHorizontalGroup(
            YES_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(YES_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(cmdOK, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        YES_panelLayout.setVerticalGroup(
            YES_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(YES_panelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout YES_panel1Layout = new javax.swing.GroupLayout(YES_panel1);
        YES_panel1.setLayout(YES_panel1Layout);
        YES_panel1Layout.setHorizontalGroup(
            YES_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(YES_panel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(YES_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        YES_panel1Layout.setVerticalGroup(
            YES_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(YES_panel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(YES_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        panelRound1.add(YES_panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 90, 35));

        NO_panel1.setRoundBottomLeft(40);
        NO_panel1.setRoundBottomRight(40);
        NO_panel1.setRoundTopLeft(40);
        NO_panel1.setRoundTopRight(40);

        NO_panel.setBackground(new java.awt.Color(255, 255, 255));
        NO_panel.setRoundBottomLeft(40);
        NO_panel.setRoundBottomRight(40);
        NO_panel.setRoundTopLeft(40);
        NO_panel.setRoundTopRight(40);

        cmdNO.setForeground(new java.awt.Color(0, 0, 0));
        cmdNO.setText("NO");
        cmdNO.setFocusable(false);
        cmdNO.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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

        javax.swing.GroupLayout NO_panelLayout = new javax.swing.GroupLayout(NO_panel);
        NO_panel.setLayout(NO_panelLayout);
        NO_panelLayout.setHorizontalGroup(
            NO_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NO_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(cmdNO, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        NO_panelLayout.setVerticalGroup(
            NO_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NO_panelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cmdNO, javax.swing.GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout NO_panel1Layout = new javax.swing.GroupLayout(NO_panel1);
        NO_panel1.setLayout(NO_panel1Layout);
        NO_panel1Layout.setHorizontalGroup(
            NO_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NO_panel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(NO_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        NO_panel1Layout.setVerticalGroup(
            NO_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NO_panel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(NO_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        panelRound1.add(NO_panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 130, 90, 35));

        getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
       
        delete();   
         
    }//GEN-LAST:event_cmdOKActionPerformed

    private void cmdOKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMouseEntered
        cmdOK.setBackground(new Color(255, 48, 48));
        cmdOK.setForeground(new Color(255, 255, 255));
        YES_panel1.setBackground(new Color(255, 48, 48));
        YES_panel.setBackground(new Color(255, 48, 48));
        
    }//GEN-LAST:event_cmdOKMouseEntered

    private void cmdOKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMouseExited
       
        cmdOK.setBackground(new Color(255, 255, 255));
        cmdOK.setForeground(new Color(0,0,0));
        YES_panel1.setBackground(new Color(0,0,0));
        YES_panel.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_cmdOKMouseExited

    private void cmdNOMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdNOMouseEntered
        cmdNO.setBackground(new Color(255, 48, 48));
        cmdNO.setForeground(new Color(255, 255, 255));
        NO_panel1.setBackground(new Color(255, 48, 48));
        NO_panel.setBackground(new Color(255, 48, 48));
    }//GEN-LAST:event_cmdNOMouseEntered

    private void cmdNOMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdNOMouseExited
        cmdNO.setBackground(new Color(255, 255, 255));
        cmdNO.setForeground(new Color(0,0,0));
        NO_panel1.setBackground(new Color(0,0,0));
        NO_panel.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_cmdNOMouseExited

    private void cmdNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNOActionPerformed
      cancel();
    }//GEN-LAST:event_cmdNOActionPerformed
   
    public static enum MessageType {
        CANCEL, OK
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.PanelRound NO_panel;
    private Custom.PanelRound NO_panel1;
    private Custom.PanelRound YES_panel;
    private Custom.PanelRound YES_panel1;
    private button.Button cmdNO;
    private button.Button cmdOK;
    private Custom.PanelRound panelRound1;
    private javax.swing.JTextPane txt;
    // End of variables declaration//GEN-END:variables
}
