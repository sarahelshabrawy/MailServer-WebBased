package com.example.mail_server.Model;

import com.example.mail_server.Model.Account.Account;
import com.example.mail_server.Model.Account.AccountBuilder;
import com.example.mail_server.Model.Account.AccountProxy;
import com.example.mail_server.Model.DataManagement.FileManager;

import java.io.IOException;

public class User {

    private Account currentUser;
    private AccountProxy proxy;
    private AccountBuilder builder;
    public User()
    {
        proxy = new AccountProxy();
        builder = new AccountBuilder();
    }
    public boolean signUp(String name, String email, String password) throws IOException {
        if(proxy.checkEmail(email))
            return false;
        currentUser = builder.build(name, email, password);
        return true;
    }

    public boolean signIn(String email, String password) throws IOException {
        currentUser = proxy.checkPassword(email, password);
        if(currentUser == null)
            return false;
        return true;
    }
    public void Compose(Mail mail){
        FileManager json = new FileManager();
        //add here method to get user's path instead
        json.saveJsonFile(mail, mail.getSender());
        for (String receiver: mail.getReceivers()) {
            //add here method to get user's path instead
            json.saveJsonFile(mail,receiver);
        }
    }
}
