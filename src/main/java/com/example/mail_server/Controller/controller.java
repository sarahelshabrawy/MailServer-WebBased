package com.example.mail_server.Controller;

import com.example.mail_server.Model.Mail;
import com.example.mail_server.Model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class controller {
    private User user;

    public controller(){
        user = new User();
    }
    @CrossOrigin
    @PostMapping("/compose")
    public void compose(@RequestBody Mail mail){
        user.Compose(mail);
    }


}
