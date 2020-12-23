package com.jamith.rmi.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jamith Nimantha
 */

public class QuestionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String type;

    private String name;

    private List<AnswerDTO> answerDTOS;

    public QuestionDTO() {
    }

    /**
     * @return Question Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id Question ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Question Type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type Question Type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return Question Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Question Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return List of AnswerDTOs for the Question
     */
    public List<AnswerDTO> getAnswerDTOS() {
        return answerDTOS;
    }

    /**
     * @param answerDTOS Set list of AnswerDTOs for Question
     */
    public void setAnswerDTOS(List<AnswerDTO> answerDTOS) {
        this.answerDTOS = answerDTOS;
    }

    /**
     * @return Generated ToString for the Object
     */
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
