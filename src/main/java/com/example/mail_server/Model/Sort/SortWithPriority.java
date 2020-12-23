package com.example.mail_server.Model.Sort;

import com.example.mail_server.Model.Mail;
import com.example.mail_server.Model.DataStructures.PriorityQueue;

import java.util.LinkedList;
import java.util.List;

public class SortWithPriority implements ISortMail {

    @Override
    public List<Mail> Sort(LinkedList<Mail> mails) {
        PriorityQueue<Mail> mailPriorityQueue = new PriorityQueue<>();
        for (Mail mail : mails)
            mailPriorityQueue.insert(mail,mail.getPriority());
        return mailPriorityQueue.toList();
    }
}
