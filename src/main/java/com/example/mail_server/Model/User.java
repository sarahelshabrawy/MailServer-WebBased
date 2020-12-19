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
    public boolean Compose(Mail mail){
        for (String receiver: mail.getReceivers()) {
            if(!proxy.checkEmail(receiver)){
                return false;
            }
        }
        FileManager json = new FileManager();

      String path="./Accounts/"+currentUser.getEmail()+"/sent/"+mail.getReceivers()[0]+".json";
        json.saveJsonFile(mail, path);
        for (String receiver: mail.getReceivers()) {
           path="./Accounts/"+receiver+"/inbox/"+currentUser.getEmail()+".json";
            json.saveJsonFile(mail,path);
        }
        return true;
    }
}
