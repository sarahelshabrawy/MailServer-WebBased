package com.example.mail_server.Model.Account;

import com.example.mail_server.Model.DataManagement.FileManager;
import com.example.mail_server.Model.Mail;
import com.example.mail_server.Model.Sort.ISortMail;
import com.example.mail_server.Model.Sort.SortFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Account {
    private String name;
    private String email;
    private String password;
    private LinkedList<Mail> inbox ;
    private LinkedList<Mail> sent;
    private LinkedList<Mail> draft;
    private LinkedList<Mail> trash;
    private HashMap<String,LinkedList<Mail>> userFolders;
    private FileManager fileManager;

    public Account(){
        inbox = new LinkedList<>();
        sent = new LinkedList<Mail>();
        draft = new LinkedList<Mail>();
        trash = new LinkedList<Mail>();
        userFolders = new HashMap<String,LinkedList<Mail>>();
        fileManager = new FileManager();
    }


    public LinkedList<Mail> loadFolder(String folderName,String sort) throws IOException {
        String path = "./Accounts/" + email + "/" + folderName + "/index.json";
        JSONArray mails = fileManager.listJsonObjects(path);
        LinkedList<Mail> mailList = new LinkedList<>();
        for (Object o : mails) {
            JSONObject obj = (JSONObject) o;
            Mail mail = new Mail();
            mail.setId((String) obj.get("id"));
            mail.setDate((String) obj.get("date"));
            mail.setSender((String) obj.get("sender"));
            mail.setSubject((String) obj.get("subject"));
            mail.setBody((String) obj.get("body"));
            String[] receivers = new String[1];
            receivers[0] = obj.get("receiver").toString();
            mail.setReceivers(receivers);
            mailList.add(mail);
        }
        SortFactory sortFactory = new SortFactory();
        ISortMail sortMail = sortFactory.sortMails(sort);
        mailList = (LinkedList<Mail>) sortMail.Sort(mailList);
        return mailList;
    }

    public void addUserFolder(String folderName){}

    public LinkedList<Mail> getUserFolder(String folderName) {
        return userFolders.get(folderName);
    }

    public LinkedList<Mail> getInbox() {
        return inbox;
    }

    public void setInbox(LinkedList<Mail> inbox) {
        this.inbox = inbox;
    }

    public LinkedList<Mail> getSent() {
        return sent;
    }

    public void setSent(LinkedList<Mail> sent) {
        this.sent = sent;
    }

    public LinkedList<Mail> getDraft() {
        return draft;
    }

    public void setDraft(LinkedList<Mail> draft) {
        this.draft = draft;
    }

    public LinkedList<Mail> getTrash() {
        return trash;
    }

    public void setTrash(LinkedList<Mail> trash) {
        this.trash = trash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
