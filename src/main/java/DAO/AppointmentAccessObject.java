package DAO;

import Helper.DateConverter;
import Model.Appointments;
import Model.Customers;

import java.sql.*;
import java.time.OffsetDateTime;

public class AppointmentAccessObject {
//    TODO: fix and build

    public static ResultSet getAllAppointments() throws SQLException {
        String query = "SELECT * FROM appointments";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = st.executeQuery();

        return result;
    }

    public static ResultSet getAllAppointmentsWithContacts() throws SQLException {
        String query = "SELECT * FROM appointments " +
        "LEFT JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID\n";

        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = st.executeQuery();

        return result;
    }

//    •  Appointment_ID
//•  Title
//•  Description
//•  Location
//•  Contact
//•  Type
//•  Start Date and Time
//•  End Date and Time
//•  Customer_ID
//•  User_ID

    public static ResultSet getAppointmentByOverlap(OffsetDateTime start, OffsetDateTime end) throws SQLException {
        String query = "SELECT * FROM appointments WHERE (Start BETWEEN ? AND ?) OR (End BETWEEN ? AND ?)";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);

        st.setTimestamp(6, Timestamp.valueOf( DateConverter.formatForTimestamp(start)));
        st.setTimestamp(7, Timestamp.valueOf( DateConverter.formatForTimestamp(end)));
        st.setTimestamp(6, Timestamp.valueOf( DateConverter.formatForTimestamp(start)));
        st.setTimestamp(7, Timestamp.valueOf( DateConverter.formatForTimestamp(end)));

        ResultSet result = st.executeQuery();

        System.out.println(st);
        return result;
    }

    public static ResultSet getAppointmentByColumn(String colName, int colID) throws SQLException {
        String query = "SELECT * FROM appointments WHERE " + colName + " = ?";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, colID);
        ResultSet result = st.executeQuery();

        System.out.println(st);
        return result;
    }
    public static int createAppointment(Appointments appt) throws SQLException {
        String query = "INSERT INTO appointments ";
        query += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//        Appointment_ID, Title, Description,
//        Location, Type, Start, End,
//        Create_Date, Created_By, Last_Update, Last_Updated_By,
//        Customer_ID, User_ID, Contact_ID
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, appt.getAppointmentID());
        st.setString(2, appt.getTitle());
        st.setString(3, appt.getDescription());
        st.setString(4, appt.getLocation());
        st.setString(5, appt.getType());
        st.setTimestamp(6, Timestamp.valueOf( DateConverter.formatForTimestamp(appt.getStart())));
        st.setTimestamp(7, Timestamp.valueOf( DateConverter.formatForTimestamp(appt.getEnd())));
        st.setTimestamp(8, Timestamp.valueOf( DateConverter.formatForTimestamp(appt.getCreateDate())));
        st.setString(9, appt.getCreatedBy());
        st.setTimestamp(10,  Timestamp.valueOf(DateConverter.formatForTimestamp(appt.getLastUpdate())));
        st.setString(11, appt.getLastUpdatedBy());
        st.setInt(12, appt.getCustomerId());
        st.setInt(13, appt.getUserId());
        st.setInt(14, appt.getContactId());

        System.out.println(st);
        return st.executeUpdate();
    }
    public static int updateAppointment(Appointments appt) throws SQLException {
        String query = "UPDATE appointments SET ";
        query += "Title = ?, Description = ?, Location = ?, ";
        query += "Type = ?, Start = ?, End = ?, ";
        query += "Last_Update = ?, Last_Updated_By = ?, ";
        query += "Customer_ID = ?, User_ID = ?, Contact_ID = ? ";
        query += "WHERE Appointment_ID = ?;";

//        Appointment_ID, Title, Description,
//        Location, Type, Start, End,
//        Create_Date, Created_By, Last_Update, Last_Updated_By,
//        Customer_ID, User_ID, Contact_ID
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);

        st.setString(1, appt.getTitle());
        st.setString(2, appt.getDescription());
        st.setString(3, appt.getLocation());
        st.setString(4, appt.getType());
        st.setTimestamp(5, Timestamp.valueOf( DateConverter.formatForTimestamp(appt.getStart())));
        st.setTimestamp(6, Timestamp.valueOf( DateConverter.formatForTimestamp(appt.getEnd())));
        st.setTimestamp(7,  Timestamp.valueOf(DateConverter.formatForTimestamp(appt.getLastUpdate())));
        st.setString(8, appt.getLastUpdatedBy());
        st.setInt(9, appt.getCustomerId());
        st.setInt(10, appt.getUserId());
        st.setInt(11, appt.getContactId());
        st.setInt(12, appt.getAppointmentID());

        System.out.println(st);
        return st.executeUpdate();
    }
    public static int deleteAppointment(int apptID) throws SQLException {
        String query = "DELETE FROM appointments WHERE Appointment_ID = ?";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, apptID);
        return st.executeUpdate();
    }
    public static int deleteAppointmentByColumn(String colName, int colID) throws SQLException {
        String query = "DELETE FROM appointments WHERE " + colName + " = ?";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, colID);
        return st.executeUpdate();
    }
}
