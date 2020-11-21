package com.jamith.rmi.controller;

import com.jamith.rmi.util.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Jamith Nimantha
 */
public class QuestionAnswerManagementController {

    @FXML
    private Button btnHome;

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        ViewLoader.view(stage, this.getClass().getResource("/com/jamith/rmi/view/MainPanel.fxml"));
    }
}
