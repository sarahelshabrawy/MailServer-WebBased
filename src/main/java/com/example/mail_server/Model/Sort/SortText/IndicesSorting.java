package com.example.mail_server.Model.Sort.SortText;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndicesSorting {

public static class indexedWord{
    String word;
    int start ;
    int end;

    public indexedWord(String word, int start, int end) {
        this.word = word;
        this.start = start;
        this.end = end;
    }
}



    public Object[] sortMailContent(String text) {

        LinkedList<indexedWord> temp = new LinkedList<>();
            String regex = "\\w+";
            System.out.println(text);
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find())
                temp.add(new indexedWord(matcher.group(),matcher.start(),matcher.end()-1));
            Object[] arr =  temp.toArray();
            divide(arr,0,arr.length-1);
            return arr;
//            for (Object word : arr) {
//                indexedWord castedObject = (indexedWord) word;
//                bw.write("\n" +  castedObject.word + " " + castedObject.start+ " "+ castedObject.end);
//            }
    }

    void merge(Object[] arr, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        Object[] L = new indexedWord[n1];
        Object[] R = new indexedWord[n2];

        /*Copy data to temp arrays*/
        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);
        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if(((indexedWord)L[i]).word.compareToIgnoreCase(((indexedWord)R[j]).word)<0){
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
    void divide(Object[] arr, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            divide(arr, l, m);
            divide(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public static void main(String[] args) {
        IndicesSorting h = new IndicesSorting();
        h.sortMailContent("It is a long established fact");
    }
}
