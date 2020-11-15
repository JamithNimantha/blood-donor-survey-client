package com.jamith.rmi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jamith Nimantha
 */
@Getter
@Setter
@ToString
public class ResponseDTO implements Serializable {

    private Integer id;

    private AnswerDTO answerDTO;

    private UserDTO userDTO;

    private Date date;
}
