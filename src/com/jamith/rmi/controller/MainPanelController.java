package com.jamith.rmi.controller;

import com.jamith.rmi.service.ServiceFactory;
import com.jamith.rmi.service.ServiceHandler;
import com.jamith.rmi.service.UserService;
import com.jamith.rmi.util.Notification;
import com.jamith.rmi.util.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane pane;

    @FXML
    private Button btnQA;

    @FXML
    private Button btnAnalytical;

    @FXML
    private Button btnLogout;

    private UserService userService;

    @FXML
    private Button btnHome;

    private AnchorPane paneLoader;

    @FXML
    void btnAnalyticalOnAction(ActionEvent event) throws IOException {
        paneLoader = FXMLLoader.load(this.getClass().getResource("/com/jamith/rmi/view/AnalysisDashboard.fxml"));
        pane.getChildren().setAll(paneLoader);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        boolean sample = userService.logout("sample");
        if (sample) {
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            ViewLoader.view(stage, this.getClass().getResource("/com/jamith/rmi/view/Login.fxml"));
            Notification.infoNotify("Logout Successfully !", "You have Logout Successfully!", event);
        }
    }

    @FXML
    void btnUMOnAction(ActionEvent event) throws IOException {
        paneLoader = FXMLLoader.load(this.getClass().getResource("/com/jamith/rmi/view/UserManagement.fxml"));
        pane.getChildren().setAll(paneLoader);
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
            boolean dsf = userService.logout("dsf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnQAOnAction(ActionEvent event) throws IOException {
        paneLoader = FXMLLoader.load(this.getClass().getResource("/com/jamith/rmi/view/QuestionAnswerManagement.fxml"));
        pane.getChildren().setAll(paneLoader);
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {

    }
}
