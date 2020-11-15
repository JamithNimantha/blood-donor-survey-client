package com.jamith.rmi.service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author Jamith Nimantha
 */
public class ServiceHandler implements ServiceFactory {

    private static ServiceHandler serviceHandler;
    private ServiceFactory serviceFactory;
    private UserService userService;
    private QuestionAnswerService questionAnswerService;

    private ServiceHandler() {
        try {
            serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:6666/survey");
            userService = (UserService) serviceFactory.getService(ServiceType.USER);
            questionAnswerService = (QuestionAnswerService) serviceFactory.getService(ServiceType.QUESTIONANSWER);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ServiceHandler getInstance() {
        if (serviceHandler == null) {
            serviceHandler = new ServiceHandler();
        }
        return serviceHandler;
    }

    @Override
    public SuperService getService(ServiceType serviceType) throws Exception {
        switch (serviceType) {
            case USER:
                return userService;
            case QUESTIONANSWER:
                return questionAnswerService;
            default:
                return null;
        }
    }
}
