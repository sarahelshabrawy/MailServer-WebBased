package com.example.mail_server.Model.Search;

import com.example.mail_server.Model.Mail.Priority;
import com.example.mail_server.Model.Mail.indexMail;

import java.util.LinkedList;

public class searchFacade {
    public LinkedList<searchResults> search(LinkedList<indexMail> mails, String x){
        LinkedList<searchResults> searchResults = new LinkedList<>();
        BinarySearch binarySearch = new BinarySearch();
        LinearSearch linearSearch = new LinearSearch();
        for(indexMail mail : mails){
            Interval[] subjectOccurrences =  binarySearch.search(mail.getSortedSubject(),0,mail.getSortedSubject().length-1,x);
            Interval[] bodyOccurrences =  binarySearch.search(mail.getSortedSubject(),0,mail.getSortedBody().length-1,x);
             Interval[] priorityOccurrences = linearSearch.search(Priority.mapPriority(mail.getPriority()),x);
            Interval[] senderOccurrences = linearSearch.search(mail.getSender(),x);
            Interval[] dateOccurrences = linearSearch.search(mail.getDate(),x);
            if(subjectOccurrences!=null || bodyOccurrences!=null || priorityOccurrences!=null|| senderOccurrences!=null || dateOccurrences!=null)
                searchResults.add(new searchResults(mail, subjectOccurrences, bodyOccurrences, senderOccurrences, priorityOccurrences, priorityOccurrences, dateOccurrences));
        }
        return searchResults;
    }

}
