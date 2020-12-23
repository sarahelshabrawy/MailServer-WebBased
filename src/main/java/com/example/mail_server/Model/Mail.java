package com.example.mail_server.Model;

import com.example.mail_server.Model.Sort.SortText.IndicesSorting;

public class Mail {

    private String subject;
    private String body;
    private String sender;
    private String[] receivers;
    private String date;
    private int priority;
    private String[] attachments;
    private String id = "0" ;
    private Object[] sortedBody;
    private Object[] sortedSubject;
    private IndicesSorting indicesSorting = new IndicesSorting();

    public Mail(){}

    public Mail(String subject, String body, String sender, String[] receivers, String date, int priority) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.receivers = receivers;
        this.date = date;
        this.priority = priority;
        //this.attachments = attachments;

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.sortedSubject = indicesSorting.sortMailContent(subject);
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.sortedBody = indicesSorting.sortMailContent(body);
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String[] getReceivers() {
        return receivers;
    }

    public void setReceivers(String[] receivers) {
        this.receivers = receivers;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String[] getAttachments() {
        return attachments;
    }

    public void setAttachments(String[] attachments) {
        this.attachments = attachments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object[] getSortedBody() {
        return sortedBody;
    }

    public Object[] getSortedSubject() {
        return sortedSubject;
    }

    public void setSortedBody(Object[] sortedBody) {
        this.sortedBody = sortedBody;
    }

    public void setSortedSubject(Object[] sortedSubject) {
        this.sortedSubject = sortedSubject;
    }

}
