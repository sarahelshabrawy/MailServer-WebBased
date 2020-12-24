package com.example.mail_server.Model.Filter;

import com.example.mail_server.Model.Mail;

import java.util.LinkedList;


public class SubjectField implements FilterField {
    private  String subject;

    @Override
    public LinkedList<Mail> filter(LinkedList<Mail> mails ){
        LinkedList<Mail> SubjectMails =new LinkedList<Mail>();

        System.out.println(subject);
        for(Mail mail : mails){
            if(mail.getSubject().contains(subject)){
                SubjectMails.add(mail);
            }
        }
        return SubjectMails;
    }


    public void setFilter(String subject){
        this.subject=subject;
    }
}
