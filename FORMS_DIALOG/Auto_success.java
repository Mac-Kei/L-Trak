 
package FORMS_DIALOG;
 
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;  
import messageSwing.Glass;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Auto_success extends javax.swing.JDialog {

    private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;

    public Auto_success(JFrame fram) {
        super(fram, true);
        this.fram = fram;
        initComponents();
        init();
        
         
        back.setBackground(new Color(1,1,1,100)); 
        panel.setBackground(new Color(214,103,103,200)); 
        
        auto();
    }
    
    
     private Thread searchThread;
    private void auto(){
         
        if (searchThread != null && searchThread.isAlive()) {
        searchThread.interrupt();
    }
    searchThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    SwingUtilities.invokeLater(Auto_success.this::delete);
                }catch (InterruptedException e) {
                }   }
        });
    searchThread.start();
    }
    
    void exit(){
         messageType = Auto_success.MessageType.CANCEL;
                          closeMessage();
    }
    

    private void init() { 
        setBackground(new Color(0, 0, 0, 0));
        StyledDocument doc = txt.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
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

    public void showMessage( String message) {
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
                        Logger.getLogger(Auto_success.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
             }
        }).start();int a=0; 
        
    }
    void delete(){
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
                        Logger.getLogger(Auto_success.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
             }
        }).start();int a=0; 
        
    }     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        back = new Custom.PanelRound();
        panel = new Custom.PanelRound();
        txt = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back.setBackground(new java.awt.Color(102, 102, 102));
        back.setLayout(new java.awt.GridBagLayout());

        panel.setBackground(new java.awt.Color(214, 103, 103));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt.setEditable(false);
        txt.setBackground(new java.awt.Color(226, 225, 245));
        txt.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txt.setForeground(new java.awt.Color(255, 255, 255));
        txt.setText("Logged in successfully");
        txt.setFocusable(false);
        txt.setOpaque(false);
        panel.add(txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 700, 90));
        txt.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 0));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.ABOVE_BASELINE;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 300, 0);
        back.add(panel, gridBagConstraints);

        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1536, 940));

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    public static enum MessageType {
        CANCEL, OK
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.PanelRound back;
    private Custom.PanelRound panel;
    private javax.swing.JTextPane txt;
    // End of variables declaration//GEN-END:variables
}
