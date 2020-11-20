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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Jamith Nimantha
 */
public class MainPanelController implements Initializable {

    @FXML
    private Button btnUM;

    @FXML
    private Button btnQA;

    @FXML
    private Button btnAnalytical;

    @FXML
    private Button btnLogout;

    private UserService userService;

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
        boolean sample = userService.logout("sample");
        if (sample) {
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/Login.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();

            Alert a = new Alert(Alert.AlertType.INFORMATION, "Logout Successfully", ButtonType.OK);
            a.initOwner(stage);
            a.setTitle("Logout Successfully !");
            a.setHeaderText(null);
            a.setContentText("You have Logout Successfully!");
            a.show();
        }
    }

    @FXML
    void btnUMOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnUM.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/Survey.fxml"));
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
        try {
            userService = (UserService) ServiceHandler.getInstance().getService(ServiceFactory.ServiceType.USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnQAOnAction(ActionEvent event) {

    }
}
