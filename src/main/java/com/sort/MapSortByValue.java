//****************************************************************
//* Copyright (c) 2015. All Rights Reserved.
//****************************************************************
package com.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MapSortByValue {
    public static void main(final String[] args) {
        final Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("A", 100);
        map.put("B", 200);
        map.put("C", 200);
        map.put("D", 400);
        final ValueComparator comparator = new ValueComparator(map);
        final TreeMap<String, Integer> treeMap =
                new TreeMap<String, Integer>(comparator);
        treeMap.putAll(map);
        System.out.println(treeMap);

        final Scanner scanner = new Scanner("333 127 11111");
        System.out.println(scanner.nextShort());
        final Integer[] a = {0, 1, 2, 3, 4, 5};
        final List<Integer> a1 = Arrays.asList(a);
        a[2] = 53;
        System.out.println(a1.get(2));
        final Integer[] a2 = {0, 1, 2, 3, 4, 5};
        final Integer[] a3 = a2;
        a2[0] = 50;
        System.out.println(a3[0]);
        final String[] k = {"1", "2", null, "3"};
        final String[] v = {"A", null, "B", "C"};
        final Map<String, String> m1 = new HashMap<String, String>();
        for (int i = 0; i < 4; i++) {
            m1.put(k[i], v[i]);
            System.out.print(m1.get(k[i]) + " ,");
        }

    }
}

class ValueComparator implements Comparator<String> {
    public Map<String, Integer> map;

    public ValueComparator(final Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public int compare(final String object1, final String object2) {
        if (this.map.get(object1) >= this.map.get(object2)) {
            return -1;
        } else {
            return 1;
        }

    }

}
