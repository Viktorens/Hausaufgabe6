module com.example.hausaufgabe6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;


    opens com.example.hausaufgabe6 to javafx.fxml;
    exports com.example.hausaufgabe6;
}