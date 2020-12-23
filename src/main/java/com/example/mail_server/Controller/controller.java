package com.example.mail_server.Controller;

import com.example.mail_server.Model.Account.Account;
import com.example.mail_server.Model.Contact;
import com.example.mail_server.Model.Mail;
import com.example.mail_server.Model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.LinkedList;

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


    public controller(){
        user = User.getInstance();
         /*   Account acc= new Account();
            acc.setName("radwa");
            acc.setEmail("tosahassan97@gmail.com");
            acc.setPassword("123456");
            user.setCurrentUser(acc);
*/
    }
    @CrossOrigin
    @PostMapping("/compose")
    public boolean compose(@RequestBody Mail mail) throws IOException {
        System.out.println(mail.getBody());
        return user.Compose(mail);
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
    public LinkedList<Mail> getListMails(@RequestParam(value = "folderName") String folderName) throws IOException {
        Account acc = user.getCurrentUser();
        return acc.loadFolder(folderName);
    }

    @CrossOrigin
    @RequestMapping("/sortMails")
    @ResponseBody
    public LinkedList<Mail> sortMails(@RequestParam(value = "sort") String sort) {
        //new ??
        Account acc = user.getCurrentUser();
        return acc.sortFolder(sort);
    }


    @CrossOrigin
    @RequestMapping("/filter")
    @ResponseBody
    public LinkedList<Mail> getFilteredMails(@RequestParam(value = "sender") String senderField,@RequestParam(value = "subject") String subjectField) {
      LinkedList<Mail> mails= user.filter(senderField,subjectField);

        return mails;
    }

    @CrossOrigin
    @RequestMapping("/search")
    @ResponseBody
    public LinkedList<Mail> getsearchedMails(@RequestParam(value = "sender") String senderField,@RequestParam(value = "subject") String subjectField) {
        LinkedList<Mail> mails= user.filter(senderField,subjectField);

        return mails;
    }
    @CrossOrigin
    @PostMapping("/addContact")
    public boolean  addContact(@RequestBody Contact contact) throws IOException {
        return user.addContact(contact);
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
    @PostMapping ("/delete")
    @ResponseBody
    public LinkedList<Mail> deleteMails(@RequestBody String[] id) throws IOException {

           return user.deleteMail(id);
    }

}
