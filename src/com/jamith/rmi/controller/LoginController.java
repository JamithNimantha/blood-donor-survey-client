package com.jamith.rmi.controller;

import com.jamith.rmi.service.ServiceFactory;
import com.jamith.rmi.service.ServiceHandler;
import com.jamith.rmi.service.UserService;
import com.jamith.rmi.util.Notification;
import com.jamith.rmi.util.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Jamith Nimantha
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    private UserService userService;

    @FXML
    private Button btnStatSurvey;


    /**
     * Load Start Survey Pane
     *
     * @param event Action Event
     * @throws IOException
     */
    @FXML
    void btnStatSurveyOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnStatSurvey.getScene().getWindow();
        ViewLoader.view(stage, this.getClass().getResource("/com/jamith/rmi/view/Register.fxml"));
    }

    /**
     * Admin Login
     *
     * @param event Action Event
     * @throws IOException
     */
    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        String session = userService.login(txtEmail.getText(), txtPassword.getText());
        System.out.println("Generated Session : " + session);
        if (session != null) {
            ViewLoader.view(stage, this.getClass().getResource("/com/jamith/rmi/view/MainPanel.fxml"));
        } else {
            Notification.errorNotify("Invalid Login!", "Invalid Email or Password. Please try again!", event);
        }
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
        validateLogin();
        try {
            userService = (UserService) ServiceHandler.getInstance().getService(ServiceFactory.ServiceType.USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set Login button disable if the both Email and Password fields are empty
     * Check the state of the Email and password fields for empty and bind it for the Login Button
     */
    private void validateLogin() {
        btnLogin.disableProperty()
                .bind(txtPassword.textProperty().isEmpty()
                        .or(txtEmail.textProperty().isEmpty())
                );
    }
}
