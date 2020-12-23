package com.example.mail_server.Model.Sort;

import com.example.mail_server.Model.Sort.SortText.SortWithBody;
import com.example.mail_server.Model.Sort.SortText.SortWithSender;
import com.example.mail_server.Model.Sort.SortText.SortWithSubject;

public class SortFactory {
    public ISortMail sortMails(String sortAccordingTo){
        if(sortAccordingTo.equalsIgnoreCase("subject"))
            return new SortWithSubject();
        else if (sortAccordingTo.equalsIgnoreCase("body"))
            return new SortWithBody();
        else if (sortAccordingTo.equalsIgnoreCase("sender"))
            return new SortWithSender();
        else if (sortAccordingTo.equalsIgnoreCase("date"))
            return new SortWithDate();
        else if(sortAccordingTo.equalsIgnoreCase("priority"))
            return new SortWithPriority();
        return null;
    }

}
