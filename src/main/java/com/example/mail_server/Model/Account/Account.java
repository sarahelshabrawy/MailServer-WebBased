package com.example.mail_server.Model.Account;

import com.example.mail_server.Model.Mail;

import java.util.HashMap;
import java.util.LinkedList;

public class Account {
    private String id;
    private String name;
    private String email;
    private String password;
    private LinkedList<Mail> inbox ;
    private LinkedList<Mail> sent;
    private LinkedList<Mail> draft;
    private LinkedList<Mail> trash;
    private HashMap<String,LinkedList<Mail>> userFolders;

    public Account(){
        inbox = new LinkedList<Mail>();
        sent = new LinkedList<Mail>();
        draft = new LinkedList<Mail>();
        trash = new LinkedList<Mail>();
        userFolders = new HashMap<String,LinkedList<Mail>>();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
