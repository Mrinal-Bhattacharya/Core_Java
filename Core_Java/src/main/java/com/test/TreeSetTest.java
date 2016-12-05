//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.test;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(final String[] args) {
        final Set<Person> set = new TreeSet<Person>();
        final Person p1 = new Person(25, "May");
        final Person p2 = new Person(65, "Amy");
        final Person p3 = new Person(25, "Amya");
        System.out.println(set.add(p1));
        System.out.println(set.add(p2));
        System.out.println(set.add(p3));
    }
}

class Person implements Comparable<Person> {

    int age;
    String name;

    public Person(final int age, final String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int hashCode() {

        return this.name.length();

    }

    @Override
    public boolean equals(final Object o) {
        final Person p = (Person)o;
        return this.name.equalsIgnoreCase(p.name);

    }

    @Override
    public int compareTo(final Person o) {

        return Double.compare(age, o.age);

    }

}
