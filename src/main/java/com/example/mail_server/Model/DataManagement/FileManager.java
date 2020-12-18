package com.example.mail_server.Model.DataManagement;

import com.example.mail_server.Model.Account.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

    public void addAccount(Account account) throws IOException {
        String path = "/Account/Accounts.json";
        JSONArray accounts = listJsonObjects(path);

        JSONObject new_account = new JSONObject();
        new_account.put("name",account.getName());
        new_account.put("email",account.getEmail());
        new_account.put("password",account.getPassword());

        accounts.add(new_account);
        addObjectToJson(path,accounts);

    }

    public boolean move() {
        return false;
    }

    public boolean delete() {
        return false;
    }

    public void addObjectToJson(String path, JSONArray array) throws IOException{
        File index = new File(path);
        FileWriter writer = new FileWriter(index);
        writer.write(array.toJSONString());
    }

    public JSONArray listJsonObjects(String path) throws IOException{
        File index = new File(path);
        FileWriter writer = new FileWriter(index);

        if(!index.exists()) {
            index.createNewFile();
            JSONArray array = new JSONArray();
            writer.write(array.toJSONString());
            return array;
        }

        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse( new FileReader(path) );
            JSONArray jsonArray = (JSONArray) obj;

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
}
