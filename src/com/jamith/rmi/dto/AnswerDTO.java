package com.jamith.rmi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Jamith Nimantha
 */

@Getter
@Setter
@ToString
public class AnswerDTO implements Serializable {

    private Integer id;

    private String name;

    private QuestionDTO questionDTO;
}
