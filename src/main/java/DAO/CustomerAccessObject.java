package DAO;

import Model.Customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerAccessObject {
    //    TODO: fix and build

    public static ResultSet getCustomers() throws SQLException {
        String query = "SELECT * FROM customers";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = st.executeQuery();

        return result;
    }

    public static ResultSet updateCustomer(Customers customer, String username, int divisionId) throws SQLException {
        String query = "UPDATE customers SET ";
        query += "Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, ";
        query += "Last_Update = ?, Last_Updated_By = ?, Division_ID = ?";
        query += "WHERE Customer_ID = ?";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, customer.getCustomerName());
        st.setString(2, customer.getAddress());
        st.setString(3, customer.getPostalCode());
        st.setString(4, customer.getPhone());
//        st.setTimestamp(5, customer.getLastUpdate());
        st.setString(6, username);
        st.setInt(7, divisionId);
        st.setInt(3, customer.getCustomerId());
        ResultSet result = st.executeQuery();

        return result;
    }

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
