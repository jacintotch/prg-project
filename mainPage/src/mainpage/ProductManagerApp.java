package mainpage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ProductManagerApp {
    private final JFrame frame;
    private JTextField nameField, descriptionField, priceField, stockField, barcodeField, searchField;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private final String currentUsername;

    public ProductManagerApp(String username) {
        this.currentUsername = username;

        if (currentUserHasAccess()) {
            // User has access, initialize components
        } else {
            JOptionPane.showMessageDialog(null, "Access denied.");
        }

        frame = new JFrame("Product Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(173, 216, 230)); 

        JPanel formPanel = createFormPanel();
        JScrollPane scrollPane = createProductTable();

        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);

        loadProducts();
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for flexible layout
        formPanel.setBackground(new Color(101, 203, 255)); // Background color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Search field
        gbc.gridx = 0; // Column 0 for labels
        gbc.gridy = 0; // Row 0
        formPanel.add(createLabel("Search by Barcode:"), gbc);

        gbc.gridx = 1; // Column 1 for text fields
        searchField = createTextField();
        formPanel.add(searchField, gbc);

        gbc.gridx = 2; // Column 2 for button
        JButton searchButton = createButton("Search");
        searchButton.addActionListener(new SearchProductAction());
        formPanel.add(searchButton, gbc);

        // Name field
        gbc.gridx = 0; // Reset to column 0
        gbc.gridy = 1; // Move to next row
        formPanel.add(createLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField = createTextField();
        formPanel.add(nameField, gbc);

        // Description field
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(createLabel("Description:"), gbc);

        gbc.gridx = 1;
        descriptionField = createTextField();
        formPanel.add(descriptionField, gbc);

        // Price field
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(createLabel("Price:"), gbc);

        gbc.gridx = 1;
        priceField = createTextField();
        formPanel.add(priceField, gbc);

        // Stock Quantity field
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(createLabel("Stock Quantity:"), gbc);

        gbc.gridx = 1;
        stockField = createTextField();
        formPanel.add(stockField, gbc);

        // Barcode field
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(createLabel("Barcode:"), gbc);

        gbc.gridx = 1;
        barcodeField = createTextField();
        formPanel.add(barcodeField, gbc);

        // Add button
        gbc.gridx = 1;
        gbc.gridy = 6;
        JButton addButton = createButton("Add/Update Product");
        addButton.addActionListener(new AddProductAction());
        formPanel.add(addButton, gbc);

        return formPanel;
    }

    private JScrollPane createProductTable() {
        tableModel = new DefaultTableModel(new String[]{"Name", "Description", "Price", "Stock", "Barcode"}, 0);
        productTable = new JTable(tableModel);
        productTable.setBackground(Color.WHITE);
        productTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        return scrollPane;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(0, 102, 204));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return button;
    }

    private class AddProductAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String description = descriptionField.getText();
            String priceText = priceField.getText();
            String stockText = stockField.getText();
            String barcode = barcodeField.getText();

            if (name.isEmpty() || priceText.isEmpty() || stockText.isEmpty() || barcode.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double price;
            int stock;
            try {
                price = Double.parseDouble(priceText);
                stock = Integer.parseInt(stockText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for price and stock.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection conn = getConnection()) {
                // Checking if product already exists
                String checkSql = "SELECT * FROM products WHERE barcode = ?";
                PreparedStatement checkPst = conn.prepareStatement(checkSql);
                checkPst.setString(1, barcode);
                ResultSet rs = checkPst.executeQuery();

                if (rs.next()) {
                    // Update existing product
                    String updateSql = "UPDATE products SET name = ?, description = ?, price = ?, stock_quantity = ? WHERE barcode = ?";
                    PreparedStatement updatePst = conn.prepareStatement(updateSql);
                    updatePst.setString(1, name);
                    updatePst.setString(2, description);
                    updatePst.setDouble(3, price);
                    updatePst.setInt(4, stock);
                    updatePst.setString(5, barcode);
                    updatePst.executeUpdate();

                    // Update table
                    int row = getRowIndexByBarcode(barcode);
                    if (row != -1) {
                        tableModel.setValueAt(name, row, 0);
                        tableModel.setValueAt(description, row, 1);
                        tableModel.setValueAt(price, row, 2);
                        tableModel.setValueAt(stock, row, 3);
                    }
                    JOptionPane.showMessageDialog(frame, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Insert new product
                    String insertSql = "INSERT INTO products (name, description, price, stock_quantity, barcode) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement insertPst = conn.prepareStatement(insertSql);
                    insertPst.setString(1, name);
                    insertPst.setString(2, description);
                    insertPst.setDouble(3, price);
                    insertPst.setInt(4, stock);
                    insertPst.setString(5, barcode);
                    insertPst.executeUpdate();

                    tableModel.addRow(new Object[]{name, description, price, stock, barcode});
                    JOptionPane.showMessageDialog(frame, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }

                clearFields();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SearchProductAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String barcode = searchField.getText().trim();

            if (barcode.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a barcode to search.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection conn = getConnection()) {
                String sql = "SELECT name, description, price, stock_quantity FROM products WHERE barcode = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, barcode);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    nameField.setText(rs.getString("name"));
                    descriptionField.setText(rs.getString("description"));
                    priceField.setText(String.valueOf(rs.getDouble("price")));
                    stockField.setText(String.valueOf(rs.getInt("stock_quantity")));
                    barcodeField.setText(barcode); // Set barcode in the input field
                } else {
                    JOptionPane.showMessageDialog(frame, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int getRowIndexByBarcode(String barcode) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 4).equals(barcode)) {
                return i;
            }
        }
        return -1;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "user", "Dinadois2$");
    }

    private boolean currentUserHasAccess() {
        // Dummy implementation; replace with actual logic
        Set<String> allowedUsers = new HashSet<>();
        allowedUsers.add("manager");
        allowedUsers.add("admin");
        return allowedUsers.contains(currentUsername);
    }

    private void loadProducts() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT name, description, price, stock_quantity, barcode FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock_quantity");
                String barcode = rs.getString("barcode");

                tableModel.addRow(new Object[]{name, description, price, stock, barcode});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        descriptionField.setText("");
        priceField.setText("");
        stockField.setText("");
        barcodeField.setText("");
    }

 
}
