package com.example.mail_server.Model.Sort.SortMail;

import java.lang.Integer;

import java.util.*;

public class SortNumbers  {
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
//        ListIterator<Integer> listIterator = list.listIterator(l);

        /* Merge the temp lists */
        int k = l;
        while (leftIterator.hasNext() && rightIterator.hasNext()) {
            Integer left = leftIterator.next();
            Integer right = rightIterator.next();
            if(left<right){
                list.set(k,left);
//                sortedList.add(left);
                rightIterator.previous();
            } else {
                list.set(k,right);
                leftIterator.previous();
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (leftIterator.hasNext()) {
            list.set(k,leftIterator.next());
            k++;
//            listIterator.set(leftIterator.next());
//            sortedList.add(leftIterator.next());
        }

        /* Copy remaining elements of R[] if any */
        while (rightIterator.hasNext()) {
            list.set(k,rightIterator.next());
            k++;
//            listIterator.set(rightIterator.next());
        }
        System.out.println(Arrays.toString(list.toArray()));
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

    public static void main(String[] args) {
        SortNumbers lala = new SortNumbers();
        List<Integer> haha = new LinkedList<>();
        for (int i = 3; i >= 0; i--) {
            haha.add(i);
        }

        haha = lala.Sort((LinkedList<Integer>) haha);
        System.out.println(Arrays.toString(haha.toArray()));

    }
}