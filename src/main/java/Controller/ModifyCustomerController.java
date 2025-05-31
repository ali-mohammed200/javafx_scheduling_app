package Controller;

import DAO.CountryAccessObject;
import DAO.CustomerAccessObject;
import DAO.FirstLevelDivisionAccessObject;
import Helper.RStoObjectMapper;
import Model.*;
import com.c195_pa.schedulingsystem.MainApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ResourceBundle;

/**
 * ModifyCustomerController is used to manage Editing Customers
 */
public class ModifyCustomerController implements Initializable {
    private static Customers customer;
    @FXML
    private Stage stage;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField inputID;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputAddress;
    @FXML
    private TextField inputPostalCode;
    @FXML
    private TextField inputPhoneNumber;
    @FXML
    private ComboBox dropDownCountry;
    @FXML
    private ComboBox dropDownDivision;
    @FXML
    private Label warningLabel;
    @FXML
    private Circle statusIcon;
    @FXML
    private Label statusLabel;

    /**
     * Function to set a customer for editing
     * @param selectedCustomer selectedCustomer
     */
    public static void setCustomer(Customers selectedCustomer) {
        System.out.println(customer);
        customer = selectedCustomer;
    }

    /**
     * Function to return an ObservableList of Countries
     * @return ObservableList Countries
     */
    public static ObservableList<Countries> countryList() {
        try {
            ResultSet rs = CountryAccessObject.getCountries();
            return RStoObjectMapper.rsToCountryList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to return an ObservableList of FirstLevelDivisions
     * @return ObservableList FirstLevelDivisions
     * @param id id
     */
    public static ObservableList<FirstLevelDivisions> fdlListByCountry(int id) {
        try {
            ResultSet rs = FirstLevelDivisionAccessObject.getFirstLevelDivisionsByCountry(id);
            return RStoObjectMapper.rsToFDLList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Event Handler when clicking on country dropdown
     * to populate related FirstLevelDivision dropdown
     * @param actionEvent actionEvent
     */
    @FXML
    private void onCountryAction(ActionEvent actionEvent) {
        System.out.println(dropDownCountry.getValue());
        Countries country = (Countries) dropDownCountry.getValue();
        dropDownDivision.setItems(fdlListByCountry(country.getCountryID()));
    }

    /**
     * Event Handler for saving an edited customer
     * - Validates Inputs
     * @param actionEvent actionEvent
     * @throws SQLException SQLException
     * @throws IOException IOException
     */
    @FXML
    private void onSave(ActionEvent actionEvent) throws SQLException, IOException {
        String warning = "";
        String name = inputName.getText();
        if (name == "") {
            warning += "Name is empty. ";
        }

        String address = inputAddress.getText();
        if (address == "") {
            warning += "Address is empty. ";
        }

        String postalCode = inputPostalCode.getText();
        if (postalCode == "") {
            warning += "Postal Code is empty. ";
        }

        String phoneNumber = inputPhoneNumber.getText();
        if (phoneNumber == "") {
            warning += "Phone Number is empty. ";
        }

        Object countryValue = dropDownCountry.getValue();
        if (countryValue == null) {
            warning += "Country is empty. ";
        }

        Object divisionValue = dropDownDivision.getValue();
        if (divisionValue == null) {
            warning += "Division is empty. ";
        }

        System.out.println(warning);
        System.out.println(dropDownCountry.getValue());

        if (warning.length() > 0) {
            warningLabel.setText("Exception:\n" + warning);
        } else {
            Users currentUser = MainController.getCurrentUser();
            FirstLevelDivisions firstLevelDivision = (FirstLevelDivisions) dropDownDivision.getValue();
            Customers customer = new Customers(Integer.parseInt(inputID.getText()), name, address, postalCode, phoneNumber, OffsetDateTime.now(), currentUser.getUserName(), firstLevelDivision.getDivisionId());
            CustomerAccessObject.updateCustomer(customer);
            onCancel(actionEvent);
        }
    }

    /**
     * Event Handler for canceling customer creation
     * @param actionEvent actionEvent
     * @throws IOException IOException
     */
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

    /**
     * Overrides the initialize method of the Initializable interface
     *  sets data on the screen for modification
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(customer);
        inputID.setText(String.valueOf(customer.getCustomerId()));
        inputName.setText(customer.getCustomerName());
        inputAddress.setText(customer.getAddress());
        inputPhoneNumber.setText(customer.getPhone());
        inputPostalCode.setText(customer.getPostalCode());
        dropDownCountry.setItems(countryList());
        Countries country = customer.getFirstLevelDivision().getCountry();
        dropDownCountry.setValue(country);
        dropDownDivision.setItems(fdlListByCountry(country.getCountryID()));
        dropDownDivision.setValue(customer.getFirstLevelDivision());

        if (customer instanceof PremiumCustomers) {
            statusIcon.setFill(Color.YELLOW);
            statusLabel.setText("Premium");
            anchorPane.setStyle("-fx-background-color: #ffffcc;");
        } else {
            statusLabel.setText("Regular");
            anchorPane.setStyle("-fx-background-color: #ddffd9;");
        }
    }
}
