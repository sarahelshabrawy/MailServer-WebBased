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
    public boolean Compose(Mail mail) throws IOException {
        for (String receiver: mail.getReceivers()) {
            if(!proxy.checkEmail(receiver)){
                return false;
            }
        }
        mail.setSender(currentUser.getEmail());
        FileManager json = new FileManager();
        String myPath = "./Accounts/"+currentUser.getEmail()+"/sent/index.json";
        json.setNewID(mail, myPath);
        String path="./Accounts/"+currentUser.getEmail()+"/sent/"+mail.getId()+".json";
        json.saveJsonFile(mail, path);
        json.addMailToIndex(mail, myPath);
        for (String receiver: mail.getReceivers()) {
            myPath = "./Accounts/"+receiver+"/inbox/index.json";
            json.setNewID(mail, myPath);
            path="./Accounts/"+receiver+"/inbox/"+mail.getId()+".json";
            json.saveJsonFile(mail,path);
            json.addMailToIndex(mail, myPath);
        }
        return true;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }
}
