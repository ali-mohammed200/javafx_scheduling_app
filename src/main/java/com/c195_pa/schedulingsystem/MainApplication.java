package com.c195_pa.schedulingsystem;

import DAO.CustomerAccessObject;
import DAO.DatabaseConnecter;
import Helper.DateConverter;
import Helper.RStoObjectMapper;
import Model.Customers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainApplication extends Application {

    public static void main(String[] args) {
        DatabaseConnecter.setConnection();
        launch();
    }




//    public static void main(String[] args) {
//
//        try {
//            DatabaseConnecter.setConnection();
////            ResultSet rs = AppointmentAccessObject.getAllAppointments();
//            ResultSet rs = AppointmentAccessObject.getAppointmentByColumn("Customer_ID", 1);
//
//            while (rs.next()) {
//                System.out.println("\ndb\n");
//                int x = rs.getInt(1);
//                String s = rs.getString(2);
//
//                Timestamp tr = rs.getTimestamp(6);
//                Timestamp tr2 = rs.getTimestamp(8);
//
//                System.out.println(x);
//                System.out.println(s);
//                System.out.println(tr);
//                System.out.println(tr2);
//            }
//
//            Customers cs = new Customers(5, "Ned Flanders", "12 road", "60076",
//                    "129-345-6456", OffsetDateTime.now(), "admin3",
//                    OffsetDateTime.now(), "admin3", 60);
//
//            Appointments apt = new Appointments(4, "ttiel2", "tets2", "NY2", "In-person",
//                    OffsetDateTime.now(), OffsetDateTime.now(), OffsetDateTime.now(), "admind3", OffsetDateTime.now(),
//                    "admind3", 4, 2, 3);
//
//            System.out.println(OffsetDateTime.now());
//            System.out.println(OffsetDateTime.now().format((DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))));
//            System.out.println(Timestamp.valueOf(OffsetDateTime.now().format((DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))));
//
//            System.out.println("\n");
//            System.out.println(DateConverter.convertFromLocaltoUTC(OffsetDateTime.now()));
//            System.out.println(DateConverter.formatForTimestamp(DateConverter.convertFromLocaltoUTC(OffsetDateTime.now())));
//            System.out.println(DateConverter.convertFromUTCtoLocal(OffsetDateTime.now()));
//
//
//
////            CustomerAccessObject.createCustomer(cs);
////            CustomerAccessObject.updateCustomer(cs);
////            CustomerAccessObject.deleteCustomer(cs.getCustomerId());
//
////            AppointmentAccessObject.createAppointment(apt);
////            AppointmentAccessObject.updateAppointment(apt);
//            AppointmentAccessObject.deleteAppointment(3);
//
//            DatabaseConnecter.closeConnection();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public static void main(String[] args) {
//
//        try {
//        DatabaseConnecter.setConnection();
//        ResultSet rs = CustomerAccessObject.getAllCustomers();
//
//        while (rs.next()) {
//            System.out.println("\n");
//            int x = rs.getInt(1);
//            String s = rs.getString(2);
//
//            Timestamp tr = rs.getTimestamp(6);
//            Timestamp tr2 = rs.getTimestamp(8);
//
//            System.out.println(x);
//            System.out.println(s);
//            System.out.println(tr);
//            System.out.println(tr2);
//        }
//
//        Customers cs = new Customers(5, "Ned Flanders", "12 road", "60076",
//                "129-345-6456", OffsetDateTime.now(), "admin3",
//                OffsetDateTime.now(), "admin3", 60);
//
//        System.out.println(OffsetDateTime.now());
//        System.out.println(OffsetDateTime.now().format((DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))));
//        System.out.println(Timestamp.valueOf(OffsetDateTime.now().format((DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))));
//
//        System.out.println("\n");
//        System.out.println(DateConverter.convertFromLocaltoUTC(OffsetDateTime.now()));
//        System.out.println(DateConverter.formatForTimestamp(DateConverter.convertFromLocaltoUTC(OffsetDateTime.now())));
//        System.out.println(DateConverter.convertFromUTCtoLocal(OffsetDateTime.now()));
//
//
//
////            CustomerAccessObject.createCustomer(cs);
////            CustomerAccessObject.updateCustomer(cs);
//            CustomerAccessObject.deleteCustomer(cs.getCustomerId());
//
//
//        DatabaseConnecter.closeConnection();
//
//    } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public static void main(String[] args) {
//        try {
//            DatabaseConnecter.setConnection();
//            System.out.println(ZonedDateTime.now());
//            Statement stmt = DatabaseConnecter.getConnection().createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM countries");
//
//            while (rs.next()) {
//                System.out.println("\n\n\n");
//                int x = rs.getInt(1);
//                String s = rs.getString(2);
//
//                //This is it
//                System.out.println(OffsetDateTime.now().toString());
//
//                ZoneId myz = ZoneId.systemDefault();
//                ZonedDateTime zdt = ZonedDateTime.now(myz);
//                System.out.println(zdt);
//
//                OffsetDateTime ts = rs.getObject("Create_Date", OffsetDateTime.class);
//                System.out.println(ts.toInstant().atOffset(zdt.getOffset()));
//                // up to this point
//
//                String f = String.valueOf(rs.getTimestamp(3));
//                String f1 = String.valueOf(rs.getTimestamp(3).toLocalDateTime());
//                String f11 = String.valueOf(rs.getTimestamp(3).toLocalDateTime().toLocalTime());
//                String f12 = String.valueOf(rs.getTimestamp(3).toInstant().atZone(ZoneId.of("UTC")));
//                String f123 = String.valueOf(rs.getTimestamp(3).toInstant().atZone(myz));
//                String f13 = String.valueOf(rs.getTimestamp(3).toInstant().atZone(ZoneId.of("America/New_York")));
//
//                String f2 = rs.getString(4);
//                String f3 = rs.getString(5);
//                String f4 = rs.getString(6);
//
//                System.out.println(x);
//                System.out.println(s);
//                System.out.println(f);
//                System.out.println(f1);
//                System.out.println(f11);
//                System.out.println(f12);
//                System.out.println(f123);
//                System.out.println(f13);
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

//    @Override
//    public void start(Stage stage) throws IOException {
//        Locale currentLocale = new Locale("en");
//        ResourceBundle localizationBundle = ResourceBundle.getBundle("login", currentLocale);
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/login-view.fxml"));
//        fxmlLoader.setResources(localizationBundle);
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle(localizationBundle.getString("appName"));
//        stage.setScene(scene);
//        stage.show();
//        DatabaseConnecter.setConnection();
//    }

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
}
