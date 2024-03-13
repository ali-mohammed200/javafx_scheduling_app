package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Stage stage;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TableView tablePart;
    @FXML
    private TableColumn cPartId;
    @FXML
    private TableColumn cPartName;
    @FXML
    private TableColumn cPartInvLvl;
    @FXML
    private TableColumn cPartPrice;
    @FXML
    private TextField searchBarPart;
    @FXML
    private TableView tableProduct;
    @FXML
    private TableColumn cProdId;
    @FXML
    private TableColumn cProdName;
    @FXML
    private TableColumn cProdInvLvl;
    @FXML
    private TableColumn cProdPrice;
    @FXML
    private TextField searchBarProduct;
    @FXML
    private Label productsWarning;
    @FXML
    private Label partsWarning;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onAddPart(ActionEvent actionEvent) {
    }

    public void onModifyPart(ActionEvent actionEvent) {
    }

    public void onDeletePart(ActionEvent actionEvent) {
    }

    public void onExitApplication(ActionEvent actionEvent) {
    }

    public void onAddProduct(ActionEvent actionEvent) {
    }

    public void onModifyProduct(ActionEvent actionEvent) {
    }

    public void onDeleteProduct(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        tablePart.setPlaceholder(new Label("No parts to show"));
//        tableProduct.setPlaceholder(new Label("No products to show"));
//
//        tablePart.setItems(Inventory.getAllParts());
//        tableProduct.setItems(Inventory.getAllProducts());
//
//        cPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        cPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        cPartInvLvl.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        cPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        cProdId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        cProdName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        cProdInvLvl.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        cProdPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}