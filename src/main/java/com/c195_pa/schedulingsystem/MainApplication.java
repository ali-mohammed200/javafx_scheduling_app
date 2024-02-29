package com.c195_pa.schedulingsystem;

import DAO.DatabaseConnecter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import java.util.Locale;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Locale currentLocale = new Locale("en");
        ResourceBundle localizationBundle = ResourceBundle.getBundle("login", currentLocale);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/login-view.fxml"));
        fxmlLoader.setResources(localizationBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(localizationBundle.getString("appName"));
        stage.setScene(scene);
        stage.show();
        DatabaseConnecter.setConnection();
    }

    public static void main(String[] args) {
        launch();
    }
//    public static void main(String[] args){
//        try {
//            DatabaseConnecter.setConnection();
//            System.out.println(ZonedDateTime.now());
//            Statement stmt = DatabaseConnecter.getConnection().createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM countries");
//
//            while (rs.next()) {
//                int x = rs.getInt(1);
//                String s = rs.getString(2);
//                String f = String.valueOf(rs.getTimestamp(3));
//                String f2 = rs.getString(4);
//                String f3 = rs.getString(5);
//                String f4 = rs.getString(6);
//                System.out.println(x);
//                System.out.println(s);
//                System.out.println(f);
//                System.out.println(f2);
//                System.out.println(f3);
//                System.out.println(f4);
//            }
//
//            DatabaseConnecter.closeConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
