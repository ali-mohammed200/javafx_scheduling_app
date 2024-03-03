package DAO;

import Helper.DateConverter;
import Model.Customers;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class CustomerAccessObject {
    public static ResultSet getAllCustomers() throws SQLException {
        String query = "SELECT * FROM customers";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = st.executeQuery();

        return result;
    }

    public static ResultSet getCustomer(int customerID) throws SQLException {
        String query = "SELECT * FROM customers WHERE Customer_ID = ?";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, customerID);
        ResultSet result = st.executeQuery();

        return result;
    }

    public static int createCustomer(Customers customer) throws SQLException {
        String query = "INSERT INTO customers ";
        query += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, customer.getCustomerId());
        st.setString(2, customer.getCustomerName());
        st.setString(3, customer.getAddress());
        st.setString(4, customer.getPostalCode());
        st.setString(5, customer.getPhone());
        st.setTimestamp(6, Timestamp.valueOf( DateConverter.formatForTimestamp(customer.getCreateDate())));
        st.setString(7, customer.getCreatedBy());
        st.setTimestamp(8,  Timestamp.valueOf(DateConverter.formatForTimestamp(customer.getLastUpdate())));
        st.setString(9, customer.getLastUpdatedBy());
        st.setInt(10, customer.getDivisionId());
        System.out.println(st);
        return st.executeUpdate();
    }

    public static int updateCustomer(Customers customer) throws SQLException {
        String query = "UPDATE customers SET ";
        query += "Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, ";
        query += "Last_Update = ?, Last_Updated_By = ?, Division_ID = ?";
        query += " WHERE Customer_ID = ?";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, customer.getCustomerName());
        st.setString(2, customer.getAddress());
        st.setString(3, customer.getPostalCode());
        st.setString(4, customer.getPhone());
        st.setTimestamp(5,  Timestamp.valueOf(DateConverter.formatForTimestamp(customer.getLastUpdate())));
        st.setString(6, customer.getLastUpdatedBy());
        st.setInt(7, customer.getDivisionId());
        st.setInt(8, customer.getCustomerId());
        System.out.println(st);
        return st.executeUpdate();
    }

    public static int deleteCustomer(int customerID) throws SQLException {
        String query = "DELETE FROM customers WHERE Customer_ID = ?";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, customerID);
        return st.executeUpdate();
    }
}
