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

    /**
     * @return Answer Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id Answer Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Anwer Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set Answer Name
     *
     * @param name Answer Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return questionDTO
     */
    public QuestionDTO getQuestionDTO() {
        return questionDTO;
    }

    /**
     * Set Question DTO
     *
     * @param questionDTO
     */
    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    /**
     * @return Generated ToString for the Object
     */
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
