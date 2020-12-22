package com.example.mail_server.Model.Sort;

import com.example.mail_server.Model.Mail;

import java.util.LinkedList;
import java.util.List;

public interface ISortMail {
    public List<Mail> Sort(LinkedList<Mail> mails);
}
