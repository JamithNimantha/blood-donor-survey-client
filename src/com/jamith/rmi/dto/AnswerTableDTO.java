package com.jamith.rmi.dto;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Jamith Nimantha
 */
public class AnswerTableDTO {

    private Integer id;

    private String answer;

    private Button btnUpdate;

    private Button btnDelete;

    private AnswerDTO answerDTO;

    /**
     * Set answer details
     *
     * @param dto Answer DTO
     */
    public void setTO(AnswerDTO dto) {
        this.setId(dto.getId());
        this.setAnswer(dto.getName());

        // Initialize Edit Button
        Button editButton = new Button();
        editButton.setStyle("-fx-font: 22 arial white; -fx-base: #396cff;");
        editButton.setGraphic(new ImageView(new Image("/com/jamith/rmi/image/icons8-pencil-15.png")));
        this.setBtnUpdate(editButton);

        // Initialize Delete Button
        Button delete = new Button();
        delete.setStyle("-fx-font: 22 arial white; -fx-base: #db2a10;");
        delete.setGraphic(new ImageView(new Image("/com/jamith/rmi/image/icons8-cancel-15.png")));
        this.setBtnDelete(delete);

        this.setAnswerDTO(dto);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public AnswerDTO getAnswerDTO() {
        return answerDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }
}
