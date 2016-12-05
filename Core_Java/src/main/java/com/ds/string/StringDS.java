package com.ds.string;

import java.util.ArrayList;
import java.util.List;

public class StringDS {
    public static void main(final String[] args) {
        final String str = "nitin";
        System.out.println(isPalindrom(str));
        reverseRecurive("test");
        final StringDS ds = new StringDS();
        ds.test(0);
        final List<Integer> z = new ArrayList<Integer>();
        ds.generic(z);

        final StringBuffer s = new StringBuffer("ABC");
        // doPerm(s, s.length());
        premu(s, 0, 2);

    }

    public static boolean palindrom(final String str) {
        int start = 0;
        int end = str.length() - 1;
        final int middle = (start + end) / 2;
        int i = 0;
        for (; i <= middle; i++) {
            if (str.charAt(start) == str.charAt(end)) {
                start--;
                end--;
            } else {
                break;
            }

        }
        if (i == middle + 1) {
            return true;
        }
        return false;

    }

    public void generic(final List a) {

    }

    public void test(final long x) {
        System.out.println("test1");
    }

    public void test(final Integer x) {
        System.out.println("test2");
    }

    public static boolean isUniqueChars2(final String str) {

        // initalize array of boolean with false value. Total we have 256 ascii
        // char
        final boolean[] char_set = new boolean[256];

        for (int i = 0; i < str.length(); i++) {
            // return ascii value of char
            final int val = str.charAt(i);
            if (char_set[val])
                return false;
            char_set[val] = true;
        }
        return true;
    }

    public static boolean isPalindrom(final String str) {
        if (str.length() == 0 || str.length() == 1) {
            return true;
        }
        if (str.charAt(0) == str.charAt(str.length() - 1)) {
            return isPalindrom(str.substring(1, str.length() - 1));
        } else {
            return false;
        }
    }

    public static String reverseRecurive(final String str) {
        if (str.length() <= 1) {
            return str;
        }

        return reverseRecurive(str.substring(1)) + str.charAt(0);
    }

    public static String removeDuplicatesEff(final char[] str) {
        if (str == null)
            return null;
        final int len = str.length;
        if (len < 2)
            return String.valueOf(str);
        final boolean[] hit = new boolean[256];
        for (int i = 0; i < 256; ++i) {
            hit[i] = false;
        }
        hit[str[0]] = true;
        int tail = 1;
        for (int i = 1; i < len; ++i) {
            if (!hit[str[i]]) {
                str[tail] = str[i];
                ++tail;
                hit[str[i]] = true;
            }
        }
        str[tail] = 0;
        return String.valueOf(str);
    }

    public static boolean isRotation(final String s1, final String s2) {
        final int len = s1.length();
        /* check that s1 and s2 are equal length and not empty */
        if (len == s2.length() && len > 0) {
            /* concatenate s1 and s1 within new buffer */
            final String s1s1 = s1 + s1;
            // if we have isSubStringMethod
            // return isSubstring(s1s1, s2);
        }
        return false;

    }

    private static void swap(final StringBuffer str, final int pos1,
            final int pos2) {
        final char t1 = str.charAt(pos1);
        str.setCharAt(pos1, str.charAt(pos2));
        str.setCharAt(pos2, t1);
    }

    private static void premu(final StringBuffer str, final int left,
            final int right) {
        int i;
        if (left == right) {
            System.out.println(str);
        } else {
            for (i = left; i <= right; i++) {
                swap(str, left, i);
                premu(str, left + 1, right);
                swap(str, left, i);
            }
        }
    }
}
