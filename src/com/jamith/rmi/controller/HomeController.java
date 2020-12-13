package com.jamith.rmi.controller;

import com.jamith.rmi.service.QuestionAnswerService;
import com.jamith.rmi.service.ServiceFactory;
import com.jamith.rmi.service.ServiceHandler;
import com.jamith.rmi.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Jamith Nimantha
 */
public class HomeController implements Initializable {

    @FXML
    private Label lblAdmin;

    @FXML
    private Label lblUsers;

    @FXML
    private Label lblQuestion;

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
            UserService userService = (UserService) ServiceHandler.getInstance().getService(ServiceFactory.ServiceType.USER);
            QuestionAnswerService questionAnswerService = (QuestionAnswerService) ServiceHandler.getInstance().getService(ServiceFactory.ServiceType.QUESTIONANSWER);
            long admin = userService.getAllUsers().stream().filter(userDTO -> userDTO.getType().equals("ADMIN")).count();
            long user = userService.getAllUsers().stream().filter(userDTO -> userDTO.getType().equals("USER")).count();
            long questions = questionAnswerService.getAllQuestions().size();
            lblAdmin.setText(String.valueOf(admin));
            lblUsers.setText(String.valueOf(user));
            lblQuestion.setText(String.valueOf(questions));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
