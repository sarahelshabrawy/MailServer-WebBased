package com.example.mail_server.Model.Sort.SortText;

import com.example.mail_server.Model.Sort.ISortMail;
import com.example.mail_server.Model.indexMail;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

abstract class SortText implements ISortMail {

        @Override
        public List<indexMail> Sort(LinkedList<indexMail> mails) {
            return divide(mails, 0, mails.size()-1);
        }

        LinkedList<indexMail> merge(LinkedList<indexMail> list, int l, int m, int r) {
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
                if(getStringAttribute(left).compareToIgnoreCase(getStringAttribute(right))<0){
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
        List<indexMail> divide(LinkedList<indexMail> list, int l, int r) {
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
        abstract String getStringAttribute(indexMail mail);
    }
