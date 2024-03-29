package com.jamith.rmi.dto;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jamith Nimantha
 */

public class QuestionTableDTO {

    private Integer id;

    private String question;

    private Button btnUpdate;

    private Button btnDelete;

    private QuestionDTO questionDTO;

    private List<AnswerTableDTO> answerTableDTOS;

    public void setTO(QuestionDTO dto) {
        this.setId(dto.getId());
        this.setQuestion(dto.getName());

        Button edit = new Button();
        edit.setStyle("-fx-font: 22 arial white; -fx-base: #396cff;");
        edit.setGraphic(new ImageView(new Image("/com/jamith/rmi/image/icons8-pencil-15.png")));
        this.setBtnUpdate(edit);

        Button del = new Button();
        del.setStyle("-fx-font: 22 arial white; -fx-base: #db2a10;");
        del.setGraphic(new ImageView(new Image("/com/jamith/rmi/image/icons8-cancel-15.png")));
        this.setBtnDelete(del);

        List<AnswerTableDTO> answerTableDTOList = dto.getAnswerDTOS().stream().map(answerDTO -> {
            AnswerTableDTO answerTableDTO = new AnswerTableDTO();
            answerTableDTO.setTO(answerDTO);
            return answerTableDTO;
        }).collect(Collectors.toList());

        this.setAnswerTableDTOS(answerTableDTOList);

        this.setQuestionDTO(dto);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Button getBtnUpdate() {
        return btnUpdate;
    }

    public void setBtnUpdate(Button btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    public QuestionDTO getQuestionDTO() {
        return questionDTO;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    public List<AnswerTableDTO> getAnswerTableDTOS() {
        return answerTableDTOS;
    }

    public void setAnswerTableDTOS(List<AnswerTableDTO> answerTableDTOS) {
        this.answerTableDTOS = answerTableDTOS;
    }
}
