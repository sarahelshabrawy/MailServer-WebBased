package com.example.mail_server.Model.Account;

import com.example.mail_server.Model.DataManagement.FileManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

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
    public Account checkPassword(String email, String password) throws IOException {
        FileManager manager = new FileManager();
        JSONArray array = manager.listJsonObjects("./Accounts/Accounts.json");
        for(int i = 0 ;i < array.size();i++)
        {
            JSONObject object = (JSONObject) array.get(i);
            if( ( (String)object.get("email") ).equals(email) && ( (String)object.get("password") ).equals(password))
            {
                Account account = new Account();
                account.setEmail(email);
                account.setName( (String) object.get("name") );
                account.setPassword(password);
                return account;
            }
        }
        return null;
    }

}
