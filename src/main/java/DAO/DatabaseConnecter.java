package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnecter is used to handle connections to the database
 */
public class DatabaseConnecter {
    private static final String username = "root";
    private static final String password = "Password123";
    private static final String url = "jdbc:mysql://localhost:3306/client_schedule?connectionTimeZone=UTC";

    private static final String labUsername = "sqlUser";
    private static final String labPassword = "Passw0rd!";
    private static final String labUrl = "jdbc:mysql://localhost/client_schedule?connectionTimeZone = SERVER";

    private static Connection connection = null;

    /**
     * Function to get connection
     * @return Connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Function to set the connection
     * Used on initial entry of application
     */
    public static void setConnection() {
        try {
            DatabaseConnecter.connection = DriverManager.getConnection(url, username, password); // Local
//            DatabaseConnecter.connection = DriverManager.getConnection(labUrl, labUsername, labPassword); // Lab
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to close the connection
     * Used on application exit
     */
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
