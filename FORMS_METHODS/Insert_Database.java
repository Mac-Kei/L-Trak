 
package FORMS_METHODS;
  
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Insert_Database {
        Connection con; 
        PreparedStatement pst, ps;
        ResultSet rs, rst;
    public void Connect() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver"); 
    }
     
        public boolean insert_item( 
                                   JTextField room,
                                   JComboBox equipment, 
                                   JTextField unit, 
                                   JTextField date, 
                                    JComboBox status){ 
                    boolean insert_item = false;
                    String Room = String.valueOf(room.getText());               
                    String Equipment= String.valueOf(equipment.getSelectedItem());
                    int Unit = Integer.parseInt(String.valueOf(unit.getText()));
                    String Date = String.valueOf(date.getText());  
                    String Status = String.valueOf(status.getSelectedItem());
                    
                    try {
            Class.forName("com.mysql.jdbc.Driver");
            con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
             ps= (PreparedStatement) con.prepareStatement("INSERT INTO `inventory_list`(`room_assigned`, `equipment`, `unit_no`, `date_receive`, `status`) VALUES (?,?,?,?,?)");
            ps.setString(1, Room);
            ps.setString(2, Equipment);
            ps.setInt(3, Unit);
            ps.setString(4, Date);
            ps.setString(5, Status);  
            
             int rowsInserted = ps.executeUpdate(); 
              if (rowsInserted > 0) {
                  insert_item = true; 
                  //success
                 }
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Insert_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Insert_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
                    return insert_item;
}
        
        
        
        public boolean insert_account( 
                                   JTextField Fullname,
                                   JTextField ID, 
                                   JTextField Email, 
                                   JPasswordField Password)
                                     { 
                    boolean insert_account = false;
                    String fullname = String.valueOf(Fullname.getText());               
                    int id= Integer.parseInt(String.valueOf(ID.getText()));
                    String email= String.valueOf(Email.getText());
                    String password = String.valueOf(Password.getPassword());   
                    
                    try {
            Class.forName("com.mysql.jdbc.Driver");
            con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
             ps= (PreparedStatement) con.prepareStatement("INSERT INTO `accounts`(`name`, `id_no`, `email`, `password`, `number`, `status`) VALUES (?,?,?,?,?,?)");
            ps.setString(1, fullname);
            ps.setInt(2, id);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, "09123456789");  
            ps.setString(6, "USER"); 
            
             int rowsInserted = ps.executeUpdate(); 
              if (rowsInserted > 0) {
                  insert_account = true; 
                  //success
                 }
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Insert_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Insert_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
                    return insert_account;
}
    
    
    
    
            public boolean insert_history( 
                                    
                                   String Text, 
                                   String Email )
                                     { 
                    boolean insert_history = false;
                    
                    String text = String.valueOf(Text);   
                    String email= String.valueOf(Email); 
                    
                    
                    LocalDateTime now = LocalDateTime.now();
                     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String whole = now.format(formatter);
        
                    DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                    DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("hh:mm a");
                    String date = now.format(dateformat);
                    String time = now.format(timeformat);
                    System.out.println("Formatted Date and Time: " + date);
                    System.out.println("Formatted Date and Time: " + time);
                    
                    try {
            Class.forName("com.mysql.jdbc.Driver");
            con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
             ps= (PreparedStatement) con.prepareStatement("INSERT INTO `history`(`text`, `date`, `time`, `user`) VALUES (?,?,?,?)");
            ps.setString(1, text);
            ps.setString(2, date);
            ps.setString(3, time);
            ps.setString(4, email);  
            
             int rowsInserted = ps.executeUpdate(); 
              if (rowsInserted > 0) {
                  insert_history = true; 
                  //success
                 }
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Insert_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Insert_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
                    return insert_history;
}
    
    
    
    
    
    
    
    
}
