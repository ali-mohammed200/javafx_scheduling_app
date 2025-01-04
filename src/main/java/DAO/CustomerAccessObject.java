package DAO;

import Model.Customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CustomerAccessObject used to access data in the customers table
 */
public class CustomerAccessObject {
    /**
     * Function to get all customers with first_level_divisions from the database
     * @return ResultSet
     * @throws SQLException SQLException
     */
    public static ResultSet getAllCustomersWithFDLData() throws SQLException {
        String query = "SELECT * FROM customers\n" + "LEFT JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID\n" + "LEFT JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID;";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = st.executeQuery();

        return result;
    }

    /**
     * Function to create a customer in the database
     * @param customer customer
     * @return int
     * @throws SQLException SQLException
     */
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
        st.setObject(5, customer.getCreateDate());
        st.setString(6, customer.getCreatedBy());
        st.setObject(7, customer.getLastUpdate());
        st.setString(8, customer.getLastUpdatedBy());
        st.setInt(9, customer.getDivisionId());
        System.out.println(st);
        return st.executeUpdate();
    }

    /**
     * Function to update a customer in the database
     * @param customer customer
     * @return int
     * @throws SQLException SQLException
     */
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
        st.setObject(5, customer.getLastUpdate());
        st.setString(6, customer.getLastUpdatedBy());
        st.setInt(7, customer.getDivisionId());
        st.setInt(8, customer.getCustomerId());
        System.out.println(st);
        return st.executeUpdate();
    }

    /**
     * Function to delete a customer in the database along with their associated appointments
     * @param customerID customerID
     * @return int
     * @throws SQLException SQLException
     */
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
        int response = st.executeUpdate();
        if(response == 0){ // Nothing was deleted since they had no appts, so just delete the customer
            response = deleteCustomer(customerID);
        }
        System.out.println("deleted response value " + response);
        return response;
    }

    /**
     * Function to delete a customer in the database
     * @param customerID customerID
     * @return int
     * @throws SQLException SQLException
     */
    public static int deleteCustomer(int customerID) throws SQLException {
        String query = "DELETE FROM customers WHERE Customer_ID = ?";
        System.out.println(query);
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, customerID);
        int response = st.executeUpdate();
        return response;
    }
}
