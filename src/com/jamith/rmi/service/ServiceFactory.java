package com.jamith.rmi.service;

/**
 * @author Jamith Nimantha
 */
public interface ServiceFactory extends SuperService {

    SuperService getService(ServiceType serviceType) throws Exception;

    enum ServiceType {
        USER, QUESTIONANSWER, RESPONSE
    }

}
