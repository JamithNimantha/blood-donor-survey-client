package com.jamith.rmi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Jamith Nimantha
 */
public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblRegister;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/MainPanel.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void lblRegisterOnMouseClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage) lblRegister.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/Register.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }


}
