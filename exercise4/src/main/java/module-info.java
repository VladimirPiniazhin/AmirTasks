module com.example.exercise4 {
    requires javafx.controls;
    requires javafx.fxml;
    //requires mongo.java.driver;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires java.desktop;


    opens com.example.exercise4 to javafx.fxml;
    exports com.example.exercise4;
}