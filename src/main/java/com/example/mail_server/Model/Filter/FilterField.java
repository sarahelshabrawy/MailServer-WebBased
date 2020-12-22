package com.example.mail_server.Model.Filter;

import java.util.LinkedList;
import com.example.mail_server.Model.Mail;

public interface FilterField {
    public LinkedList<Mail> filter(LinkedList<Mail> mails);
    public void setFilter(String subject);
}
