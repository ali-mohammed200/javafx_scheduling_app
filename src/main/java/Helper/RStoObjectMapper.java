package Helper;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;

public class RStoObjectMapper {
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

            Customers customer = new Customers(cust_id, cust_name, cust_address, cust_postalCode, cust_phone, cust_divisionID);
            FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions(fdl_divisionID, fdl_division, fdl_countryID);
            Countries country = new Countries(countryID, countryName);

            firstLevelDivision.setCountry(country);
            customer.setFirstLevelDivision(firstLevelDivision);

            System.out.println(customer);

            customers.add(customer);
        }

        return customers;
    }

    public static ObservableList<Appointments> rsToApptList(ResultSet rs) throws SQLException {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList(new ArrayList<Appointments>());
//        Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date,
//                Created_By, Last_Update, Last_Updated_By, Customer_ID,
//                User_ID, Contact_ID, Contact_ID, Contact_Name, Email
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
//                //This is it
//                System.out.println(OffsetDateTime.now().toString());
//
//                ZoneId myz = ZoneId.systemDefault();
//                ZonedDateTime zdt = ZonedDateTime.now(myz);
//                System.out.println(zdt);
//
//                OffsetDateTime ts = rs.getObject("Create_Date", OffsetDateTime.class);
//                System.out.println(ts.toInstant().atOffset(zdt.getOffset()));
//                // up to this point

            Appointments appointment = new Appointments(appt_id, appt_title, appt_description, appt_location, appt_type, appt_start, appt_end, appt_custId, appt_userId, appt_contactId);
            Contacts contant = new Contacts(appt_contactId, contactName, contactEmail);

            appointment.setContacts(contant);

            System.out.println(appointment);

            appointments.add(appointment);
        }

        return appointments;
    }

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
}
//Customer_ID, Customer_Name, Address, Postal_Code, Phone,
//Create_Date, Created_By, Last_Update, Last_Updated_By,
//Division_ID, Division_ID, Division, Create_Date, Created_By,
//Last_Update, Last_Updated_By, Country_ID, Country_ID, Country,
//Create_Date, Created_By, Last_Update, Last_Updated_By