package Controller;

import DAO.AppointmentAccessObject;
import DAO.CustomerAccessObject;
import DAO.DatabaseConnecter;
import DAO.ReportsQueryObject;
import Helper.DateConverter;
import Helper.RStoObjectMapper;
import Model.Appointments;
import Model.Customers;
import Model.Users;
import com.c195_pa.schedulingsystem.MainApplication;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

/**
 * MainController is used to handle the main logic of the application
 * - Customer Management
 * - Appointment Management
 * - Viewing reports
 *
 * Lamdas are used in this file in the initialize method
 * They are used to set nested data in the tableview cells
 * Example:
 * - Country and FirstLevelDivision Data on the Customers Model
 * - Contact Data on Appointments Model
 */
public class MainController implements Initializable {
    private static Users currentUser;
    private final ToggleGroup group = new ToggleGroup();
    @FXML
    public Tab customerTab;
    @FXML
    public SingleSelectionModel<Tab> selectionModel;
    @FXML
    private Stage stage;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView customerTable;
    @FXML
    private TableColumn cId;
    @FXML
    private TableColumn cName;
    @FXML
    private TableColumn cAddress;
    @FXML
    private TableColumn cPostalCode;
    @FXML
    private TableColumn cPhone;
    @FXML
    private TableColumn cDivId;
    @FXML
    private TableColumn<Customers, String> cDivision;
    @FXML
    private TableColumn<Customers, String> cCountryId;
    @FXML
    private TableColumn<Customers, String> cCountry;
    @FXML
    private Label custWarning;
    @FXML
    private TableView apptTable;
    @FXML
    private TableColumn cApptId;
    @FXML
    private TableColumn cApptTitle;
    @FXML
    private TableColumn cApptDesc;
    @FXML
    private TableColumn cApptLocation;
    @FXML
    private TableColumn cApptType;
    @FXML
    private TableColumn<Appointments, String> cApptStart;
    @FXML
    private TableColumn<Appointments, String> cApptEnd;
    @FXML
    private TableColumn cApptCustId;
    @FXML
    private TableColumn cApptUserId;
    @FXML
    private TableColumn cApptContactId;
    @FXML
    private TableColumn<Appointments, String> cApptContactName;
    @FXML
    private TableColumn<Appointments, String> cApptContactEmail;
    @FXML
    private RadioButton weekButton;
    @FXML
    private RadioButton monthButton;
    @FXML
    private RadioButton allButton;
    @FXML
    private Label apptWarning;
    @FXML
    private Text greetingLabel;
    @FXML
    private TableView upcomingApptTable;
    @FXML
    private TableColumn upcomingApptTableID;
    @FXML
    private TableColumn<Appointments, String> upcomingApptTableStart;
    @FXML
    private Text reports1Text;
    @FXML
    private Text reports2Text;
    @FXML
    private Text reports3Text;

    /**
     * Function to get CurrentUser
     * @return Users
     */
    public static Users getCurrentUser() {
        return currentUser;
    }

    /**
     * Function to set CurrentUser
     * @param user user
     */
    public static void setCurrentUser(Users user) {
        currentUser = user;
    }

    /**
     * Function to return an ObservableList of Customers
     * @return ObservableList Customers
     */
    public static ObservableList<Customers> customerList() {
        try {
            ResultSet rs = CustomerAccessObject.getAllCustomersWithFDLData();
            return RStoObjectMapper.rsToCustomerList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to return an ObservableList of Appointments
     * @return ObservableList Appointments
     */
    public static ObservableList<Appointments> apptList() {
        try {
            ResultSet rs = AppointmentAccessObject.getAllAppointmentsWithContacts();
            Appointments.setAllAppointments(RStoObjectMapper.rsToApptList(rs));
            return Appointments.getAllAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to set the active tab
     * @param tab tab
     */
    public void setActiveTab(int tab) {
        selectionModel.select(tab);
    }

    /**
     * Event Handler to open Add Customer Form
     * @param event event
     * @throws IOException IOException
     */
    @FXML
    protected void onAddCustomer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/add-customer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Event Handler to open Modify Customer Form
     * @param event event
     * @throws IOException IOException
     */
    @FXML
    protected void onModifyCustomer(ActionEvent event) throws IOException {
        Customers selectedCustomer = (Customers) customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/modify-customer-view.fxml"));
            ModifyCustomerController modifyCustomerController = fxmlLoader.getController();
            System.out.println(selectedCustomer);
            ModifyCustomerController.setCustomer(selectedCustomer);

            Scene scene = new Scene(fxmlLoader.load());
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Modify Customer");
            stage.setScene(scene);
            stage.show();

        } else {
            setWarningLabel("No customer selected to modify. ", custWarning);
        }
    }

    /**
     * Function which sets a warning animation on a label
     * fades after 2 seconds
     * @param warning warning
     * @param label label
     */
    @FXML
    protected void setWarningLabel(String warning, Label label) {
        label.setText(warning);
        Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }

            protected void interpolate(double frac) {
                if (((int) ((float) frac * 100)) == 100) {
                    label.setText("");
                } else {
                    label.setText(warning);
                }
            }

        };
        animation.play();
    }

    /**
     * Event Handler for deleting a customer
     * Loads an alert modal for confirmation
     * @param event event
     * @throws SQLException SQLException
     */
    @FXML
    protected void onDeleteCustomer(ActionEvent event) throws SQLException {
        Customers selectedCustomer = (Customers) customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete Customer");
            alert.setHeaderText("Are you sure you want to delete " + selectedCustomer.getCustomerName() + "?");
            String contentText = "Associated customer appointments will also be deleted.";
            alert.setContentText(contentText);
            if (alert.showAndWait().get() == ButtonType.OK) {
                CustomerAccessObject.deleteCustomerWithAppts(selectedCustomer.getCustomerId());
                setWarningLabel(selectedCustomer.getCustomerName() + " has been deleted!", custWarning);
                customerTable.setItems(customerList());
                apptTable.setItems(apptList());
                refreshReports();
            } else {
                setWarningLabel("Not Deleted", custWarning);
            }
        } else {
            setWarningLabel("No customer selected", custWarning);
        }
    }

    /**
     * Event handler to exit the application
     * Closes the database connection
     * @param event event
     */
    @FXML
    protected void onExitApplication(ActionEvent event) {
        DatabaseConnecter.closeConnection();
        stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    /**
     * Event Handler to open Modify Appointment Form
     * @param event event
     * @throws IOException IOException
     */
    public void onModifyAppointment(ActionEvent event) throws IOException {
        Appointments selectedAppointment = (Appointments) apptTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/modify-appointment-view.fxml"));
            ModifyAppointmentController modifyAppointmentController = fxmlLoader.getController();
            System.out.println(selectedAppointment);
            ModifyAppointmentController.setAppointment(selectedAppointment);

            Scene scene = new Scene(fxmlLoader.load());
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Modify Appointment");
            stage.setScene(scene);
            stage.show();
        } else {
            setWarningLabel("No Appointment selected to modify. ", apptWarning);
        }
    }

    /**
     * Event Handler for deleting an appointment
     * Loads an alert modal for confirmation
     * @param actionEvent actionEvent
     * @throws SQLException SQLException
     */
    public void onDeleteAppointment(ActionEvent actionEvent) throws SQLException {
        Appointments selectedAppointment = (Appointments) apptTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Appointment");
            alert.setHeaderText("Are you sure you want to delete this appointment: ID - " + selectedAppointment.getAppointmentID() + "?");
            String contentText = "Confirm your choice";
            alert.setContentText(contentText);
            if (alert.showAndWait().get() == ButtonType.OK) {
                AppointmentAccessObject.deleteAppointment(selectedAppointment.getAppointmentID());
                setWarningLabel(selectedAppointment.getAppointmentID() + " - " + selectedAppointment.getType() + " has been deleted!", apptWarning);
                apptTable.setItems(apptList());
                refreshReports();
            } else {
                setWarningLabel("Appointment Not Deleted", apptWarning);
            }
        } else {
            setWarningLabel("No appointment selected", apptWarning);
        }
    }

    /**
     * Fetches reports from the database and sets the reports
     * @throws SQLException SQLException
     */
    private void refreshReports() throws SQLException {
        reports1Text.setText(ReportsQueryObject.getReport1());
        reports2Text.setText(ReportsQueryObject.getReport2());
        reports3Text.setText(ReportsQueryObject.getReport3());
    }

    /**
     * Event Handler to open Add Appointment Form
     * @param event event
     * @throws IOException IOException
     */
    @FXML
    protected void onAddAppointment(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/add-appointment-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Overrides the initialize method of the Initializable interface
     * - Sets data for the tables on the screen
     * - Sets welcome message and current timestamp
     * - Sets toggle actions for filtering appointments
     * - Sets reports
     *
     * Lambas are used here:
     * They are used to set nested data in the tableview cells
     * Example:
     * - Country and FirstLevelDivision Data on the Customers Model for the CustomersTable
     * - Contact Data on Appointments Model for the AppointmentsTable
     * - The filteredLists also use a lambda
     * @see "src/main/java/Model/Appointments.java"
     *
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectionModel = tabPane.getSelectionModel();

        String welcomeText = "Welcome " + getCurrentUser().getUserName() + "!";
        welcomeText += "\n\nAt this moment, it is " + DateConverter.readableDateFormat(OffsetDateTime.now(ZoneId.systemDefault()));
        greetingLabel.setText(welcomeText);
        customerTable.setPlaceholder(new Label("No customers to show"));
        apptTable.setPlaceholder(new Label("No appointments to show"));

        upcomingApptTable.setPlaceholder(new Label("No upcoming appointments for you to show"));
        upcomingApptTableID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        upcomingApptTableStart.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().formattedStart()));

        customerTable.setItems(customerList());


        cId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        cName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        cAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        cPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        cPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cDivId.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        cDivision.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstLevelDivision().getDivisionName()));
        cCountryId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFirstLevelDivision().getCountryId())));
        cCountry.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstLevelDivision().getCountry().getCountryName()));

        apptTable.setItems(apptList());
        cApptId.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        cApptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        cApptDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        cApptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        cApptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        cApptStart.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().formattedStart()));
        cApptEnd.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().formattedEnd()));
        cApptCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        cApptUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        cApptContactId.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        cApptContactName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContacts().getContactName()));
        cApptContactEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContacts().getEmail()));

        FilteredList<Appointments> filteredList = new FilteredList<>(FXCollections.observableList(Appointments.getAllAppointments()));
        upcomingApptTable.setItems(filteredList);
        filteredList.setPredicate(Appointments.getAppointmentsWithin15Minutes());

        weekButton.setToggleGroup(group);
        monthButton.setToggleGroup(group);
        allButton.setToggleGroup(group);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (weekButton.isSelected()) {
                    System.out.println("Week");
                    Appointments.getAllAppointments().setPredicate(Appointments.getCurrentWeekAppointments());
                } else if (monthButton.isSelected()) {
                    System.out.println("Month");
                    Appointments.getAllAppointments().setPredicate(Appointments.getCurrentMonthAppointments());
                } else {
                    Appointments.getAllAppointments().setPredicate(null);
                }
            }
        });

        try {
            refreshReports();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}