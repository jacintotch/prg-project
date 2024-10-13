package mainpage;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Jacinto Celestino Tchayevala
 */
public class login extends javax.swing.JFrame {
  
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    public login() {
        initComponents();
         password.setEchoChar('*');
         conn = MainPage.conn();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        password = new javax.swing.JPasswordField();
        username = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        imgLogin = new javax.swing.JLabel();
        passwordTitle = new javax.swing.JLabel();
        usernameTitle = new javax.swing.JLabel();
        showPassword = new javax.swing.JCheckBox();
        loginButton = new javax.swing.JButton();
        head = new javax.swing.JLabel();
        errorPass = new javax.swing.JLabel();
        errorUser1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        password.setBackground(new java.awt.Color(0, 0, 0));
        password.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        password.setForeground(new java.awt.Color(255, 255, 255));
        password.setBorder(null);
        password.setCaretColor(new java.awt.Color(255, 255, 255));

        username.setBackground(new java.awt.Color(0, 0, 0));
        username.setFont(new java.awt.Font("Yu Gothic", 1, 16)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setBorder(null);
        username.setCaretColor(new java.awt.Color(255, 255, 255));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        imgLogin.setBackground(new java.awt.Color(0, 0, 0));
        imgLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/UI/The Ultimate Double Cheeseburger.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        passwordTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        passwordTitle.setText("PASSWORD");

        usernameTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        usernameTitle.setText("USERNAME");

        showPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        showPassword.setText("Show password");
        showPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordActionPerformed(evt);
            }
        });

        loginButton.setBackground(new java.awt.Color(102, 102, 102));
        loginButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("LOGIN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        loginButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginButtonKeyPressed(evt);
            }
        });

        head.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        head.setText("EJEPAN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                .addComponent(password))
                            .addComponent(passwordTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(showPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(errorPass, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(head, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(errorUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(head)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(errorUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorPass, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPassword)
                .addGap(18, 18, 18)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
       
    }//GEN-LAST:event_usernameActionPerformed

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordActionPerformed
    if (showPassword.isSelected()) {
        password.setEchoChar((char) 0); 
    } else {
        password.setEchoChar('*');  
}
    }//GEN-LAST:event_showPasswordActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
// Get the username and password from the input fields
String username1 = username.getText();
char[] password1 = password.getPassword();

// Check if username or password fields are empty before proceeding
if (username1.isEmpty() || password1.length == 0) {
    errorUser1.setText("Username or password cannot be empty");
    errorUser1.setForeground(Color.RED); 
    return; 
}

try {
    String sqlquery = "SELECT user_password FROM login WHERE user_name=?";
    pst = conn.prepareStatement(sqlquery); 
    pst.setString(1, username1); 
    rs = pst.executeQuery(); 

    if (rs.next()) {
        // Retrieve the stored password hash from the result set
        String storedHash = rs.getString("user_password");
        String hashedInputPassword = PasswordUtils.hashPassword(String.valueOf(password1));

        // Compare the hashed input password with the stored hash
        if (hashedInputPassword.equals(storedHash)) {
            sell sellPage = new sell(); // Create a new instance of the 'sell' page
            sellPage.setVisible(true); 
            this.dispose(); 
        } else {
            errorUser1.setText("Incorrect credentials");
            errorUser1.setForeground(Color.RED); 
            password.setText(""); 
        }
    } else {
        errorUser1.setText("Incorrect credentials");
        errorUser1.setForeground(Color.RED); 
        password.setText(""); 
    }
} catch (SQLException e) {
    errorUser1.setText("Database error: " + e.getMessage()); 
    errorUser1.setForeground(Color.RED); 
}

    }//GEN-LAST:event_loginButtonActionPerformed

    private void loginButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginButtonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginButtonKeyPressed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorPass;
    private javax.swing.JLabel errorUser1;
    private javax.swing.JLabel head;
    private javax.swing.JLabel imgLogin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordTitle;
    private javax.swing.JCheckBox showPassword;
    private javax.swing.JTextField username;
    private javax.swing.JLabel usernameTitle;
    // End of variables declaration//GEN-END:variables
}
