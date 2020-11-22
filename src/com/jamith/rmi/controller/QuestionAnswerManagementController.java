package com.jamith.rmi.controller;

import com.jamith.rmi.dto.AnswerDTO;
import com.jamith.rmi.dto.AnswerTableDTO;
import com.jamith.rmi.dto.QuestionDTO;
import com.jamith.rmi.dto.QuestionTableDTO;
import com.jamith.rmi.service.QuestionAnswerService;
import com.jamith.rmi.service.ServiceFactory;
import com.jamith.rmi.service.ServiceHandler;
import com.jamith.rmi.util.Notification;
import com.jamith.rmi.util.ViewLoader;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Jamith Nimantha
 */
public class QuestionAnswerManagementController implements Initializable {

    @FXML
    private Button btnHome;

    @FXML
    private TableView<AnswerTableDTO> tblAnswer;

    @FXML
    private TableView<QuestionTableDTO> tblQuestion;

    @FXML
    private TextField txtQuestion;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtAnswer;

    @FXML
    private Button btnAdd;

    private QuestionAnswerService questionAnswerService;

    private QuestionTableDTO toBeUpdated;

    @FXML
    private Button btnAnswerClear;

    private AnswerTableDTO toBeUpdatedAnswer;

    private List<AnswerDTO> toBeDeletedAnswerDTOs = new ArrayList<>();


    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (!txtAnswer.getText().trim().isEmpty()) {

            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO.setName(txtAnswer.getText());

            AnswerTableDTO answerTableDTO = new AnswerTableDTO();
            answerTableDTO.setTO(answerDTO);
            answerTableDTO.getBtnUpdate().setOnAction(event1 -> {
                tblAnswer.setDisable(true);
                txtAnswer.setText(answerTableDTO.getAnswer());
                tblAnswer.getItems().remove(answerTableDTO);
                toBeUpdatedAnswer = answerTableDTO;
                txtAnswer.requestFocus();

            });
            answerTableDTO.getBtnDelete().setOnAction(event1 -> {
                tblAnswer.getItems().remove(answerTableDTO);
                if (toBeUpdated != null) {
                    toBeUpdated.getQuestionDTO().getAnswerDTOS().remove(answerTableDTO.getAnswerDTO());
                    toBeUpdated.getAnswerTableDTOS().remove(answerTableDTO);
                }
            });
            if (toBeUpdated == null) {
                tblAnswer.getItems().add(answerTableDTO);
            } else {
                if (tblAnswer.isDisable()) {
                    toBeUpdatedAnswer.setAnswer(txtAnswer.getText());
                    toBeUpdatedAnswer.getAnswerDTO().setName(txtAnswer.getText());
                } else {
                    tblAnswer.getItems().add(answerTableDTO);
                    toBeUpdated.getQuestionDTO().getAnswerDTOS().add(answerTableDTO.getAnswerDTO());
                    toBeUpdated.getAnswerTableDTOS().add(answerTableDTO);
                }
            }
            clearAnswer();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (toBeUpdated == null) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setName(txtQuestion.getText());
            questionDTO.setAnswerDTOS(new ArrayList<>());
            tblAnswer.getItems().forEach(answerTableDTO -> questionDTO.getAnswerDTOS().add(answerTableDTO.getAnswerDTO()));
            try {
                boolean isSaved = questionAnswerService.saveQuestion(questionDTO);
                if (isSaved) {
                    Notification.infoNotify("Questions Saved!", "Questions Saved Successfully", event);
                    clear();
                } else {
                    Notification.errorNotify("Error !", "Questions Could Not Saved!", event);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            QuestionDTO questionDTO = toBeUpdated.getQuestionDTO();
            questionDTO.setName(txtQuestion.getText());
            try {
                if (!toBeDeletedAnswerDTOs.isEmpty()) {
                    questionAnswerService.deleteAnswers(toBeDeletedAnswerDTOs);
                }
                boolean rst = questionAnswerService.updateQuestion(questionDTO);
                if (rst) {
                    Notification.infoNotify("Questions Updated!", "Questions Updated Successfully", event);
                    clear();
                } else {
                    Notification.errorNotify("Error!", "Questions Could Not Updated!", event);
                }

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        ViewLoader.view(stage, this.getClass().getResource("/com/jamith/rmi/view/MainPanel.fxml"));
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
            loadQuestionTable();
            loadAnswerTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnAdd.disableProperty().bind(txtQuestion.textProperty().isEmpty().or(txtAnswer.textProperty().isEmpty()));
        btnSave.disableProperty().bind(txtQuestion.textProperty().isEmpty().or(Bindings.isEmpty(tblAnswer.getItems())));
    }

    private void loadQuestionTable() {

        tblQuestion.getItems().clear();
        tblQuestion.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblQuestion.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("question"));
        tblQuestion.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("btnUpdate"));
        tblQuestion.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        try {
            List<QuestionDTO> questionDTOList = questionAnswerService.getAllQuestions();
            List<QuestionTableDTO> questionTableDTOS = questionDTOList.stream().map(questionDTO -> {
                QuestionTableDTO questionTableDTO = new QuestionTableDTO();
                questionTableDTO.setTO(questionDTO);

                questionTableDTO.getBtnDelete().setOnAction(event -> {
                    boolean action = Notification.confirmNotify(
                            "Are you Sure?",
                            "This Action Can Not Be Undone!",
                            event);
                    if (action) {
                        tblQuestion.getItems().remove(questionTableDTO);
                        try {
                            questionAnswerService.deleteQuestion(questionDTO.getId());
                            Notification.infoNotify("Question Removed !", "Question Removed Successfully!", event);
                        } catch (Exception e) {
                            Notification.errorNotify("Error!", "Question Could not Removed!", event);
                            e.printStackTrace();
                        }
                    }
                });

                questionTableDTO.getBtnUpdate().setOnAction(event -> {
                    clearFields();
                    btnSave.setText("Update");
                    toBeUpdated = questionTableDTO;
                    txtQuestion.setText(questionDTO.getName());
                    tblAnswer.getItems().addAll(questionTableDTO.getAnswerTableDTOS());
                    tblQuestion.setDisable(true);
                    questionTableDTO.getAnswerTableDTOS().forEach(answerTableDTO -> {
                        answerTableDTO.getBtnUpdate().setOnAction(event1 -> {
                            tblAnswer.setDisable(true);
                            txtAnswer.setText(answerTableDTO.getAnswer());
                            tblAnswer.getItems().remove(answerTableDTO);
                            toBeUpdatedAnswer = answerTableDTO;
                            txtAnswer.requestFocus();

                        });
                        answerTableDTO.getBtnDelete().setOnAction(event1 -> {
                            toBeDeletedAnswerDTOs.add(answerTableDTO.getAnswerDTO());
                            tblAnswer.getItems().remove(answerTableDTO);
                            toBeUpdated.getQuestionDTO().getAnswerDTOS().remove(answerTableDTO.getAnswerDTO());
                            toBeUpdated.getAnswerTableDTOS().remove(answerTableDTO);
                        });
                    });
                });

                return questionTableDTO;
            }).collect(Collectors.toList());

            tblQuestion.getItems().addAll(questionTableDTOS);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAnswerClearOnAction(ActionEvent event) {
        tblAnswer.getItems().clear();
        clearAnswer();
    }

    private void clearAnswer() {
        if (toBeUpdated != null) {
            tblAnswer.getItems().clear();
            tblAnswer.getItems().addAll(toBeUpdated.getAnswerTableDTOS());
        }
        toBeUpdatedAnswer = null;
        txtAnswer.clear();
        tblAnswer.setDisable(false);
    }

    private void clear() {
        clearFields();
        loadQuestionTable();
        toBeUpdated = null;
        toBeUpdatedAnswer = null;
        toBeDeletedAnswerDTOs = new ArrayList<>();
        tblAnswer.setDisable(false);
        tblQuestion.setDisable(false);
        btnSave.setText("Save");
    }

    private void clearFields() {
        txtQuestion.clear();
        txtAnswer.clear();
        tblAnswer.getItems().clear();

    }

    private void loadAnswerTable() {
        tblAnswer.getItems().clear();
        tblAnswer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("answer"));
        tblAnswer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("btnUpdate"));
        tblAnswer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }
}
