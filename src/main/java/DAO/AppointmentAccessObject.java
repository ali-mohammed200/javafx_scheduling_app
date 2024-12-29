package DAO;

import Helper.DateConverter;
import Model.Appointments;
import Model.Customers;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.TimeZone;

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

//     1 - 4p
//    2-3 start and end are not between these
//    12-3 start is between these
//    3-4 end is between these

    public static ResultSet getAppointmentByOverlap(int appointmentID, OffsetDateTime start, OffsetDateTime end, Integer Customer_ID) throws SQLException {
        String query = "SELECT * FROM appointments WHERE Customer_ID = ? ";
        query += "AND ((Start BETWEEN ? AND ?) OR (End BETWEEN ? AND ?) OR (Start < ? AND End > ?))";
        if (appointmentID > 0) {
            query += " AND Appointment_ID != ?";
        }
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);

        st.setInt(1, Customer_ID);
        st.setObject(2,  start);
        st.setObject(3,  end);
        st.setObject(4,  start);
        st.setObject(5,  end);
        st.setObject(6,  start);
        st.setObject(7,  end);

        if (appointmentID > 0) {
            st.setInt(8,  appointmentID);
        }

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
        query += " (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By,  Customer_ID, User_ID, Contact_ID) ";
        query += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//        Title, Description,
//        Location, Type, Start, End,
//        Create_Date, Created_By, Last_Update, Last_Updated_By,
//        Customer_ID, User_ID, Contact_ID
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);

        st.setString(1, appt.getTitle());
        st.setString(2, appt.getDescription());
        st.setString(3, appt.getLocation());
        st.setString(4, appt.getType());
        st.setObject(5, appt.getStart());
        st.setObject(6, appt.getEnd());
        st.setObject(7,  appt.getCreateDate());
        st.setString(8, appt.getCreatedBy());
        st.setObject(9,  appt.getLastUpdate());
        st.setString(10, appt.getLastUpdatedBy());
        st.setInt(11, appt.getCustomerId());
        st.setInt(12, appt.getUserId());
        st.setInt(13, appt.getContactId());

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
        st.setObject(5,  appt.getStart());
        st.setObject(6,  appt.getEnd());
        st.setObject(7,  appt.getLastUpdate());
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
