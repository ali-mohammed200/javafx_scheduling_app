package com.c195_pa.schedulingsystem;

import DAO.DatabaseConnecter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainApplication extends Application {
    public static void main(String[] args) {
        DatabaseConnecter.setConnection();
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
//        TODO: Test locale and check if we can switch locals
        Locale currentLocale = new Locale("en");
        ResourceBundle localizationBundle = ResourceBundle.getBundle("login", currentLocale);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/login-view.fxml"));
        fxmlLoader.setResources(localizationBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(localizationBundle.getString("appName"));
        stage.setScene(scene);
        stage.show();
    }
}
