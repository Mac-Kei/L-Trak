 
package ACTION_TABLE;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

 
public class TableActionRender extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean bln1, int row, int i1){
        Component com = super.getTableCellRendererComponent(jtable, o, isSelected, bln1, row, i1);
        PanelAction action = new PanelAction();
        
        if(isSelected==false && row % 2 == 0){
            action.setBackground(Color.WHITE);  
        } else{
            action.setBackground(com.getBackground()); 
        }
         
       // action.setBackground(com.getBackground());
        
           
       
 
        return action;
    }
    
    
}
