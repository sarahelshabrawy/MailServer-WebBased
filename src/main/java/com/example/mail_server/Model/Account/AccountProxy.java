package com.example.mail_server.Model.Account;

import java.io.File;

public class AccountProxy {

    public boolean checkEmail(String email){
        File folder = new File("./Accounts");
        File[] list = folder.listFiles();
        for(File file : list)
        {
            if(file.getName().equals(email))
            {
                return true;
            }
        }
        return false;
    }
    public Account checkPassword(String email, String password){
        return null;
    }

}
