package com.jamith.rmi.dto;

import javafx.scene.control.Button;
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
        edit.setText("Edit");
        this.setUpdateButton(edit);

        Button del = new Button();
        del.setText("Delete");
        this.setDeleteButton(del);

        this.setUserDTO(dto);

    }
}
