package com.example.mail_server.Model.Sort.SortMail;

import java.lang.Integer;

import java.util.*;

public class SortOmmElnumbers  {
    //leih ??
    public List<Integer> Sort(LinkedList<Integer> mails) {
        return divide(mails, 0, mails.size()-1);
    }

    LinkedList<Integer> merge(LinkedList<Integer> list, int l, int m, int r) {
        /* Create temp lists */
        List<Integer> L = new ArrayList<>(list.subList(l,m+1));
        List<Integer> R = new ArrayList<>(list.subList(m+1,r+1));
        /*Copy data to temp lists*/
        ListIterator<Integer> leftIterator = L.listIterator();
        ListIterator<Integer> rightIterator = R.listIterator();
        ListIterator<Integer> listIterator = list.listIterator(l);

        /* Merge the temp lists */
        while (leftIterator.hasNext() && rightIterator.hasNext()) {
            Integer left = leftIterator.next();
            Integer right = rightIterator.next();
            listIterator.next();
            if(left<right){
                listIterator.set(left);
                rightIterator.previous();
            } else {
                listIterator.set(right);
                leftIterator.previous();
            }
        }

        /* Copy remaining elements of L[] if any */
        while (leftIterator.hasNext()) {
            listIterator.next();
            listIterator.set(leftIterator.next());
        }

        /* Copy remaining elements of R[] if any */
        while (rightIterator.hasNext()) {
            listIterator.next();
            listIterator.set(rightIterator.next());
        }
        return list;
    }

    // Main function that sorts list[l..r] using
    // merge()
    List<Integer> divide(LinkedList<Integer> list, int l, int r) {
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
        return null;
    }

//    public static void main(String[] args) {
//        SortOmmElnumbers lala = new SortOmmElnumbers();
//        List<Integer> haha = new LinkedList<>();
//        for (int i = 10; i >= 0; i--) {
//            haha.add(i);
//        }
//
//        haha = lala.Sort((LinkedList<Integer>) haha);
//        System.out.println(Arrays.toString(haha.toArray()));
//
//    }
}