package com.dao;

import com.models.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public void addAppointment(Appointment appointment) {
        String query = "INSERT INTO Appointments (PatientID, DoctorID, Date, Time, Status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, appointment.getPatientID());
            stmt.setInt(2, appointment.getDoctorID());
            stmt.setString(3, appointment.getDate());
            stmt.setString(4, appointment.getTime());
            stmt.setString(5, appointment.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM Appointments";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentID(rs.getInt("AppointmentID"));
                appointment.setPatientID(rs.getInt("PatientID"));
                appointment.setDoctorID(rs.getInt("DoctorID"));
                appointment.setDate(rs.getString("Date"));
                appointment.setTime(rs.getString("Time"));
                appointment.setStatus(rs.getString("Status"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public void updateAppointment(Appointment appointment) {
        String query = "UPDATE Appointments SET PatientID = ?, DoctorID = ?, Date = ?, Time = ?, Status = ? WHERE AppointmentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, appointment.getPatientID());
            stmt.setInt(2, appointment.getDoctorID());
            stmt.setString(3, appointment.getDate());
            stmt.setString(4, appointment.getTime());
            stmt.setString(5, appointment.getStatus());
            stmt.setInt(6, appointment.getAppointmentID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAppointment(int appointmentID) {
        String query = "DELETE FROM Appointments WHERE AppointmentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, appointmentID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
