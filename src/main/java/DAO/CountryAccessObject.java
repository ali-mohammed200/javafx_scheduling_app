package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CountryAccessObject used to access data in the countries table
 */
public class CountryAccessObject {
    /**
     * Function to get all countries from the database
     * @return ResultSet
     * @throws SQLException SQLException
     */
    public static ResultSet getCountries() throws SQLException {
        String query = "SELECT * FROM countries";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = st.executeQuery();

        return result;
    }
}