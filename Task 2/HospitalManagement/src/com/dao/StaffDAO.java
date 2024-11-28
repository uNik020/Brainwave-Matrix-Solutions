package com.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.models.Staff;

public class StaffDAO {
    public void addStaff(Staff staff) {
        String query = "INSERT INTO Staff (Name, Role, ContactNumber, Department) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getRole());
            stmt.setString(3, staff.getContactNumber());
            stmt.setString(4, staff.getDepartment());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM Staff";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffID(rs.getInt("StaffID"));
                staff.setName(rs.getString("Name"));
                staff.setRole(rs.getString("Role"));
                staff.setContactNumber(rs.getString("ContactNumber"));
                staff.setDepartment(rs.getString("Department"));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    public void updateStaff(Staff staff) {
        String query = "UPDATE Staff SET Name = ?, Role = ?, ContactNumber = ?, Department = ? WHERE StaffID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getRole());
            stmt.setString(3, staff.getContactNumber());
            stmt.setString(4, staff.getDepartment());
            stmt.setInt(5, staff.getStaffID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStaff(int staffID) {
        String query = "DELETE FROM Staff WHERE StaffID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, staffID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}