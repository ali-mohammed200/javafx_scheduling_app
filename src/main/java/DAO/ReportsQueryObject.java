package DAO;

import Helper.DateConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

/**
 * ReportsQueryObject is used to access the database for running report queries
 */
public class ReportsQueryObject {
    /**
     * Function to get total number of customer appointments by type and month
     * The resultset is converted within the same function as opposed to
     * the other Database Access Objects which use the RStoObjectMapper
     * @return String
     * @throws SQLException
     */
    public static String getReport1() throws SQLException {
        String query = "SELECT Monthname(Start) Month, Type, COUNT(*) Count FROM appointments GROUP BY Monthname(Start), Type;";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = st.executeQuery();

        String report = "Month, Type, Count\n\n";

        while (result.next()) {
            report += result.getString("Month") + ", " + result.getString("Type") + ", " + result.getInt("Count") + "\n";
        }

        return report;
    }

    /**
     * Function to get the schedule for each contact in the organization
     * The resultset is converted within the same function as opposed to
     * the other Database Access Objects which use the RStoObjectMapper
     * @return String
     * @throws SQLException
     */
    public static String getReport2() throws SQLException {
        String query = "SELECT * FROM appointments\n" + "LEFT JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID\n" + "ORDER BY appointments.Contact_ID ASC, start ASC;";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = st.executeQuery();

        int currentContact = -1;
        String report = "\t\t\tAppointment_ID, Title, Type, Description, Start, End, Customer_ID\n";

        while (result.next()) {
            int appt_contactId = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            String contactEmail = result.getString("Email");
            if (currentContact != appt_contactId) {
                currentContact = appt_contactId;
                report += "\n" + appt_contactId + " - " + contactName + ", " + contactEmail + "\n\n";
            }

            int appt_id = result.getInt("Appointment_ID");
            String appt_title = result.getString("Title");
            String appt_description = result.getString("Description");
            String appt_type = result.getString("Type");
            OffsetDateTime appt_start = DateConverter.convertFromUTCtoLocal(result.getObject("Start", OffsetDateTime.class));
            OffsetDateTime appt_end = DateConverter.convertFromUTCtoLocal(result.getObject("End", OffsetDateTime.class));
            int appt_custId = result.getInt("Customer_ID");

            report += "\t\t\t";
            report += appt_id + ", " + appt_title + ", " + appt_type + ", " + appt_description + ", ";
            report += DateConverter.readableDateFormat(appt_start) + " - " + DateConverter.readableDateFormat(appt_end) + ", ";
            report += appt_custId + "\n";
        }

        return report;
    }

    /**
     * Function to get customer counts by country
     * The resultset is converted within the same function as opposed to
     * the other Database Access Objects which use the RStoObjectMapper
     * @return String
     * @throws SQLException
     */
    public static String getReport3() throws SQLException {
        String query = "SELECT Country, Count(*) Count FROM customers\n" + "LEFT JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID\n" + "LEFT JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID\n" + "GROUP BY Country;";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = st.executeQuery();

        String report = "Country, Count\n\n";

        while (result.next()) {
            report += result.getString("Country") + ", " + result.getInt("Count") + "\n";
        }

        return report;
    }
}
