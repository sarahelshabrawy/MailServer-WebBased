package com.example.mail_server.Model.Sort;

import com.example.mail_server.Model.Mail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;

public class SortDate implements ISortMail {
    @Override
    public List<Mail> Sort(LinkedList<Mail> mails) {
        try {
            return divide((Mail[]) mails.toArray(), 0, mails.size());
        }
        catch (ParseException ignored){
            return null;
        }
    }

    void merge(Mail[] arr, int l, int m, int r) throws ParseException {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        Mail[] L = new Mail[n1];
        Mail[] R = new Mail[n2];

        /*Copy data to temp arrays*/
        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr,m+1,R,0,n2);
//        for (int j = 0; j < n2; ++j)
//            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (formatDate(L[i].getDate()).before(formatDate(R[j].getDate()) )) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    List<Mail> divide(Mail[] arr, int l, int r )throws ParseException {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            divide(arr, l, m);
            divide(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
        return Arrays.asList(arr);

    }
    Date formatDate(String string) throws ParseException {
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        return format.parse(string);
    }


}
