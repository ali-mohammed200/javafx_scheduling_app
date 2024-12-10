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

    public static ResultSet getAllCustomersWithFDLData() throws SQLException {
        String query = "SELECT * FROM customers\n" +
                "LEFT JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID\n" +
                "LEFT JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID;";
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
        System.out.println(customer.getCreateDate());
        String query = "INSERT INTO customers ";
        query += " (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) ";
        query += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, customer.getCustomerName());
        st.setString(2, customer.getAddress());
        st.setString(3, customer.getPostalCode());
        st.setString(4, customer.getPhone());
        st.setTimestamp(5, Timestamp.valueOf( DateConverter.formatForTimestamp(customer.getCreateDate())));
        st.setString(6, customer.getCreatedBy());
        st.setTimestamp(7,  Timestamp.valueOf(DateConverter.formatForTimestamp(customer.getLastUpdate())));
        st.setString(8, customer.getLastUpdatedBy());
        st.setInt(9, customer.getDivisionId());
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

    public static int deleteCustomerWithAppts(int customerID) throws SQLException {
//        https://stackoverflow.com/questions/3331992/how-to-delete-from-multiple-tables-in-mysql
//        DELETE FROM `articles`, `comments`
//        USING `articles`,`comments`
//        WHERE `comments`.`article_id` = `articles`.`id` AND `articles`.`id` = 4
        String query = "DELETE FROM appointments, customers USING appointments, customers WHERE appointments.Customer_ID = customers.Customer_ID AND customers.Customer_ID = ?";
        System.out.println(query);
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, customerID);
        return st.executeUpdate();
    }

}
