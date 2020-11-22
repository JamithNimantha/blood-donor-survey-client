package com.jamith.rmi.dto;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Jamith Nimantha
 */

@Getter
@Setter
@ToString
public class AnswerTableDTO {

    private Integer id;

    private String answer;

    private Button btnUpdate;

    private Button btnDelete;

    private AnswerDTO answerDTO;

    public void setTO(AnswerDTO dto) {
        this.setId(dto.getId());
        this.setAnswer(dto.getName());

        Button editButton = new Button();
        editButton.setGraphic(new ImageView(new Image("/com/jamith/rmi/image/icons8-pencil-15.png")));
        this.setBtnUpdate(editButton);

        Button delete = new Button();
        delete.setGraphic(new ImageView(new Image("/com/jamith/rmi/image/icons8-cancel-15.png")));
        this.setBtnDelete(delete);

        this.setAnswerDTO(dto);

    }

}
