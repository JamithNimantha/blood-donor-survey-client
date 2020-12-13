package com.jamith.rmi.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jamith Nimantha
 */

public class QuestionDTO implements Serializable {

    private Integer id;

    private String type;

    private String name;

    private List<AnswerDTO> answerDTOS;

    public QuestionDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AnswerDTO> getAnswerDTOS() {
        return answerDTOS;
    }

    public void setAnswerDTOS(List<AnswerDTO> answerDTOS) {
        this.answerDTOS = answerDTOS;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuestionDTO{");
        sb.append("id=").append(id);
        sb.append(", type='").append(type).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", answerDTOS=").append(answerDTOS);
        sb.append('}');
        return sb.toString();
    }
}
