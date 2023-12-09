module com.example.pachero {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;
    requires javafx.media;


    opens com.example.pachero to javafx.fxml;
    exports com.example.pachero;
}