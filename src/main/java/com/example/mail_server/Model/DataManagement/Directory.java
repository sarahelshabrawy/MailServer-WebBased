package com.example.mail_server.Model.DataManagement;

import com.example.mail_server.Model.Account.Account;

import java.io.File;

public class Directory{


    public void createFolder(String path){
        File folder = new File(path);
        System.out.println(folder.mkdir());
    }
    public boolean move() {
        return false;
    }
}
