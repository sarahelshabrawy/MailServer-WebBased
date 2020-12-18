package com.example.mail_server.Model;

import com.example.mail_server.Model.Account.Account;

public class User {

    private Account currentUser;

//    public Account register(){}
//
//    public boolean signIn(){}
//
    public void Compose(Mail mail){
        JSONTest json = new JSONTest();
        //add here method to get user's path instead
        json.saveJsonFile(mail, mail.getSender());
        for (String receiver: mail.getReceivers()) {
            //add here method to get user's path instead
            json.saveJsonFile(mail,receiver);
        }
    }
}
