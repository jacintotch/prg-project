package mainpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * MainPage class handles the connection to the database and user login logic.
 * It also checks if the current user has a specific role, such as "Administrator."
 * 
 * @author Jacinto Celestino Tchayevala
 * 
 */
public class newEmploye extends javax.swing.JDialog {

        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        
    public newEmploye(JFrame parentFrame, boolean modal) {
        
        initComponents();
        user_password.setEchoChar('*');
        conn = MainPage.conn();
        
    }






   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txt_fullname = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        user_password = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_role = new javax.swing.JComboBox<>();
        showPassword = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(101, 203, 255));

        jButton1.setBackground(new java.awt.Color(242, 242, 220));
        jButton1.setFont(new java.awt.Font("Segoe UI Black", 2, 18)); // NOI18N
        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt_fullname.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N

        txt_username.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        user_password.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Full name");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("E-mail");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Password");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Position");

        txt_role.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Select Role", "Administrator", "Cashier", "Manager", "Director"}));
        txt_role.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_roleActionPerformed(evt);
            }
        });

        showPassword.setText("Show password");
        showPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Registration ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(user_password)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fullname)
                            .addComponent(txt_username)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_role, 0, 253, Short.MAX_VALUE)
                            .addComponent(showPassword)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel5)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_role, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(user_password, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(showPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
conn = MainPage.conn();
String fullname = txt_fullname.getText(); 
    String username = txt_username.getText();
    char[] password = user_password.getPassword();  // char array for better security
    String selectedRole = (String) txt_role.getSelectedItem();
    
    try {
        // Hash the password using SHA-256
        String hashedPassword = PasswordUtils.hashPassword(String.valueOf(password));
        
        String sqlCheck = "SELECT COUNT(*) FROM login WHERE user_name = ?";
        pst = conn.prepareStatement(sqlCheck);
        pst.setString(1, username);
        rs = pst.executeQuery();
        
        if (rs.next() && rs.getInt(1) > 0) {
            JOptionPane.showMessageDialog(null, username + " is already registered");
            return;
        }
        
        String sqlInsert = "INSERT INTO login (user_fullname, user_name, user_password, role) VALUES (?, ?, ?, ?)";
        pst = conn.prepareStatement(sqlInsert);
        pst.setString(1, fullname);
        pst.setString(2, username);
        pst.setString(3, hashedPassword);  // Store the hashed password
        pst.setString(4, selectedRole);
        
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "User added successfully!");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add user.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    } finally {
        if (rs != null) try { rs.close(); } catch (SQLException e) { /* handle exception */ }
        if (pst != null) try { pst.close(); } catch (SQLException e) { /* handle exception */ }
    }
 
            
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_roleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_roleActionPerformed
        JComboBox<String> roleComboBox = new JComboBox<>(new String[] {"Select Role", "Administrator", "Cashier", "Manager", "Director"});
        String selectedRole = (String) roleComboBox.getSelectedItem();

            if (selectedRole.equals("Select Role")) {
              // Handle as optional (no valid role selected)
            } else {
             // Process the selected role
            System.out.println("Selected role: " + selectedRole);
            }
    }//GEN-LAST:event_txt_roleActionPerformed

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordActionPerformed
           if (showPassword.isSelected()) {
        user_password.setEchoChar((char) 0); // Show password
    } else {
        user_password.setEchoChar('*'); // Hide password 
}
    }//GEN-LAST:event_showPasswordActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox showPassword;
    private javax.swing.JTextField txt_fullname;
    private javax.swing.JComboBox<String> txt_role;
    private javax.swing.JTextField txt_username;
    private javax.swing.JPasswordField user_password;
    // End of variables declaration//GEN-END:variables
}
