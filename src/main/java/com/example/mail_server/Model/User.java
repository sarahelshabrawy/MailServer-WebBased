package com.example.mail_server.Model;

import com.example.mail_server.Model.Account.Account;
import com.example.mail_server.Model.Account.AccountBuilder;
import com.example.mail_server.Model.Account.AccountProxy;
import com.example.mail_server.Model.DataManagement.Directory;
import com.example.mail_server.Model.DataManagement.FileManager;
import com.example.mail_server.Model.Filter.FilterField;
import com.example.mail_server.Model.Filter.Filteration;
import com.example.mail_server.Model.Filter.SenderField;
import com.example.mail_server.Model.Filter.SubjectField;

import java.io.IOException;
import java.util.LinkedList;

public class User {

    private Account currentUser;
    private static AccountProxy proxy;
    private static AccountBuilder builder;
    private static User firstInstance;

    private User() {}
    public static User getInstance()
    {
        if(firstInstance == null){
            firstInstance = new User();
            proxy = new AccountProxy();
            builder = new AccountBuilder();
        }
        return firstInstance;
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


    public boolean addContact(Contact contact) throws IOException {
        if(!currentUser.CheckContactName(contact.getName())){
            return false;
        }
        for (String email: contact.getEmail()) {
            if(!proxy.checkEmail(email)){
                return false; }
        }
        System.out.println("aaaaaaaaaaaaaaa");
        FileManager json = new FileManager();
        String path="./Accounts/"+currentUser.getEmail()+"/contacts.json";
        json.addContact(contact,path);

        return true;
    }

    public LinkedList<Mail> filter(String senderField,String subjectField){
        LinkedList<Mail> mails = currentUser.getCurrentFolderMails();
        FilterField sender= new SenderField();
        sender.setFilter(senderField);
        FilterField subject= new SubjectField();
        subject.setFilter(subjectField);
        FilterField totalFilter= new Filteration(sender,subject);
        mails=totalFilter.filter(mails);

        return mails;

    }
    public boolean Compose(Mail mail) throws IOException {
        Directory directory=new Directory();
        for (String receiver: mail.getReceivers()) {
            if(!proxy.checkEmail(receiver)){
                return false;
            }
        }
        mail.setSender(currentUser.getEmail());
        FileManager json = new FileManager();
        String myPath = "./Accounts/"+currentUser.getEmail()+"/sent/index.json";
        json.setNewID(mail, myPath);
        directory.createFolder("./Accounts/"+currentUser.getEmail()+"/sent/"+mail.getId());
        String path="./Accounts/"+currentUser.getEmail()+"/sent/"+mail.getId()+"/"+mail.getId()+".json";
        json.saveJsonFile(mail, path);
        json.addMailToIndex(mail, myPath);
        for (String receiver: mail.getReceivers()) {
            myPath = "./Accounts/"+receiver+"/inbox/index.json";
            json.setNewID(mail, myPath);
            directory.createFolder("./Accounts/"+receiver+"/inbox/"+mail.getId());
            path="./Accounts/"+receiver+"/inbox/"+mail.getId()+"/"+mail.getId()+".json";
            json.saveJsonFile(mail,path);
            json.addMailToIndex(mail, myPath);
        }
        return true;
    }
    public LinkedList<Mail> moveMail(String[] id,String folderName) throws IOException {
        FileManager json=new FileManager();
        LinkedList<Mail> mails=currentUser.getCurrentFolderMails();
        for(int i=0;i<id.length;i++){
            for(Mail mail: mails){
                if(mail.getId().equalsIgnoreCase(id[i])){
                    mails.remove(mail);
                    json.moveMail(id[i],currentUser,mail,folderName);

                    break;
                }
            }

        }

        return mails;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }
}
