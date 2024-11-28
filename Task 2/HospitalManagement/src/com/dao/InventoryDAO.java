package com.dao;

import com.models.Inventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    public void addItem(Inventory item) {
        String query = "INSERT INTO Inventory (ItemName, Quantity, Supplier, ExpiryDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, item.getItemName());
            stmt.setInt(2, item.getQuantity());
            stmt.setString(3, item.getSupplier());
            stmt.setString(4, item.getExpiryDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Inventory> getAllItems() {
        List<Inventory> items = new ArrayList<>();
        String query = "SELECT * FROM Inventory";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Inventory item = new Inventory();
                item.setItemID(rs.getInt("ItemID"));
                item.setItemName(rs.getString("ItemName"));
                item.setQuantity(rs.getInt("Quantity"));
                item.setSupplier(rs.getString("Supplier"));
                item.setExpiryDate(rs.getString("ExpiryDate"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void updateItem(Inventory item) {
        String query = "UPDATE Inventory SET ItemName = ?, Quantity = ?, Supplier = ?, ExpiryDate = ? WHERE ItemID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, item.getItemName());
            stmt.setInt(2, item.getQuantity());
            stmt.setString(3, item.getSupplier());
            stmt.setString(4, item.getExpiryDate());
            stmt.setInt(5, item.getItemID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int itemID) {
        String query = "DELETE FROM Inventory WHERE ItemID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, itemID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
