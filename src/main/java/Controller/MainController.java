package Controller;

import DAO.CustomerAccessObject;
import DAO.DatabaseConnecter;
import Helper.RStoObjectMapper;
import Model.Customers;
import com.c195_pa.schedulingsystem.MainApplication;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Stage stage;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button exitButton;
    @FXML
    private Tab mainTab;
    @FXML
    public Tab customerTab;
    @FXML
    private Tab apptTab;
    @FXML
    private TabPane tabPane; //= new TabPane(mainTab, customerTab, apptTab);
    @FXML
    public SingleSelectionModel<Tab> selectionModel; // = tabPane.getSelectionModel();

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private AnchorPane customerAnchorPane;
    @FXML
    private Pane customerPane;
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
    private Button addCustButton;
    @FXML
    private Button modifyCustButton;
    @FXML
    private Button deleteCustButton;
    @FXML
    private Label custWarning;
    @FXML
    private AnchorPane apptAnchorPane;
    @FXML
    private Pane apptPane;
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
    private TableColumn cApptStart;
    @FXML
    private TableColumn cApptEnd;
    @FXML
    private TableColumn cApptCustId;
    @FXML
    private TableColumn cApptUserId;
    @FXML
    private TableColumn cApptContactId;
    @FXML
    private Button addApptButton;
    @FXML
    private Button modifyApptButton;
    @FXML
    private Button deleteApptButton;
    @FXML
    private Label apptWarning;

    public void setActiveTab(int tab){
        selectionModel.select(tab);
    }

    public static ObservableList<Customers> customerList() {
        try {
            ResultSet rs = CustomerAccessObject.getAllCustomersWithFDLData();

            return RStoObjectMapper.rsToCustomerList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onAddCustomer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/add-customer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void onModifyPart(ActionEvent actionEvent) {
    }

    public void onDeletePart(ActionEvent actionEvent) {
    }

    @FXML
    protected void onExitApplication(ActionEvent event) {
        DatabaseConnecter.closeConnection();
        stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    public void onAddProduct(ActionEvent actionEvent) {
    }

    public void onModifyProduct(ActionEvent actionEvent) {
    }

    public void onDeleteProduct(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectionModel = tabPane.getSelectionModel();

        customerTable.setPlaceholder(new Label("No customers to show"));
        apptTable.setPlaceholder(new Label("No appointments to show"));

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

    }
}