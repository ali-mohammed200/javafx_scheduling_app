package Controller;

import DAO.ContactAccessObject;
import DAO.CountryAccessObject;
import DAO.CustomerAccessObject;
import DAO.FirstLevelDivisionAccessObject;
import Helper.RStoObjectMapper;
import Model.*;
import com.c195_pa.schedulingsystem.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    @FXML
    private Stage stage;
    @FXML private TextField inputID;
    @FXML private TextField inputTitle;
    @FXML private TextField inputDescription;
    @FXML private TextField inputLocation;
    @FXML private ComboBox dropDownContact;
    @FXML private TextField inputType;
    @FXML private DatePicker startDate;
    @FXML private ComboBox startHour;
    @FXML private ComboBox startMin;
    @FXML private ComboBox startUnit;
    @FXML private DatePicker endDate;
    @FXML private ComboBox endHour;
    @FXML private ComboBox endMin;
    @FXML private ComboBox endUnit;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private Label warningLabel;
    @FXML private TextField inputUserID;
    @FXML private TextField inputCustomerID;

//    @FXML
//    private void onCountryAction(ActionEvent actionEvent) {
//        System.out.println(dropDownCountry.getValue());
//        Countries country = (Countries) dropDownCountry.getValue();
//        dropDownDivision.setItems(fdlListByCountry(country.getCountryID()));
//    }

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

        String type = inputType.getText();
        if (type == "") {
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
        } catch (NumberFormatException e) {
            warning += "Customer ID is not a integer. ";
        }

        int userID = 0;
        try {
            userID = Integer.parseInt(inputUserID.getText());
        } catch (NumberFormatException e) {
            warning += "User ID is not a integer. ";
        }

        System.out.println(title);
        System.out.println(description);
        System.out.println(location);
        System.out.println(contactValue);
        System.out.println(startDateValue);
        System.out.println(startHourValue);
        System.out.println(startMinValue);
        System.out.println(startUnitValue);
        System.out.println(endDateValue);
        System.out.println(endHourValue);
        System.out.println(endMinValue);
        System.out.println(endUnitValue);
        System.out.println(userID);
        System.out.println(customerID);

        if (warning.length() > 0) {
            warningLabel.setText("Exception:\n" + warning);
        } else {
//            TODO: Build Date Time Object Using Values Above
//            TODO: Convert to ETC or UTC or Local
//            TODO: Check Rubric
//            TODO: Create Appointment and Contact obj
//            TODO: Insert into DB and Refresh APPTLIST()
//            Users currentUser = MainController.getCurrentUser();
//            FirstLevelDivisions firstLevelDivision = (FirstLevelDivisions) dropDownDivision.getValue();
//            Customers customer = new Customers(name, address, postalCode,
//                    phoneNumber, OffsetDateTime.now(), currentUser.getUserName(), OffsetDateTime.now(),
//                    currentUser.getUserName(), firstLevelDivision.getDivisionId());
//
//            CustomerAccessObject.createCustomer(customer);
            onCancel(actionEvent); //close add parts window
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
        mainController.setActiveTab(1);
        stage.show();
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
            hours.add(String.valueOf(i));
        }
        return hours;
    }

    public static ObservableList<String> getMinutes() {
//      https://stackoverflow.com/questions/19933499/how-to-print-the-integer-00-instead-of-java-printing-0
        ObservableList<String> minutes = FXCollections.observableArrayList(new ArrayList<String>());
        for (int i = 0; i < 60; i++) {
            minutes.add(String.format("%02d", i));
        }
        return minutes;
    }

    public static ObservableList<String> getTimeUnits() {
        return FXCollections.observableArrayList("AM", "PM");
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

    }
}
