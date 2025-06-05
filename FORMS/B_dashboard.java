 
package FORMS;
 
import ACTION_TABLE.TableActionRender;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import ACTION_TABLE.TableActionCellEditor;
import ACTION_TABLE.TableActionEvent;
import FORMS_DIALOG.Auto_success;
import FORMS_DIALOG.Delete_Dialog;
import FORMS_DIALOG.Update_Dialog;
import FORMS_METHODS.Check_Database;
import FORMS_METHODS.Delete_Database;
import FORMS_METHODS.Get_Database;
import FORMS_METHODS.Insert_Database;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import FORMS_METHODS.Table_Methods;
import FORMS_METHODS.Update_Database;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import date.datechooser.DateChooser;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.EncryptionMethod;

public class B_dashboard extends javax.swing.JFrame {
  
    private DateChooser datechooser = new DateChooser();
    private DateChooser datechooser2 = new DateChooser();
    Table_Methods TM = new Table_Methods();
    Insert_Database ID = new Insert_Database();
    Get_Database GD = new Get_Database();
    Check_Database CD = new Check_Database();
    Update_Database UD = new Update_Database();
    Delete_Database DD = new Delete_Database();
    
    public B_dashboard() {
        initComponents();

        
        import_Methods();
        functions_Item();
        functions_Users();
        boolean_Methods();
        filter_date();
        total_item();
          
    }
       Connection con; 
        PreparedStatement pst, ps;
        ResultSet rs, rst;
    public void Connect() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver"); 
    }
        void filter_date() { 
        datechooser.setDateFormat(new SimpleDateFormat("MM-dd-yyyy"));
        datechooser2.setDateFormat(new SimpleDateFormat("MM-dd-yyyy"));
        
        datechooser.setTextField(edit_Date);
        datechooser2.setTextField(add_Date);

    }
 
    
    //ITEM TABLE FUNCTION
    
    private int Unit_number =0;
    void show_message(String meassage){
        Auto_success AS = new Auto_success(this);
        AS.showMessage(meassage);
    }
    
    void functions_Item(){ 
        TableActionEvent event=new TableActionEvent(){
            
            @Override
            public void onDelete(int row){ 
                
                TM.click_row_jlabel(table_Item, item_label, 1);
                TM.click_row_jlabel(table_Item, unit_label, 2);
                
                
              if(String.valueOf(profile_Status1.getText()).equals("USER")){
                  show_message("Only admin can delete item.");
              }else{
                  TM.click_row_jtext(table_Item, edit_Unit, 2);
                Unit_number = Integer.parseInt(String.valueOf(edit_Unit.getText())); 
               if(table_Item.isEditing()){
                   table_Item.getCellEditor().stopCellEditing();
                }
                delete_item();  
                }
              }
                

            
            @Override
            public void onEdit(int row) {
                if(String.valueOf(profile_Status1.getText()).equals("USER")){
                  show_message("Only admin can edit item.");
              }else{
                System.out.print("Edit row : "+row);
                TABB.setSelectedIndex(7);
                
                TM.click_row_jtext(table_Item, edit_Room, 0);
                TM.click_row_combobox(table_Item, edit_Equipment, 1);
                TM.click_row_jtext(table_Item, edit_Unit, 2);
                TM.click_row_jtext(table_Item, edit_Date, 3);
                TM.click_row_combobox(table_Item, edit_Status, 4);
                
                Unit_number = Integer.parseInt(String.valueOf(edit_Unit.getText()));
                System.out.print("the unit is: "+ Unit_number);
            }}
        }; 
        table_Item.getColumnModel().getColumn(5).setCellRenderer(new TableActionRender());
        table_Item.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        
    }
    
    void delay_delete_item(){
         Auto_success AS = new Auto_success(this);
              AS.showMessage("Item deleted successfully.");
              if(GD.get_item_for_admin(table_Item)){
                    if (table_Item.isEditing()) {
                        table_Item.getCellEditor().stopCellEditing();
                        }
                            table_Item.clearSelection();
                }
    }
    
    
    
    private Thread searchThread;
    void delete_item(){ 
        
        
        String editor= String.valueOf(profile_Email.getText()); 
        String item = String.valueOf(item_label.getText());
        String unit=  String.valueOf(unit_label.getText());
         
        Delete_Dialog ds = new Delete_Dialog(this);
        ds.showMessage("ARE YOU SURE YOU WANT TO DELETE THIS ITEM?");
        if (ds.getMessageType() == Delete_Dialog.MessageType.OK)
        {
            if(DD.delete_Item(Unit_number)){
               if(ID.insert_history("was deleted "+item+" Unit no. "+unit+" at", editor)){

                    }
       
                    if (searchThread != null && searchThread.isAlive()) {
                        searchThread.interrupt();
                    }
                    searchThread = new Thread(() -> {
                        try {
                            Thread.sleep(500);
                            SwingUtilities.invokeLater(this::delay_delete_item);
                        } catch (InterruptedException e) {
                        }
                    });
                    searchThread.start();
                        }
             
             
        }
    }
    
    
    //USER TABLE FUNCTION 
     private int User_ID = 0;
        void functions_Users(){ 
        Update_Dialog ud = new Update_Dialog(this);
        TableActionEvent event=new TableActionEvent(){
            @Override
            public void onDelete(int row){ 
                
                TM.click_row_jtext(table_User, edit_Unit, 2);
                TM.click_row_jlabel(table_User, user_label, 0);
                TM.click_row_jlabel(table_User, status_label, 4);
                String id = String.valueOf(edit_Unit.getText()); 
                
                
                
               if(String.valueOf(profile_Status1.getText()).equals("USER")){
                  show_message("Only admin can delete User.");
              }else{
                TM.click_row_jtext(table_User, edit_Unit, 2);
                User_ID = Integer.parseInt(String.valueOf(edit_Unit.getText())); 
               if(table_User.isEditing()){
                   table_User.getCellEditor().stopCellEditing();
               }
                delete_user();  
                } 
            }
            @Override
            public void onEdit(int row) { 
                
                if(String.valueOf(profile_Status1.getText()).equals("USER")){
                  show_message("Only admin can edit User status.");
              }else{
                
                TM.click_row_jtext(table_User, edit_Unit, 2);
                TM.click_row_jlabel(table_User, user_label, 0);
                TM.click_row_jlabel(table_User, status_label, 4);
                String id = String.valueOf(edit_Unit.getText()); 
                
                String editor= String.valueOf(profile_Email.getText());
                    
                 String user = String.valueOf(user_label.getText());
                String status= String.valueOf(status_label.getText());
                
                ud.showMessage("CHANGE USER’S STATUS", id, editor, user );
                if (ud.getMessageType() == Update_Dialog.MessageType.OK)
                {
                    edit_user_status();
                    
                   
                } 
            }}
        }; 
        table_User.getColumnModel().getColumn(5).setCellRenderer(new TableActionRender());
        table_User.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
         
    }
        void edit_user_status(){
            Auto_success  AS = new Auto_success(this); 
             if (searchThread != null && searchThread.isAlive()) {
                        searchThread.interrupt();
                    }
                    searchThread = new Thread(() -> {
                        try {
                            Thread.sleep(500);
                             AS.showMessage("User status update successful.");
                        } catch (InterruptedException e) {
                        }
                    });
                    searchThread.start();
                    
            if(GD.get_users(table_User)){
                if (table_User.isEditing()) {
                        table_User.getCellEditor().stopCellEditing();
                        }
                            table_User.clearSelection();
            }
                       
        }
        
        
        
        
        void delay_delete_user(){
         Auto_success AS = new Auto_success(this);
              AS.showMessage("User deleted successfully.");
              if(GD.get_users(table_User)){
                    if (table_User.isEditing()) {
                        table_User.getCellEditor().stopCellEditing();
                        }
                            table_User.clearSelection();
                }
    }
     
         
    void delete_user(){ 
        Delete_Dialog ds = new Delete_Dialog(this);
        String editor= String.valueOf(profile_Email.getText()); 
        String user = String.valueOf(user_label.getText()); 
        
        ds.showMessage("ARE YOU SURE YOU WANT TO DELETE THIS USER?");
        if (ds.getMessageType() == Delete_Dialog.MessageType.OK)
        {
            if(DD.delete_User(User_ID)){
                if(ID.insert_history("was deleted User "+user+" at", editor)){

                    }
       
                    if (searchThread != null && searchThread.isAlive()) {
                        searchThread.interrupt();
                    }
                    searchThread = new Thread(() -> {
                        try {
                            Thread.sleep(500);
                            SwingUtilities.invokeLater(this::delay_delete_user);
                        } catch (InterruptedException e) {
                        }
                    });
                    searchThread.start();
                        }
             
             
        }
    }
    
     
    
    void boolean_Methods(){
        sort_panel.setVisible(false);
        sort_panel_user.setVisible(false);
    }
 
    void import_Methods(){  
        
        table_Item.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table_User.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table_History.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        TM.table_align_center(table_Item, 0);
        TM.table_align_center(table_Item, 1);
        TM.table_align_center(table_Item, 2);
        TM.table_align_center(table_Item, 3);
        TM.table_align_center(table_Item, 4);
        
        TM.table_align_center(table_User, 0);
        TM.table_align_center(table_User, 1);
        TM.table_align_center(table_User, 2);
        TM.table_align_center(table_User, 3);
        TM.table_align_center(table_User, 4);
        
        TM.table_align_right(table_History, 1);
        
        
        add_Unit.setDocument(new number_only());
        edit_Unit.setDocument(new number_only());
        
        
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        TABB = new javax.swing.JTabbedPane();
        HOME_TAB = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        back_underline3 = new javax.swing.JPanel();
        back_underline2 = new javax.swing.JPanel();
        imageAvatar1 = new Swing.ImageAvatar();
        Head_status = new javax.swing.JLabel();
        Head_name = new javax.swing.JLabel();
        users_panel = new Custom.PanelRound();
        users_btn = new button.Button();
        dash_panel = new Custom.PanelRound();
        dash_btn = new button.Button();
        profile_panel = new Custom.PanelRound();
        profile_btn = new button.Button();
        history_panel = new Custom.PanelRound();
        history_btn = new button.Button();
        backup_panel = new Custom.PanelRound();
        backup_btn = new button.Button();
        jLabel5 = new javax.swing.JLabel();
        back_btn1 = new button.Button();
        DASHBOARD_TAB = new keeptoo.KGradientPanel();
        sort_panel = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        sort_Notworking = new button.Button();
        sort_Equipment = new button.Button();
        sort_Room = new button.Button();
        sort_Unit = new button.Button();
        sort_Date = new button.Button();
        sort_Working = new button.Button();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        back_btn = new button.Button();
        back_underline1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        panelRound1 = new Custom.PanelRound();
        panelRound2 = new Custom.PanelRound();
        jLabel15 = new javax.swing.JLabel();
        search_textfield = new javax.swing.JTextField();
        sort_btn = new button.Button();
        jPanel17 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane1 = new Scroll.ScrollWin_in_Register_Admin();
        table_Item = new table.table();
        button1 = new button.Button();
        button9 = new button.Button();
        item_label = new javax.swing.JLabel();
        unit_label = new javax.swing.JLabel();
        PROFILE_TAB = new keeptoo.KGradientPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        imageAvatar3 = new Swing.ImageAvatar();
        profile_Status1 = new javax.swing.JLabel();
        profile_Status = new javax.swing.JLabel();
        profile_Name1 = new javax.swing.JLabel();
        profile_Name = new javax.swing.JLabel();
        profile_Email = new javax.swing.JLabel();
        profile_ID = new javax.swing.JLabel();
        profile_Number = new javax.swing.JLabel();
        button6 = new button.Button();
        jPanel36 = new javax.swing.JPanel();
        back_btn8 = new button.Button();
        back_underline9 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        USER_TAB = new keeptoo.KGradientPanel();
        sort_panel_user = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        sort_Equipment1 = new button.Button();
        sort_Room1 = new button.Button();
        sort_Unit1 = new button.Button();
        sort_Date1 = new button.Button();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        back_btn6 = new button.Button();
        back_underline7 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        panelRound3 = new Custom.PanelRound();
        panelRound4 = new Custom.PanelRound();
        jLabel70 = new javax.swing.JLabel();
        search_user = new javax.swing.JTextField();
        sort_user_btn = new button.Button();
        jPanel42 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        jScrollPane2 = new Scroll.ScrollWin_in_Register_Admin();
        table_User = new table.table();
        status_label = new javax.swing.JLabel();
        user_label = new javax.swing.JLabel();
        HISTORY_TAB = new keeptoo.KGradientPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_History = new table.table();
        jPanel49 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        back_btn3 = new button.Button();
        back_underline4 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        BACKUP_TAB = new keeptoo.KGradientPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Back_up = new button.Button();
        Restore = new button.Button();
        jLabel25 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        back_btn4 = new button.Button();
        back_underline5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        ADD_ITEM = new keeptoo.KGradientPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        add_Date = new javax.swing.JTextField();
        add_Room = new javax.swing.JTextField();
        add_Unit = new javax.swing.JTextField();
        add_Status = new javax.swing.JComboBox<>();
        button2 = new button.Button();
        button3 = new button.Button();
        add_Equipment = new javax.swing.JComboBox<>();
        jPanel24 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        EDIT_ITEM = new keeptoo.KGradientPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        edit_Date = new javax.swing.JTextField();
        edit_Room = new javax.swing.JTextField();
        edit_Unit = new javax.swing.JTextField();
        edit_Status = new javax.swing.JComboBox<>();
        button4 = new button.Button();
        button5 = new button.Button();
        edit_Equipment = new javax.swing.JComboBox<>();
        jPanel32 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        EDIT_PROFILE = new keeptoo.KGradientPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        imageAvatar7 = new Swing.ImageAvatar();
        profile_update_Status1 = new javax.swing.JLabel();
        profile_update_Status = new javax.swing.JLabel();
        profile_update_Name1 = new javax.swing.JLabel();
        button7 = new button.Button();
        profile_update_Number = new javax.swing.JTextField();
        profile_update_Name = new javax.swing.JTextField();
        profile_update_Email = new javax.swing.JTextField();
        profile_update_ID = new javax.swing.JTextField();
        button8 = new button.Button();
        jPanel40 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        HISTORY_TAB1 = new keeptoo.KGradientPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jScrollPane4 = new Scroll.ScrollWin_in_Register_Admin();
        table_equipment_total = new table.table();
        jPanel52 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        back_btn5 = new button.Button();
        back_underline6 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TABB.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        HOME_TAB.setkEndColor(new java.awt.Color(15, 0, 0));
        HOME_TAB.setkGradientFocus(700);
        HOME_TAB.setkStartColor(new java.awt.Color(161, 0, 0));
        HOME_TAB.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back_underline3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(back_underline3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 82, 45, 3));

        back_underline2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(back_underline2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 82, 22, 3));

        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empty_avatar.png"))); // NOI18N
        jPanel1.add(imageAvatar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 100, 100));

        Head_status.setBackground(new java.awt.Color(0, 0, 0));
        Head_status.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        Head_status.setText("ADMIN");
        jPanel1.add(Head_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 350, 50));

        Head_name.setBackground(new java.awt.Color(0, 0, 0));
        Head_name.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        Head_name.setText("Heeseung Lee");
        jPanel1.add(Head_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 860, 50));

        users_panel.setBackground(new java.awt.Color(255, 255, 255));
        users_panel.setRoundBottomLeft(70);
        users_panel.setRoundBottomRight(70);
        users_panel.setRoundTopLeft(70);
        users_panel.setRoundTopRight(70);
        users_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        users_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/button/users.png"))); // NOI18N
        users_btn.setEffectColor(new java.awt.Color(0, 0, 0));
        users_btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        users_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                users_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                users_btnMouseExited(evt);
            }
        });
        users_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                users_btnActionPerformed(evt);
            }
        });
        users_panel.add(users_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 260));

        jPanel1.add(users_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, 405, 280));

        dash_panel.setBackground(new java.awt.Color(255, 255, 255));
        dash_panel.setRoundBottomLeft(70);
        dash_panel.setRoundBottomRight(70);
        dash_panel.setRoundTopLeft(70);
        dash_panel.setRoundTopRight(70);
        dash_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dash_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/button/dashboard.png"))); // NOI18N
        dash_btn.setEffectColor(new java.awt.Color(0, 0, 0));
        dash_btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dash_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dash_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dash_btnMouseExited(evt);
            }
        });
        dash_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dash_btnActionPerformed(evt);
            }
        });
        dash_panel.add(dash_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 260));

        jPanel1.add(dash_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 405, 280));

        profile_panel.setBackground(new java.awt.Color(255, 255, 255));
        profile_panel.setRoundBottomLeft(70);
        profile_panel.setRoundBottomRight(70);
        profile_panel.setRoundTopLeft(70);
        profile_panel.setRoundTopRight(70);
        profile_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profile_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/button/profile.png"))); // NOI18N
        profile_btn.setEffectColor(new java.awt.Color(0, 0, 0));
        profile_btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        profile_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profile_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profile_btnMouseExited(evt);
            }
        });
        profile_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profile_btnActionPerformed(evt);
            }
        });
        profile_panel.add(profile_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 260));

        jPanel1.add(profile_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 405, 280));

        history_panel.setBackground(new java.awt.Color(255, 255, 255));
        history_panel.setRoundBottomLeft(70);
        history_panel.setRoundBottomRight(70);
        history_panel.setRoundTopLeft(70);
        history_panel.setRoundTopRight(70);
        history_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        history_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/button/history.png"))); // NOI18N
        history_btn.setEffectColor(new java.awt.Color(0, 0, 0));
        history_btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        history_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                history_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                history_btnMouseExited(evt);
            }
        });
        history_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                history_btnActionPerformed(evt);
            }
        });
        history_panel.add(history_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 260));

        jPanel1.add(history_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 405, 280));

        backup_panel.setBackground(new java.awt.Color(255, 255, 255));
        backup_panel.setRoundBottomLeft(70);
        backup_panel.setRoundBottomRight(70);
        backup_panel.setRoundTopLeft(70);
        backup_panel.setRoundTopRight(70);
        backup_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backup_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/button/backup&reset.png"))); // NOI18N
        backup_btn.setEffectColor(new java.awt.Color(0, 0, 0));
        backup_btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backup_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backup_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backup_btnMouseExited(evt);
            }
        });
        backup_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backup_btnActionPerformed(evt);
            }
        });
        backup_panel.add(backup_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 260));

        jPanel1.add(backup_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, 405, 280));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/bottle_white.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 500, 190, 260));

        back_btn1.setText("Log out");
        back_btn1.setToolTipText("");
        back_btn1.setFocusable(false);
        back_btn1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        back_btn1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        back_btn1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        back_btn1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        back_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btn1ActionPerformed(evt);
            }
        });
        jPanel1.add(back_btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 50, 100, 40));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.insets = new java.awt.Insets(-50, -50, 0, 0);
        HOME_TAB.add(jPanel1, gridBagConstraints);

        TABB.addTab("home", HOME_TAB);

        DASHBOARD_TAB.setkEndColor(new java.awt.Color(15, 0, 0));
        DASHBOARD_TAB.setkGradientFocus(700);
        DASHBOARD_TAB.setkStartColor(new java.awt.Color(161, 0, 0));
        DASHBOARD_TAB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sort_panel.setBackground(new java.awt.Color(217, 217, 217));
        sort_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setBackground(new java.awt.Color(214, 103, 103));
        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("SORT BY:");
        sort_panel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 20));

        sort_Notworking.setBackground(new java.awt.Color(217, 217, 217));
        sort_Notworking.setText("  Status (Not Working)");
        sort_Notworking.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sort_Notworking.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        sort_Notworking.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        sort_Notworking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sort_NotworkingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sort_NotworkingMouseExited(evt);
            }
        });
        sort_Notworking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_NotworkingActionPerformed(evt);
            }
        });
        sort_panel.add(sort_Notworking, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 230, 30));

        sort_Equipment.setBackground(new java.awt.Color(217, 217, 217));
        sort_Equipment.setText("  Equipment (A-Z)");
        sort_Equipment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sort_Equipment.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        sort_Equipment.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        sort_Equipment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sort_EquipmentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sort_EquipmentMouseExited(evt);
            }
        });
        sort_Equipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_EquipmentActionPerformed(evt);
            }
        });
        sort_panel.add(sort_Equipment, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 230, 30));

        sort_Room.setBackground(new java.awt.Color(217, 217, 217));
        sort_Room.setText("  Room (A-Z)");
        sort_Room.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sort_Room.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        sort_Room.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        sort_Room.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sort_RoomMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sort_RoomMouseExited(evt);
            }
        });
        sort_Room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_RoomActionPerformed(evt);
            }
        });
        sort_panel.add(sort_Room, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 230, 30));

        sort_Unit.setBackground(new java.awt.Color(217, 217, 217));
        sort_Unit.setText("  Unit No.");
        sort_Unit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sort_Unit.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        sort_Unit.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        sort_Unit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sort_UnitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sort_UnitMouseExited(evt);
            }
        });
        sort_Unit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_UnitActionPerformed(evt);
            }
        });
        sort_panel.add(sort_Unit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 230, 30));

        sort_Date.setBackground(new java.awt.Color(217, 217, 217));
        sort_Date.setText("  Date Received ");
        sort_Date.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sort_Date.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        sort_Date.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        sort_Date.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sort_DateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sort_DateMouseExited(evt);
            }
        });
        sort_Date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_DateActionPerformed(evt);
            }
        });
        sort_panel.add(sort_Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 230, 30));

        sort_Working.setBackground(new java.awt.Color(217, 217, 217));
        sort_Working.setText("  Status (Working) ");
        sort_Working.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sort_Working.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        sort_Working.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        sort_Working.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sort_WorkingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sort_WorkingMouseExited(evt);
            }
        });
        sort_Working.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_WorkingActionPerformed(evt);
            }
        });
        sort_panel.add(sort_Working, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 230, 30));

        DASHBOARD_TAB.add(sort_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 230, 220));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        DASHBOARD_TAB.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1550, 5));

        jPanel7.setBackground(new java.awt.Color(150, 0, 0));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back_btn.setBackground(new java.awt.Color(150, 0, 0));
        back_btn.setForeground(new java.awt.Color(255, 255, 255));
        back_btn.setText("Back");
        back_btn.setToolTipText("");
        back_btn.setFocusable(false);
        back_btn.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        back_btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        back_btn.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        back_btn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });
        jPanel7.add(back_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 20, 50, 30));

        back_underline1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.add(back_underline1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 50, 50, 3));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/bottle_mini.png"))); // NOI18N
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 100));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 50)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("INVENTORY LIST");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 520, 60));

        DASHBOARD_TAB.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1560, 110));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(0, 0, 0));
        panelRound1.setRoundBottomLeft(55);
        panelRound1.setRoundBottomRight(55);
        panelRound1.setRoundTopLeft(55);
        panelRound1.setRoundTopRight(55);
        panelRound1.setLayout(new java.awt.GridBagLayout());

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(50);
        panelRound2.setRoundBottomRight(50);
        panelRound2.setRoundTopLeft(50);
        panelRound2.setRoundTopRight(50);
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-magnifying-glass-25.png"))); // NOI18N
        panelRound2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 30, 40));

        search_textfield.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        search_textfield.setForeground(new java.awt.Color(110, 0, 2));
        search_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        search_textfield.setCaretColor(new java.awt.Color(110, 0, 2));
        search_textfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_textfieldMouseClicked(evt);
            }
        });
        search_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_textfieldActionPerformed(evt);
            }
        });
        search_textfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_textfieldKeyReleased(evt);
            }
        });
        panelRound2.add(search_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, 320, 30));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.insets = new java.awt.Insets(13, 13, 13, 13);
        panelRound1.add(panelRound2, gridBagConstraints);

        jPanel8.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 380, 44));

        sort_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-funnel-30.png"))); // NOI18N
        sort_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_btnActionPerformed(evt);
            }
        });
        jPanel8.add(sort_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 30, 30));

        jPanel17.setBackground(new java.awt.Color(214, 103, 103));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel17.setLayout(new java.awt.GridBagLayout());

        jLabel26.setBackground(new java.awt.Color(214, 103, 103));
        jLabel26.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("NO.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel17.add(jLabel26, gridBagConstraints);

        jLabel27.setBackground(new java.awt.Color(214, 103, 103));
        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("UNIT");
        jPanel17.add(jLabel27, new java.awt.GridBagConstraints());

        jPanel8.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 170, 100));

        jPanel18.setBackground(new java.awt.Color(214, 103, 103));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel18.setLayout(new java.awt.GridBagLayout());

        jLabel14.setBackground(new java.awt.Color(214, 103, 103));
        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("ASSIGNED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel18.add(jLabel14, gridBagConstraints);

        jLabel17.setBackground(new java.awt.Color(214, 103, 103));
        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("ROOM");
        jPanel18.add(jLabel17, new java.awt.GridBagConstraints());

        jPanel8.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 240, 100));

        jPanel19.setBackground(new java.awt.Color(214, 103, 103));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel19.setLayout(new java.awt.GridBagLayout());

        jLabel18.setBackground(new java.awt.Color(214, 103, 103));
        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("STATUS");
        jPanel19.add(jLabel18, new java.awt.GridBagConstraints());

        jPanel8.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 70, 550, 100));

        jPanel22.setBackground(new java.awt.Color(214, 103, 103));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel22.setLayout(new java.awt.GridBagLayout());

        jLabel28.setBackground(new java.awt.Color(214, 103, 103));
        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("EQUIPMENT ");
        jPanel22.add(jLabel28, new java.awt.GridBagConstraints());

        jPanel8.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 280, 100));

        jPanel20.setBackground(new java.awt.Color(214, 103, 103));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel20.setLayout(new java.awt.GridBagLayout());

        jLabel29.setBackground(new java.awt.Color(214, 103, 103));
        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("DATE RECEIVED");
        jPanel20.add(jLabel29, new java.awt.GridBagConstraints());

        jPanel8.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 70, 250, 100));

        jPanel21.setBackground(new java.awt.Color(153, 153, 153));
        jPanel21.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jScrollPane1.setFocusable(false);

        table_Item.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        table_Item.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ROOM ASSIGNED", "EQUIPMENT", "UNIT NO.", "DATE RECEICE", "STATUS", "BUTTON"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Item.setFocusable(false);
        table_Item.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        table_Item.setGridColor(new java.awt.Color(0, 0, 0));
        table_Item.setRowHeight(100);
        table_Item.setSelectionBackground(new java.awt.Color(102, 102, 102));
        table_Item.setSelectionForeground(new java.awt.Color(255, 255, 255));
        table_Item.setShowGrid(true);
        table_Item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ItemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_Item);
        if (table_Item.getColumnModel().getColumnCount() > 0) {
            table_Item.getColumnModel().getColumn(0).setMinWidth(233);
            table_Item.getColumnModel().getColumn(0).setPreferredWidth(233);
            table_Item.getColumnModel().getColumn(0).setMaxWidth(233);
            table_Item.getColumnModel().getColumn(1).setMinWidth(265);
            table_Item.getColumnModel().getColumn(1).setPreferredWidth(265);
            table_Item.getColumnModel().getColumn(1).setMaxWidth(265);
            table_Item.getColumnModel().getColumn(2).setMinWidth(167);
            table_Item.getColumnModel().getColumn(2).setPreferredWidth(167);
            table_Item.getColumnModel().getColumn(2).setMaxWidth(167);
            table_Item.getColumnModel().getColumn(3).setMinWidth(235);
            table_Item.getColumnModel().getColumn(3).setPreferredWidth(235);
            table_Item.getColumnModel().getColumn(3).setMaxWidth(235);
            table_Item.getColumnModel().getColumn(5).setMinWidth(300);
            table_Item.getColumnModel().getColumn(5).setPreferredWidth(300);
            table_Item.getColumnModel().getColumn(5).setMaxWidth(300);
        }

        jPanel21.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1450, 600));

        jPanel8.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 1450, 560));

        button1.setBackground(new java.awt.Color(110, 0, 2));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-plus-35.png"))); // NOI18N
        button1.setText("ADD ITEM");
        button1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        button1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel8.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 30, 110, 30));

        button9.setBackground(new java.awt.Color(110, 0, 2));
        button9.setForeground(new java.awt.Color(255, 255, 255));
        button9.setText("VIEW TOTAL");
        button9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });
        jPanel8.add(button9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 30, 110, 30));

        item_label.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.add(item_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, 150, 20));

        unit_label.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.add(unit_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 40, 150, 20));

        DASHBOARD_TAB.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1550, 770));

        TABB.addTab("dashboard", DASHBOARD_TAB);

        PROFILE_TAB.setkEndColor(new java.awt.Color(15, 0, 0));
        PROFILE_TAB.setkGradientFocus(700);
        PROFILE_TAB.setkStartColor(new java.awt.Color(161, 0, 0));
        PROFILE_TAB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setLayout(new java.awt.GridBagLayout());

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel35.setForeground(new java.awt.Color(255, 255, 255));
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel49.setBackground(new java.awt.Color(0, 0, 0));
        jLabel49.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("Status:");
        jPanel35.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 180, 40));

        jLabel50.setBackground(new java.awt.Color(0, 0, 0));
        jLabel50.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setText("Full Name:");
        jPanel35.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 180, 40));

        jLabel51.setBackground(new java.awt.Color(0, 0, 0));
        jLabel51.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("Email:");
        jPanel35.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 180, 40));

        jLabel52.setBackground(new java.awt.Color(0, 0, 0));
        jLabel52.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel52.setText("ID No.:");
        jPanel35.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 180, 40));

        jLabel53.setBackground(new java.awt.Color(0, 0, 0));
        jLabel53.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel53.setText("Phone Number:");
        jPanel35.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 180, 40));

        imageAvatar3.setBorderSize(2);
        imageAvatar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/avatar.png"))); // NOI18N
        jPanel35.add(imageAvatar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 130, 120));

        profile_Status1.setBackground(new java.awt.Color(0, 0, 0));
        profile_Status1.setFont(new java.awt.Font("Century Gothic", 2, 14)); // NOI18N
        profile_Status1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        profile_Status1.setText("ADMIN");
        jPanel35.add(profile_Status1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 640, 30));

        profile_Status.setBackground(new java.awt.Color(0, 0, 0));
        profile_Status.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        profile_Status.setText("ADMIN");
        jPanel35.add(profile_Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 550, 40));

        profile_Name1.setBackground(new java.awt.Color(0, 0, 0));
        profile_Name1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        profile_Name1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        profile_Name1.setText("Heeseung Lee");
        jPanel35.add(profile_Name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 640, 40));

        profile_Name.setBackground(new java.awt.Color(0, 0, 0));
        profile_Name.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        profile_Name.setText("Heeseung Lee");
        jPanel35.add(profile_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 550, 40));

        profile_Email.setBackground(new java.awt.Color(0, 0, 0));
        profile_Email.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        profile_Email.setText("hee@gmial.com");
        jPanel35.add(profile_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 550, 40));

        profile_ID.setBackground(new java.awt.Color(0, 0, 0));
        profile_ID.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        profile_ID.setText("126");
        jPanel35.add(profile_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 550, 40));

        profile_Number.setBackground(new java.awt.Color(0, 0, 0));
        profile_Number.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        profile_Number.setText("09091234567");
        jPanel35.add(profile_Number, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, 550, 40));

        button6.setBackground(new java.awt.Color(110, 0, 2));
        button6.setForeground(new java.awt.Color(255, 255, 255));
        button6.setText("EDIT");
        button6.setFocusable(false);
        button6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });
        jPanel35.add(button6, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 420, 120, 40));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 27;
        jPanel34.add(jPanel35, gridBagConstraints);

        PROFILE_TAB.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1530, 770));

        jPanel36.setBackground(new java.awt.Color(150, 0, 0));
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back_btn8.setBackground(new java.awt.Color(150, 0, 0));
        back_btn8.setForeground(new java.awt.Color(255, 255, 255));
        back_btn8.setText("Back");
        back_btn8.setToolTipText("");
        back_btn8.setFocusable(false);
        back_btn8.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        back_btn8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        back_btn8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        back_btn8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        back_btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btn8ActionPerformed(evt);
            }
        });
        jPanel36.add(back_btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 20, 50, 30));

        back_underline9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.add(back_underline9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 50, 50, 3));

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/bottle_mini.png"))); // NOI18N
        jPanel36.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 100));

        jLabel56.setBackground(new java.awt.Color(255, 255, 255));
        jLabel56.setFont(new java.awt.Font("SansSerif", 1, 50)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("PROFILE");
        jPanel36.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 570, 60));

        PROFILE_TAB.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1560, 110));

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        PROFILE_TAB.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1560, 5));

        TABB.addTab("profile", PROFILE_TAB);

        USER_TAB.setkEndColor(new java.awt.Color(15, 0, 0));
        USER_TAB.setkGradientFocus(700);
        USER_TAB.setkStartColor(new java.awt.Color(161, 0, 0));
        USER_TAB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sort_panel_user.setBackground(new java.awt.Color(217, 217, 217));
        sort_panel_user.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel67.setBackground(new java.awt.Color(214, 103, 103));
        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("SORT BY:");
        sort_panel_user.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 20));

        sort_Equipment1.setBackground(new java.awt.Color(217, 217, 217));
        sort_Equipment1.setText("  Name (A-Z)");
        sort_Equipment1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sort_Equipment1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        sort_Equipment1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        sort_Equipment1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sort_Equipment1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sort_Equipment1MouseExited(evt);
            }
        });
        sort_Equipment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_Equipment1ActionPerformed(evt);
            }
        });
        sort_panel_user.add(sort_Equipment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 230, 30));

        sort_Room1.setBackground(new java.awt.Color(217, 217, 217));
        sort_Room1.setText("  ID No.");
        sort_Room1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sort_Room1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        sort_Room1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        sort_Room1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sort_Room1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sort_Room1MouseExited(evt);
            }
        });
        sort_Room1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_Room1ActionPerformed(evt);
            }
        });
        sort_panel_user.add(sort_Room1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 230, 30));

        sort_Unit1.setBackground(new java.awt.Color(217, 217, 217));
        sort_Unit1.setText("  User");
        sort_Unit1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sort_Unit1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        sort_Unit1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        sort_Unit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sort_Unit1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sort_Unit1MouseExited(evt);
            }
        });
        sort_Unit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_Unit1ActionPerformed(evt);
            }
        });
        sort_panel_user.add(sort_Unit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 230, 30));

        sort_Date1.setBackground(new java.awt.Color(217, 217, 217));
        sort_Date1.setText("  Admin ");
        sort_Date1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sort_Date1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        sort_Date1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        sort_Date1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sort_Date1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sort_Date1MouseExited(evt);
            }
        });
        sort_Date1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_Date1ActionPerformed(evt);
            }
        });
        sort_panel_user.add(sort_Date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 230, 30));

        USER_TAB.add(sort_panel_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 230, 160));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        USER_TAB.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1550, 5));

        jPanel27.setBackground(new java.awt.Color(150, 0, 0));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back_btn6.setBackground(new java.awt.Color(150, 0, 0));
        back_btn6.setForeground(new java.awt.Color(255, 255, 255));
        back_btn6.setText("Back");
        back_btn6.setToolTipText("");
        back_btn6.setFocusable(false);
        back_btn6.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        back_btn6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        back_btn6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        back_btn6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        back_btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btn6ActionPerformed(evt);
            }
        });
        jPanel27.add(back_btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 20, 50, 30));

        back_underline7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.add(back_underline7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 50, 50, 3));

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/bottle_mini.png"))); // NOI18N
        jPanel27.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 100));

        jLabel69.setBackground(new java.awt.Color(255, 255, 255));
        jLabel69.setFont(new java.awt.Font("SansSerif", 1, 50)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("LIST OF USERS");
        jPanel27.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 520, 60));

        USER_TAB.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1560, 110));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound3.setBackground(new java.awt.Color(0, 0, 0));
        panelRound3.setRoundBottomLeft(55);
        panelRound3.setRoundBottomRight(55);
        panelRound3.setRoundTopLeft(55);
        panelRound3.setRoundTopRight(55);
        panelRound3.setLayout(new java.awt.GridBagLayout());

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setRoundBottomLeft(50);
        panelRound4.setRoundBottomRight(50);
        panelRound4.setRoundTopLeft(50);
        panelRound4.setRoundTopRight(50);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-magnifying-glass-25.png"))); // NOI18N
        panelRound4.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 30, 40));

        search_user.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        search_user.setForeground(new java.awt.Color(110, 0, 2));
        search_user.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        search_user.setCaretColor(new java.awt.Color(110, 0, 2));
        search_user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_userKeyReleased(evt);
            }
        });
        panelRound4.add(search_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, 320, 30));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.insets = new java.awt.Insets(13, 13, 13, 13);
        panelRound3.add(panelRound4, gridBagConstraints);

        jPanel28.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 380, 44));

        sort_user_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-funnel-30.png"))); // NOI18N
        sort_user_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort_user_btnActionPerformed(evt);
            }
        });
        jPanel28.add(sort_user_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 30, 30));

        jPanel42.setBackground(new java.awt.Color(214, 103, 103));
        jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel42.setLayout(new java.awt.GridBagLayout());

        jLabel74.setBackground(new java.awt.Color(214, 103, 103));
        jLabel74.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("ID NO.");
        jPanel42.add(jLabel74, new java.awt.GridBagConstraints());

        jPanel28.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 175, 100));

        jPanel43.setBackground(new java.awt.Color(214, 103, 103));
        jPanel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel43.setLayout(new java.awt.GridBagLayout());

        jLabel76.setBackground(new java.awt.Color(214, 103, 103));
        jLabel76.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("FULL NAME");
        jPanel43.add(jLabel76, new java.awt.GridBagConstraints());

        jPanel28.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 250, 100));

        jPanel44.setBackground(new java.awt.Color(214, 103, 103));
        jPanel44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel44.setLayout(new java.awt.GridBagLayout());

        jLabel77.setBackground(new java.awt.Color(214, 103, 103));
        jLabel77.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("STATUS");
        jPanel44.add(jLabel77, new java.awt.GridBagConstraints());

        jPanel28.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, 510, 100));

        jPanel45.setBackground(new java.awt.Color(214, 103, 103));
        jPanel45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel45.setLayout(new java.awt.GridBagLayout());

        jLabel78.setBackground(new java.awt.Color(214, 103, 103));
        jLabel78.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("EMAIL");
        jPanel45.add(jLabel78, new java.awt.GridBagConstraints());

        jPanel28.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 330, 100));

        jPanel46.setBackground(new java.awt.Color(214, 103, 103));
        jPanel46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel46.setLayout(new java.awt.GridBagLayout());

        jLabel79.setBackground(new java.awt.Color(214, 103, 103));
        jLabel79.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("PHONE NO.");
        jPanel46.add(jLabel79, new java.awt.GridBagConstraints());

        jPanel28.add(jPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 240, 100));

        jPanel47.setBackground(new java.awt.Color(153, 153, 153));
        jPanel47.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel47.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jScrollPane2.setFocusable(false);

        table_User.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        table_User.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Email", "ID", "Number", "STATUS", "BUTTON"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_User.setFocusable(false);
        table_User.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        table_User.setGridColor(new java.awt.Color(0, 0, 0));
        table_User.setRowHeight(100);
        table_User.setSelectionBackground(new java.awt.Color(102, 102, 102));
        table_User.setSelectionForeground(new java.awt.Color(255, 255, 255));
        table_User.setShowGrid(true);
        jScrollPane2.setViewportView(table_User);
        if (table_User.getColumnModel().getColumnCount() > 0) {
            table_User.getColumnModel().getColumn(0).setMinWidth(242);
            table_User.getColumnModel().getColumn(0).setPreferredWidth(242);
            table_User.getColumnModel().getColumn(0).setMaxWidth(242);
            table_User.getColumnModel().getColumn(1).setMinWidth(305);
            table_User.getColumnModel().getColumn(1).setPreferredWidth(305);
            table_User.getColumnModel().getColumn(1).setMaxWidth(305);
            table_User.getColumnModel().getColumn(2).setMinWidth(170);
            table_User.getColumnModel().getColumn(2).setPreferredWidth(170);
            table_User.getColumnModel().getColumn(2).setMaxWidth(170);
            table_User.getColumnModel().getColumn(3).setMinWidth(220);
            table_User.getColumnModel().getColumn(3).setPreferredWidth(220);
            table_User.getColumnModel().getColumn(3).setMaxWidth(220);
            table_User.getColumnModel().getColumn(4).setMinWidth(270);
            table_User.getColumnModel().getColumn(4).setPreferredWidth(270);
            table_User.getColumnModel().getColumn(4).setMaxWidth(270);
        }

        jPanel47.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1450, 600));

        jPanel28.add(jPanel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 1450, 560));

        status_label.setForeground(new java.awt.Color(255, 255, 255));
        jPanel28.add(status_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 40, 150, 20));

        user_label.setForeground(new java.awt.Color(255, 255, 255));
        jPanel28.add(user_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, 150, 20));

        USER_TAB.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1550, 770));

        TABB.addTab("user", USER_TAB);

        HISTORY_TAB.setkEndColor(new java.awt.Color(15, 0, 0));
        HISTORY_TAB.setkGradientFocus(700);
        HISTORY_TAB.setkStartColor(new java.awt.Color(161, 0, 0));
        HISTORY_TAB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel48.setBackground(new java.awt.Color(153, 153, 153));
        jPanel48.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel48.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jScrollPane5.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        table_History.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_History.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        table_History.setRowHeight(100);
        table_History.setShowGrid(true);
        jScrollPane5.setViewportView(table_History);
        if (table_History.getColumnModel().getColumnCount() > 0) {
            table_History.getColumnModel().getColumn(0).setMinWidth(1140);
            table_History.getColumnModel().getColumn(0).setPreferredWidth(1140);
            table_History.getColumnModel().getColumn(0).setMaxWidth(1140);
        }

        jPanel48.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 1450, 610));

        jPanel5.add(jPanel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 1450, 560));

        jPanel49.setBackground(new java.awt.Color(214, 103, 103));
        jPanel49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel49.setLayout(new java.awt.GridBagLayout());

        jLabel80.setBackground(new java.awt.Color(214, 103, 103));
        jLabel80.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("USER");
        jPanel49.add(jLabel80, new java.awt.GridBagConstraints());

        jPanel5.add(jPanel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 70, 310, 100));

        jPanel50.setBackground(new java.awt.Color(214, 103, 103));
        jPanel50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel50.setLayout(new java.awt.GridBagLayout());

        jLabel81.setBackground(new java.awt.Color(214, 103, 103));
        jLabel81.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("ACTIVITY");
        jPanel50.add(jLabel81, new java.awt.GridBagConstraints());

        jPanel5.add(jPanel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 1160, 100));

        HISTORY_TAB.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1550, 770));

        jPanel13.setBackground(new java.awt.Color(150, 0, 0));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back_btn3.setBackground(new java.awt.Color(150, 0, 0));
        back_btn3.setForeground(new java.awt.Color(255, 255, 255));
        back_btn3.setText("Back");
        back_btn3.setToolTipText("");
        back_btn3.setFocusable(false);
        back_btn3.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        back_btn3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        back_btn3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        back_btn3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        back_btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btn3ActionPerformed(evt);
            }
        });
        jPanel13.add(back_btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 20, 50, 30));

        back_underline4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.add(back_underline4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 50, 50, 3));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/bottle_mini.png"))); // NOI18N
        jPanel13.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 100));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 50)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("HISTORY");
        jPanel13.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 520, 60));

        HISTORY_TAB.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1550, 110));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        HISTORY_TAB.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1550, 5));

        TABB.addTab("history", HISTORY_TAB);

        BACKUP_TAB.setkEndColor(new java.awt.Color(15, 0, 0));
        BACKUP_TAB.setkGradientFocus(700);
        BACKUP_TAB.setkStartColor(new java.awt.Color(161, 0, 0));
        BACKUP_TAB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        Back_up.setBackground(new java.awt.Color(0, 153, 204));
        Back_up.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        Back_up.setForeground(new java.awt.Color(255, 255, 255));
        Back_up.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-upload-64 (1).png"))); // NOI18N
        Back_up.setText(" BACKUP");
        Back_up.setFocusable(false);
        Back_up.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        Back_up.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Back_up.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Back_upMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Back_upMouseExited(evt);
            }
        });
        Back_up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_upActionPerformed(evt);
            }
        });

        Restore.setBackground(new java.awt.Color(245, 10, 61));
        Restore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        Restore.setForeground(new java.awt.Color(255, 255, 255));
        Restore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-download-64.png"))); // NOI18N
        Restore.setText("  RESTORE");
        Restore.setFocusable(false);
        Restore.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        Restore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Restore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RestoreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RestoreMouseExited(evt);
            }
        });
        Restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestoreActionPerformed(evt);
            }
        });

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("SansSerif", 2, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Backup prevents the lost of your data.");

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("SansSerif", 2, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("Restoring your data is effecient to recover unwanted deleted items or accounts.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(Back_up, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50)
                .addComponent(Restore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(180, 180, 180))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Back_up, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(Restore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(70, 70, 70)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(300, 300, 300))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(200, 200, 200))
        );

        BACKUP_TAB.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1540, 770));

        jPanel15.setBackground(new java.awt.Color(150, 0, 0));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back_btn4.setBackground(new java.awt.Color(150, 0, 0));
        back_btn4.setForeground(new java.awt.Color(255, 255, 255));
        back_btn4.setText("Back");
        back_btn4.setToolTipText("");
        back_btn4.setFocusable(false);
        back_btn4.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        back_btn4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        back_btn4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        back_btn4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        back_btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btn4ActionPerformed(evt);
            }
        });
        jPanel15.add(back_btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 20, 50, 30));

        back_underline5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.add(back_underline5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 50, 50, 3));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/bottle_mini.png"))); // NOI18N
        jPanel15.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 100));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 50)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("BACK AND RESTORE");
        jPanel15.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 570, 60));

        BACKUP_TAB.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1560, 110));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        BACKUP_TAB.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1560, 5));

        TABB.addTab("backup", BACKUP_TAB);

        ADD_ITEM.setkEndColor(new java.awt.Color(15, 0, 0));
        ADD_ITEM.setkGradientFocus(700);
        ADD_ITEM.setkStartColor(new java.awt.Color(161, 0, 0));
        ADD_ITEM.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(new java.awt.GridBagLayout());

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setBackground(new java.awt.Color(0, 0, 0));
        jLabel34.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Status:");
        jPanel29.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 230, 40));

        jLabel38.setBackground(new java.awt.Color(0, 0, 0));
        jLabel38.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Room Assigned:");
        jPanel29.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 230, 40));

        jLabel39.setBackground(new java.awt.Color(0, 0, 0));
        jLabel39.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("Equipment:");
        jPanel29.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 230, 40));

        jLabel40.setBackground(new java.awt.Color(0, 0, 0));
        jLabel40.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Unit No.:");
        jPanel29.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 230, 40));

        jLabel41.setBackground(new java.awt.Color(0, 0, 0));
        jLabel41.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Date Reveived:");
        jPanel29.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 230, 40));

        add_Date.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        add_Date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        add_Date.setCaretColor(new java.awt.Color(110, 0, 2));
        jPanel29.add(add_Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 190, 40));

        add_Room.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        add_Room.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        add_Room.setCaretColor(new java.awt.Color(110, 0, 2));
        jPanel29.add(add_Room, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 350, 40));

        add_Unit.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        add_Unit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        add_Unit.setCaretColor(new java.awt.Color(110, 0, 2));
        jPanel29.add(add_Unit, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 190, 40));

        add_Status.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        add_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Working", "Not Working" }));
        add_Status.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel29.add(add_Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 350, 40));

        button2.setBackground(new java.awt.Color(110, 0, 2));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("BACK");
        button2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel29.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 120, 30));

        button3.setBackground(new java.awt.Color(110, 0, 2));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("ADD");
        button3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        jPanel29.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 120, 30));

        add_Equipment.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        add_Equipment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aircon", "CD/DVD Drive", "CPU", "Fan", "Headphones", "Keyboard", "Laptop", "Monitor", "Motherboard", "Mouse", "Network Cable", "Power Supply", "Printer", "Projector", "RAM", "Scanner", "Speakers", "Surge Protector", "UPS", "Webcam", "WiFi Router" }));
        add_Equipment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel29.add(add_Equipment, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 350, 40));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 22;
        gridBagConstraints.gridheight = 22;
        gridBagConstraints.ipadx = 240;
        gridBagConstraints.ipady = 82;
        jPanel23.add(jPanel29, gridBagConstraints);

        ADD_ITEM.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1530, 780));

        jPanel24.setBackground(new java.awt.Color(150, 0, 0));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/bottle_mini.png"))); // NOI18N
        jPanel24.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 100));

        jLabel37.setBackground(new java.awt.Color(255, 255, 255));
        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 50)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("ADD ITEM");
        jPanel24.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 570, 60));

        ADD_ITEM.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1560, 110));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        ADD_ITEM.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1560, 5));

        TABB.addTab("add_item", ADD_ITEM);

        EDIT_ITEM.setkEndColor(new java.awt.Color(15, 0, 0));
        EDIT_ITEM.setkGradientFocus(700);
        EDIT_ITEM.setkStartColor(new java.awt.Color(161, 0, 0));
        EDIT_ITEM.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setLayout(new java.awt.GridBagLayout());

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setBackground(new java.awt.Color(0, 0, 0));
        jLabel42.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("Status:");
        jPanel31.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 230, 40));

        jLabel43.setBackground(new java.awt.Color(0, 0, 0));
        jLabel43.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Room Assigned:");
        jPanel31.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 230, 40));

        jLabel44.setBackground(new java.awt.Color(0, 0, 0));
        jLabel44.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("Equipment:");
        jPanel31.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 230, 40));

        jLabel45.setBackground(new java.awt.Color(0, 0, 0));
        jLabel45.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("Unit No.:");
        jPanel31.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 230, 40));

        jLabel46.setBackground(new java.awt.Color(0, 0, 0));
        jLabel46.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel46.setText("Date Reveived:");
        jPanel31.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 230, 40));

        edit_Date.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        edit_Date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        edit_Date.setCaretColor(new java.awt.Color(110, 0, 2));
        jPanel31.add(edit_Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 190, 40));

        edit_Room.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        edit_Room.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        edit_Room.setCaretColor(new java.awt.Color(110, 0, 2));
        jPanel31.add(edit_Room, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 350, 40));

        edit_Unit.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        edit_Unit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        edit_Unit.setCaretColor(new java.awt.Color(110, 0, 2));
        jPanel31.add(edit_Unit, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 190, 40));

        edit_Status.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        edit_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Working", "Not Working" }));
        edit_Status.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel31.add(edit_Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 350, 40));

        button4.setBackground(new java.awt.Color(110, 0, 2));
        button4.setForeground(new java.awt.Color(255, 255, 255));
        button4.setText("BACK");
        button4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        jPanel31.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 120, 30));

        button5.setBackground(new java.awt.Color(110, 0, 2));
        button5.setForeground(new java.awt.Color(255, 255, 255));
        button5.setText("UPDATE");
        button5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });
        jPanel31.add(button5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 120, 30));

        edit_Equipment.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        edit_Equipment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aircon", "CD/DVD Drive", "CPU", "Fan", "Headphones", "Keyboard", "Laptop", "Monitor", "Motherboard", "Mouse", "Network Cable", "Power Supply", "Printer", "Projector", "RAM", "Scanner", "Speakers", "Surge Protector", "UPS", "Webcam", "WiFi Router" }));
        edit_Equipment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel31.add(edit_Equipment, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 350, 40));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 22;
        gridBagConstraints.gridheight = 22;
        gridBagConstraints.ipadx = 240;
        gridBagConstraints.ipady = 82;
        jPanel30.add(jPanel31, gridBagConstraints);

        EDIT_ITEM.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1530, 780));

        jPanel32.setBackground(new java.awt.Color(150, 0, 0));
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/bottle_mini.png"))); // NOI18N
        jPanel32.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 100));

        jLabel48.setBackground(new java.awt.Color(255, 255, 255));
        jLabel48.setFont(new java.awt.Font("SansSerif", 1, 50)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("EDIT ITEM");
        jPanel32.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 570, 60));

        EDIT_ITEM.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1560, 110));

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        EDIT_ITEM.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1560, 5));

        TABB.addTab("edit_item", EDIT_ITEM);

        EDIT_PROFILE.setkEndColor(new java.awt.Color(15, 0, 0));
        EDIT_PROFILE.setkGradientFocus(700);
        EDIT_PROFILE.setkStartColor(new java.awt.Color(161, 0, 0));
        EDIT_PROFILE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setLayout(new java.awt.GridBagLayout());

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel39.setForeground(new java.awt.Color(255, 255, 255));
        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel59.setBackground(new java.awt.Color(0, 0, 0));
        jLabel59.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel59.setText("Status:");
        jPanel39.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 180, 40));

        jLabel60.setBackground(new java.awt.Color(0, 0, 0));
        jLabel60.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel60.setText("Full Name:");
        jPanel39.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 180, 40));

        jLabel61.setBackground(new java.awt.Color(0, 0, 0));
        jLabel61.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel61.setText("Email:");
        jPanel39.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 180, 40));

        jLabel62.setBackground(new java.awt.Color(0, 0, 0));
        jLabel62.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel62.setText("ID No.:");
        jPanel39.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 180, 40));

        jLabel63.setBackground(new java.awt.Color(0, 0, 0));
        jLabel63.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel63.setText("Phone Number:");
        jPanel39.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 180, 40));

        imageAvatar7.setBorderSize(2);
        imageAvatar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/avatar.png"))); // NOI18N
        jPanel39.add(imageAvatar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 130, 120));

        profile_update_Status1.setBackground(new java.awt.Color(0, 0, 0));
        profile_update_Status1.setFont(new java.awt.Font("Century Gothic", 2, 14)); // NOI18N
        profile_update_Status1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        profile_update_Status1.setText("USER");
        jPanel39.add(profile_update_Status1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 640, 30));

        profile_update_Status.setBackground(new java.awt.Color(0, 0, 0));
        profile_update_Status.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        profile_update_Status.setText("ADMIN");
        jPanel39.add(profile_update_Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 550, 40));

        profile_update_Name1.setBackground(new java.awt.Color(0, 0, 0));
        profile_update_Name1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        profile_update_Name1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        profile_update_Name1.setText("Heeseung Lee");
        jPanel39.add(profile_update_Name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 640, 40));

        button7.setBackground(new java.awt.Color(110, 0, 2));
        button7.setForeground(new java.awt.Color(255, 255, 255));
        button7.setText("UPDATE");
        button7.setFocusable(false);
        button7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });
        jPanel39.add(button7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 410, 120, 40));

        profile_update_Number.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        profile_update_Number.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        profile_update_Number.setCaretColor(new java.awt.Color(110, 0, 2));
        jPanel39.add(profile_update_Number, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, 350, 40));

        profile_update_Name.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        profile_update_Name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        profile_update_Name.setCaretColor(new java.awt.Color(110, 0, 2));
        jPanel39.add(profile_update_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 350, 40));

        profile_update_Email.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        profile_update_Email.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        profile_update_Email.setCaretColor(new java.awt.Color(110, 0, 2));
        profile_update_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profile_update_EmailActionPerformed(evt);
            }
        });
        jPanel39.add(profile_update_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 350, 40));

        profile_update_ID.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        profile_update_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        profile_update_ID.setCaretColor(new java.awt.Color(110, 0, 2));
        jPanel39.add(profile_update_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 350, 40));

        button8.setBackground(new java.awt.Color(110, 0, 2));
        button8.setForeground(new java.awt.Color(255, 255, 255));
        button8.setText("BACK");
        button8.setFocusable(false);
        button8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });
        jPanel39.add(button8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 410, 120, 40));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 27;
        jPanel38.add(jPanel39, gridBagConstraints);

        EDIT_PROFILE.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1530, 780));

        jPanel40.setBackground(new java.awt.Color(150, 0, 0));
        jPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/bottle_mini.png"))); // NOI18N
        jPanel40.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 100));

        jLabel72.setBackground(new java.awt.Color(255, 255, 255));
        jLabel72.setFont(new java.awt.Font("SansSerif", 1, 50)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("EDIT PROFILE");
        jPanel40.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 570, 60));

        EDIT_PROFILE.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1560, 110));

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        EDIT_PROFILE.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1560, 5));

        TABB.addTab("edit_profile", EDIT_PROFILE);

        HISTORY_TAB1.setkEndColor(new java.awt.Color(15, 0, 0));
        HISTORY_TAB1.setkGradientFocus(700);
        HISTORY_TAB1.setkStartColor(new java.awt.Color(161, 0, 0));
        HISTORY_TAB1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel51.setBackground(new java.awt.Color(153, 153, 153));
        jPanel51.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel51.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        table_equipment_total.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        table_equipment_total.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "EQUIPMENT", "WORKING", "NOT WORKING", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_equipment_total.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        table_equipment_total.setGridColor(new java.awt.Color(0, 0, 0));
        table_equipment_total.setRowHeight(100);
        table_equipment_total.setSelectionBackground(new java.awt.Color(102, 102, 102));
        table_equipment_total.setSelectionForeground(new java.awt.Color(255, 255, 255));
        table_equipment_total.setShowGrid(true);
        jScrollPane4.setViewportView(table_equipment_total);
        if (table_equipment_total.getColumnModel().getColumnCount() > 0) {
            table_equipment_total.getColumnModel().getColumn(0).setMinWidth(695);
            table_equipment_total.getColumnModel().getColumn(0).setPreferredWidth(695);
            table_equipment_total.getColumnModel().getColumn(0).setMaxWidth(695);
            table_equipment_total.getColumnModel().getColumn(1).setMinWidth(240);
            table_equipment_total.getColumnModel().getColumn(1).setPreferredWidth(240);
            table_equipment_total.getColumnModel().getColumn(1).setMaxWidth(240);
            table_equipment_total.getColumnModel().getColumn(2).setMinWidth(240);
            table_equipment_total.getColumnModel().getColumn(2).setPreferredWidth(240);
            table_equipment_total.getColumnModel().getColumn(2).setMaxWidth(240);
        }

        jPanel51.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -49, 1450, 610));

        jPanel9.add(jPanel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 1450, 560));

        jPanel52.setBackground(new java.awt.Color(214, 103, 103));
        jPanel52.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel52.setLayout(new java.awt.GridBagLayout());

        jLabel82.setBackground(new java.awt.Color(214, 103, 103));
        jLabel82.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("TOTAL ITEM");
        jPanel52.add(jLabel82, new java.awt.GridBagConstraints());

        jPanel9.add(jPanel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 70, 270, 100));

        jPanel53.setBackground(new java.awt.Color(214, 103, 103));
        jPanel53.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel53.setLayout(new java.awt.GridBagLayout());

        jLabel83.setBackground(new java.awt.Color(214, 103, 103));
        jLabel83.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("EQUIPMENT");
        jPanel53.add(jLabel83, new java.awt.GridBagConstraints());

        jPanel9.add(jPanel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 700, 100));

        jPanel56.setBackground(new java.awt.Color(214, 103, 103));
        jPanel56.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel56.setLayout(new java.awt.GridBagLayout());

        jLabel84.setBackground(new java.awt.Color(214, 103, 103));
        jLabel84.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("WORKING");
        jPanel56.add(jLabel84, new java.awt.GridBagConstraints());

        jPanel9.add(jPanel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 250, 100));

        jPanel57.setBackground(new java.awt.Color(214, 103, 103));
        jPanel57.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel57.setLayout(new java.awt.GridBagLayout());

        jLabel85.setBackground(new java.awt.Color(214, 103, 103));
        jLabel85.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("NOT WORKING");
        jPanel57.add(jLabel85, new java.awt.GridBagConstraints());

        jPanel9.add(jPanel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, 260, 100));

        HISTORY_TAB1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1550, 770));

        jPanel54.setBackground(new java.awt.Color(150, 0, 0));
        jPanel54.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back_btn5.setBackground(new java.awt.Color(150, 0, 0));
        back_btn5.setForeground(new java.awt.Color(255, 255, 255));
        back_btn5.setText("Back");
        back_btn5.setToolTipText("");
        back_btn5.setFocusable(false);
        back_btn5.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        back_btn5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        back_btn5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        back_btn5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        back_btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btn5ActionPerformed(evt);
            }
        });
        jPanel54.add(back_btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 20, 50, 30));

        back_underline6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel54.add(back_underline6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 50, 50, 3));

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/bottle_mini.png"))); // NOI18N
        jPanel54.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 100));

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 50)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("TOTAL EQUIPMENTS");
        jPanel54.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 620, 60));

        HISTORY_TAB1.add(jPanel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1550, 110));

        jPanel55.setBackground(new java.awt.Color(255, 255, 255));
        jPanel55.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        HISTORY_TAB1.add(jPanel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1550, 5));

        TABB.addTab("total_items", HISTORY_TAB1);

        getContentPane().add(TABB, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 1600, 930));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dash_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash_btnMouseEntered
         dash_btn.setBackground(new Color(0,0,0));
         dash_panel.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_dash_btnMouseEntered

    private void profile_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profile_btnMouseEntered
         profile_btn.setBackground(new Color(0,0,0));
         profile_panel.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_profile_btnMouseEntered

    private void users_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users_btnMouseEntered
         users_btn.setBackground(new Color(0,0,0));
         users_panel.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_users_btnMouseEntered

    private void history_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_history_btnMouseEntered
         history_btn.setBackground(new Color(0,0,0));
         history_panel.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_history_btnMouseEntered

    private void backup_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backup_btnMouseEntered
         backup_btn.setBackground(new Color(0,0,0));
         backup_panel.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_backup_btnMouseEntered

    
    
    private void dash_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash_btnMouseExited
         dash_btn.setBackground(new Color(255, 255, 255));
         dash_panel.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_dash_btnMouseExited

    private void profile_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profile_btnMouseExited
         profile_btn.setBackground(new Color(255, 255, 255));
         profile_panel.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_profile_btnMouseExited

    private void users_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_users_btnMouseExited
         users_btn.setBackground(new Color(255, 255, 255));
         users_panel.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_users_btnMouseExited

    private void history_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_history_btnMouseExited
         history_btn.setBackground(new Color(255, 255, 255));
         history_panel.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_history_btnMouseExited

    private void backup_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backup_btnMouseExited
         backup_btn.setBackground(new Color(255, 255, 255));
         backup_panel.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_backup_btnMouseExited

    
    void reload_item(){
        
    }
    
    
    
    
    private void dash_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dash_btnActionPerformed
        TABB.setSelectedIndex(1);
        if(GD.get_item_for_admin(table_Item)){
            
        }
        
        
        
        
    }//GEN-LAST:event_dash_btnActionPerformed

    private void profile_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profile_btnActionPerformed
        TABB.setSelectedIndex(2);
    }//GEN-LAST:event_profile_btnActionPerformed

    private void users_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_users_btnActionPerformed
        TABB.setSelectedIndex(3);
        if(GD.get_users(table_User)){
            
        }
    }//GEN-LAST:event_users_btnActionPerformed

    private void history_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_history_btnActionPerformed
        TABB.setSelectedIndex(4);
        if(GD.get_history(table_History)){
            
        }
        
    }//GEN-LAST:event_history_btnActionPerformed

    private void backup_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backup_btnActionPerformed
        TABB.setSelectedIndex(5);
    }//GEN-LAST:event_backup_btnActionPerformed

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
        TABB.setSelectedIndex(0);
    }//GEN-LAST:event_back_btnActionPerformed

    private void back_btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btn3ActionPerformed
        TABB.setSelectedIndex(0);
    }//GEN-LAST:event_back_btn3ActionPerformed

    private void back_btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btn4ActionPerformed
        TABB.setSelectedIndex(0);
    }//GEN-LAST:event_back_btn4ActionPerformed

    private void search_textfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_textfieldKeyReleased
         TM.search(table_Item, search_textfield);
         
    }//GEN-LAST:event_search_textfieldKeyReleased

    private void sort_EquipmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_EquipmentActionPerformed
        DefaultTableModel model = (DefaultTableModel) table_Item.getModel();
        TM.sortTable(model, 1);
        
        sort_on_off_item();
        
    }//GEN-LAST:event_sort_EquipmentActionPerformed

    private void sort_RoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_RoomActionPerformed
        DefaultTableModel model = (DefaultTableModel) table_Item.getModel();
        TM.sortTable(model, 0);
        sort_on_off_item();
    }//GEN-LAST:event_sort_RoomActionPerformed

     
    
    private void sort_UnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_UnitActionPerformed
        if(GD.get_item_for_admin(table_Item)){
            
        }
        sort_on_off_item();
    }//GEN-LAST:event_sort_UnitActionPerformed

    private void sort_DateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_DateActionPerformed
        DefaultTableModel model = (DefaultTableModel) table_Item.getModel();
        TM.sortTable(model, 3);
        sort_on_off_item();
    }//GEN-LAST:event_sort_DateActionPerformed

    private void sort_WorkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_WorkingActionPerformed
        DefaultTableModel model = (DefaultTableModel) table_Item.getModel();
        TM.sortTableDescending(model, 4);
        sort_on_off_item();
    }//GEN-LAST:event_sort_WorkingActionPerformed

    private void sort_NotworkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_NotworkingActionPerformed
        DefaultTableModel model = (DefaultTableModel) table_Item.getModel();
        TM.sortTable(model, 4);
        sort_on_off_item();
    }//GEN-LAST:event_sort_NotworkingActionPerformed
    
    private int sort_item=0; 
    public void sort_on_off_item(){
                 if(sort_item==0){
             sort_panel.setVisible(true);
             sort_item = sort_item + 1;
         }else if(sort_item == 1){
             sort_panel.setVisible(false);
             sort_item = sort_item - 1;
         }
    }
     
    private void sort_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_btnActionPerformed
         
          sort_on_off_item();
    }//GEN-LAST:event_sort_btnActionPerformed

    private void sort_EquipmentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_EquipmentMouseEntered
         sort_Equipment.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_sort_EquipmentMouseEntered

    private void sort_RoomMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_RoomMouseEntered
        sort_Room.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_sort_RoomMouseEntered

    private void sort_UnitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_UnitMouseEntered
        sort_Unit.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_sort_UnitMouseEntered

    private void sort_DateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_DateMouseEntered
        sort_Date.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_sort_DateMouseEntered

    private void sort_WorkingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_WorkingMouseEntered
        sort_Working.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_sort_WorkingMouseEntered

    private void sort_NotworkingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_NotworkingMouseEntered
        sort_Notworking.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_sort_NotworkingMouseEntered

    
    
    
    private void sort_EquipmentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_EquipmentMouseExited
        sort_Equipment.setBackground(new Color(217,217,217));
    }//GEN-LAST:event_sort_EquipmentMouseExited

    private void sort_RoomMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_RoomMouseExited
        sort_Room.setBackground(new Color(217,217,217));
    }//GEN-LAST:event_sort_RoomMouseExited

    private void sort_DateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_DateMouseExited
        sort_Date.setBackground(new Color(217,217,217));
    }//GEN-LAST:event_sort_DateMouseExited

    private void sort_WorkingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_WorkingMouseExited
        sort_Working.setBackground(new Color(217,217,217));
    }//GEN-LAST:event_sort_WorkingMouseExited

    private void sort_NotworkingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_NotworkingMouseExited
        sort_Notworking.setBackground(new Color(217,217,217));
    }//GEN-LAST:event_sort_NotworkingMouseExited

    private void sort_UnitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_UnitMouseExited
        sort_Unit.setBackground(new Color(217,217,217));
    }//GEN-LAST:event_sort_UnitMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    
    }//GEN-LAST:event_formWindowOpened

    private void back_btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btn8ActionPerformed
        TABB.setSelectedIndex(0);
    }//GEN-LAST:event_back_btn8ActionPerformed

    private void profile_update_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profile_update_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profile_update_EmailActionPerformed

    private void sort_Equipment1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_Equipment1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_sort_Equipment1MouseEntered

    private void sort_Equipment1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_Equipment1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_sort_Equipment1MouseExited

    private void sort_Equipment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_Equipment1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) table_User.getModel();
        TM.sortTable(model, 1);
        
        sort_on_off_user();
    }//GEN-LAST:event_sort_Equipment1ActionPerformed

    private void sort_Room1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_Room1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_sort_Room1MouseEntered

    private void sort_Room1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_Room1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_sort_Room1MouseExited

    private void sort_Room1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_Room1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sort_Room1ActionPerformed

    private void sort_Unit1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_Unit1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_sort_Unit1MouseEntered

    private void sort_Unit1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_Unit1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_sort_Unit1MouseExited

    private void sort_Unit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_Unit1ActionPerformed
                DefaultTableModel model = (DefaultTableModel) table_User.getModel();
        TM.sortTableDescending(model, 4);
        
        sort_on_off_user();
    }//GEN-LAST:event_sort_Unit1ActionPerformed

    private void sort_Date1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_Date1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_sort_Date1MouseEntered

    private void sort_Date1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sort_Date1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_sort_Date1MouseExited

    private void sort_Date1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_Date1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) table_User.getModel();
        TM.sortTable(model, 4);
        
        sort_on_off_user();
    }//GEN-LAST:event_sort_Date1ActionPerformed

    private void back_btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btn6ActionPerformed
        TABB.setSelectedIndex(0);
    }//GEN-LAST:event_back_btn6ActionPerformed

    private void search_userKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_userKeyReleased
       TM.search(table_User, search_user);
    }//GEN-LAST:event_search_userKeyReleased

    
    
    
    private int sort_user=0; 
    public void sort_on_off_user(){
                 if(sort_user==0){
             sort_panel_user.setVisible(true);
             sort_user = sort_user + 1;
         }else if(sort_user == 1){
             sort_panel_user.setVisible(false);
             sort_user = sort_user - 1;
         }
    }
    
    
    private void sort_user_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort_user_btnActionPerformed
         sort_on_off_user();
    }//GEN-LAST:event_sort_user_btnActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        TABB.setSelectedIndex(1);
        if (table_Item.isEditing()) {
        table_Item.getCellEditor().stopCellEditing();
    }
    table_Item.clearSelection();
                           
    }//GEN-LAST:event_button4ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
       if(String.valueOf(profile_Status1.getText()).equals("USER")){
                  show_message("Only admin can add item.");
              }else{
           TABB.setSelectedIndex(6);
       }
        
        
        
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        TABB.setSelectedIndex(1);
    }//GEN-LAST:event_button2ActionPerformed

    private String email1="";
    private String email2="";
    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button8ActionPerformed
       
    TABB.setSelectedIndex(2);
    }//GEN-LAST:event_button8ActionPerformed

    private void table_ItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ItemMouseClicked
     
         
    }//GEN-LAST:event_table_ItemMouseClicked

    private void search_textfieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_textfieldMouseClicked
         
    }//GEN-LAST:event_search_textfieldMouseClicked

    private void search_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_textfieldActionPerformed
          
        
    }//GEN-LAST:event_search_textfieldActionPerformed
 
    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        
   Auto_success AS = new Auto_success(this);
     int add_unit = Integer.parseInt(String.valueOf(add_Unit.getText()));
     
     
     
        if(String.valueOf(add_Room.getText()).equals("")){
            AS.showMessage("Must fill all Items.");
        }else if(String.valueOf(add_Equipment.getSelectedItem()).equals("")){
            AS.showMessage("Must fill all Items.");
        }else if(String.valueOf(add_Unit.getText()).equals("")){
            AS.showMessage("Must fill all Items.");
        }else if(String.valueOf(add_Date.getText()).equals("")){
            AS.showMessage("Must fill all Items.");
        } else if(String.valueOf(add_Status.getSelectedItem()).equals("")){
            AS.showMessage("Must fill all Items.");
        }else if(CD.check_Unit(add_unit)){
            AS.showMessage("Unit No. Already Exist. Try another.");
            
        }
        else{
            if(ID.insert_item(add_Room, add_Equipment, add_Unit, add_Date, add_Status)){
                AS.showMessage("New item added successfull.");
                
                String user= String.valueOf(profile_Email.getText());
                String item = String.valueOf(add_Equipment.getSelectedItem());
                String id = String.valueOf(add_Unit.getText());
                if(ID.insert_history("was added "+item+" Unit no. "+id+" at", user)){

                    }
                
                TABB.setSelectedIndex(1);
                if(GD.get_item_for_admin(table_Item)){
                    if (table_Item.isEditing()) {
                        table_Item.getCellEditor().stopCellEditing();
                        }
                            table_Item.clearSelection();
                }
            
        }
        
        
        
        }
        
    }//GEN-LAST:event_button3ActionPerformed

    private int for_id = 0;
    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        int id =Integer.parseInt(String.valueOf(profile_ID.getText())); 
        for_id = id;
         
        TABB.setSelectedIndex(8);
        profile_update_Name1.setText(String.valueOf(profile_Name1.getText()));
        profile_update_Status1.setText(String.valueOf( profile_Status1.getText()));
        profile_update_Name.setText(String.valueOf( profile_Name.getText()));
        profile_update_Email.setText(String.valueOf( profile_Email.getText()));
        profile_update_ID.setText(String.valueOf( profile_ID.getText()));
        profile_update_Number.setText(String.valueOf( profile_Number.getText()));
        profile_update_Status.setText(String.valueOf( profile_Status.getText()));
    }//GEN-LAST:event_button6ActionPerformed

    private int ID1=0;
    private int ID2=0;
    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
          
          Auto_success AS = new Auto_success(this);

            String email1 = String.valueOf(profile_update_Email.getText());
            String email2 = String.valueOf(profile_Email.getText());

            int ID1 = Integer.parseInt(profile_ID.getText());
            int ID2 = Integer.parseInt(profile_update_ID.getText());

             

             
            if (String.valueOf(profile_update_Name.getText()).equals("")) {
                AS.showMessage("Name cannot be empty.");
            } else if (email1.equals("")) {
                AS.showMessage("Email cannot be empty.");
            } else if (String.valueOf(profile_update_ID.getText()).equals("")) {
                AS.showMessage("ID number cannot be empty.");
            } else if (String.valueOf(profile_update_Number.getText()).equals("")) {
                AS.showMessage("Phone number cannot be empty.");
            } else {
                boolean canUpdate = true;

                 
                if (!email1.equalsIgnoreCase(email2)) {
                    if (CD.check_email(email1)) {
                        AS.showMessage("Email already existed. Please try another.");
                        canUpdate = false;
                    }
                }

                 
                if (ID1 != ID2) {
                    if (CD.check_id(ID2)) {
                        AS.showMessage("ID number already exist. Please try another.");
                        canUpdate = false;
                    }
                }

                 
                if (canUpdate) {
                    if (UD.update_account(profile_update_Name, profile_update_Email, profile_update_ID, profile_update_Number, profile_ID)) {
                        AS.showMessage("Profile Update successful.");
                        updated_profile();
                    }
                }
            }

         
        email1 = "";
        email2 = "";
    }//GEN-LAST:event_button7ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
         Auto_success AS = new Auto_success(this);
 
             
            int ID2 = Integer.parseInt(edit_Unit.getText());

             

             
            if (String.valueOf(edit_Room.getText()).equals("")) {
                AS.showMessage("Room cannot be empty.");
            } else if (String.valueOf(edit_Equipment.getSelectedItem()).equals("")) {
                AS.showMessage("Equipment cannot be empty.");
            } else if (String.valueOf(edit_Unit.getText()).equals("")) {
                AS.showMessage("Unit number cannot be empty.");
            } else if (String.valueOf(edit_Date.getText()).equals("")) {
                AS.showMessage("Date cannot be empty.");
            } 
            
             else {
                boolean canUpdate = true;

                  
                if (Unit_number != ID2) {
                    if (CD.check_Unit(ID2)) {
                        AS.showMessage("Unit number already exist. Please try another.");
                        canUpdate = false;
                    }
                } 
                if (canUpdate) {
                    if (UD.update_item(edit_Room, edit_Equipment, edit_Unit, edit_Date, edit_Status, Unit_number)) {
                        AS.showMessage("Item update successful.");
                        
                        String user= String.valueOf(profile_Email.getText());
                        String item = String.valueOf(edit_Equipment.getSelectedItem());
                        String id = String.valueOf(edit_Unit.getText());
                        if(ID.insert_history("was edited "+item+" Unit no. "+id+" at", user)){

                        }
                        TABB.setSelectedIndex(1);
                          
                         if(GD.get_item_for_admin(table_Item)){
                             if (table_Item.isEditing()) {
                                    table_Item.getCellEditor().stopCellEditing();
                                }
                                table_Item.clearSelection();
                            }
                    }
                }
            }
    }//GEN-LAST:event_button5ActionPerformed

    private void back_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btn1ActionPerformed
        
        String user = String.valueOf(profile_Email.getText());
        Delete_Dialog ds = new Delete_Dialog(this);
       ds.showMessage("ARE YOU SURE YOU WANT TO LOGOUT THIS SESSION?");
        if (ds.getMessageType() == Delete_Dialog.MessageType.OK)
        {
            
            if(ID.insert_history("was logged out at", user)){
            
        }
            System.exit(0);
        }
    }//GEN-LAST:event_back_btn1ActionPerformed

    private void RestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestoreActionPerformed
        if(String.valueOf(profile_Status1.getText()).equals("USER")){
                  show_message("Only admin can use this feature.");
              }else{
            restore();
        } 
        
       
        
    }//GEN-LAST:event_RestoreActionPerformed

    
   void restore() {
        
    // Show file chooser to select the ZIP file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select Backup ZIP File");
    int userSelection = fileChooser.showOpenDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File zipFile = fileChooser.getSelectedFile();

        // Prompt the user to enter the password
        String password = JOptionPane.showInputDialog(null, "Enter the password for the backup ZIP file:", 
                                                      "Password Required", JOptionPane.PLAIN_MESSAGE);
        if (password == null || password.isEmpty()) {
//            delay_warning("INVALID PASSWORD", "Password not provided. Restore process aborted.");
            System.err.println("Password not provided. Restore process aborted.");
            return;
        }

        // Extract the ZIP file
        File extractedFolder = extractZipFile(zipFile, password);
        if (extractedFolder != null) {
            // Locate the CSV files inside the extracted folder
            File[] files = extractedFolder.listFiles((dir, name) -> name.endsWith("_backup.csv"));
            if (files != null && files.length > 0) {
                boolean allRestoredSuccessfully = true;

                // Loop through all CSV files and restore each table
                for (File csvFile : files) {
                    String tableName = getTableNameFromFile(csvFile); // Extract table name from the file
                    if (tableName != null) {
                        System.out.println("Restoring table: " + tableName);
                        try {
                            restoreDatabaseFromCSV("jdbc:mysql://localhost/l_track", "root", "", tableName, csvFile);
                        } catch (Exception e) {
                            allRestoredSuccessfully = false;
                            e.printStackTrace();
                            System.err.println("Failed to restore table: " + tableName);
                        }
                    }
                }

                // Display success dialog if all tables were restored successfully
                if (allRestoredSuccessfully) {
                    Auto_success scs = new Auto_success(this);
                    scs.showMessage("RESTORE SUCCESSFUL");
                } else {
                    Auto_success scs = new Auto_success(this);
                    scs.showMessage("PARTIAL RESTORE - Some tables failed.");
                }
            } else {
                Auto_success scs = new Auto_success(this);
                scs.showMessage("RESTORE FAILED - No CSV files found.");
            }
        }
    }
}
         
// Method to extract table name from the CSV file
private String getTableNameFromFile(File csvFile) {
    // Extract table name from the file name (assuming the format is `tableName_backup.csv`)
    String fileName = csvFile.getName();
    if (fileName.endsWith("_backup.csv")) {
        return fileName.replace("_backup.csv", "");
    }
    return null;  // Return null if the file name doesn't match the expected pattern
}

public File extractZipFile(File zipFile, String password) {
    try {
        // Specify the extraction location
        File extractFolder = new File(zipFile.getParent(), "Extracted_Backup");
        if (!extractFolder.exists()) {
            extractFolder.mkdir();
        }
        
        // Extract the ZIP file using Zip4j
        ZipFile zip = new ZipFile(zipFile);
        if (zip.isEncrypted()) {
            zip.setPassword(password.toCharArray());
        }
        zip.extractAll(extractFolder.getAbsolutePath());
        
        System.out.println("Backup extracted to: " + extractFolder.getAbsolutePath());
        return extractFolder;
    } catch (ZipException e) {
//        delay_warning("INVALID PASSWORD","Failed to extract ZIP file. Please Enter the correct password to restore.");
        System.err.println("Failed to extract ZIP file: " + e.getMessage());
        return null;
    }
}
 public void restoreDatabaseFromCSV(String dbUrl, String dbUser, String dbPassword, String tableName, File csvFilePath) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    BufferedReader reader = null;
    String line;
    int rowCount = 0;

    try {
        // Create connection to the database
        connection = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        connection.setAutoCommit(false); // Use batch processing for performance

        // Prepare CSV reader
        reader = new BufferedReader(new FileReader(csvFilePath));

        // Read the header (column names)
        String[] columns = reader.readLine().split(","); // Split header by commas

        // Prepare the INSERT statement with the ON DUPLICATE KEY UPDATE clause
        StringBuilder insertQuery = buildInsertQuery(tableName, columns);
        preparedStatement = (PreparedStatement) connection.prepareStatement(insertQuery.toString());

        // Read and insert rows from CSV
        while ((line = reader.readLine()) != null) {
            // Handle trailing spaces and ensure consistent parsing
            String[] values = line.split(",", -1); // Use -1 to include empty trailing values

            for (int i = 0; i < values.length; i++) {
                String value = values[i].trim(); // Trim whitespace

                if (value.isEmpty()) {
                    // Handle null or empty values appropriately based on column type
                    preparedStatement.setNull(i + 1, Types.VARCHAR); // Default to VARCHAR null
                } else {
                    preparedStatement.setString(i + 1, value); // Use the value as-is
                }
            } 
            // Add the batch for batch processing
            preparedStatement.addBatch(); 
            // Execute batch periodically to avoid memory issues (e.g., every 1000 rows)
            if (++rowCount % 1000 == 0) {
                preparedStatement.executeBatch();
            }
        } 
        // Execute the remaining batch
        preparedStatement.executeBatch(); 
        // Commit the transaction
        connection.commit();
        System.out.println("Restore completed successfully!"); 
    } catch (SQLException | IOException e) {
        e.printStackTrace();
        if (connection != null) {
            try {
                connection.rollback(); // Rollback in case of error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    } finally { 
        try {
            if (reader != null) reader.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }}

private static byte[] readImageFile(File imageFile) throws IOException {
    try (FileInputStream fis = new FileInputStream(imageFile);
         ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        return bos.toByteArray();
    }
} 
private static StringBuilder buildInsertQuery(String tableName, String[] columns) {
    StringBuilder insertQuery = new StringBuilder("INSERT INTO " + tableName + " ("); 
    // Add columns to the INSERT query
    for (String column : columns) {
        insertQuery.append(column).append(", ");
    }  
    insertQuery.delete(insertQuery.length() - 2, insertQuery.length()); 
    insertQuery.append(") VALUES ("); 
    // Add placeholders for the values
    for (int i = 0; i < columns.length; i++) {
        insertQuery.append("?, ");
    } 
    // Remove the last comma and space
    insertQuery.delete(insertQuery.length() - 2, insertQuery.length()); 
    insertQuery.append(") ON DUPLICATE KEY UPDATE "); 
    // Add the ON DUPLICATE KEY UPDATE part of the query
    for (String column : columns) {
        insertQuery.append(column).append("=VALUES(").append(column).append("), ");
    } 
    // Remove the last comma and space
    insertQuery.delete(insertQuery.length() - 2, insertQuery.length()); 
    return insertQuery;
}

    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void Back_upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back_upActionPerformed
        
        if(String.valueOf(profile_Status1.getText()).equals("USER")){
                  show_message("Only admin can use this feature.");
              }else{
            backup();
        }
        
         
    }//GEN-LAST:event_Back_upActionPerformed

    private void Back_upMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_upMouseEntered
         Back_up.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3)); 
         Back_up.setBackground(new Color(0,126,168));
    }//GEN-LAST:event_Back_upMouseEntered

    private void RestoreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RestoreMouseEntered
        Restore.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        Back_up.setBackground(new Color(0,153,204));
    }//GEN-LAST:event_RestoreMouseEntered

    private void Back_upMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_upMouseExited
        Back_up.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3)); 
        Restore.setBackground(new Color(201,8,50));
    }//GEN-LAST:event_Back_upMouseExited

    private void RestoreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RestoreMouseExited
        Restore.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3)); 
        Restore.setBackground(new Color(245,10,61));
    }//GEN-LAST:event_RestoreMouseExited

    
    
    
    void total_item(){
        TM.table_align_center(table_equipment_total, 0);
        TM.table_align_center(table_equipment_total, 1);
        TM.table_align_center(table_equipment_total, 2);
        TM.table_align_center(table_equipment_total, 3);
        
        table_equipment_total.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
    }
    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed
        
        if(GD.get_total_item(table_equipment_total)){
            
        }
        TABB.setSelectedIndex(9);
    }//GEN-LAST:event_button9ActionPerformed

    private void back_btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btn5ActionPerformed
        TABB.setSelectedIndex(1);
    }//GEN-LAST:event_back_btn5ActionPerformed
                                     
  void backup() {  
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select Folder to Save Backup");
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    
    int userSelection = fileChooser.showSaveDialog(null);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File selectedFolder = fileChooser.getSelectedFile();
        String selectedPath = selectedFolder.getAbsolutePath();
        
        // Prompt user for a password
        String password = JOptionPane.showInputDialog(null, "Enter a password to protect the backup zip:", 
                                                      "Password Input", JOptionPane.PLAIN_MESSAGE);
        
        if (password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Backup cancelled: Password is required.", 
                                          "Cancelled", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Create a new folder for the backup
        File backupFolder = new File(selectedPath + File.separator + "Database_Backup");
        if (!backupFolder.exists()) {
            boolean created = backupFolder.mkdir();
            if (created) {
                System.out.println("New folder created: " + backupFolder.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create backup folder!", 
                                              "Backup Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        boolean allBackupsSuccessful = true; 
        String[] tables = { "inventory_list", "accounts", "history" };
        
        for (String table : tables) {
            try {
                backupDatabaseTable("jdbc:mysql://localhost/l_track", "root", "", table, backupFolder.getAbsolutePath());
            } catch (Exception e) {
                allBackupsSuccessful = false;
                e.printStackTrace();
                System.err.println("Failed to backup table: " + table);
            }
        }

        // Zip and password-protect the folder
        boolean zipSuccess = zipAndProtectFolder(backupFolder, password);
        
        if (zipSuccess) {
            deleteFolder(backupFolder);
        } else {
            allBackupsSuccessful = false;
        }

        if (allBackupsSuccessful && zipSuccess) {
            backup_success_delay();
        } else {
            JOptionPane.showMessageDialog(null, "Backup completed with errors. Please check logs.", 
                                          "Partial Success", JOptionPane.WARNING_MESSAGE);
        }
    }
}


    void backup_success_delay(){
        Auto_success AS = new Auto_success(this);
        if (searchThread != null && searchThread.isAlive()) {
            searchThread.interrupt();
        }
        searchThread = new Thread(() -> {
            try {
                Thread.sleep(200);
                AS.showMessage("BACKUP SUCCESSFUL");
            } catch (InterruptedException e) {
            }
        });
        searchThread.start();
    }  
   
   
    public static void backupDatabaseTable(String dbUrl, String dbUser, String dbPassword, String tableName, String folderPath) {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null; 
    try {
        // Connect to the database
        connection = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        statement = connection.createStatement();
        
        // Execute query to retrieve table data
        String query = "SELECT * FROM " + tableName;
        resultSet = statement.executeQuery(query);
        
        // Create backup file
        File backupFile = new File(folderPath, tableName + "_backup.csv");

        // Use try-with-resources to ensure proper closing of file streams
        try (FileWriter fileWriter = new FileWriter(backupFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            
            // Write column names (header)
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                printWriter.print(resultSet.getMetaData().getColumnName(i));
                if (i < columnCount) printWriter.print(",");
            }
            printWriter.println();
            
            // Write table data (rows)
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    printWriter.print(resultSet.getString(i));
                    if (i < columnCount) printWriter.print(",");
                }
                printWriter.println();
            }  
            System.out.println("Backup completed successfully! File saved to: " + backupFile.getAbsolutePath());
        }
        
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    public static boolean zipAndProtectFolder(File folder, String password) {
        try {
            // Specify the ZIP file location
            File zipFile = new File(folder.getParentFile(), folder.getName() + ".zip");
            
            // Create a ZipFile instance with the password
            ZipFile zip = new ZipFile(zipFile, password.toCharArray());
            
            // Create a ZipParameters instance to specify encryption
            ZipParameters parameters = new ZipParameters();
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD); // You can use AES for stronger encryption
            
            // Iterate over all files inside the folder and add them to the ZIP file
            for (File file : folder.listFiles()) {
                if (file.isFile()) {
                    zip.addFile(file, parameters);
                }
            }
            
            System.out.println("Files zipped and password protected: " + zipFile.getAbsolutePath());
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void deleteFolder(File folder) {
        if (folder.isDirectory()) {
            // Recursively delete all files and subfolders
            for (File file : folder.listFiles()) {
                deleteFolder(file);
            }
        }
        // Delete the folder/file
        boolean deleted = folder.delete();
        if (deleted) {
            System.out.println("Deleted: " + folder.getAbsolutePath());
        } else {
            System.err.println("Failed to delete: " + folder.getAbsolutePath());
        }
    }
         
    
      
    
     
    
    
    
    
    
    
    
    
    
    void updated_profile(){
         Auto_success AS = new Auto_success(this); 
         
        String  username= String.valueOf(profile_update_Email.getText());
        int id = Integer.parseInt(String.valueOf(profile_update_ID.getText()));
         try {
            Class.forName("com.mysql.jdbc.Driver");
             con= (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost/l_track", "root", "");
            pst= (PreparedStatement) con.prepareStatement("SELECT * FROM `accounts` WHERE `email`=? AND `id_no`=?");
            pst.setString(1, username);
            pst.setInt(2, id); 
       
           rs=pst.executeQuery();
            if(rs.next()){
                  
            profile_update_Name1.setText(String.valueOf(rs.getString("name")));
            profile_update_Status1.setText(String.valueOf(rs.getString("status"))); 
            profile_update_Status.setText(String.valueOf(rs.getString("status"))); 
            
            profile_Name1.setText(String.valueOf(rs.getString("name")));
            profile_Status1.setText(String.valueOf(rs.getString("status"))); 
            profile_Name.setText(String.valueOf(rs.getString("name")));
            profile_Email.setText(String.valueOf(rs.getString("email")));
            profile_ID.setText(String.valueOf(rs.getString("id_no")));
            profile_Number.setText(String.valueOf(rs.getString("number")));
            profile_Status.setText(String.valueOf(rs.getString("status")));
            
            Head_name.setText(String.valueOf(rs.getString("name")));
            Head_status.setText(String.valueOf(rs.getString("status"))); 
             
                
            }} 
        catch (SQLException ex) {
            Logger.getLogger(Check_Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Check_Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        UIManager.put("defaultFont",new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new B_dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KGradientPanel ADD_ITEM;
    private keeptoo.KGradientPanel BACKUP_TAB;
    private button.Button Back_up;
    private keeptoo.KGradientPanel DASHBOARD_TAB;
    private keeptoo.KGradientPanel EDIT_ITEM;
    private keeptoo.KGradientPanel EDIT_PROFILE;
    private keeptoo.KGradientPanel HISTORY_TAB;
    private keeptoo.KGradientPanel HISTORY_TAB1;
    private keeptoo.KGradientPanel HOME_TAB;
    public javax.swing.JLabel Head_name;
    public javax.swing.JLabel Head_status;
    private keeptoo.KGradientPanel PROFILE_TAB;
    private button.Button Restore;
    private javax.swing.JTabbedPane TABB;
    private keeptoo.KGradientPanel USER_TAB;
    private javax.swing.JTextField add_Date;
    private javax.swing.JComboBox<String> add_Equipment;
    private javax.swing.JTextField add_Room;
    private javax.swing.JComboBox<String> add_Status;
    private javax.swing.JTextField add_Unit;
    private button.Button back_btn;
    private button.Button back_btn1;
    private button.Button back_btn3;
    private button.Button back_btn4;
    private button.Button back_btn5;
    private button.Button back_btn6;
    private button.Button back_btn8;
    private javax.swing.JPanel back_underline1;
    private javax.swing.JPanel back_underline2;
    private javax.swing.JPanel back_underline3;
    private javax.swing.JPanel back_underline4;
    private javax.swing.JPanel back_underline5;
    private javax.swing.JPanel back_underline6;
    private javax.swing.JPanel back_underline7;
    private javax.swing.JPanel back_underline9;
    private button.Button backup_btn;
    private Custom.PanelRound backup_panel;
    private button.Button button1;
    private button.Button button2;
    private button.Button button3;
    private button.Button button4;
    private button.Button button5;
    private button.Button button6;
    private button.Button button7;
    private button.Button button8;
    private button.Button button9;
    private button.Button dash_btn;
    private Custom.PanelRound dash_panel;
    private javax.swing.JTextField edit_Date;
    private javax.swing.JComboBox<String> edit_Equipment;
    private javax.swing.JTextField edit_Room;
    private javax.swing.JComboBox<String> edit_Status;
    private javax.swing.JTextField edit_Unit;
    private button.Button history_btn;
    private Custom.PanelRound history_panel;
    private Swing.ImageAvatar imageAvatar1;
    private Swing.ImageAvatar imageAvatar3;
    private Swing.ImageAvatar imageAvatar7;
    private javax.swing.JLabel item_label;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private Custom.PanelRound panelRound1;
    private Custom.PanelRound panelRound2;
    private Custom.PanelRound panelRound3;
    private Custom.PanelRound panelRound4;
    public javax.swing.JLabel profile_Email;
    public javax.swing.JLabel profile_ID;
    public javax.swing.JLabel profile_Name;
    public javax.swing.JLabel profile_Name1;
    public javax.swing.JLabel profile_Number;
    public javax.swing.JLabel profile_Status;
    public javax.swing.JLabel profile_Status1;
    private button.Button profile_btn;
    private Custom.PanelRound profile_panel;
    private javax.swing.JTextField profile_update_Email;
    private javax.swing.JTextField profile_update_ID;
    private javax.swing.JTextField profile_update_Name;
    private javax.swing.JLabel profile_update_Name1;
    private javax.swing.JTextField profile_update_Number;
    private javax.swing.JLabel profile_update_Status;
    private javax.swing.JLabel profile_update_Status1;
    private javax.swing.JTextField search_textfield;
    private javax.swing.JTextField search_user;
    private button.Button sort_Date;
    private button.Button sort_Date1;
    private button.Button sort_Equipment;
    private button.Button sort_Equipment1;
    private button.Button sort_Notworking;
    private button.Button sort_Room;
    private button.Button sort_Room1;
    private button.Button sort_Unit;
    private button.Button sort_Unit1;
    private button.Button sort_Working;
    private button.Button sort_btn;
    private javax.swing.JPanel sort_panel;
    private javax.swing.JPanel sort_panel_user;
    private button.Button sort_user_btn;
    private javax.swing.JLabel status_label;
    private table.table table_History;
    private table.table table_Item;
    private table.table table_User;
    private table.table table_equipment_total;
    private javax.swing.JLabel unit_label;
    private javax.swing.JLabel user_label;
    private button.Button users_btn;
    private Custom.PanelRound users_panel;
    // End of variables declaration//GEN-END:variables
}
