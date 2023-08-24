package com.fullcycle.admin.catalogo.domain.category;

import java.util.Scanner;

public class Wesley {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int k = scan.nextInt();
        scan.close();

        System.out.println(getSmallestAndLargest(s, k));
    }

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = s.substring(0, k);
        String largest = s.substring(0, k);

        for(int i = 0; i <= s.length() - k; i++) {
            String subString = s.substring(i,i+k);

            if(subString.compareTo(smallest)<0)
            {
                smallest = subString;
            }

            if(subString.compareTo(largest) > 0) {
                largest = subString;
            }
        }



        return smallest + "\n" + largest;
    }
}
