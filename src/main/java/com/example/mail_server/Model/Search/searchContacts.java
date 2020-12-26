package com.example.mail_server.Model.Search;

import com.example.mail_server.Model.Contact;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Collections;
import java.util.LinkedList;

public class searchContacts {
    public LinkedList<contactSearchResults> search(LinkedList<Contact> contacts, String x){
        LinkedList<contactSearchResults> contactSearchResults = new LinkedList<>();
        LinearSearch linearSearch = new LinearSearch();
        for(Contact contact : contacts){
            Interval[] nameOccurrences = linearSearch.search(contact.getName(),x);
            LinkedList<Interval> emailOccurrences = new LinkedList<>();
            for (String email:contact.getEmail()) {
                Collections.addAll(emailOccurrences,linearSearch.search(email,x));
        }
            if(emailOccurrences.size() != 0 ||( nameOccurrences!=null&&nameOccurrences.length > 0 ))
                contactSearchResults.add(new contactSearchResults(contact,Interval.toArray(emailOccurrences), nameOccurrences));
        }
        System.out.println("ALOOOOOOOOOOOOOOO");
        System.out.println(Arrays.toString(contactSearchResults.toArray()));
        return contactSearchResults;
    }
}
