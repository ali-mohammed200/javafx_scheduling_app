package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnecter {
    private static final String username = "root";
    private static final String password = "Password123";
    private static final String url = "jdbc:mysql://localhost:3306/client_schedule?connectionTimeZone=UTC";
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
