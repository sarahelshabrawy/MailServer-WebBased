package com.example.mail_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class MailServerApplication {

    public static void main(String[] args) {
        File folder = new File("/Accounts");
        System.out.println(folder.mkdir());
        SpringApplication.run(MailServerApplication.class, args);
    }

}
