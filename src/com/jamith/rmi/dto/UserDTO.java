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
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String fullName;
    private String email;
    private String mobile;
    private String type;
    private String password;
    private transient String salt;

}
