package com.example.mail_server.Controller;

import com.example.mail_server.Model.Email;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class controller {
        @CrossOrigin
        @PostMapping("/compose")
    public void compose(@RequestBody Email email){

        }
}
