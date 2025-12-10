package com.awsproyect.AWS_Proyect.Service;

public interface ISessionService {

    String login(Long id, String password);
    boolean verifySession(String sessionString);
    boolean  logoutSession(String sessionString);
}
