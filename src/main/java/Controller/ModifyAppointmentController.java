package Controller;

import DAO.AppointmentAccessObject;
import DAO.ContactAccessObject;
import Helper.DateConverter;
import Helper.RStoObjectMapper;
import Model.Appointments;
import Model.Contacts;
import Model.Users;
import com.c195_pa.schedulingsystem.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {
    private static Appointments appointment;
    @FXML
    private Stage stage;
    @FXML
    private TextField inputID;
    @FXML
    private TextField inputTitle;
    @FXML
    private TextField inputDescription;
    @FXML
    private TextField inputLocation;
    @FXML
    private ComboBox dropDownContact;
    @FXML
    private TextField inputType;
    @FXML
    private DatePicker startDate;
    @FXML
    private ComboBox startHour;
    @FXML
    private ComboBox startMin;
    @FXML
    private ComboBox startUnit;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox endHour;
    @FXML
    private ComboBox endMin;
    @FXML
    private ComboBox endUnit;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField inputUserID;
    @FXML
    private TextField inputCustomerID;

    public static void setAppointment(Appointments selectedAppointment) {
        appointment = selectedAppointment;
        System.out.println(appointment);
    }

    public static ObservableList<Contacts> contactList() {
        try {
            ResultSet rs = ContactAccessObject.getContacts();
            return RStoObjectMapper.rsToContactList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<String> getHours() {
        ObservableList<String> hours = FXCollections.observableArrayList(new ArrayList<String>());
        for (int i = 1; i <= 12; i++) {
            hours.add(String.format("%02d", i));
        }
        return hours;
    }

    public static ObservableList<String> getMinutes() {
        ObservableList<String> minutes = FXCollections.observableArrayList(new ArrayList<String>());
        for (int i = 0; i < 60; i++) {
            minutes.add(String.format("%02d", i));
        }
        return minutes;
    }

    public static ObservableList<String> getTimeUnits() {
        return FXCollections.observableArrayList("AM", "PM");
    }

    @FXML
    private void onSave(ActionEvent actionEvent) throws SQLException, IOException {
        String warning = "";
        String title = inputTitle.getText();
        if (title == "") {
            warning += "Title is empty. ";
        }

        String description = inputDescription.getText();
        if (description == "") {
            warning += "Description is empty. ";
        }

        String location = inputLocation.getText();
        if (location == "") {
            warning += "Location is empty. ";
        }

        Object contactValue = dropDownContact.getValue();
        if (contactValue == null) {
            warning += "Contact is empty. ";
        }

        String typeValue = inputType.getText();
        if (typeValue == "") {
            warning += "Type is empty. ";
        }


        LocalDate startDateValue = startDate.getValue();
        if (startDateValue == null) {
            warning += "Start Date is empty. ";
        }

        String startHourValue = (String) startHour.getValue();
        if (startHourValue == null) {
            warning += "Start Hour is empty. ";
        }

        String startMinValue = (String) startMin.getValue();
        if (startMinValue == null) {
            warning += "Start Minute is empty. ";
        }

        String startUnitValue = (String) startUnit.getValue();
        if (startUnitValue == null) {
            warning += "Start Unit is empty. ";
        }

        LocalDate endDateValue = endDate.getValue();
        if (endDateValue == null) {
            warning += "End Date is empty. ";
        }

        String endHourValue = (String) endHour.getValue();
        if (endHourValue == null) {
            warning += "End Hour is empty. ";
        }

        String endMinValue = (String) endMin.getValue();
        if (endMinValue == null) {
            warning += "End Minute is empty. ";
        }

        String endUnitValue = (String) endUnit.getValue();
        if (endUnitValue == null) {
            warning += "End Unit is empty. ";
        }

        int customerID = 0;
        try {
            customerID = Integer.parseInt(inputCustomerID.getText());
            if (customerID < 1) {
                warning += "Customer ID is not valid. ";
            }
        } catch (NumberFormatException e) {
            warning += "Customer ID is not an integer. ";
        }

        int userID = 0;
        try {
            userID = Integer.parseInt(inputUserID.getText());
            if (userID < 1) {
                warning += "User ID is not valid. ";
            }
        } catch (NumberFormatException e) {
            warning += "User ID is not an integer. ";
        }

        String stringStartTime = DateConverter.buildTimeString(startHourValue, startMinValue, startUnitValue);
        OffsetDateTime odtStartLocal = DateConverter.buildOffsetDateTimeObject(stringStartTime, startDateValue);

        String stringEndTime = DateConverter.buildTimeString(endHourValue, endMinValue, endUnitValue);
        OffsetDateTime odtEndLocal = DateConverter.buildOffsetDateTimeObject(stringEndTime, endDateValue);

        if (!DateConverter.withinBusinessHours(odtStartLocal) || !DateConverter.withinBusinessHours(odtEndLocal)) {
            warning += "Time(s) are not within business hours: 8am - 10pm (Weekdays). ";
        }

        OffsetDateTime odtStartUTC = DateConverter.convertFromLocaltoUTC(odtStartLocal);
        OffsetDateTime odtEndUTC = DateConverter.convertFromLocaltoUTC(odtEndLocal);

        if (odtStartUTC.isAfter(odtEndUTC)) {
            warning += "Start can not be after end. ";
        }

        int appointmentID = Integer.parseInt(inputID.getText());

        if (warning.length() > 0) {
            warningLabel.setText("Exception:\n" + warning);
        } else {
            ResultSet rs = AppointmentAccessObject.getAppointmentByOverlap(appointmentID, odtStartUTC, odtEndUTC, customerID);
            if (rs.next()) {
                warningLabel.setText("Exception:\n" + "Overlapping appointments found!");
            } else {
                warningLabel.setText("");
                Users currentUser = MainController.getCurrentUser();
                Contacts contact = (Contacts) contactValue;
                Appointments appointment = new Appointments(appointmentID, title, description, location, typeValue, odtStartUTC, odtEndUTC, currentUser, customerID, userID, contact.getContactID());
                try {
                    AppointmentAccessObject.updateAppointment(appointment);
                    onCancel(actionEvent);
                } catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println(e);
                    warningLabel.setText("Exception:\n" + "Customer ID or User ID does not exist");
                }

            }

        }
    }

    @FXML
    private void onCancel(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management - Main");
        stage.setScene(scene);
        MainController mainController = fxmlLoader.getController();
        mainController.setActiveTab(2);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropDownContact.setItems(contactList());

        startHour.setItems(getHours());
        endHour.setItems(getHours());

        startMin.setItems(getMinutes());
        endMin.setItems(getMinutes());

        startUnit.setItems(getTimeUnits());
        endUnit.setItems(getTimeUnits());


        System.out.println(appointment);
        inputID.setText(String.valueOf(appointment.getAppointmentID()));
        inputTitle.setText(appointment.getTitle());
        inputDescription.setText(appointment.getDescription());
        inputLocation.setText(appointment.getLocation());
        dropDownContact.setValue(appointment.getContacts());
        inputType.setText(appointment.getType());
        startDate.setValue(appointment.getStart().toLocalDate());

        int sHour;
        String sUnit;
        if (appointment.getStart().getHour() > 12) {
            sHour = appointment.getStart().getHour() - 12;
            sUnit = "PM";
        } else if (appointment.getStart().getHour() == 0) {
            sHour = 12;
            sUnit = "AM";
        } else {
            sHour = appointment.getStart().getHour();
            sUnit = "AM";
        }
        startHour.setValue((String.format("%02d", sHour)));
        startMin.setValue((String.format("%02d", appointment.getStart().getMinute())));
        startUnit.setValue(sUnit);
        endDate.setValue(appointment.getEnd().toLocalDate());

        int eHour;
        String eUnit;
        if (appointment.getEnd().getHour() > 12) {
            eHour = appointment.getEnd().getHour() - 12;
            eUnit = "PM";
        } else if (appointment.getEnd().getHour() == 0) {
            eHour = 12;
            eUnit = "AM";
        } else {
            eHour = appointment.getEnd().getHour();
            eUnit = "AM";
        }
        endHour.setValue((String.format("%02d", eHour)));
        endMin.setValue((String.format("%02d", appointment.getEnd().getMinute())));
        endUnit.setValue(eUnit);
        inputCustomerID.setText(String.valueOf(appointment.getCustomerId()));
        inputUserID.setText(String.valueOf(appointment.getUserId()));
    }
}
