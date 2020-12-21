package Filter;

import com.example.mail_server.Model.Mail;

import java.util.LinkedList;


public class Filteration implements FilterType{
    private FilterType senderFilter;
    private FilterType subjectFilter;
    private String subject;
    private String sender;



    public  Filteration (FilterType senderFilter, FilterType subjectFilter){
          this.senderFilter=senderFilter;
          this.subjectFilter=subjectFilter;
   }
    @Override
    public LinkedList<Mail> filter(LinkedList<Mail> mails) {
       subjectFilter.setFilter(subject);
       LinkedList<Mail> SubjectMails=subjectFilter.filter(mails);
        senderFilter.setFilter(sender);
        return senderFilter.filter(SubjectMails);


    }

    @Override
    public void setFilter(String subject) {

    }


    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
