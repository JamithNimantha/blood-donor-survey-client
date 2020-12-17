package com.jamith.rmi.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @author Jamith Nimantha
 *
 *  Notification Generator
 */
public class Notification {

    private static Alert alert;

    private Notification() {
    }

    /**
     * Create Error Type Notification
     *
     * @param title   Title of the Error
     * @param content content for the error
     * @param event   instance of the event
     */
    public static void errorNotify(String title, String content, ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR, content, ButtonType.OK);
        createNotify(event, alert, title, content);
        alert.show();
    }

    /**
     * Create Info Type Notification
     *
     * @param title   Title of the Error
     * @param content content for the error
     * @param event   instance of the event
     */
    public static void infoNotify(String title, String content, ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
        createNotify(event, alert, title, content);
        alert.show();
    }

    /**
     * Create Warning Type Notification
     *
     * @param title   Title of the Error
     * @param content content for the error
     * @param event   instance of the event
     */
    public static void warningNotify(String title, String content, ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, content, ButtonType.OK);
        createNotify(event, alert, title, content);
        alert.show();
    }

    /**
     * Create Confirm Type Notification
     *
     * @param title   Title of the Error
     * @param content content for the error
     * @param event   instance of the event
     */
    public static boolean confirmNotify(String title, String content, ActionEvent event) {
        alert = new Alert(Alert.AlertType.CONFIRMATION, content, ButtonType.OK, ButtonType.CANCEL);
        createNotify(event, alert, title, content);
        Optional<ButtonType> buttonType = alert.showAndWait();
        return buttonType.filter(type -> ButtonType.OK == type).isPresent();
    }


    /**
     * Create Notification And View
     *
     * @param event   instance of the event
     * @param alert   Alert Type
     * @param title   Title Title of the Error
     * @param content content content for the error
     */
    private static void createNotify(ActionEvent event, Alert alert, String title, String content) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

    }
}
