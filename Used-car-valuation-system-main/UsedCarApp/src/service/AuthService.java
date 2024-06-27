package service;

import database.CreateDatabase;

import java.sql.*;

public class AuthService {
    public boolean login(String account, String password) {
        String sql = "SELECT * FROM user_login WHERE user_account = ? AND password = ?";
        try (Connection conn = CreateDatabase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, account);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
