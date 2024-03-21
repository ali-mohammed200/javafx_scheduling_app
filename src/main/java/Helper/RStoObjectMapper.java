package Helper;

import Model.Countries;
import Model.Customers;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
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