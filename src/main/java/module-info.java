module com.c195_pa.schedulingsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.c195_pa.schedulingsystem to javafx.fxml;
    exports com.c195_pa.schedulingsystem;
    exports Controller;
    opens Controller to javafx.fxml;
}