package com.example.mail_server.Model.Filter;

import com.example.mail_server.Model.Mail;

import java.util.LinkedList;

public class SenderField implements FilterField {
    private String sender;
    @Override
    public LinkedList<Mail> filter(LinkedList<Mail> mails) {
        LinkedList<Mail> SenderMails =new LinkedList<Mail>();


        for(Mail mail : mails){
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
