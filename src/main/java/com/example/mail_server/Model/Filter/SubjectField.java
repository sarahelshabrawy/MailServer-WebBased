package com.example.mail_server.Model.Filter;

import com.example.mail_server.Model.Mail;
import com.example.mail_server.Model.indexMail;

import java.util.LinkedList;


public class SubjectField implements FilterField {
    private  String subject;

    @Override
    public LinkedList<indexMail> filter(LinkedList<indexMail> mails) {
        LinkedList<indexMail> SubjectMails =new LinkedList<>();


        for(indexMail mail : mails){
            if(mail.getSubject().equalsIgnoreCase(subject)){
                SubjectMails.add(mail);
            }
        }
        return SubjectMails;
    }


    public void setFilter(String subject){
        this.subject=subject;
    }
}
