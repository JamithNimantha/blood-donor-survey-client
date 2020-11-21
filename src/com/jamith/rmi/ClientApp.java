package com.jamith.rmi;


import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApp extends Application {

    public static void main(String[] args) {
        LauncherImpl.launchApplication(ClientApp.class, AppPreLoader.class, args);
    }

    public void start(Stage primaryStage) throws Exception {
        notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
        Parent parent = FXMLLoader.load(this.getClass().getResource("/com/jamith/rmi/view/QuestionAnswerManagement.fxml"));
//        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/com/debuggerme/fiverr/articlerewriter/assets/logo.png")));
        Scene temp = new Scene(parent);
        primaryStage.setScene(temp);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Survey Questioner");
//        primaryStage.setResizable(false);

        primaryStage.show();
    }
}
