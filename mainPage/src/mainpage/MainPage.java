package mainpage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * MainPage class handles the connection to the database and user login logic.
 * It also checks if the current user has a specific role, such as "Administrator."
 * 
 * @author Jacinto Celestino Tchayevala
 * 
 */
public class MainPage {

    // Assume currentUsername stores the username of the logged-in user
    public static String currentUsername = "guest"; 

 
    public static Connection conn() {
        try {
            String url = "jdbc:mysql://localhost:3306/pos";
            String user = "user";
            String password = "Dinadois2$";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

 
public static boolean currentUserHasAccess() {
    boolean hasAccess = false;
    Connection conn = conn(); // Ensure the connection method is correct
    try {
        String sql = "SELECT role FROM login WHERE user_name = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, currentUsername);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String userRole = rs.getString("role");
            
            // Check if the role is "Administrator", "Manager", or "Director"
            if (userRole.equalsIgnoreCase("Administrator") || 
                userRole.equalsIgnoreCase("Manager") || 
                userRole.equalsIgnoreCase("Director")) {
                hasAccess = true;
            }
        }
        rs.close();
        pst.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error checking user role: " + e.getMessage());
    }
    return hasAccess;
}



    public static void main(String[] args) {
        login loginFrame = new login();
        loginFrame.setVisible(true);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        
 /* newEmploye employePane = new newEmploye(null, true);
employePane.setVisible(true);
employePane.pack();*/
      
       
    }
}