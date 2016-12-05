//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationTest extends Parent implements Serializable {

    public SerializationTest(final String str) {
        super(str);

    }

    private static int size = 20;
    private String name;
    private Address address;

    public static void main(final String[] args) throws Exception {
        // final ArrayList a = new ArrayList();
        // a.

        final FileOutputStream f = new FileOutputStream("tmp");
        final ObjectOutput s = new ObjectOutputStream(f);
        final SerializationTest test = new SerializationTest("parent");
        final Address address = new Address("Add123");
        // address.add = "Add123";
        test.address = address;
        test.name = "Mrinal";
        test.size = 10;
        // test.parent = "";
        s.writeObject(test);
        s.flush();
        final FileInputStream in = new FileInputStream("tmp");
        final ObjectInputStream s2 = new ObjectInputStream(in);
        final SerializationTest t2 = (SerializationTest)s2.readObject();
        test.size = 30;
        System.out.println("Name " + t2.name);
        System.out.println(t2.size);
        System.out.println(t2.address.add);
        System.out.println(t2.parent);
    }
}

class Address implements Serializable {
    String add;

    public Address(final String str) {
        add = str;
    }
}

class Parent implements Serializable {
    String parent = "testing parent";

    public Parent(final String str) {
        this.parent = str;
    }
}
