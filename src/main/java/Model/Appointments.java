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

    public static FilteredList<Appointments> getAllAppointments() {
        return allAppointments;
    }

    public static void setAllAppointments(ObservableList<Appointments> appointments) {
        allAppointments = new FilteredList<>(appointments);
    }

    public static Predicate<Appointments> getAppointmentsWithin15Minutes() {
        Predicate<Appointments> within15 = i -> isWithin15Min(i.getUserId(), i.getStart());
        return within15;
    }

    public static Predicate<Appointments> getCurrentWeekAppointments() {
        Predicate<Appointments> withinWeek = i -> isInCurrentWeek(i.getStart());
        return withinWeek;
    }

    public static Predicate<Appointments> getCurrentMonthAppointments() {
        Predicate<Appointments> withinMonth = i -> isInCurrentMonth(i.getStart());
        return withinMonth;
    }

    public static boolean isWithin15Min(int userID, OffsetDateTime dateTime) {
        OffsetDateTime now = OffsetDateTime.now(ZoneId.systemDefault());
        return !dateTime.isBefore(now) && !dateTime.isAfter(now.plusMinutes(15)) && userID == MainController.getCurrentUser().getUserID();
    }

    public static boolean isInCurrentWeek(OffsetDateTime dateTime) {
        OffsetDateTime now = OffsetDateTime.now(ZoneId.systemDefault());
        OffsetDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toLocalDate().atStartOfDay(now.getOffset()).toOffsetDateTime();
        OffsetDateTime endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).toLocalDate().atTime(23, 59, 59).atOffset(now.getOffset());
        return !dateTime.isBefore(startOfWeek) && !dateTime.isAfter(endOfWeek);
    }

    public static boolean isInCurrentMonth(OffsetDateTime dateTime) {
        OffsetDateTime now = OffsetDateTime.now(ZoneId.systemDefault());
        OffsetDateTime startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth()).toLocalDate().atStartOfDay(now.getOffset()).toOffsetDateTime();
        OffsetDateTime endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate().atTime(23, 59, 59).atOffset(now.getOffset());
        return !dateTime.isBefore(startOfMonth) && !dateTime.isAfter(endOfMonth);
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OffsetDateTime getStart() {
        return start;
    }

    public void setStart(OffsetDateTime start) {
        this.start = start;
    }

    public OffsetDateTime getEnd() {
        return end;
    }

    public void setEnd(OffsetDateTime end) {
        this.end = end;
    }

    public String formattedStart() {
        return DateConverter.readableDateFormat(this.start);
    }

    public String formattedEnd() {
        return DateConverter.readableDateFormat(this.end);
    }

    public OffsetDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(OffsetDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public OffsetDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(OffsetDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }


    @Override
    public String toString() {
        return "Appointments{" + "appointmentID=" + appointmentID + ", title='" + title + '\'' + ", description='" + description + '\'' + ", location='" + location + '\'' + ", type='" + type + '\'' + ", start=" + start + ", end=" + end + ", createDate=" + createDate + ", createdBy='" + createdBy + '\'' + ", lastUpdate=" + lastUpdate + ", lastUpdatedBy='" + lastUpdatedBy + '\'' + ", customerId=" + customerId + ", userId=" + userId + ", contactId=" + contactId + ", contacts=" + contacts + '}';
    }
}
