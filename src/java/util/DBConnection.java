package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/clinicdb?useSSL=false&serverTimezone=UTC";

    private static final String USER = "Ashwin";

    private static final String PASSWORD = "Zinger@2026";

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Loading MySQL Driver...");

            con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Database connection successful!");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found.");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }

        return con;
    }
}