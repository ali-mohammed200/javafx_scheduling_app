package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivisionAccessObject {
    public static ResultSet getFirstLevelDivision() throws SQLException {
        String query = "SELECT Division_ID, Division, Country_ID FROM first_level_divisions";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = st.executeQuery();

        return result;
    }

    public static ResultSet getFirstLevelDivisionByCountry(int Country_ID) throws SQLException {
        String query = "SELECT Division_ID, Division, Country_ID FROM first_level_divisions WHERE Country_ID = ?";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, Country_ID);
        ResultSet result = st.executeQuery();

        return result;
    }
}
