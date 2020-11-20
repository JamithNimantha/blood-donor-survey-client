package com.jamith.rmi.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @author Jamith Nimantha
 */
public class Notification {

    private static Alert alert;

    private Notification() {
    }

    public static void errorNotify(String title, String content, ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR, content, ButtonType.OK);
        createNotify(event, alert, title, content);
        alert.show();
    }

    public static void infoNotify(String title, String content, ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
        createNotify(event, alert, title, content);
        alert.show();
    }

    public static void warningNotify(String title, String content, ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, content, ButtonType.OK);
        createNotify(event, alert, title, content);
        alert.show();
    }

    public static boolean confirmNotify(String title, String content, ActionEvent event) {
        alert = new Alert(Alert.AlertType.CONFIRMATION, content, ButtonType.OK, ButtonType.CANCEL);
        createNotify(event, alert, title, content);
        Optional<ButtonType> buttonType = alert.showAndWait();
        return buttonType.filter(type -> ButtonType.OK == type).isPresent();
    }

    private static void createNotify(ActionEvent event, Alert alert, String title, String content) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

    }
}
