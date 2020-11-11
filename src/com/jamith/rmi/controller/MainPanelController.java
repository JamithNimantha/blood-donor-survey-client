package com.jamith.rmi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Jamith Nimantha
 */
public class MainPanelController {

    @FXML
    private Button btnStartSurvey;

    @FXML
    private Button btnAnalytical;

    @FXML
    private Button btnLogout;

    @FXML
    void btnAnalyticalOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnAnalytical.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/AnalysisDashboard.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/Login.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnStartSurveyOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnStartSurvey.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/Survey.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
