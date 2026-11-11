package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import model.Appointment;
import util.DBConnection;

public class AppointmentDAO {

    // Q3.2 - Add Appointment (CREATE)
    public boolean addAppointment(Appointment a) {
        String sql = "INSERT INTO appointments "
                + "(patient_name, patient_surname, contact_number, doctor_name, "
                + "appointment_date, appointment_time, reason) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getPatientName());
            ps.setString(2, a.getPatientSurname());
            ps.setString(3, a.getContactNumber());
            ps.setString(4, a.getDoctorName());
            ps.setDate(5, Date.valueOf(a.getAppointmentDate()));
            ps.setTime(6, Time.valueOf(a.getAppointmentTime()));
            ps.setString(7, a.getReason());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();   // e.g. duplicate doctor/date/time slot
            return false;
        }
    }

    // Q3.3 - View Appointments (READ)
    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments ORDER BY appointment_date, appointment_time";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Appointment a = new Appointment();
                a.setAppointmentId(rs.getInt("appointment_id"));
                a.setPatientName(rs.getString("patient_name"));
                a.setPatientSurname(rs.getString("patient_surname"));
                a.setContactNumber(rs.getString("contact_number"));
                a.setDoctorName(rs.getString("doctor_name"));
                a.setAppointmentDate(rs.getDate("appointment_date").toLocalDate());
                a.setAppointmentTime(rs.getTime("appointment_time").toLocalTime());
                a.setReason(rs.getString("reason"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
