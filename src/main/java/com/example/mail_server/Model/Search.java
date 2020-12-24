package com.example.mail_server.Model;

import com.example.mail_server.Model.Sort.SortText.indexedWord;

import java.awt.*;
import java.util.LinkedList;

public class Search {

    private static class searchResults{
        Object source;
        Point[] occurrences;

        public searchResults(Object source, Point[] occurrences) {
            this.source = source;
            this.occurrences = occurrences;
        }
    }

    public LinkedList<searchResults> search(LinkedList<Mail> mails, String x){
        LinkedList<searchResults> searchResults = new LinkedList<>();
        for(Mail mail : mails){
            Point[] occurrences = (Point[]) binarySearch(mail.getSortedSubject(),0,mail.getSortedSubject().length-1,x);
            if(occurrences!=null)
                searchResults.add(new searchResults(mail, occurrences));
        }
        return searchResults;
    }


    Object[] binarySearch(Object[] arr, int l, int r, String x)
    {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            indexedWord castedElement = (indexedWord) arr[mid];
            if (castedElement.getWord().equalsIgnoreCase(x))
                return findAllOccurrences(arr,mid,x);//return its original index in the UNSORTED array

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (castedElement.getWord().compareToIgnoreCase(x) > 0)
                return binarySearch(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return null;
    }

    public Object[] findAllOccurrences  (Object[] arr,int mid,String x){
        LinkedList<Point> occurrencesIndices = new LinkedList<>();
        for (int i = mid; i < arr.length ; i++) {
            indexedWord castedElement = (indexedWord) arr[i];
            if (castedElement.getWord().equalsIgnoreCase(x))
                occurrencesIndices.add(new Point(castedElement.getStart(),castedElement.getEnd()));
            else break;
        }
        for (int i = mid-1; i > 0 ; i--) {
            indexedWord castedElement = (indexedWord) arr[i];
            if (castedElement.getWord().equalsIgnoreCase(x))
                occurrencesIndices.add(new Point(castedElement.getStart(),castedElement.getEnd()));
            else break;
        }
        return occurrencesIndices.toArray();
    }



}
