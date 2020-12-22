package com.example.mail_server.Model.Account;

import com.example.mail_server.Model.Contact;
import com.example.mail_server.Model.DataManagement.FileManager;
import com.example.mail_server.Model.Mail;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Account {
    private String name;
    private String email;
    private String password;
    private LinkedList<Mail> currentFolderMails ;
    private LinkedList<Contact> contacts;
    private HashMap<String,LinkedList<Mail>> userFolders;
    private FileManager fileManager;




    public Account(){
        currentFolderMails=new LinkedList<Mail>();
        contacts=new LinkedList<Contact>();
        userFolders = new HashMap<String,LinkedList<Mail>>();
        fileManager = new FileManager();
    }

    public LinkedList<Contact> loadContacts() throws IOException {
        String path = "./Accounts/" + email+ "/" + "contacts.json";
        JSONArray contacts = fileManager.listJsonObjects(path);
        LinkedList<Contact> contactList = new LinkedList<Contact>();
        for ( int i = 0 ;i < contacts.size();i++){
            JSONObject obj = (JSONObject) contacts.get(i);
           Contact contact=new Contact();
           contact.setName((String) obj.get("name"));
            JSONArray Emails=new JSONArray();
            Emails= (JSONArray) obj.get("email");
           System.out.println( Emails.size());
            String[] Contact_Email=new String[Emails.size()];
            for ( int j = 0 ;j< Emails.size();j++){

                Contact_Email[j]=(String) Emails.get(j);

            }
            contact.setEmail(Contact_Email);
            contactList.add(contact);
        }

       this.contacts=contactList;
        return contactList;
    }
    public LinkedList<Mail> loadFolder(String folderName) throws IOException {
        String path = "./Accounts/" + email + "/" + folderName + "/index.json";
        JSONArray mails = fileManager.listJsonObjects(path);
        LinkedList<Mail> mailList = new LinkedList<Mail>();
        for ( int i = 0 ;i < mails.size();i++){
            JSONObject obj = (JSONObject) mails.get(i);
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
        this.currentFolderMails=mailList;
        return mailList;
    }

    public void addUserFolder(String folderName){}

    public LinkedList<Mail> getUserFolder(String folderName) {
        return userFolders.get(folderName);
    }

    public LinkedList<Mail> getCurrentFolderMails() {
        return currentFolderMails;
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

    public LinkedList<Contact> getContacts() {
        return contacts;
    }

}
