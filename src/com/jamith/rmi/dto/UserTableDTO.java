package com.jamith.rmi.dto;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Jamith Nimantha
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(Button updateButton) {
        this.updateButton = updateButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
