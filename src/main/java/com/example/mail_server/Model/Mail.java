package com.example.mail_server.Model;

public class Mail {

    private String subject;
    private String body;
    private String sender;
    private String[] receivers;
    private String date;
    private int priority;
    private String[] attachments;

    public Mail(String subject, String body, String sender, String[] receivers, String date, int priority, String[] attachments) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.receivers = receivers;
        this.date = date;
        this.priority = priority;
        this.attachments = attachments;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
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

}
