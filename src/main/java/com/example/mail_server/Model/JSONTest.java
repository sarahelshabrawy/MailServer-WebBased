package com.example.mail_server.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JSONTest {

    public static void main(String[] args) {
        Mail mail = new Mail("A","B","C", new String[]{"A", "B"},"45",6,new String[]{"SAD","HAPPY"});
        String filePath = "output.json";
        JSONTest j = new JSONTest();
        j.saveJsonFile(mail,filePath);

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