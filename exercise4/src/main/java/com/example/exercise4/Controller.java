package com.example.exercise4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.bson.Document;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;

public class Controller {

    @FXML
    private TextField city;
    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField age;

    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private Button add;
    @FXML
    private Button read;

    private DBConnector dbConnector = new DBConnector();

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    private void showAlert(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    @FXML
    private void add() {
        String result = dbConnector.write(name.getText(), Integer.parseInt(age.getText()), city.getText());
        city.setText("");
        name.setText("");
        age.setText("");

        if (result.equals("200")) {
            showSuccess("Document added successfully.");
        } else {
            showAlert("Error adding document.");
        }
    }
    @FXML
    private void read() {
        String result = dbConnector.read(id.getText());

        if (result.equals("400")) {
            showAlert("User with id: " + id.getText() + " not found.");
        } else {
            showSuccess(result);
        }
        id.setText("");
        city.setText("");
        name.setText("");
        age.setText("");
    }
    @FXML
    private void delete() {
        String result = dbConnector.delete(id.getText());

        if (result.equals("400")) {
            showAlert("User with id: " + id.getText() + " not found.");
        } else {
            showSuccess("Document deleted successfully.");
        }
        id.setText("");
        city.setText("");
        name.setText("");
        age.setText("");
    }
    @FXML
    private void update() {
        String result = dbConnector.update(id.getText(), name.getText(), Integer.parseInt(age.getText()), city.getText());

        if (result.equals("200")) {
            showSuccess("Document updated successfully.");
        } else {
            showAlert("Error updating");
        }
        id.setText("");
        city.setText("");
        name.setText("");
        age.setText("");
    }


}