package com.jamith.rmi.service;

import java.rmi.Remote;

/**
 * @author Jamith Nimantha
 */
public interface ServiceFactory extends Remote {

    public Service getService(ServiceType serviceType) throws Exception;

    public enum ServiceType {
        USER
    }

}
