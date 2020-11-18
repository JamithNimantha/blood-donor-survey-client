package com.jamith.rmi.controller;

import com.jamith.rmi.dto.QuestionDTO;
import com.jamith.rmi.service.QuestionAnswerService;
import com.jamith.rmi.service.ServiceFactory;
import com.jamith.rmi.service.ServiceHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
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
    private PieChart chart;

    @FXML
    private Button btnHome;

    @FXML
    private ComboBox<String> cmbQuestion;

    private QuestionAnswerService questionAnswerService;

    private List<QuestionDTO> questionDTOList = new ArrayList<>();


    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/jamith/rmi/view/MainPanel.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void cmbOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            questionAnswerService = (QuestionAnswerService) ServiceHandler.getInstance().getService(ServiceFactory.ServiceType.QUESTIONANSWER);
            loadAllQuestions();
        } catch (Exception e) {
            e.printStackTrace();
        }


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("18-25", 13),
                new PieChart.Data("25-35", 25),
                new PieChart.Data("35-45", 10),
                new PieChart.Data("Above 45", 22));
        chart.setData(pieChartData);

//        cmbQuestion.setPrefWidth(150);
//        cmbQuestion.setStyle("-fx-font: 30px \"Serif\";");
    }

    /**
     * Load all the questions to the combo box
     */
    private void loadAllQuestions() {
        try {
            List<QuestionDTO> questions = questionAnswerService.getAllQuestions();
            for (QuestionDTO question : questions) {
                cmbQuestion.getItems().add(String.format("%d. %s", questions.indexOf(question) + 1, question.getName()));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
