package com.example.mail_server.Model.Account;

public class AccountBuilder {

    private Account account;

    public AccountBuilder(){
        account = new Account();
    }

    public void buildIndexFile(){}

    public void buildDirectories(){

    }
    public Account build(String name ,String email ,String password){
        account.setName(name);
        account.setEmail(email);
        account.setPassword(password);
        buildDirectories();
        buildIndexFile();
        return  account;
    }

}
