package com.services;

import com.dao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthService {
    public static String login(String username, String password) {
        String query = "SELECT Role FROM Users WHERE Username = ? AND Password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}