 
package FORMS_METHODS;
 
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import table.table;

public class Table_Methods {
    
    public void table_align_center(table table, int column){
         DefaultTableCellRenderer rightRenderer= new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
         
         table.getColumnModel().getColumn(column).setCellRenderer(rightRenderer);
         
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(100, 32));
     }
    
    public void table_align_right(table table, int column){
         DefaultTableCellRenderer rightRenderer= new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
         
         table.getColumnModel().getColumn(column).setCellRenderer(rightRenderer);
         
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(100, 32));
     }
    
         public void search(table table, JTextField searchField) {
        DefaultTableModel studentsTableModel = (DefaultTableModel) table.getModel(); // Get table model
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(studentsTableModel); // Create row sorter
        table.setRowSorter(rowSorter);  // Set the row sorter on the table
         
            String searchText = searchField.getText().trim(); // Get the text from the search field
            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null); // Show all rows if search field is empty
            } else {
                try {
                    // Apply a regex filter for case-insensitive matching
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                } catch (java.util.regex.PatternSyntaxException ex) {
                    // Handle invalid regex patterns gracefully
                    System.err.println("Invalid regex pattern: " + ex.getMessage());
                    rowSorter.setRowFilter(null); // Show all rows if regex is invalid
                }
            }
        
    } 
    
        public void sortTable(DefaultTableModel model, int columnIndex) {
    // Extract rows from the model into a List
    List<Object[]> rows = new ArrayList<>();
    for (int i = 0; i < model.getRowCount(); i++) {
        Object[] row = new Object[model.getColumnCount()];
        for (int j = 0; j < model.getColumnCount(); j++) {
            row[j] = model.getValueAt(i, j);
        }
        rows.add(row);
    }

    // Sort the list by the specified column index, case-insensitive
    rows.sort(Comparator.comparing(
        row -> row[columnIndex] != null ? row[columnIndex].toString().toLowerCase() : ""
    ));

    // Clear the table and re-insert sorted rows
    model.setRowCount(0); // Clear existing rows
    for (Object[] row : rows) {
        model.addRow(row); // Add sorted rows back to the model
    }
}
    
    public void sortTableDescending(DefaultTableModel model, int columnIndex) {
    // Extract rows from the model into a List
    List<Object[]> rows = new ArrayList<>();
    for (int i = 0; i < model.getRowCount(); i++) {
        Object[] row = new Object[model.getColumnCount()];
        for (int j = 0; j < model.getColumnCount(); j++) {
            row[j] = model.getValueAt(i, j);
        }
        rows.add(row);
    }

    // Sort the list by the specified column index in descending alphabetical order
    rows.sort(Comparator.comparing((Object[] row) -> row[columnIndex].toString()).reversed());

    // Clear the table and re-insert sorted rows
    model.setRowCount(0); // Clear existing rows
    for (Object[] row : rows) {
        model.addRow(row); // Add sorted rows back to the model
    }
}
    
    
         public void sort_number(DefaultTableModel model, int columnIndex) {
    // Extract rows from the model into a List
    List<Object[]> rows = new ArrayList<>();
    for (int i = 0; i < model.getRowCount(); i++) {
        Object[] row = new Object[model.getColumnCount()];
        for (int j = 0; j < model.getColumnCount(); j++) {
            row[j] = model.getValueAt(i, j);
        }
        rows.add(row);
    }

    // Sort the list based on the type of the value in the specified column
    rows.sort((row1, row2) -> {
        Object val1 = row1[columnIndex];
        Object val2 = row2[columnIndex];

        if (val1 instanceof Number && val2 instanceof Number) {
            return Integer.compare(((Number) val1).intValue(), ((Number) val2).intValue());
        } else {
            return val1.toString().compareToIgnoreCase(val2.toString());
        }
    });

    // Clear the table and re-insert sorted rows
    model.setRowCount(0); // Clear existing rows
    for (Object[] row : rows) {
        model.addRow(row); // Add sorted rows back to the model
    }
}
         
         
      public void click_row_jlabel(table table, JLabel id, int column){
    
            int viewRow = table.getSelectedRow(); // The row index in the view (sorted table)
            if (viewRow != -1) { // Ensure a row is selected 
            int modelRow = table.convertRowIndexToModel(viewRow); // Convert view index to model index  
            String ID = table.getModel().getValueAt(modelRow, column).toString(); // Column 2 
            id.setText(String.valueOf(ID));  
 }}
      
      
       public void click_row_jtext(table table, JTextField id, int column){
    
            int viewRow = table.getSelectedRow(); // The row index in the view (sorted table)
            if (viewRow != -1) { // Ensure a row is selected 
            int modelRow = table.convertRowIndexToModel(viewRow); // Convert view index to model index  
            String ID = table.getModel().getValueAt(modelRow, column).toString(); // Column 2 
            id.setText(String.valueOf(ID));  
 }}
         
               public void click_row_combobox(table table, JComboBox<String>  id, int column){
    
            int viewRow = table.getSelectedRow(); // The row index in the view (sorted table)
            if (viewRow != -1) { // Ensure a row is selected 
            int modelRow = table.convertRowIndexToModel(viewRow); // Convert view index to model index  
            String ID = table.getModel().getValueAt(modelRow, column).toString(); // Column 2 
            id.setSelectedItem(String.valueOf(ID));  
 }}
         
         
}
