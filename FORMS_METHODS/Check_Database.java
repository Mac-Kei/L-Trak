 
package FORMS_METHODS;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

 
public class Check_Database {
    
    Connection con; 
        PreparedStatement pst, ps;
        ResultSet rs, rst;
    public void Connect() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver"); 
    }
    
    
    
        public boolean check_Unit(int Unit ){
       
        boolean check_Unit=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
            pst= (PreparedStatement) con.prepareStatement("SELECT * FROM `inventory_list` WHERE `unit_no`=?");
            pst.setInt(1, Unit); 
       
           rs=pst.executeQuery();
            if(rs.next()){
                check_Unit=true;
            }} 
        catch (SQLException ex) {
            Logger.getLogger(Check_Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Check_Database.class.getName()).log(Level.SEVERE, null, ex);
        }
          return check_Unit;
        
    }
        
        
        
            public boolean check_account(String email, String password){
       
        boolean check_account=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
             con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
            pst= (PreparedStatement) con.prepareStatement("SELECT * FROM `accounts` WHERE `email`=? AND `password`=?");
            pst.setString(1, email);
            pst.setString(2, password); 
       
           rs=pst.executeQuery();
            if(rs.next()){
                check_account=true;
            }} 
        catch (SQLException ex) {
            Logger.getLogger(Check_Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Check_Database.class.getName()).log(Level.SEVERE, null, ex);
        }
          return check_account;
        
    }
    
    public boolean check_email(String email){
       
        boolean check_email=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
             con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
            pst= (PreparedStatement) con.prepareStatement("SELECT * FROM `accounts` WHERE `email`=?");
            pst.setString(1, email); 
       
           rs=pst.executeQuery();
            if(rs.next()){
                check_email=true;
            }} 
        catch (SQLException ex) {
            Logger.getLogger(Check_Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Check_Database.class.getName()).log(Level.SEVERE, null, ex);
        }
          return check_email;
        
    }
            
    
    
    
    
    
     public boolean check_id(int ID){ 
        boolean check_id=false;
        try {
             
            Class.forName("com.mysql.jdbc.Driver");
            int id= ID;
             con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
            pst= (PreparedStatement) con.prepareStatement("SELECT * FROM `accounts` WHERE `id_no`=?");
            pst.setInt(1, id);
            
            
            rs=pst.executeQuery();
            if(rs.next()){
                check_id=true;
            }} 
        catch (SQLException ex) {
            Logger.getLogger(Check_Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Check_Database.class.getName()).log(Level.SEVERE, null, ex);
        }
          return check_id;
        
    }
                    
    
    
    
}
