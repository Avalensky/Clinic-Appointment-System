s<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, model.Appointment, dao.AppointmentDAO"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Appointments</title>
    <style>
        table { border-collapse: collapse; width: 90%; }
        th, td { border: 1px solid #999; padding: 8px; text-align: left; }
        th { background: #eee; }
    </style>
</head>
<body>
    <h2>Booked Appointments</h2>

    <%
        AppointmentDAO dao = new AppointmentDAO();
        List<Appointment> appointments = dao.getAllAppointments();
    %>

    <table>
        <tr>
            <th>ID</th><th>Patient</th><th>Contact</th><th>Doctor</th>
            <th>Date</th><th>Time</th><th>Reason</th>
        </tr>
        <% for (Appointment a : appointments) { %>
        <tr>
            <td><%= a.getAppointmentId() %></td>
            <td><%= a.getPatientName() %> <%= a.getPatientSurname() %></td>
            <td><%= a.getContactNumber() %></td>
            <td><%= a.getDoctorName() %></td>
            <td><%= a.getAppointmentDate() %></td>
            <td><%= a.getAppointmentTime() %></td>
            <td><%= a.getReason() %></td>
        </tr>
        <% } %>
    </table>

    <p><a href="index.html">Book another appointment</a></p>
</body>
</html>
