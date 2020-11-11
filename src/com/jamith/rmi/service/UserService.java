package com.jamith.rmi.service;

import com.jamith.rmi.dto.UserDTO;

/**
 * @author Jamith Nimantha
 */
public interface UserService extends Service {

    public boolean saveUser(UserDTO userDTO) throws Exception;

    public boolean registerUser(UserDTO userDTO) throws Exception;
}
