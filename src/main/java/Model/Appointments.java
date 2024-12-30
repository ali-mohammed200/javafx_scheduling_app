package Model;

import Controller.MainController;
import Helper.DateConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Appointments Model
 * 3 Lamdas are used in this file to filter the appointment list
 */
public class Appointments {
    private static FilteredList<Appointments> allAppointments = new FilteredList<>(FXCollections.observableList(new ArrayList<Appointments>()));
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private OffsetDateTime start;
    private OffsetDateTime end;
    private OffsetDateTime createDate;
    private String createdBy;
    private OffsetDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;
    private Contacts contacts;

    /**
     * Appointments Constructor
     * @param appointmentID appointmentID
     * @param title title
     * @param description description
     * @param location location
     * @param type type
     * @param start start
     * @param end end
     * @param user user
     * @param customerId customerId
     * @param userId userId
     * @param contactId contactId
     */
    public Appointments(int appointmentID, String title, String description, String location, String type, OffsetDateTime start, OffsetDateTime end, Users user, int customerId, int userId, int contactId) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = DateConverter.convertFromLocaltoUTC(OffsetDateTime.now());
        this.createdBy = user.getUserName();
        this.lastUpdate = DateConverter.convertFromLocaltoUTC(OffsetDateTime.now());
        this.lastUpdatedBy = user.getUserName();
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * Appointments Constructor
     * @param appointmentID appointmentID
     * @param title title
     * @param description description
     * @param location location
     * @param type type
     * @param start start
     * @param end end
     * @param customerId customerId
     * @param userId userId
     * @param contactId contactId
     */
    public Appointments(int appointmentID, String title, String description, String location, String type, OffsetDateTime start, OffsetDateTime end, int customerId, int userId, int contactId) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * Appointments Constructor
     * @param appointmentID appointmentID
     * @param title title
     * @param description description
     * @param location location
     * @param type type
     * @param start start
     * @param end end
     * @param createDate createDate
     * @param createdBy createdBy
     * @param lastUpdate lastUpdate
     * @param lastUpdatedBy lastUpdatedBy
     * @param customerId customerId
     * @param userId userId
     * @param contactId contactId
     */
    public Appointments(int appointmentID, String title, String description, String location, String type, OffsetDateTime start, OffsetDateTime end, OffsetDateTime createDate, String createdBy, OffsetDateTime lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * Function to get all appointments
     * @return FilteredList Appointments
     */
    public static FilteredList<Appointments> getAllAppointments() {
        return allAppointments;
    }

    /**
     * Function to set appointments
     * @param appointments appointments
     */
    public static void setAllAppointments(ObservableList<Appointments> appointments) {
        allAppointments = new FilteredList<>(appointments);
    }

    /**
     * Function to filter the appointment list
     * A lambda is used to take each appointment in the list and filter to the
     * appointments that match the criteria
     * @return Predicate Appointments
     */
    public static Predicate<Appointments> getAppointmentsWithin15Minutes() {
        Predicate<Appointments> within15 = i -> isWithin15Min(i.getUserId(), i.getStart());
        return within15;
    }

    /**
     * Function to filter the appointment list
     * A lambda is used to take each appointment in the list and filter to the
     * appointments that match the criteria
     * @return Predicate Appointments
     */
    public static Predicate<Appointments> getCurrentWeekAppointments() {
        Predicate<Appointments> withinWeek = i -> isInCurrentWeek(i.getStart());
        return withinWeek;
    }

    /**
     * Function to filter the appointment list
     * A lambda is used to take each appointment in the list and filter to the
     * appointments that match the criteria
     * @return Predicate Appointments
     */
    public static Predicate<Appointments> getCurrentMonthAppointments() {
        Predicate<Appointments> withinMonth = i -> isInCurrentMonth(i.getStart());
        return withinMonth;
    }

    /**
     * Function to check an appointment if it is within 15 minutes
     * @param userID userID
     * @param dateTime dateTime
     * @return boolean
     */
    public static boolean isWithin15Min(int userID, OffsetDateTime dateTime) {
        OffsetDateTime now = OffsetDateTime.now(ZoneId.systemDefault());
        return !dateTime.isBefore(now) && !dateTime.isAfter(now.plusMinutes(15)) && userID == MainController.getCurrentUser().getUserID();
    }

    /**
     * Function to check an appointment if it is within the current week
     * @param dateTime dateTime
     * @return boolean
     */
    public static boolean isInCurrentWeek(OffsetDateTime dateTime) {
        OffsetDateTime now = OffsetDateTime.now(ZoneId.systemDefault());
        OffsetDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toLocalDate().atStartOfDay(now.getOffset()).toOffsetDateTime();
        OffsetDateTime endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).toLocalDate().atTime(23, 59, 59).atOffset(now.getOffset());
        return !dateTime.isBefore(startOfWeek) && !dateTime.isAfter(endOfWeek);
    }

    /**
     * Function to check an appointment if it is within the current month
     * @param dateTime dateTime
     * @return boolean
     */
    public static boolean isInCurrentMonth(OffsetDateTime dateTime) {
        OffsetDateTime now = OffsetDateTime.now(ZoneId.systemDefault());
        OffsetDateTime startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth()).toLocalDate().atStartOfDay(now.getOffset()).toOffsetDateTime();
        OffsetDateTime endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate().atTime(23, 59, 59).atOffset(now.getOffset());
        return !dateTime.isBefore(startOfMonth) && !dateTime.isAfter(endOfMonth);
    }

    /**
     * Function to get appointment id
     * @return int
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Function to set the appointment
     * @param appointmentID appointmentID
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Function to get title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Function to set title
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Function to get description
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Function to set description
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Function to get location
     * @return String
     */
    public String getLocation() {
        return location;
    }

    /**
     * Function to set location
     * @param location location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Function to get type
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Function to set type
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Function to get start
     * @return OffsetDateTime
     */
    public OffsetDateTime getStart() {
        return start;
    }

    /**
     * Function to set start
     * @param start start
     */
    public void setStart(OffsetDateTime start) {
        this.start = start;
    }

    /**
     * Function to get end
     * @return OffsetDateTime
     */
    public OffsetDateTime getEnd() {
        return end;
    }

    /**
     * Function to set end
     * @param end end
     */
    public void setEnd(OffsetDateTime end) {
        this.end = end;
    }

    /**
     * Function to format the start
     * @return String
     */
    public String formattedStart() {
        return DateConverter.readableDateFormat(this.start);
    }

    /**
     * Function to format the end
     * @return String
     */
    public String formattedEnd() {
        return DateConverter.readableDateFormat(this.end);
    }

    /**
     * Function to get createDate
     * @return OffsetDateTime
     */
    public OffsetDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Function to set createDate
     * @param createDate createDate
     */
    public void setCreateDate(OffsetDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Function to get createdBy
     * @return String
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Function to set createdBy
     * @param createdBy createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Function to get lastUpdate
     * @return OffsetDateTime
     */
    public OffsetDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Function to set lastUpdate
     * @param lastUpdate lastUpdate
     */
    public void setLastUpdate(OffsetDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Function to get lastUpdatedBy
     * @return String
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Function to set lastUpdatedBy
     * @param lastUpdatedBy lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Function to get customerId
     * @return int
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Function to set customerId
     * @param customerId customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Function to userId
     * @return int
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Function to set userId
     * @param userId userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Function to get contactId
     * @return int
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Function to set contactId
     * @param contactId contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Function to get contacts
     * @return Contacts
     */
    public Contacts getContacts() {
        return contacts;
    }

    /**
     * Function to set contacts
     * @param contacts contacts
     */
    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    /**
     * Function to return string version of model
     * @return String
     */
    @Override
    public String toString() {
        return "Appointments{" + "appointmentID=" + appointmentID + ", title='" + title + '\'' + ", description='" + description + '\'' + ", location='" + location + '\'' + ", type='" + type + '\'' + ", start=" + start + ", end=" + end + ", createDate=" + createDate + ", createdBy='" + createdBy + '\'' + ", lastUpdate=" + lastUpdate + ", lastUpdatedBy='" + lastUpdatedBy + '\'' + ", customerId=" + customerId + ", userId=" + userId + ", contactId=" + contactId + ", contacts=" + contacts + '}';
    }
}
