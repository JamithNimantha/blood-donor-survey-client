package com.jamith.rmi.dto;

import java.io.Serializable;

/**
 * @author Jamith Nimantha
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String fullName;
    private String email;
    private String mobile;
    private String type;
    private String password;
    private transient String salt;

    /**
     * @return User ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set User Id
     *
     * @param id User ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return User Full Name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Set User Full Name
     *
     * @param fullName User Full Name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return User Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set User Email
     *
     * @param email User email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Get User Mobile Number
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Set User Mobile Number
     *
     * @param mobile User Mobile Number
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return User Type
     */
    public String getType() {
        return type;
    }

    /**
     * Set User Type
     *
     * @param type User Type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return User Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set User Password
     *
     * @param password User Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Get Salt Value
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Set Salt Value
     *
     * @param salt Salt Value
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return Generated TOString for the Object
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDTO{");
        sb.append("id=").append(id);
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", salt='").append(salt).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
