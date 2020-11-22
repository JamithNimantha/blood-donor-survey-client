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
public class UserTableDTO {
    private Integer id;
    private String fullName;
    private String email;
    private String mobile;
    private String type;
    private Button updateButton;
    private Button deleteButton;
    private UserDTO userDTO;

    public void setTO(UserDTO dto) {
        this.setId(dto.getId());
        this.setFullName(dto.getFullName());
        this.setEmail(dto.getEmail());
        this.setType(dto.getType());
        this.setMobile(dto.getMobile());

        Button edit = new Button();
        edit.setStyle("-fx-font: 22 arial white; -fx-base: #396cff;");
        edit.setGraphic(new ImageView(new Image("/com/jamith/rmi/image/icons8-pencil-15.png")));
        this.setUpdateButton(edit);

        Button del = new Button();
        del.setStyle("-fx-font: 22 arial white; -fx-base: #db2a10;");
        del.setGraphic(new ImageView(new Image("/com/jamith/rmi/image/icons8-cancel-15.png")));
        this.setDeleteButton(del);

        this.setUserDTO(dto);

    }
}
