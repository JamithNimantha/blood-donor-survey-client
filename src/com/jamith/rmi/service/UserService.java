package com.jamith.rmi.service;

import com.jamith.rmi.dto.UserDTO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Jamith Nimantha
 */
public interface UserService extends SuperService {

    /**
     * Save instance of user
     *
     * @param userDTO UserDTO
     * @return true if the instance of the user is saved
     * @throws Exception
     */
    boolean saveUser(UserDTO userDTO) throws Exception;

    /**
     * Register User
     *
     * @param userDTO UserDTO
     * @return true if user registered
     * @throws Exception
     */
    boolean registerUser(UserDTO userDTO) throws Exception;

    /**
     * Create New Admin User
     *
     * @param userDTO Admin UserDTO
     * @return true if admin created
     * @throws Exception
     */
    boolean createAdminUser(UserDTO userDTO) throws Exception;

    /**
     * Update the instance of the User
     *
     * @param userDTO UserDTO
     * @return true if user updated
     * @throws Exception
     */
    boolean updateUser(UserDTO userDTO) throws Exception;

    /**
     * Delete User
     *
     * @param id id of the User to be deleted
     * @return true if user deleted
     * @throws Exception
     */
    boolean deleteUser(Integer id) throws Exception;

    /**
     * Login validation for the user. Generate session If email and password are validated
     *
     * @param email    email of the user
     * @param password password of the user
     * @return the generated session for the useer
     * @throws RemoteException
     */
    String login(String email, String password) throws RemoteException;

    /**
     * logOut and destroy the user session
     *
     * @param cookie session cookie
     * @return true if session destroyed
     */
    boolean logout(String cookie) throws RemoteException;


    /**
     * Get all the Users as a UserDTO List
     *
     * @return all the Users
     */
    List<UserDTO> getAllUsers() throws RemoteException;

    /**
     * Find User by Email
     *
     * @return UserDTO
     * @throws RemoteException
     */
    UserDTO findByEmail(String email) throws RemoteException;
    
}
