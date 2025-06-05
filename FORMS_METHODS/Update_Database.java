 
package FORMS_METHODS;
 
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Update_Database {
    
      Connection con; 
        PreparedStatement pst, ps;
        ResultSet rs, rst;
    public void Connect() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver"); 
    }
    
    
    public boolean update_account(JTextField name, JTextField email, JTextField id1, JTextField number, JLabel id2){
         
         String Name = String.valueOf(name.getText());
         String Email = String.valueOf(email.getText());
         int ID1 =  Integer.parseInt(String.valueOf(id1.getText()));
         String ID_number = String.valueOf(id1.getText());
         String Number = String.valueOf(number.getText());
         
         int ID2 = Integer.parseInt(String.valueOf(id2.getText()));
          
         boolean update_account=false;
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");

            String sql="UPDATE `accounts` SET `name`=?, `email`=?, `id_no`=?, `number`=? WHERE `id_no`=?";
            ps=(PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, Name);
            ps.setString(2, Email);  
            ps.setInt(3, ID1);
            ps.setString(4, Number);  
            ps.setInt(5, ID2); 
            
                if(ps.executeUpdate()>0){ 
                     update_account=true;  
            }
               
        } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Update_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(Update_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           }
         
         return  update_account;
     }
     
    
    
    public boolean update_item(JTextField room, JComboBox equipment, JTextField unit, JTextField date, JComboBox status, int id2){
         
         String Room = String.valueOf(room.getText());
         String Equipment = String.valueOf(equipment.getSelectedItem());
         int Unit =  Integer.parseInt(String.valueOf(unit.getText()));
         String Date = String.valueOf(date.getText());
         String Status = String.valueOf(status.getSelectedItem());
         
         int Unit2 = id2;
          
         boolean update_item=false;
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");

            String sql="UPDATE `inventory_list` SET `room_assigned`=?, `equipment`=?, `unit_no`=?, `date_receive`=?, `status`=?  WHERE `unit_no`=?";
            ps=(PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, Room);
            ps.setString(2, Equipment);  
            ps.setInt(3, Unit);
            ps.setString(4, Date);  
            ps.setString(5, Status);
            ps.setInt(6, Unit2); 
            
                if(ps.executeUpdate()>0){ 
                     update_item=true;  
                   
            }
               
        } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Update_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(Update_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           }
         
         return  update_item;
     }
    
    
    
     public boolean update_password(JTextField email, JPasswordField password){
         
         String Email = String.valueOf(email.getText());
         String Password = String.valueOf(password.getPassword()); 
         
         boolean update_password=false;
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");

            String sql="UPDATE `accounts` SET`password`=? WHERE `email`=?";
            ps=(PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, Password);
            ps.setString(2, Email);   
                if(ps.executeUpdate()>0){ 
                     update_password=true;  
            }
               
        } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Update_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(Update_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           }
         
         return  update_password;
     }
     
         public boolean update_user_status(JComboBox Status, JLabel User_id){
         
         String status = String.valueOf(Status.getSelectedItem()); 
         int id = Integer.parseInt(String.valueOf(User_id.getText()));
         boolean update_user_status=false;
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");

            String sql="UPDATE `accounts` SET `status`=? WHERE `id_no`=?";
            ps=(PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, id);   
                if(ps.executeUpdate()>0){ 
                     update_user_status=true;  
            }
               
        } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Update_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(Update_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
           }
         
         return  update_user_status;
     }
    
    
}
