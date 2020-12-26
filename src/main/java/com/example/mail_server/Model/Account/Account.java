package com.example.mail_server.Model.Account;

import com.example.mail_server.Model.Contact;
import com.example.mail_server.Model.DataManagement.FileManager;
import com.example.mail_server.Model.Search.searchFacade;
import com.example.mail_server.Model.Sort.ISortMail;
import com.example.mail_server.Model.Sort.SortText.indexedWord;
import com.example.mail_server.Model.Mail.indexMail;
import com.example.mail_server.Model.Sort.SortFactory;
import com.example.mail_server.Model.Search.searchResults;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Account {
    private String name;
    private String email;
    private String password;
    private LinkedList<indexMail> currentFolderindexMails ;
    String currentFolderName;
    private LinkedList<Contact> contacts;
    private HashMap<String,LinkedList<indexMail>> userFolders;
    private FileManager fileManager;




    public Account(){
        currentFolderindexMails=new LinkedList<indexMail>();
        contacts= new LinkedList<>();
        userFolders = new HashMap<>();
        fileManager = new FileManager();
    }

    public boolean CheckContactName(String name) throws IOException {
        String path = "./Accounts/" + email+ "/" + "contacts.json";
        JSONArray contacts = fileManager.listJsonObjects(path);

        for (Object contact : contacts) {
            JSONObject obj = (JSONObject) contact;
            if (((String) obj.get("name")).equalsIgnoreCase(name)) {
                return false;
            }
        }

        return true;
    }
    public LinkedList<Contact> loadContacts() throws IOException {
        String path = "./Accounts/" + email+ "/" + "contacts.json";
        JSONArray contacts = fileManager.listJsonObjects(path);
        LinkedList<Contact> contactList = new LinkedList<Contact>();
        for (Object o : contacts) {
            JSONObject obj = (JSONObject) o;
            Contact contact = new Contact();
            contact.setName((String) obj.get("name"));
            JSONArray Emails = new JSONArray();
            Emails = (JSONArray) obj.get("email");
            System.out.println(Emails.size());
            String[] Contact_Email = new String[Emails.size()];
            for (int j = 0; j < Emails.size(); j++) {

                Contact_Email[j] = (String) Emails.get(j);

            }
            contact.setEmail(Contact_Email);
            contactList.add(contact);
        }

       this.contacts=contactList;
        return contactList;
    }


    public LinkedList<indexMail> loadFolder(String folderName,int page) throws IOException {
        int start=page*11-10;int end =page*11;
        int counter=1;
        this.currentFolderName=folderName;
        String path = "./Accounts/" + email + "/" + folderName + "/index.json";
        System.out.println(path);
        JSONArray mails = fileManager.listJsonObjects(path);
        System.out.println(mails.toJSONString());
        System.out.println(mails.size()+"WWW");
        LinkedList<indexMail> mailList = new LinkedList<>();
        for (Object o : mails) {
            JSONObject obj = (JSONObject) o;
            String receiver = obj.get("receiver").toString();
            indexMail mail = new indexMail((String) obj.get("subject"),(String) obj.get("body"),
                    (String) obj.get("sender"),receiver,(String) obj.get("date"), ((Long) obj.get("priority")).intValue());
            mail.setId((String) obj.get("id"));

            JSONArray sortedBody = (JSONArray) obj.get("sortedBody");
            indexedWord[] sortedBodywow = new indexedWord[sortedBody.size()];
            int i=0;
            for(Object s : sortedBody){
                JSONObject jsonObject = (JSONObject) s ;
                indexedWord castedObject = new indexedWord((String)jsonObject.get("word"),
                        ((Long) jsonObject.get("start")).intValue(),((Long) jsonObject.get("end")).intValue());
                sortedBodywow[i] = castedObject;
                i++;
            }

            JSONArray sortedSubject = (JSONArray) obj.get("sortedBody");
            indexedWord[] sortedSubjectwow = new indexedWord[sortedSubject.size()];
            i=0;
            for(Object s : sortedSubject){
                JSONObject jsonObject = (JSONObject) s ;
                indexedWord castedObject = new indexedWord((String)jsonObject.get("word"),
                        ((Long) jsonObject.get("start")).intValue(),((Long) jsonObject.get("end")).intValue());
                sortedSubjectwow[i] = castedObject;
                i++;
            }
            mail.setSortedBody(sortedBodywow);
            mail.setSortedBody(sortedSubjectwow);
            if(counter>=start&&counter<=end) {
                mailList.add(mail);
                if (counter == end)
                    break;
            }
            counter++;

        }
        this.currentFolderindexMails=mailList;
        System.out.println("MAMA"+Arrays.toString(mailList.toArray()));
        return this.currentFolderindexMails;
    }

    public LinkedList<indexMail> sortFolder(String sort){
        SortFactory sortFactory = new SortFactory();
        ISortMail sortMail = sortFactory.sortMails(sort);
        if(currentFolderindexMails!=null && !currentFolderindexMails.isEmpty())
              currentFolderindexMails = (LinkedList<indexMail>) sortMail.Sort(currentFolderindexMails);
        return currentFolderindexMails;
    }
    public LinkedList<searchResults> searchFolder(String target){
        searchFacade Search = new searchFacade();
        return  Search.search(getCurrentFolderMails(),target);
    }
    public void addUserFolder(String folderName){}

    public LinkedList<indexMail> getUserFolder(String folderName) {
        return userFolders.get(folderName);
    }

    public LinkedList<indexMail> getCurrentFolderMails() {
        return currentFolderindexMails;
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

    public String getCurrentFolderName() {
        return currentFolderName;
    }
}
