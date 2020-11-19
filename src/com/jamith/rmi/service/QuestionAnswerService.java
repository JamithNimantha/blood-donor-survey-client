package com.jamith.rmi.service;

import com.jamith.rmi.dto.QuestionDTO;
import com.jamith.rmi.dto.ResponseDTO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Jamith Nimantha
 */
public interface QuestionAnswerService extends SuperService {

    /**
     * Create new Question
     *
     * @param questionDTO QuestionDTO
     * @return true if question saved
     */
    boolean saveQuestion(QuestionDTO questionDTO) throws RemoteException;

    /**
     * Update the question
     *
     * @param questionDTO QuestionDTO
     * @return true if instance of Question updated
     */
    boolean updateQuestion(QuestionDTO questionDTO) throws RemoteException;

    /**
     * Delete the Question
     *
     * @param id id of the Question
     * @return true if question deleted
     */
    boolean deleteQuestion(Integer id) throws Exception;

    /**
     * Get all the Questions
     *
     * @return all the question
     */
    List<QuestionDTO> getAllQuestions() throws RemoteException;

    /**
     * Save all the responses
     *
     * @param responseDTOS
     * @return true if Responses saved
     * @throws RemoteException
     */
    boolean saveResponse(List<ResponseDTO> responseDTOS) throws RemoteException;

    /**
     * TODO
     *
     * @param questionDTO Question DTO
     * @return TODO
     * @throws RemoteException
     */
    byte[] generateReport(QuestionDTO questionDTO) throws RemoteException;

}
