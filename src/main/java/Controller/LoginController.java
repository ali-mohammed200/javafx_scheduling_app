package Controller;

import DAO.UserAccessObject;
import com.c195_pa.schedulingsystem.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class LoginController implements Initializable {
    private int userId;
    @FXML
    private TextField UserNameInput;
    @FXML
    private PasswordField PasswordInput;
    @FXML
    private Button ExitButton;
    @FXML
    private Button LoginButton;
    @FXML
    private Label TimeZoneBox;
    @FXML
    private Label ErrorBox;

    private ResourceBundle resourceBundle;

    @FXML
    protected void onLoginButtonClick() {
        String username = UserNameInput.getText();
        String password = PasswordInput.getText();

        if (username.length() == 0 || password.length() == 0){
            ErrorBox.setText(resourceBundle.getString("loginInputsEmpty"));
            return;
        }

        try {
            ResultSet rs = UserAccessObject.getUser(username, password);

            if (!rs.isBeforeFirst() ) {
                ErrorBox.setText(resourceBundle.getString("incorrect"));
                System.out.println("Invalid Login");
                return;
            }

            while (rs.next()) {
                userId = rs.getInt(1);
                String userName = rs.getString(2);
                System.out.println(userId);
                System.out.println(userName);
            }
            enterApplication(userId);
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }

    protected void enterApplication(int userId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        stage.setTitle("Scheduling Application - Main");
//        stage.setX(0);
//        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onExitButtonClick(){
        System.out.println("Exit");
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        String zoneDateTimeNow = ZonedDateTime.now().toString();
        String parsedTimeZone = zoneDateTimeNow.substring(zoneDateTimeNow.indexOf("["), zoneDateTimeNow.length());
        String timezoneText = resourceBundle.getString("welcome") + " " + resourceBundle.getString("timezone")
                + " " + parsedTimeZone;
        TimeZoneBox.setText(timezoneText);
        System.out.println("lll");
    }
}