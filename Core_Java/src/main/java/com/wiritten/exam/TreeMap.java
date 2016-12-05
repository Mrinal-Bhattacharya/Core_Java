//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.wiritten.exam;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class TreeMap {
    static Stack<Integer> stack = new Stack<Integer>();

    public static void main(final String[] args) {
        // stack.p
        final Set<String> t = new TreeSet<String>();
        t.add("3");
        t.add("1");
        t.add("2");
        t.add("11");
        System.out.println(t);
        System.out.println(t.add("9"));
        System.out.println(t.size());

        final Set<Person> s = new TreeSet<Person>();
        s.add(new Person());
        s.add(new Person());
        System.out.println(s);

    }

}

class Person {
    Integer age;

    public int compareTo(final Person p) {
        return this.age.compareTo(p.age);
    }
}