package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FirstLevelDivisionAccessObject is used to access data in the first_level_divisions table
 */
public class FirstLevelDivisionAccessObject {
    /**
     * Function to get all first_level_divisions with countries from the database
     * @param Country_ID Country_ID
     * @return ResultSet
     * @throws SQLException SQLException
     */
    public static ResultSet getFirstLevelDivisionsByCountry(int Country_ID) throws SQLException {
        String query = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, Country_ID);
        ResultSet result = st.executeQuery();

        return result;
    }
}
