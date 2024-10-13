package mainpage;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class EmployeeList extends JFrame {
    private JFrame parentFrame;
    
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    JTable employeeTable;
    DefaultTableModel model;
    DefaultTableModel backupModel; 
    JTextField searchField;

    public EmployeeList() {
        if (MainPage.currentUserHasAccess()) {  
        JOptionPane.showMessageDialog(null, "Access denied. You must be an Administrator, Manager, or Director.");
        this.dispose(); 
        return; 
    } else {
        JOptionPane.showMessageDialog(null, "Access granted.");
    }
        conn = MainPage.conn(); 
        model = new DefaultTableModel(new String[]{"Full Name", "E-mail", "Role", "Action"}, 0);
        backupModel = new DefaultTableModel(new String[]{"Full Name", "E-mail", "Role"}, 0); // Backup model
        initComponents();
        showEmployeeList("");
}
    


    private void initComponents() {
        setTitle("Employee List");
        setLayout(new BorderLayout());
        setSize(800, 600);

        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(new Color(230, 230, 250));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton exportButton = new JButton("Export to CSV");
        JButton addButton = new JButton("Add New Employee");
        topPanel.add(new JLabel("Search by Name or Role:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(exportButton);
        topPanel.add(addButton);

        add(topPanel, BorderLayout.NORTH);

        
        employeeTable = new JTable(model);
        employeeTable.setRowHeight(40); 
        employeeTable.setBackground(new Color(255, 250, 250)); 
        employeeTable.setGridColor(new Color(192, 192, 192)); 
        employeeTable.getTableHeader().setBackground(new Color(169, 169, 169)); 
        employeeTable.getColumnModel().getColumn(3).setPreferredWidth(200); 

        
        employeeTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        employeeTable.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));

       
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.getViewport().setBackground(new Color(255, 250, 250)); 
        add(scrollPane, BorderLayout.CENTER);

        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Save Changes");
        JButton resetButton = new JButton("Reset Changes");
        bottomPanel.add(saveButton);
        bottomPanel.add(resetButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        searchButton.addActionListener(e -> showEmployeeList(searchField.getText()));
        exportButton.addActionListener(e -> exportToCSV());
        addButton.addActionListener(e -> newEmployee()); // Call newEmployee method when clicking "Add New Employee"
        saveButton.addActionListener(e -> saveChanges());  // Save changes when clicking "Save"
        resetButton.addActionListener(e -> resetTable());  // Reset table to the original data

        setVisible(true);
    }

    // Method to populate employee list
    private void showEmployeeList(String searchQuery) {
        try {
            model.setRowCount(0); // Clear existing data
            backupModel.setRowCount(0); // Clear backup data

            String sql = "SELECT user_fullname, user_name, role FROM login WHERE user_fullname LIKE ? OR role LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchQuery + "%");
            pst.setString(2, "%" + searchQuery + "%");
            rs = pst.executeQuery();

            while (rs.next()) {
                String fullName = rs.getString("user_fullname");
                String userName = rs.getString("user_name");
                String role = rs.getString("role");
                model.addRow(new Object[]{fullName, userName, role, "View/Edit/Delete"});
                backupModel.addRow(new Object[]{fullName, userName, role}); // Add to backup
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    // Method for exporting data to CSV
    public void exportToCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save CSV file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setSelectedFile(new File("employee_list.csv"));
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".csv")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".csv");
            }

            try (FileWriter csvWriter = new FileWriter(fileToSave)) {
                csvWriter.append("Full Name,E-mail,Role\n");
                for (int i = 0; i < employeeTable.getRowCount(); i++) {
                    for (int j = 0; j < employeeTable.getColumnCount(); j++) {
                        Object value = employeeTable.getValueAt(i, j);
                        if (value != null) {
                            csvWriter.append(value.toString());
                        }
                        csvWriter.append(j == employeeTable.getColumnCount() - 1 ? "\n" : ",");
                    }
                }
                JOptionPane.showMessageDialog(null, "CSV saved successfully at " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving CSV: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Save command canceled by user.");
        }
    }

    // Method for saving changes
    private void saveChanges() {
        // This will iterate through the table rows and update the database with any changes
        try {
            for (int i = 0; i < employeeTable.getRowCount(); i++) {
                String fullName = employeeTable.getValueAt(i, 0).toString();
                String userName = employeeTable.getValueAt(i, 1).toString();
                String role = employeeTable.getValueAt(i, 2).toString();

                String sql = "UPDATE login SET user_fullname = ?, role = ? WHERE user_name = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, fullName);
                pst.setString(2, role);
                pst.setString(3, userName);
                pst.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Changes saved successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saving changes: " + e.getMessage());
        }
    }

    // Method to reset the table (reload original data from the database)
    private void resetTable() {
        model.setRowCount(0); // Clear current model
        for (int i = 0; i < backupModel.getRowCount(); i++) {
            model.addRow(new Object[]{
                backupModel.getValueAt(i, 0),
                backupModel.getValueAt(i, 1),
                backupModel.getValueAt(i, 2),
                "View/Edit/Delete"
            });
        }
        JOptionPane.showMessageDialog(null, "Table reset to original data.");
    }

    // Button Renderer class (for displaying buttons in the Action column)
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER)); // Center-align the buttons
            JButton viewButton = new JButton("View");
            JButton editButton = new JButton("Edit");
            JButton deleteButton = new JButton("Delete");

            viewButton.setBackground(Color.BLUE);
            editButton.setBackground(Color.BLUE);
            deleteButton.setBackground(Color.RED); // Red for delete
            viewButton.setForeground(Color.WHITE);
            editButton.setForeground(Color.WHITE);
            deleteButton.setForeground(Color.WHITE);
            add(viewButton);
            add(editButton);
            add(deleteButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Button Editor class (for handling button actions)
    class ButtonEditor extends DefaultCellEditor {
        protected JPanel panel;
        protected JButton viewButton, editButton, deleteButton;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center-align the buttons
            viewButton = new JButton("View");
            editButton = new JButton("Edit");
            deleteButton = new JButton("Delete");

            viewButton.addActionListener(e -> viewEmployeeDetails(getSelectedRow()));
            editButton.addActionListener(e -> editEmployeeDetails(getSelectedRow()));
            deleteButton.addActionListener(e -> deleteEmployee(getSelectedRow()));

            panel.add(viewButton);
            panel.add(editButton);
            panel.add(deleteButton);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }

        private int getSelectedRow() {
            return employeeTable.getSelectedRow();
        }

        private void viewEmployeeDetails(int row) {
            // Implement view employee details logic
            String fullName = model.getValueAt(row, 0).toString();
            String userName = model.getValueAt(row, 1).toString();
            String role = model.getValueAt(row, 2).toString();
            JOptionPane.showMessageDialog(null, "Viewing details for:\nFull Name: " + fullName + "\nE-mail: " + userName + "\nRole: " + role);
        }

        private void editEmployeeDetails(int row) {
            // Implement edit employee details logic
            String fullName = model.getValueAt(row, 0).toString();
            String userName = model.getValueAt(row, 1).toString();
            String role = model.getValueAt(row, 2).toString();

            String newFullName = JOptionPane.showInputDialog(null, "Edit Full Name:", fullName);
            String newRole = JOptionPane.showInputDialog(null, "Edit Role:", role);

            if (newFullName != null && newRole != null) {
                model.setValueAt(newFullName, row, 0);
                model.setValueAt(userName, row, 1); // Keep userName same as it is the identifier
                model.setValueAt(newRole, row, 2);
            }
        }

        private void deleteEmployee(int row) {
            // Implement delete employee logic
            String fullName = model.getValueAt(row, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + fullName + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(row);
                // Perform database deletion if needed
                JOptionPane.showMessageDialog(null, fullName + " has been deleted.");
            }
        }
    }

    // Method to create a new employee
    private void newEmployee (){
        newEmploye newEmployePage = new newEmploye(parentFrame, true); // Create an instance of the sell page
            newEmployePage.setVisible(true);

        
    }

  
}
