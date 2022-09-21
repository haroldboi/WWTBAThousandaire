module com.example.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;
    requires org.apache.commons.text;
    requires java.desktop;
    requires javafx.media;


    opens com.example.quiz to javafx.fxml;
    exports com.example.quiz;
}