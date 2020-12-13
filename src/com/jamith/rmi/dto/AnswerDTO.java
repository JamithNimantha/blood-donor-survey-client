package com.jamith.rmi.dto;

import java.io.Serializable;

/**
 * @author Jamith Nimantha
 */

public class AnswerDTO implements Serializable {

    private Integer id;

    private String name;

    private QuestionDTO questionDTO;

    public AnswerDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuestionDTO getQuestionDTO() {
        return questionDTO;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AnswerDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", questionDTO=").append(questionDTO);
        sb.append('}');
        return sb.toString();
    }
}
