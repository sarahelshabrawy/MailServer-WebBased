package com.example.mail_server.Model.Sort.SortContact;

public class SortContactFactory {
    public ISortContact sortContacts(String sortAccordingTo){
        if(sortAccordingTo.equalsIgnoreCase("subject"))
            return new SortWithContactEmail();
        else if (sortAccordingTo.equalsIgnoreCase("body"))
            return new SortWithContactName();
        return null;
    }
}
