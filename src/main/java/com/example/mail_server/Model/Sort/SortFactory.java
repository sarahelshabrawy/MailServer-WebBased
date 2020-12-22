package com.example.mail_server.Model.Sort;

public class SortFactory {
    public ISortMail sortMails(String sortAccordingTo){
        if(sortAccordingTo.equalsIgnoreCase("subject"))
            return new SortSubject();
        else if (sortAccordingTo.equalsIgnoreCase("body"))
            return new SortBody();
        else if (sortAccordingTo.equalsIgnoreCase("sender"))
            return new SortSender();
        else if (sortAccordingTo.equalsIgnoreCase("date"))
            return new SortDate();
        return null;
    }

}
