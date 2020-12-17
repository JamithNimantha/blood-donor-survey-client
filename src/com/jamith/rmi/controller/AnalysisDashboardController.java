package com.jamith.rmi.controller;

import com.jamith.rmi.dto.QuestionDTO;
import com.jamith.rmi.service.QuestionAnswerService;
import com.jamith.rmi.service.ServiceFactory;
import com.jamith.rmi.service.ServiceHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Jamith Nimantha
 */
public class AnalysisDashboardController implements Initializable {

    @FXML
    private ImageView imageView;

    @FXML
    private Button btnHome;

    @FXML
    private ComboBox<String> cmbQuestion;

    private QuestionAnswerService questionAnswerService;

    private List<QuestionDTO> questionDTOList = new ArrayList<>();


    /**
     * @param event Generate Report on Action
     */
    @FXML
    void cmbOnAction(ActionEvent event) {
        int selectedIndex = cmbQuestion.getSelectionModel().getSelectedIndex();
        try {
            byte[] bytes = questionAnswerService.generateReport(questionDTOList.get(selectedIndex));
            imageView.setImage(new Image(new ByteArrayInputStream(bytes)));
            imageView.setFitHeight(487);
            imageView.setFitWidth(757);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
        } catch (RemoteException e) {
            e.printStackTrace();
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

        try {
            questionAnswerService = (QuestionAnswerService) ServiceHandler.getInstance().getService(ServiceFactory.ServiceType.QUESTIONANSWER);
            loadAllQuestions();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        cmbQuestion.setPrefWidth(150);
//        cmbQuestion.setStyle("-fx-font: 30px \"Serif\";");
    }

    /**
     * Load all the questions to the combo box
     */
    private void loadAllQuestions() {
        try {
            questionDTOList = questionAnswerService.getAllQuestions();
            for (QuestionDTO question : questionDTOList) {
                cmbQuestion.getItems().add(String.format("%d. %s", questionDTOList.indexOf(question) + 1, question.getName()));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
