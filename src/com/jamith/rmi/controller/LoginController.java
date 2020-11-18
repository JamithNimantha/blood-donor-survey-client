package com.jamith.rmi.controller;

import com.jamith.rmi.service.ServiceFactory;
import com.jamith.rmi.service.ServiceHandler;
import com.jamith.rmi.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblRegister;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    private UserService userService;


    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnLogin.getScene().getWindow();

        String session = userService.login(txtEmail.getText(), txtPassword.getText());
        System.out.println("Generated Session : " + session);
        if (session != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/MainPanel.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING, "Error", ButtonType.OK);
            a.initOwner(stage);
            a.setTitle("Invalid Login!");
            a.setHeaderText(null);
            a.setContentText("Invalid Email or Password. Please try again!");
            a.show();
        }
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
