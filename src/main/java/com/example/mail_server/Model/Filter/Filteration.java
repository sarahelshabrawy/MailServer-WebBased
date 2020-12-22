package com.example.mail_server.Model.Filter;

import com.example.mail_server.Model.Mail;

import java.util.LinkedList;


public class Filteration implements FilterField{
    private FilterField senderFilter;
    private FilterField subjectFilter;




    public  Filteration (FilterField senderFilter, FilterField subjectFilter){
          this.senderFilter=senderFilter;
          this.subjectFilter=subjectFilter;
   }
    @Override
    public LinkedList<Mail> filter(LinkedList<Mail> mails) {
        LinkedList<Mail> SubjectMails=subjectFilter.filter(mails);
        return senderFilter.filter(SubjectMails);


    }

    @Override
    public void setFilter(String subject) {

    }



}
