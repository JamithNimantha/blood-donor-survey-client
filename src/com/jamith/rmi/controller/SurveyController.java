package com.jamith.rmi.controller;

import com.jamith.rmi.dto.AnswerDTO;
import com.jamith.rmi.dto.QuestionDTO;
import com.jamith.rmi.dto.ResponseDTO;
import com.jamith.rmi.dto.UserDTO;
import com.jamith.rmi.service.QuestionAnswerService;
import com.jamith.rmi.service.ServiceFactory;
import com.jamith.rmi.service.ServiceHandler;
import com.jamith.rmi.service.UserService;
import com.jamith.rmi.util.Notification;
import com.jamith.rmi.util.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    public void setEmail(String email) {
        this.email = email;
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        ViewLoader.view(stage, this.getClass().getResource("/com/jamith/rmi/view/Login.fxml"));
    }

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        System.out.println(this.email);
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
                Notification.errorNotify("Error!", "Please Answer All The Questions!", event);
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
                    ViewLoader.view(stage, this.getClass().getResource("/com/jamith/rmi/view/Register.fxml"));

                    Notification.infoNotify("Survey Successful!", "Thank you for participating in our survey!", event);
                } else {
                    Notification.errorNotify("Error Occurred!", "Please try again!", event);
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
        System.out.println(this.email);
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
            comboBox.setStyle("-fx-font-weight: bold;");
            comboBox.setPromptText("Select One");
            for (AnswerDTO answerDTO : question.getAnswerDTOS()) {
                comboBox.getItems().add(String.valueOf(answerDTO.getName()));
            }
            child.getChildren().add(comboBox);
            comboBoxList.add(comboBox);
        }
    }
}
