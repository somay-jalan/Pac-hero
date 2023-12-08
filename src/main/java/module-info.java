module com.example.pachero {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;


    opens com.example.pachero to javafx.fxml;
    exports com.example.pachero;
}