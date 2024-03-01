package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportsQueryObject {
    //    TODO: fix and build
    public static ResultSet getUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE User_name = ? AND Password = ?";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, password);
        ResultSet result = st.executeQuery();

        return result;
    }
}
