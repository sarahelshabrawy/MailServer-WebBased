package com.example.mail_server.Controller;

import com.example.mail_server.Model.*;
import com.example.mail_server.Model.Account.Account;
import com.example.mail_server.Model.DataManagement.FileManager;
import com.example.mail_server.Model.Mail.Mail;
import com.example.mail_server.Model.Mail.MoveMails;
import com.example.mail_server.Model.Mail.indexMail;
import com.example.mail_server.Model.Search.searchResults;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Objects;

@Configuration
@Deprecated
class MyConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedHeaders("*");
            }
        };
    }

}
@RestController
@CrossOrigin
public class controller {
    private User user;
    LinkedList<String> file;



    public controller(){
        user = User.getInstance();
        file = new LinkedList<String>();
    }
    @CrossOrigin
    @PostMapping("/compose")
    public boolean compose(@RequestBody Mail mail) throws IOException {
        System.out.println(mail.getBody());
        return user.Compose(mail);
    }

    @CrossOrigin
    @PostMapping("/attachment")
    public boolean attach(@RequestParam("file") MultipartFile[] file) throws IOException {
        for(MultipartFile file0 : file) {
            file0.transferTo(Paths.get(Objects.requireNonNull("./Accounts/Attachments/" + file0.getOriginalFilename())));
            this.file.add("./Accounts/Attachments/" + file0.getOriginalFilename());
        }
        return true;
    }


    @CrossOrigin
    @PostMapping("/draft")
    public boolean draft(@RequestBody Mail mail) throws IOException {
        System.out.println(mail.getBody());
        return user.draft(mail);
    }

    @CrossOrigin
    @RequestMapping("/createAccount")
    @ResponseBody
    public boolean createAccount(@RequestParam (value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password) throws IOException {
        return user.signUp(name, email, password);
    }
    @CrossOrigin
    @RequestMapping("/signIn")
    @ResponseBody
    public boolean signIn( @RequestParam(value = "email") String email, @RequestParam(value = "password") String password) throws IOException {
        return user.signIn(email, password);
    }

    @CrossOrigin
    @RequestMapping("/getMails")
    @ResponseBody
    public LinkedList<indexMail> getListMails(@RequestParam(value = "folderName") String folderName, @RequestParam(value = "pageNumber") int pageNumber) throws IOException {
        Account acc = user.getCurrentUser();
        return acc.loadFolder(folderName,pageNumber);
    }

    @CrossOrigin
    @RequestMapping("/sortMails")
    @ResponseBody
    public LinkedList<indexMail> sortMails(@RequestParam(value = "sort") String sort) {
        //new ??
        Account acc = user.getCurrentUser();
        return acc.sortFolder(sort);
    }

    @CrossOrigin
    @RequestMapping("/searchMails")
    @ResponseBody
    public LinkedList<searchResults> searchMails(@RequestParam(value = "target") String target) {
        Account acc = user.getCurrentUser();
        return acc.searchFolder(target);
    }


    @CrossOrigin
    @RequestMapping("/filter")
    @ResponseBody
    public LinkedList<indexMail> getFilteredMails(@RequestParam(value = "sender") String senderField,@RequestParam(value = "subject") String subjectField) {
      LinkedList<indexMail> mails= user.filter(senderField,subjectField);
      return mails;
    }


    @CrossOrigin
    @PostMapping("/addContact")
    public boolean  addContact(@RequestBody Contact contact) throws IOException {
        return user.addContact(contact);
    }

    @CrossOrigin
    @GetMapping("/removeContact")
    public boolean  removeContact(@RequestParam (value = "id") String id ) throws IOException {
        String path = "./Accounts/" + user.getCurrentUser().getEmail() + "/contacts.json";
        System.out.println("HHHHHHHHHHHHH");
        FileManager fileManager = new FileManager();
        fileManager.removeContact(path,id);
        return true;
    }

    @CrossOrigin
    @RequestMapping("/getContacts")
    @ResponseBody
    public LinkedList<Contact> getContacts() throws IOException {
        Account acc = user.getCurrentUser();
        LinkedList<Contact> contacts = acc.loadContacts();
        return contacts;
    }
    @CrossOrigin
    @PostMapping ("/move")
    @ResponseBody
    public boolean moveMails(@RequestBody MoveMails moveMail) throws IOException {
         return user.moveMail(moveMail.getId(), moveMail.getFolderName());
    }

    @CrossOrigin
    @RequestMapping("/addFolder")
    @ResponseBody
    public boolean addFolder(@RequestParam(value = "folderName") String folderName) throws IOException {
      return User.getInstance().createNewFolder(folderName);
    }

    @CrossOrigin
    @RequestMapping("/getUserFolders")
    @ResponseBody
    public String[] getUserFolders(){
        return User.getInstance().getUserFoldersList();
    }
    @CrossOrigin
    @RequestMapping("/openMail")
    @ResponseBody
    public Mail openMail(@RequestParam(value = "id") String id, @RequestParam(value = "currentFolder") String currentFolder) throws IOException, ParseException, ParseException {
        String path = "./Accounts/" + user.getCurrentUser().getEmail() + "/" + currentFolder + "/" + id +"/"+id +".json";
        FileManager fileManager = new FileManager();
        Mail mail = fileManager.getMailContent(path);
        return mail;
    }
    @CrossOrigin
    @RequestMapping("/renameFolder")
    @ResponseBody
    public boolean renameFolder(@RequestParam(value = "folderName") String folderName, @RequestParam(value = "newFolderName") String newFolderName){
        System.out.println(folderName + "   " + newFolderName);
        return User.getInstance().renameFolders(folderName, newFolderName);
    }

    @CrossOrigin
    @RequestMapping("/deleteFolder")
    @ResponseBody
    public boolean deleteFolder(@RequestParam(value = "folderName") String folderName){
        System.out.println(folderName);
        return User.getInstance().deleteFolders(folderName);
    }

}
