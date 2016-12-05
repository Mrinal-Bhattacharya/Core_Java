//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package puzzls;

import java.util.Scanner;

public class StringFunny {
    /**
     * TODO - Place method description here
     * 
     * @param args
     */
    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int testCase = Integer.parseInt(in.nextLine());
        final String[] array = new String[testCase];
        for (int i = 0; i < testCase; i++) {
            array[i] = in.next();
        }
        for (int i = 0; i < array.length; i++) {
            final String result = isFunny(array[i]);
            System.out.println(result);
        }

    }

    private static String isFunny(final String str) {
        final StringBuilder orignalString = new StringBuilder(str);
        final String reverseStr = orignalString.reverse().toString();
        String result = "Funny";
        for (int i = 1; i < str.length() - 1; i++) {
            final int ascii =
                    Math.abs(((int)str.charAt(i)) - ((int)str.charAt(i - 1)));
            final int reverseAscii =
                    Math.abs(((int)reverseStr.charAt(i))
                             - ((int)reverseStr.charAt(i - 1)));
            if (ascii != reverseAscii) {
                result = "Not Funny";
                break;
            }
        }
        return result;
    }
}
