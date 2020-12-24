package com.example.mail_server.Model.Filter;

import com.example.mail_server.Model.Mail;
import com.example.mail_server.Model.indexMail;

import java.util.LinkedList;

public class SenderField implements FilterField {
    private String sender;
    @Override
    public LinkedList<indexMail> filter(LinkedList<indexMail> mails) {
        LinkedList<indexMail> SenderMails = new LinkedList<>();


        for(indexMail mail : mails){
            if(mail.getSubject().equalsIgnoreCase(sender)){
                SenderMails.add(mail);
            }
        }
        return SenderMails;
    }

    public void setFilter(String sender) {
        this.sender = sender;
    }
}
