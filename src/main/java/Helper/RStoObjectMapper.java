package Helper;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 * RStoObjectMapper a helper class which is used to take ResultSets and turn them into relevant Model Objects
 */
public class RStoObjectMapper {
    /**
     * Function to turn a ResultSet to an ObservableList of Customers
     * @param rs rs
     * @return ObservableList Customers
     * @throws SQLException SQLException
     */
    public static ObservableList<Customers> rsToCustomerList(ResultSet rs) throws SQLException {
        ObservableList<Customers> customers = FXCollections.observableArrayList(new ArrayList<Customers>());
        while (rs.next()) {
            System.out.println("\ndb\n");

            int cust_id = rs.getInt("Customer_ID");
            String cust_name = rs.getString("Customer_Name");
            String cust_address = rs.getString("Address");
            String cust_postalCode = rs.getString("Postal_Code");
            String cust_phone = rs.getString("Phone");
            int cust_divisionID = rs.getInt("Division_ID");

            int fdl_divisionID = rs.getInt("Division_ID");
            String fdl_division = rs.getString("Division");
            int fdl_countryID = rs.getInt("Country_ID");

            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");

            FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions(fdl_divisionID, fdl_division, fdl_countryID);
            Countries country = new Countries(countryID, countryName);

            firstLevelDivision.setCountry(country);

            if ("UK".equals(country.getCountryName())) {
                Customers customer = new PremiumCustomers(cust_id, cust_name, cust_address, cust_postalCode, cust_phone, cust_divisionID);
                PremiumCustomers premiumCustomer = (PremiumCustomers) customer;

                premiumCustomer.setFirstLevelDivision(firstLevelDivision);
                System.out.println(premiumCustomer);
                customers.add(premiumCustomer);
            } else {
                Customers customer = new Customers(cust_id, cust_name, cust_address, cust_postalCode, cust_phone, cust_divisionID);

                customer.setFirstLevelDivision(firstLevelDivision);
                System.out.println(customer);
                customers.add(customer);
            }
        }

        return customers;
    }

    /**
     * Function to turn a ResultSet to an ObservableList of Appointments
     * @param rs rs
     * @return ObservableList Appointments
     * @throws SQLException SQLException
     */
    public static ObservableList<Appointments> rsToApptList(ResultSet rs) throws SQLException {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList(new ArrayList<Appointments>());
        while (rs.next()) {
            System.out.println("\ndb\n");

            int appt_id = rs.getInt("Appointment_ID");
            String appt_title = rs.getString("Title");
            String appt_description = rs.getString("Description");
            String appt_location = rs.getString("Location");
            String appt_type = rs.getString("Type");
            OffsetDateTime appt_start = DateConverter.convertFromUTCtoLocal(rs.getObject("Start", OffsetDateTime.class));
            OffsetDateTime appt_end = DateConverter.convertFromUTCtoLocal(rs.getObject("End", OffsetDateTime.class));
            int appt_custId = rs.getInt("Customer_ID");
            int appt_userId = rs.getInt("User_ID");
            int appt_contactId = rs.getInt("Contact_ID");

            String contactName = rs.getString("Contact_Name");
            String contactEmail = rs.getString("Email");

            Appointments appointment = new Appointments(appt_id, appt_title, appt_description, appt_location, appt_type, appt_start, appt_end, appt_custId, appt_userId, appt_contactId);
            Contacts contact = new Contacts(appt_contactId, contactName, contactEmail);

            appointment.setContacts(contact);

            System.out.println(appointment);

            appointments.add(appointment);
        }

        return appointments;
    }

    /**
     * Function to turn a ResultSet to an ObservableList of Countries
     * @param rs rs
     * @return ObservableList Countries
     * @throws SQLException SQLException
     */
    public static ObservableList<Countries> rsToCountryList(ResultSet rs) throws SQLException {
        ObservableList<Countries> countries = FXCollections.observableArrayList(new ArrayList<Countries>());
        while (rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");

            Countries country = new Countries(countryID, countryName);
            countries.add(country);
        }

        return countries;
    }

    /**
     * Function to turn a ResultSet to an ObservableList of FirstLevelDivisions
     * @param rs rs
     * @return ObservableList FirstLevelDivisions
     * @throws SQLException SQLException
     */
    public static ObservableList<FirstLevelDivisions> rsToFDLList(ResultSet rs) throws SQLException {
        ObservableList<FirstLevelDivisions> firstLevelDivisions = FXCollections.observableArrayList(new ArrayList<FirstLevelDivisions>());
        while (rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String fdl = rs.getString("Division");
            int fdlID = rs.getInt("Division_ID");

            FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions(fdlID, fdl, countryID);
            firstLevelDivisions.add(firstLevelDivision);
        }

        return firstLevelDivisions;
    }

    /**
     * Function to turn a ResultSet to an ObservableList of Contacts
     * @param rs rs
     * @return ObservableList Contacts
     * @throws SQLException SQLException
     */
    public static ObservableList<Contacts> rsToContactList(ResultSet rs) throws SQLException {
        ObservableList<Contacts> contacts = FXCollections.observableArrayList(new ArrayList<Contacts>());
        while (rs.next()) {
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            Contacts contact = new Contacts(contactID, contactName, email);
            contacts.add(contact);
        }
        return contacts;
    }
}


