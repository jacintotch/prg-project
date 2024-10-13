package mainpage;

import javax.swing.*;
import java.awt.*;

public class WindowsPOS extends JPanel {
    private JPanel productPanel;
    private JPanel receiptPanel;
    private JTextField paymentField;
    private JButton checkoutButton;
    private double subTotal = 0;
    private final double vatRate = 0.15;

    public WindowsPOS() {
        createUI();
    }

    private void createUI() {
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 87, 34));
        JLabel headerLabel = new JLabel("Welcome to the Windows POS System");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH); // Add header to main panel

        // Product panel
        productPanel = new JPanel(new GridLayout(0, 4, 20, 20));
        addProducts();

        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(scrollPane, BorderLayout.CENTER); // Add product panel to main panel

        // Receipt panel
        receiptPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        receiptPanel.setBorder(BorderFactory.createTitledBorder("Receipt"));
        add(new JScrollPane(receiptPanel), BorderLayout.EAST); // Add receipt panel to main panel
        addReceiptHeader();

        // Payment section
        JPanel paymentPanel = new JPanel(new FlowLayout());
        paymentField = new JTextField(10);
        checkoutButton = new JButton("Checkout");
        paymentPanel.add(new JLabel("Payment Amount:"));
        paymentPanel.add(paymentField);
        paymentPanel.add(checkoutButton);
        add(paymentPanel, BorderLayout.SOUTH); // Add payment panel to main panel

        // Checkout button action
        checkoutButton.addActionListener(e -> {
            try {
                double payment = Double.parseDouble(paymentField.getText());
                if (payment >= subTotal) {
                    double vat = subTotal * vatRate;
                    double totalBill = subTotal + vat;

                    addReceiptSummary("Sub-Total", subTotal);
                    addReceiptSummary("VAT (15%)", vat);
                    addReceiptSummary("Total Bill", totalBill);

                    subTotal = 0; // Reset subtotal after checkout
                    JOptionPane.showMessageDialog(null, "Thank you for your purchase!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient payment!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid payment amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            paymentField.setText("");
        });
    }

    private void addProducts() {
        addProduct("Bread", 6.50);
        addProduct("Sweets", 0.50);
    }

    private void addProduct(String name, double price) {
        JPanel panel = createProductPanel(name, price);
        productPanel.add(panel);
    }

    private JPanel createProductPanel(String name, double price) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        JLabel productLabel = new JLabel(name);
        JLabel priceLabel = new JLabel("Price: N$ " + String.format("%.2f", price));
        JTextField qtyField = new JTextField("1", 3);

        JButton addButton = new JButton("Add to Cart");
        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> {
            int qty = Integer.parseInt(qtyField.getText());
            double totalItemCost = qty * price;
            subTotal += totalItemCost;

            addReceiptItem(name, qty, price, totalItemCost);
        });

        JPanel infoPanel = new JPanel();
        infoPanel.add(productLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(new JLabel("Qty:"));
        infoPanel.add(qtyField);
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(addButton, BorderLayout.SOUTH);

        return panel;
    }

    private void addReceiptHeader() {
        receiptPanel.add(new JLabel("Item", JLabel.LEFT));
        receiptPanel.add(new JLabel("Qty", JLabel.LEFT));
        receiptPanel.add(new JLabel("Cost/item", JLabel.LEFT));
        receiptPanel.add(new JLabel("Total", JLabel.LEFT));
    }

    private void addReceiptItem(String itemName, int qty, double costPerItem, double totalCost) {
        receiptPanel.add(new JLabel(itemName, JLabel.LEFT));
        receiptPanel.add(new JLabel(String.valueOf(qty), JLabel.LEFT));
        receiptPanel.add(new JLabel(String.format("N$ %.2f", costPerItem), JLabel.LEFT));
        receiptPanel.add(new JLabel(String.format("N$ %.2f", totalCost), JLabel.LEFT));
        receiptPanel.revalidate();
    }

    private void addReceiptSummary(String label, double amount) {
        receiptPanel.add(new JLabel(label + ": ", JLabel.LEFT));
        receiptPanel.add(new JLabel("", JLabel.LEFT)); // Empty cell for alignment
        receiptPanel.add(new JLabel("", JLabel.LEFT)); // Empty cell for alignment
        receiptPanel.add(new JLabel(String.format("N$ %.2f", amount), JLabel.LEFT));
        receiptPanel.revalidate();
    }


}
