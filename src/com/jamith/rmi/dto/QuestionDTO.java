package com.jamith.rmi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jamith Nimantha
 */

@Getter
@Setter
@ToString
public class QuestionDTO implements Serializable {

    private Integer id;

    private String type;

    private String name;

    private List<AnswerDTO> answerDTOS;
}
