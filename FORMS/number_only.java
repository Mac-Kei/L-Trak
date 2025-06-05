package FORMS;
 
import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;



//ONLY NUMBER
//        ID_Number.setDocument(new number_only());




class number_only extends PlainDocument {
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        // Only insert the string if it's numeric
        if (str.matches("\\d*")) {
            super.insertString(offset, str, attr);
             
        } else {
            // Play a beep sound if the input is invalid (non-numeric)
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void replace(int offset, int length, String str, AttributeSet attrs) throws BadLocationException {
        if (str == null) {
            return;
        }

        // Only replace with numeric values
        if (str.matches("\\d*")) {
            super.replace(offset, length, str, attrs);
            
        } else {
            // Play a beep sound if the input is invalid (non-numeric)
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    
}
 