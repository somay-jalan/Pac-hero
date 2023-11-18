module com.example.pachero {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pachero to javafx.fxml;
    exports com.example.pachero;
}