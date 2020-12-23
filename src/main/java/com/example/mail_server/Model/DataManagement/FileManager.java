package com.example.mail_server.Model.DataManagement;

import com.example.mail_server.Model.Account.Account;
import com.example.mail_server.Model.Contact;
import com.example.mail_server.Model.Mail;
import com.example.mail_server.Model.Sort.SortText.IndicesSorting;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

public class FileManager {


    public void addContact(Contact contact,String path) throws IOException {
        JSONArray contacts = listJsonObjects(path);
        JSONArray Emails=new JSONArray();
        Collections.addAll(Emails, contact.getEmail());
        JSONObject new_contact = new JSONObject();
        new_contact.put("name",contact.getName());
        new_contact.put("email",Emails);
        contacts.add(new_contact);
        addObjectToJson(path,contacts);
    }

    public void addAccount(Account account) throws IOException {
        String path = "./Accounts/Accounts.json";
        JSONArray accounts = listJsonObjects(path);
        JSONObject new_account = new JSONObject();
        new_account.put("name",account.getName());
        new_account.put("email",account.getEmail());
        new_account.put("password",account.getPassword());

        accounts.add(new_account);
        addObjectToJson(path,accounts);
    }




    public void addMailToIndex(Mail mail, String path) throws IOException {
        JSONArray mails = listJsonObjects(path);
        JSONObject newMail = new JSONObject();
        newMail.put("id",mail.getId());
        newMail.put("sender",mail.getSender());
        newMail.put("receiver", mail.getReceivers()[0]);
        newMail.put("subject", mail.getSubject());
        newMail.put("date", mail.getDate());
        newMail.put("body", mail.getBody());
        newMail.put("priority",mail.getPriority());

        JSONArray sortedBody = new JSONArray();
        for(Object word : mail.getSortedBody()){
            JSONObject wordJson = new JSONObject();
            IndicesSorting.indexedWord castedObject = (IndicesSorting.indexedWord)word ;
            wordJson.put("word",castedObject.getWord());
            wordJson.put("start",castedObject.getStart());
            wordJson.put("end",castedObject.getEnd());
            sortedBody.add(wordJson);

        }

        JSONArray sortedSubject = new JSONArray();
        for(Object word : mail.getSortedSubject()){
            JSONObject wordJson = new JSONObject();
            IndicesSorting.indexedWord castedObject = (IndicesSorting.indexedWord)word ;
            wordJson.put("word",castedObject.getWord());
            wordJson.put("start",castedObject.getStart());
            wordJson.put("end",castedObject.getEnd());
            sortedBody.add(wordJson);

        }

        newMail.put("sortedBody",sortedBody);
        newMail.put("sortedSubject",sortedSubject);
        mails.add(newMail);
        addObjectToJson(path,mails);
    }



    public boolean moveMail(String id,Account account,Mail mail,String folderName) throws IOException {
        Directory directory=new Directory();
        String sourcePath="./Accounts/"+account.getEmail()+"/"+account.getCurrentFolderName()+"/"+id;
        String path="./Accounts/"+account.getEmail()+"/"+folderName+"/index.json";
        this.setNewID(mail,path);
        this.addMailToIndex(mail,path);
        this.deleteFromIndex("./Accounts/"+account.getEmail()+"/"+account.getCurrentFolderName()+"/index.json",id);
        path="./Accounts/"+account.getEmail()+"/"+folderName+"/"+mail.getId();
        directory.createFolder(path);
        directory.CopyFolder(new File(sourcePath),new File(path),mail.getId());
        directory.DeleteFolder(new File(sourcePath));


        return true;
    }

    public void deleteFromIndex(String path ,String id) throws IOException {
        JSONObject mail=new JSONObject();
        JSONArray list = listJsonObjects(path);
        for (Object o : list) {
            mail = (JSONObject) o;
            if (((String) mail.get("id")).equalsIgnoreCase(id)) {
                break;
            }
        }
        list.remove(mail);
        addObjectToJson(path,list);

    }


    public void addObjectToJson(String path, JSONArray array) throws IOException{
        File index = new File(path);
        FileWriter writer = new FileWriter(index);
        writer.write(array.toJSONString());
        writer.close();
    }

    public JSONArray listJsonObjects(String path) throws IOException{
        File index = new File(path);
        FileWriter writer;
        if(!index.exists()) {
            index.createNewFile();
            JSONArray array = new JSONArray();
            writer = new FileWriter(index);
            writer.write(array.toJSONString());
            writer.close();
            System.out.println("created xxxxxxxxxxxxxxxxxxxxxxxxxxx");
            return array;
        }
        JSONParser jsonParser = new JSONParser();
        System.out.println("YASARAAAH"+path);
        try {
            JSONArray jsonArray= (JSONArray) jsonParser.parse( new FileReader(path) );
            System.out.println(jsonArray);
            return jsonArray;
        }
        catch (IOException | ParseException e){
            e.printStackTrace();
            return null;
        }
    }

    public void saveJsonFile(Object object,String filePath){
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(object, writer);
            Assert.assertTrue(Files.exists(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setNewID(Mail mail, String path) throws IOException {
        //leih keda
        JSONArray list = listJsonObjects(path);
            if(list == null || list.size() == 0)
            {
            mail.setId("0");
            return;
        }
        JSONObject lastMail = (JSONObject) list.get(list.size() - 1);
        long id = Long.parseLong((String)lastMail.get("id")) + 1;
        mail.setId(Long.toString(id));
    }
    public Mail getMailContent(String path) throws IOException, ParseException {
        FileReader reader = new FileReader(path);
        JSONParser parser = new JSONParser();
        JSONObject jsonobject = (JSONObject) parser.parse(reader);
        reader.close();
        Mail mail = new Mail();
        mail.setBody(jsonobject.get("body").toString());
        mail.setSubject(jsonobject.get("subject").toString());
        mail.setSender(jsonobject.get("sender").toString());
        mail.setDate(jsonobject.get("date").toString());
        mail.setId(jsonobject.get("id").toString());
        mail.setPriority(((Long) jsonobject.get("priority")).intValue());
//        mail.setAttachments(jsonobject.get(""));
        return mail;
    }
}