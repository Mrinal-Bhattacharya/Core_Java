//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.puzzel;

public class Puzzel5 {
    public static void main(final String[] args) {
        System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));// when
                                                                        // 0xcafebabe
                                                                        // read
                                                                        // (int)
                                                                        // -889275714
                                                                        // but
                                                                        // it
                                                                        // should
                                                                        // come
                                                                        // (long)
                                                                        // 3405691582
        solution();
    }

    private static void solution() {
        System.out.println(Long.toHexString(0x100000000L + 0xcafebabeL));
    }
}
