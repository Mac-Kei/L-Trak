 
package FORMS_METHODS;
 
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Delete_Database {
         Connection con; 
        PreparedStatement pst, ps;
        ResultSet rs, rst;
    public void Connect() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
    
    }   
    
     public boolean delete_Item(int Unit){ 
        boolean delete_Item= false;  
        int unit = Unit; 
        try  {
            Class.forName("com.mysql.jdbc.Driver");
            con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
             ps= (PreparedStatement) con.prepareStatement("DELETE FROM `inventory_list` WHERE `unit_no`=?");
             
            ps.setInt(1, unit);  
        int rowsAffected = ps.executeUpdate(); 
         
        if (rowsAffected > 0) {
            delete_Item = true;
        }

        } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Delete_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Delete_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
         return delete_Item;
        
    }
     
          public boolean delete_User(int ID){ 
        boolean delete_User= false;  
        int id = ID; 
        try  {
            Class.forName("com.mysql.jdbc.Driver");
            con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
             ps= (PreparedStatement) con.prepareStatement("DELETE FROM `accounts` WHERE `id_no`=?");
             
            ps.setInt(1, id);  
        int rowsAffected = ps.executeUpdate(); 
         
        if (rowsAffected > 0) {
            delete_User = true;
        }

        } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Delete_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Delete_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
         return delete_User;
        
    }
    
    
    
    
    
}
