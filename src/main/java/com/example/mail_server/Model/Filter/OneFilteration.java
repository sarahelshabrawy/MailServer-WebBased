package com.example.mail_server.Model.Filter;

import com.example.mail_server.Model.Mail;

import java.util.LinkedList;

public class OneFilteration implements FilterField {

    private FilterField senderFilter;
    private FilterField subjectFilter;



    public  OneFilteration (FilterField senderFilter, FilterField subjectFilter){
        this.senderFilter=senderFilter;
        this.subjectFilter=subjectFilter;
    }
    @Override
    public LinkedList<Mail> filter(LinkedList<Mail> mails) {
        LinkedList<Mail> SubjectMails=subjectFilter.filter(mails);
        LinkedList<Mail> SenderMails=senderFilter.filter(mails);

        for(Mail mail :SubjectMails){
            if(!SenderMails.contains(mail)){
                SenderMails.add(mail);
            }
        }
        return SenderMails;
    }

    @Override
    public void setFilter(String subject) {
    }
}
