package controller;

import dao.AppointmentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Appointment;

@WebServlet("/AddAppointmentServlet")
public class AddAppointmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Read form data
        String name    = request.getParameter("patientName");
        String surname = request.getParameter("patientSurname");
        String contact = request.getParameter("contactNumber");
        String doctor  = request.getParameter("doctorName");
        String date    = request.getParameter("appointmentDate");
        String time    = request.getParameter("appointmentTime");
        String reason  = request.getParameter("reason");

        try {
            // 2. Build the model object
            Appointment a = new Appointment(name, surname, contact, doctor,
                    LocalDate.parse(date), LocalTime.parse(time), reason);

            // 3. Save through the DAO (JDBC)
            boolean saved = new AppointmentDAO().addAppointment(a);

            // 4. Respond
            if (saved) {
                response.sendRedirect("viewAppointments.jsp");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<p>Could not save the appointment. That slot may already be booked.</p>");
            out.println("<a href='index.html'>Back to form</a>");
        }
    }
}
