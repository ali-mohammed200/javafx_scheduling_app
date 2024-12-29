package DAO;

import java.sql.*;

public class DatabaseConnecter {
    private static String username = "root"; // These credentials should be in an env file
    private static String password = "Password123";
    private static String url = "jdbc:mysql://localhost:3306/client_schedule?connectionTimeZone=UTC";
    private static Connection connection = null;

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection() {
        try {
            DatabaseConnecter.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
