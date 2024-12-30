package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ContactAccessObject used to access data in the contacts table
 */
public class ContactAccessObject {
    /**
     * Function to get all contacts from the database
     * @return ResultSet
     * @throws SQLException
     */
    public static ResultSet getContacts() throws SQLException {
        String query = "SELECT * FROM contacts";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = st.executeQuery();

        return result;
    }
}