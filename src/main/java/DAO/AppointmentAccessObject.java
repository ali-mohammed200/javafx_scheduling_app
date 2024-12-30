package DAO;

import Model.Appointments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

/**
 * AppointmentAccessObject used to access data in the appointments table
 */
public class AppointmentAccessObject {
    /**
     * Function to get all appointments with contacts from the database
     * @return ResultSet
     * @throws SQLException
     */
    public static ResultSet getAllAppointmentsWithContacts() throws SQLException {
        String query = "SELECT * FROM appointments " + "LEFT JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID\n";

        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = st.executeQuery();

        return result;
    }

    /**
     * Function to check appointment overlaps
     * If an appointmentID is less than 1, it is not used in the sql query
     * Valid appointmentID is passed in ModifyAppointmentController
     * @param appointmentID
     * @param start
     * @param end
     * @param Customer_ID
     * @return ResultSet
     * @throws SQLException
     */
    public static ResultSet getAppointmentByOverlap(int appointmentID, OffsetDateTime start, OffsetDateTime end, Integer Customer_ID) throws SQLException {
        String query = "SELECT * FROM appointments WHERE Customer_ID = ? ";
        query += "AND ((Start BETWEEN ? AND ?) OR (End BETWEEN ? AND ?) OR (Start < ? AND End > ?))";
        if (appointmentID > 0) {
            query += " AND Appointment_ID != ?";
        }
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);

        st.setInt(1, Customer_ID);
        st.setObject(2, start);
        st.setObject(3, end);
        st.setObject(4, start);
        st.setObject(5, end);
        st.setObject(6, start);
        st.setObject(7, end);

        if (appointmentID > 0) {
            st.setInt(8, appointmentID);
        }

        ResultSet result = st.executeQuery();

        System.out.println(st);
        return result;
    }

    /**
     * Function to create an appointment in the database
     * @param appt
     * @return int
     * @throws SQLException
     */
    public static int createAppointment(Appointments appt) throws SQLException {
        String query = "INSERT INTO appointments ";
        query += " (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By,  Customer_ID, User_ID, Contact_ID) ";
        query += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);

        st.setString(1, appt.getTitle());
        st.setString(2, appt.getDescription());
        st.setString(3, appt.getLocation());
        st.setString(4, appt.getType());
        st.setObject(5, appt.getStart());
        st.setObject(6, appt.getEnd());
        st.setObject(7, appt.getCreateDate());
        st.setString(8, appt.getCreatedBy());
        st.setObject(9, appt.getLastUpdate());
        st.setString(10, appt.getLastUpdatedBy());
        st.setInt(11, appt.getCustomerId());
        st.setInt(12, appt.getUserId());
        st.setInt(13, appt.getContactId());

        System.out.println(st);
        return st.executeUpdate();
    }

    /**
     * Function to update an appointment in the database
     * @param appt
     * @return int
     * @throws SQLException
     */
    public static int updateAppointment(Appointments appt) throws SQLException {
        String query = "UPDATE appointments SET ";
        query += "Title = ?, Description = ?, Location = ?, ";
        query += "Type = ?, Start = ?, End = ?, ";
        query += "Last_Update = ?, Last_Updated_By = ?, ";
        query += "Customer_ID = ?, User_ID = ?, Contact_ID = ? ";
        query += "WHERE Appointment_ID = ?;";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);

        st.setString(1, appt.getTitle());
        st.setString(2, appt.getDescription());
        st.setString(3, appt.getLocation());
        st.setString(4, appt.getType());
        st.setObject(5, appt.getStart());
        st.setObject(6, appt.getEnd());
        st.setObject(7, appt.getLastUpdate());
        st.setString(8, appt.getLastUpdatedBy());
        st.setInt(9, appt.getCustomerId());
        st.setInt(10, appt.getUserId());
        st.setInt(11, appt.getContactId());
        st.setInt(12, appt.getAppointmentID());

        System.out.println(st);
        return st.executeUpdate();
    }

    /**
     * Function to delete an appointment in the database
     * @param apptID
     * @return int
     * @throws SQLException
     */
    public static int deleteAppointment(int apptID) throws SQLException {
        String query = "DELETE FROM appointments WHERE Appointment_ID = ?";
        Connection conn = DatabaseConnecter.getConnection();
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, apptID);
        return st.executeUpdate();
    }
}
