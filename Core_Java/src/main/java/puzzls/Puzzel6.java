//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package puzzls;

public class Puzzel6 {
    public static void main(final String[] args) {
        System.out.println((char)(int)(byte)-1);
        final char c = (char)(byte)-1;
        final int i = (short)c;
        System.out.println(i);
        System.out.println((int)(char)(byte)-1);// First, the byte is converted
                                                // to an int via widening
                                                // primitive conversion, and
                                                // then the resulting int is
                                                // converted to a char by
                                                // narrowing primitive
                                                // conversion.
    }
}
