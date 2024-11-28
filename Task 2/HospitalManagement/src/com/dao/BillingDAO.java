package com.dao;

import com.models.Billing;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {
    public void addBill(Billing bill) {
        String query = "INSERT INTO Billing (PatientID, Amount, Date, Status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bill.getPatientID());
            stmt.setDouble(2, bill.getAmount());
            stmt.setString(3, bill.getDate());
            stmt.setString(4, bill.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Billing> getAllBills() {
        List<Billing> bills = new ArrayList<>();
        String query = "SELECT * FROM Billing";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Billing bill = new Billing();
                bill.setBillID(rs.getInt("BillID"));
                bill.setPatientID(rs.getInt("PatientID"));
                bill.setAmount(rs.getDouble("Amount"));
                bill.setDate(rs.getString("Date"));
                bill.setStatus(rs.getString("Status"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    public void updateBillStatus(int billID, String status) {
        String query = "UPDATE Billing SET Status = ? WHERE BillID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, billID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
