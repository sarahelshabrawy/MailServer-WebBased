package com.example.mail_server.Model.Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class contactSearchResults {
    Object contact;

    public Object getContact() {
        return contact;
    }

    public void setContact(Object contact) {
        this.contact = contact;
    }

    public Interval[] getEmailOccurrences() {
        return emailOccurrences;
    }

    public void setEmailOccurrences(Interval[] emailOccurrences) {
        this.emailOccurrences = emailOccurrences;
    }

    public Interval[] getNameOccurrences() {
        return nameOccurrences;
    }

    public void setNameOccurrences(Interval[] nameOccurrences) {
        this.nameOccurrences = nameOccurrences;
    }

    public contactSearchResults(Object contact, Interval[] emailOccurrences, Interval[] nameOccurrences) {
        this.contact = contact;
        this.emailOccurrences = emailOccurrences;
        this.nameOccurrences = nameOccurrences;
    }

    Interval[] emailOccurrences;
    Interval[] nameOccurrences;

}
