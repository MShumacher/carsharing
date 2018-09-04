package com.training.carsharing;

import com.training.carsharing.model.impl.UserAccount;

import javax.mail.MessagingException;

public interface EmailSenderService {

    //void sendEmailFromWebSite(Message entity) throws MessagingException;

    void sendVerifyKey(UserAccount userAccount, String verifyKey) throws MessagingException;
}
