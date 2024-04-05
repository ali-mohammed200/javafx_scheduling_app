package Controller;

import DAO.CountryAccessObject;
import DAO.CustomerAccessObject;
import DAO.FirstLevelDivisionAccessObject;
import Helper.RStoObjectMapper;
import Model.Countries;
import Model.Customers;
import Model.FirstLevelDivisions;
import Model.Users;
import com.c195_pa.schedulingsystem.MainApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {
    @FXML
    private Stage stage;
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
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private static Customers customer;

    public static void setCustomer(Customers selectedCustomer) {
        System.out.println(customer);
        customer = selectedCustomer;
    }

    @FXML
    private void onCountryAction(ActionEvent actionEvent) {
        System.out.println(dropDownCountry.getValue());
        Countries country = (Countries) dropDownCountry.getValue();
        dropDownDivision.setItems(fdlListByCountry(country.getCountryID()));
    }

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
            Customers customer = new Customers(Integer.parseInt(inputID.getText()), name, address, postalCode,
                    phoneNumber, OffsetDateTime.now(),
                    currentUser.getUserName(), firstLevelDivision.getDivisionId());

            CustomerAccessObject.updateCustomer(customer);
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

    public static ObservableList<Countries> countryList() {
        try {
            ResultSet rs = CountryAccessObject.getCountries();

            return RStoObjectMapper.rsToCountryList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<FirstLevelDivisions> fdlListByCountry(int id) {
        try {
            ResultSet rs = FirstLevelDivisionAccessObject.getFirstLevelDivisionsByCountry(id);

            return RStoObjectMapper.rsToFDLList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
    }
}
