package com.jamith.rmi.controller;

import com.jamith.rmi.dto.UserDTO;
import com.jamith.rmi.service.ServiceFactory;
import com.jamith.rmi.service.ServiceHandler;
import com.jamith.rmi.service.UserService;
import com.jamith.rmi.util.Notification;
import com.jamith.rmi.util.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    /**
     * Register User for the Suervey
     *
     * @param event Action Event
     */
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

                    Notification.infoNotify("Registration Completed!", "Please Start the Survey!", event);
                } else {
                    Notification.errorNotify("Error Occurred!", "Registration Failed. Please Try Again!", event);
                }
            } else {
                Notification.errorNotify("Error!", "You have already participated !", event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param event Load Login Pane
     * @throws IOException
     */
    @FXML
    void lblLoginOnMouseClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage) lblLogin.getScene().getWindow();
        ViewLoader.view(stage, this.getClass().getResource("/com/jamith/rmi/view/Login.fxml"));

    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateRegistration();
        try {
            userService = (UserService) ServiceHandler.getInstance().getService(ServiceFactory.ServiceType.USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Validate text fields for Empty Values
     */
    private void validateRegistration() {
        btnRegister.disableProperty()
                .bind(txtFirstName.textProperty().isEmpty()
                        .or(txtEmail.textProperty().isEmpty())
                        .or(txtMobile.textProperty().isEmpty())
                );
    }

}
