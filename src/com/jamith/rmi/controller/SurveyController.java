package com.jamith.rmi.controller;

import com.jamith.rmi.dto.AnswerDTO;
import com.jamith.rmi.dto.QuestionDTO;
import com.jamith.rmi.service.QuestionAnswerService;
import com.jamith.rmi.service.ServiceFactory;
import com.jamith.rmi.service.ServiceHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Jamith Nimantha
 */
public class SurveyController implements Initializable {

    @FXML
    public AnchorPane child;
    @FXML
    private ComboBox<String> cmd;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnHome;

    private String email;

    private QuestionAnswerService questionAnswerService;

    void setEmail(String email) {
        this.email = email;
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/MainPanel.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnSubmitOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            questionAnswerService = (QuestionAnswerService) ServiceHandler.getInstance().getService(ServiceFactory.ServiceType.QUESTIONANSWER);
            getAllQuestions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllQuestions() throws RemoteException {

        List<QuestionDTO> allQuestions = questionAnswerService.getAllQuestions();
        double lblLayoutX = 28.0;
        double cmbLayoutX = 25.0;
        for (QuestionDTO question : allQuestions) {
            Label label = new Label(String.format("%d. %s", allQuestions.indexOf(question) + 1, question.getName()));
            label.setLayoutX(34.0);
            lblLayoutX = lblLayoutX + 34.0;
            label.setLayoutY(lblLayoutX);
            label.prefHeight(19.0);
            label.prefWidth(370.0);
            label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
            child.getChildren().add(label);

            ComboBox<String> comboBox = new ComboBox<>();
            comboBox.setLayoutX(502.0);
            cmbLayoutX = cmbLayoutX + 34.0;
            comboBox.setLayoutY(cmbLayoutX);
            comboBox.setVisibleRowCount(7);
            comboBox.setPrefHeight(25.0);
            comboBox.setPrefWidth(250.0);
            comboBox.setPromptText("Select One");
            for (AnswerDTO answerDTO : question.getAnswerDTOS()) {
                comboBox.getItems().add(String.valueOf(answerDTO.getName()));
            }
            child.getChildren().add(comboBox);
        }
    }
}
