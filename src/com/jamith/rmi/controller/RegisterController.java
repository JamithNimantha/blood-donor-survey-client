package com.jamith.rmi.controller;

import com.jamith.rmi.dto.UserDTO;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Jamith Nimantha
 */
public class RegisterController implements Initializable {

    @FXML
    private Label lblLogin;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobile;

    @FXML
    private Button btnRegister;

    private UserService userService;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

        String fullName = txtFirstName.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();
        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(fullName);
        userDTO.setMobile(mobile);
        userDTO.setEmail(email);
        try {
            UserDTO dto = userService.findByEmail(email);
            if (dto == null) {
                boolean b = userService.registerUser(userDTO);
                if (b) {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/Survey.fxml"));
                    Parent parent = fxmlLoader.load();
                    SurveyController surveyController = fxmlLoader.getController();
                    surveyController.setEmail(email);
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.centerOnScreen();
                    stage.show();
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Registration Completed", ButtonType.OK);
                    a.initOwner(stage);
                    a.setTitle("Registration Completed!");
                    a.setHeaderText(null);
                    a.setContentText("Please Start the Survey!");
                    a.show();
                } else {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Alert a = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
                    a.initOwner(stage);
                    a.setTitle("Error Occurred!");
                    a.setHeaderText(null);
                    a.setContentText("Registration Failed. Please Try Again!");
                    a.show();
                }
            } else {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Alert a = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
                a.initOwner(stage);
                a.setTitle("Error!");
                a.setHeaderText(null);
                a.setContentText("You have already participated !");
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void lblLoginOnMouseClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage) lblLogin.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/Login.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateRegistration();
        try {
            userService = (UserService) ServiceHandler.getInstance().getService(ServiceFactory.ServiceType.USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateRegistration() {
        btnRegister.disableProperty()
                .bind(txtFirstName.textProperty().isEmpty()
                        .or(txtEmail.textProperty().isEmpty())
                        .or(txtMobile.textProperty().isEmpty())
                );
    }

}
