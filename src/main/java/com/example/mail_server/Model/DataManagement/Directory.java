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
    public void CopyFolder(File source ,File target,String id){
        try {
            if(source.isDirectory()) {
                copy(source,target,id);
            }else {
                System.out.println(target.getName()+"&"+source.getParentFile().getName());
                if(target.getName().equalsIgnoreCase(source.getParentFile().getName()+".json")){
                    target=new File(target.getParent()+"/"+id+".json");
                }
                Files.copy(source.toPath(), target.toPath());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void copy(File source,File target,String id) throws IOException{
        try {
            if(source.exists()) {
                if(!target.exists()) {
                    target.mkdir();
                }
                for(String child : source.list()) {
                    CopyFolder(new File(source,child),new File(target,child),id);
                }
            }else {
                throw new RuntimeException("File to be copied doesn't exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DeleteFolder(File file) { // to delete folders and files but not txt
        if (file.exists()) {
            for (File subFile : file.listFiles()) {
                if (subFile.isDirectory()) {
                    DeleteFolder(subFile);
                } else {
                    subFile.delete();
                }
            }
            file.delete();

        } else {
            throw new RuntimeException("Folder to be deleted doesn't exist");
        }
    }

    public boolean createUserFolder(String folderName, Account account)
    {
        String path = "./Accounts/" + account.getEmail() + "/" + folderName;
        File folder = new File(path);
        if(folder.exists())
            return false;
        folder.mkdir();
        return true;
    }

}
