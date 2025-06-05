 
package FORMS_METHODS;
 
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import table.table;

public class Get_Database {
       Connection con; 
        PreparedStatement pst, ps;
        ResultSet rs, rst;
    public void Connect() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver"); 
    }
    
    public boolean get_item_for_admin( table table ){
        boolean get_item_for_admin =false;
            DefaultTableModel model = (DefaultTableModel) table.getModel(); 
 
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
            
           try {
               Class.forName("com.mysql.jdbc.Driver");
                con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM `inventory_list` WHERE `id` ORDER BY `unit_no` ASC");
              
            rs = ps.executeQuery();
     
    while (rs.next()) { 
        get_item_for_admin = true;
             
                    String C1 = String.valueOf(rs.getString("room_assigned"));
                    String C2 = String.valueOf(rs.getString("equipment"));
                    String C3 = String.valueOf(rs.getString("unit_no"));
                    String C4 = String.valueOf(rs.getString("date_receive"));
                    String C5 = String.valueOf(rs.getString("status"));  
                    
                    String C[] = {C1, C2, C3, C4, C5};
                    DefaultTableModel tbmodel = (DefaultTableModel) table.getModel();
                    tbmodel.addRow(C);
                     
           }
             
           } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Get_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(Get_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           }
            
        return get_item_for_admin;
    }
    
    
        public boolean get_users( table table ){
        boolean get_users =false;
            DefaultTableModel model = (DefaultTableModel) table.getModel(); 
 
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
            
           try {
               Class.forName("com.mysql.jdbc.Driver");
                con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM `accounts` WHERE `id` ORDER BY `id_no` ASC");
              
            rs = ps.executeQuery();
     
    while (rs.next()) { 
        get_users = true;
             
                    String C1 = String.valueOf(rs.getString("name"));
                    String C2 = String.valueOf(rs.getString("email"));
                    String C3 = String.valueOf(rs.getString("id_no"));
                    String C4 = String.valueOf(rs.getString("number"));
                    String C5 = String.valueOf(rs.getString("status"));  
                    
                    String C[] = {C1, C2, C3, C4, C5};
                    DefaultTableModel tbmodel = (DefaultTableModel) table.getModel();
                    tbmodel.addRow(C);
                     
           }
             
           } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Get_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(Get_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           }
            
        return get_users;
    }
    
    
                public boolean get_history( table table ){
        boolean get_history =false;
            DefaultTableModel model = (DefaultTableModel) table.getModel(); 
 
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
            
           try {
               Class.forName("com.mysql.jdbc.Driver");
                con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
            ps = (PreparedStatement) con.prepareStatement("SELECT * FROM `history` WHERE `id` ORDER BY `id` DESC");
              
            rs = ps.executeQuery();
     
    while (rs.next()) { 
        get_history = true;
             
                    String C1 = "   "+String.valueOf(rs.getString("text"))+" "+String.valueOf(rs.getString("date"))+" / "+String.valueOf(rs.getString("time"));
                    String C2 = String.valueOf(rs.getString("user"))+"   "; 
                    
                    String C[] = {C1, C2};
                    DefaultTableModel tbmodel = (DefaultTableModel) table.getModel();
                    tbmodel.addRow(C);
                     
           }
             
           } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Get_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(Get_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           }
            
        return get_history;
    }
    
    
    
    
    
                
public boolean get_total_item(table table){
        boolean get_item_for_admin =false;
            DefaultTableModel model = (DefaultTableModel) table.getModel(); 
 
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
            
           try {
               Class.forName("com.mysql.jdbc.Driver");
                con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
            ps = (PreparedStatement) con.prepareStatement( "SELECT `equipment`, " +
                                                            "COUNT(*) AS `total`, " +
                                                            "COUNT(CASE WHEN status = 'Working' THEN 1 END) AS `working`, " +
                                                            "COUNT(CASE WHEN status = 'Not Working' THEN 1 END) AS `not_working` " +
                                                            "FROM `inventory_list` " +
                                                            "GROUP BY `equipment`");
              
            rs = ps.executeQuery();
     
    while (rs.next()) { 
        get_item_for_admin = true;
             
                    String C1 = String.valueOf(rs.getString("equipment"));
                    String C2 = String.valueOf(rs.getInt("working"));
                    String C3 = String.valueOf(rs.getInt("not_working")); 
                    String C4 = String.valueOf(rs.getInt("total")); 
                    
                    String C[] = {C1, C2, C3, C4};
                    DefaultTableModel tbmodel = (DefaultTableModel) table.getModel();
                    tbmodel.addRow(C);
                     
           }
             
           } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Get_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(Get_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           }
            
        return get_item_for_admin;
    }
    
                
                
                
                
                
                
    
    
}
