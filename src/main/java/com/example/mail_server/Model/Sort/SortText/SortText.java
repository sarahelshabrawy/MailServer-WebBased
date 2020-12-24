package com.example.mail_server.Model.Sort.SortText;

import com.example.mail_server.Model.Sort.ISortMail;
import com.example.mail_server.Model.indexMail;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

abstract class SortText implements ISortMail {

        @Override
        public List<indexMail> Sort(LinkedList<indexMail> mails) {
            return divide((indexMail[]) mails.toArray(), 0, mails.size()-1);
        }

        void merge(indexMail[] arr, int l, int m, int r) {
            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            /* Create temp arrays */
            indexMail[] L = new indexMail[n1];
            indexMail[] R = new indexMail[n2];

            /*Copy data to temp arrays*/
            System.arraycopy(arr, l, L, 0, n1);
            System.arraycopy(arr, m + 1, R, 0, n2);
            System.out.println(Arrays.toString(L));
            System.out.println(Arrays.toString(R));
            /* Merge the temp arrays */

            // Initial indexes of first and second subarrays
            int i = 0, j = 0;

            // Initial index of merged subarry array
            int k = l;
            while (i < n1 && j < n2) {
                if(getStringAttribute(L[i]).compareToIgnoreCase(getStringAttribute(R[j]))<0){
                    arr[k] = L[i];
                    i++;
                } else {
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
        List<indexMail> divide(indexMail[] arr, int l, int r) {
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
        abstract String getStringAttribute(indexMail mail);
    }
