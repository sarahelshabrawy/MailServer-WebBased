package com.example.mail_server.Model.DataManagement;

import com.example.mail_server.Model.Account.Account;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Directory{


    public void createFolder(String path){
        File folder = new File(path);
        System.out.println(folder.mkdir());
    }
    public boolean move(File sourceFile, File destFile)
    {
        if (sourceFile.isDirectory())
        {
            for (File file : sourceFile.listFiles())
            {
                move(file, new File(file.getPath().substring("temp".length()+1)));
            }
        }
        else
        {
            try {
                Files.move(Paths.get(sourceFile.getPath()), Paths.get(destFile.getPath()), StandardCopyOption.REPLACE_EXISTING);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }
}
