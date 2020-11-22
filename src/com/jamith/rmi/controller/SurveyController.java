package com.jamith.rmi.controller;

import com.jamith.rmi.dto.AnswerDTO;
import com.jamith.rmi.dto.QuestionDTO;
import com.jamith.rmi.dto.ResponseDTO;
import com.jamith.rmi.dto.UserDTO;
import com.jamith.rmi.service.QuestionAnswerService;
import com.jamith.rmi.service.ServiceFactory;
import com.jamith.rmi.service.ServiceHandler;
import com.jamith.rmi.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
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

    List<QuestionDTO> allQuestions = new ArrayList<>();
    private final List<ComboBox<String>> comboBoxList = new ArrayList<>();
    private UserService userService;

    void setEmail(String email) {
        this.email = email;
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/Login.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        List<ResponseDTO> responseDTOS = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        try {
            userDTO = userService.findByEmail(this.email);
        } catch (RemoteException e) {
            System.out.println("Unable to fetch UserDTO");
            e.printStackTrace();
        }
        for (ComboBox<String> comboBox : comboBoxList) {

            if (comboBox.getSelectionModel().getSelectedItem() == null) {
                System.out.println("There are some unanswered questions");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Alert a = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
                a.initOwner(stage);
                a.setTitle("Error!");
                a.setHeaderText(null);
                a.setContentText("Please Answer All The Questions!");
                a.show();
                break;
            } else {
                AnswerDTO answerDto = getAnswerDTOByQuestionIdAndAnswerName(
                        Integer.valueOf(comboBox.getId()),
                        comboBox.getSelectionModel().getSelectedItem());
                if (answerDto != null) {
                    ResponseDTO responseDTO = new ResponseDTO();
                    responseDTO.setUserDTO(userDTO);
                    responseDTO.setAnswerDTO(answerDto);
                    System.out.println(comboBox.getId());
                    responseDTOS.add(responseDTO);
                } else {
                    System.err.println("Error ! No Answer : " + comboBox.getSelectionModel().getSelectedItem());
                }

            }
        }

        if (allQuestions.size() == responseDTOS.size()) {
            try {
                boolean response = questionAnswerService.saveResponse(responseDTOS);
                if (response) {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/Register.fxml"));
                    Parent parent = fxmlLoader.load();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.centerOnScreen();
                    stage.show();

                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Successful", ButtonType.OK);
                    a.initOwner(stage);
                    a.setTitle("Survey Successful!");
                    a.setHeaderText(null);
                    a.setContentText("Thank you for participating in our survey!");
                    a.show();
                } else {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Error", ButtonType.OK);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    a.initOwner(stage);
                    a.setTitle("Error Occurred!");
                    a.setHeaderText(null);
                    a.setContentText("Please try again!");
                    a.show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("All Questions are not being answered");
        }


    }

    private AnswerDTO getAnswerDTOByQuestionIdAndAnswerName(Integer questionId, String name) {
        for (QuestionDTO dto : allQuestions) {
            if (questionId.equals(dto.getId())) {
                for (AnswerDTO answerDTO : dto.getAnswerDTOS()) {
                    if (answerDTO.getName().equals(name)) {
                        return answerDTO;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            questionAnswerService = (QuestionAnswerService) ServiceHandler.getInstance().getService(ServiceFactory.ServiceType.QUESTIONANSWER);
            userService = (UserService) ServiceHandler.getInstance().getService(ServiceFactory.ServiceType.USER);
            getAllQuestions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllQuestions() throws RemoteException {

        allQuestions = questionAnswerService.getAllQuestions();
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
            comboBox.setId(String.valueOf(question.getId()));
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
            comboBoxList.add(comboBox);
        }
    }
}
