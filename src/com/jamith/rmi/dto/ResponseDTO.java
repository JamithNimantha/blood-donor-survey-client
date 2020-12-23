package com.jamith.rmi.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jamith Nimantha
 */
public class ResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private AnswerDTO answerDTO;

    private UserDTO userDTO;

    private Date date;

    /**
     * @return Response ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id Response ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return AnswerDTO of the ResponseDTO
     */
    public AnswerDTO getAnswerDTO() {
        return answerDTO;
    }

    /**
     * @param answerDTO Set Answer DTO for the Response DTO
     */
    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }

    /**
     * @return UserDTO for the particular Response
     */
    public UserDTO getUserDTO() {
        return userDTO;
    }

    /**
     * Set UserDTO
     *
     * @param userDTO
     */
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    /**
     * Return Date of the Response
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set Date for the Response
     *
     * @param date date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return Generated TOString of the Object
     */
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
