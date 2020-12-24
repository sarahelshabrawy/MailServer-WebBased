package com.example.mail_server.Model.Filter;

import com.example.mail_server.Model.Mail;

import java.util.LinkedList;

public class SenderField implements FilterField {
    private String sender;
    @Override
    public LinkedList<Mail> filter(LinkedList<Mail> mails) {
        LinkedList<Mail> SenderMails =new LinkedList<Mail>();

 System.out.println(sender);
        for(Mail mail : mails){
            if(mail.getSender().contains(sender)){

                SenderMails.add(mail);
            }
        }

        return SenderMails;
    }

    public void setFilter(String sender) {
        this.sender = sender;
    }
}
