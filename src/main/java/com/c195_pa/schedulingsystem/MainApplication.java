package com.c195_pa.schedulingsystem;

import DAO.DatabaseConnecter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Mohammad Ali - WGU Student
 * MainApplication class is the starting point of the program
 * This is a JavaFX application built for scheduling
 *
 * Lambdas are located in:
 * - src/main/java/Controller/MainController.java
 * - src/main/java/Model/Appointments.java
 */

public class MainApplication extends Application {
    /**
     * main function - entry point of the application
     * @param args args
     */
    public static void main(String[] args) {
        DatabaseConnecter.setConnection();
        launch();
    }

    /**
     * start function
     *  - starts the application
     *  - sets locale
     *  - loads login screen
     *
     * @param stage stage
     * @throws IOException IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Locale currentLocale = Locale.getDefault();
        ResourceBundle localizationBundle = ResourceBundle.getBundle("login", currentLocale);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/login-view.fxml"));
        fxmlLoader.setResources(localizationBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(localizationBundle.getString("appName"));
        stage.setScene(scene);
        stage.show();
    }
}
