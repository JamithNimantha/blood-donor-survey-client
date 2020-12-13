package com.jamith.rmi.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jamith Nimantha
 */
public class ResponseDTO implements Serializable {

    private Integer id;

    private AnswerDTO answerDTO;

    private UserDTO userDTO;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AnswerDTO getAnswerDTO() {
        return answerDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseDTO{");
        sb.append("id=").append(id);
        sb.append(", answerDTO=").append(answerDTO);
        sb.append(", userDTO=").append(userDTO);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
