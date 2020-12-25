package com.example.mail_server.Model.Sort;

import com.example.mail_server.Model.indexMail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;

public class SortWithDate implements ISortMail {
    @Override
    public List<indexMail> Sort(LinkedList<indexMail> mails) {
        try {
            return divide(mails, 0, mails.size()-1);
        }
        catch (ParseException ignored){
            return null;
        }
    }


    LinkedList<indexMail> merge(LinkedList<indexMail> list, int l, int m, int r) throws ParseException {
        /* Create temp lists */
        List<indexMail> L = list.subList(l,m+1);
        List<indexMail> R = list.subList(m+1,r+1);
        LinkedList<indexMail> sortedList = new LinkedList<>();
        /*Copy data to temp lists*/
        ListIterator<indexMail> leftIterator = L.listIterator(0);
        ListIterator<indexMail> rightIterator = R.listIterator(0);

        /* Merge the temp lists */

        while (leftIterator.hasNext() && rightIterator.hasNext()) {
            indexMail left = leftIterator.next();
            indexMail right = rightIterator.next();
            if(formatDate(left.getDate()).before(formatDate(right.getDate()))){
                sortedList.add(left);
                rightIterator.previous();
            } else {
                sortedList.add(right);
                leftIterator.previous();
            }
        }

        /* Copy remaining elements of L[] if any */
        while (leftIterator.hasNext()) {
            sortedList.add(leftIterator.next());
        }

        /* Copy remaining elements of R[] if any */
        while (rightIterator.hasNext()) {
            sortedList.add(rightIterator.next());
        }
        return sortedList;
    }

    // Main function that sorts list[l..r] using
    // merge()
    List<indexMail> divide(LinkedList<indexMail> list, int l, int r) throws ParseException {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            divide(list, l, m);
            divide(list, m + 1, r);

            // Merge the sorted halves
            return merge(list, l, m, r);
        }
        //walla null ?
        return new LinkedList<>();
    }

    Date formatDate(String string) throws ParseException {
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        return format.parse(string);
    }


}
