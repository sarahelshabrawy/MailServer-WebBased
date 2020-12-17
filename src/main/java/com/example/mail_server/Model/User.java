package com.example.mail_server.Model;

public class User {
    public void Compose(Email email){
        JSONTest json = new JSONTest();
        //add here method to get user's path instead
        json.saveJsonFile(email,email.getSender());
        for (String receiver:email.getReceivers()) {
            //add here method to get user's path instead
            json.saveJsonFile(email,receiver);
        }
    }
}
