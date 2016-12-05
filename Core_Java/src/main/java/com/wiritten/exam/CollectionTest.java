//****************************************************************
//* Copyright (c) 2016 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.wiritten.exam;

import java.util.ArrayList;
import java.util.List;

public class CollectionTest {
    public static void main(final String[] args) {
        final List<Parent> parentWithChild = new ArrayList<Parent>();
        parentWithChild.add(new Child1());
        final List<Child1> childs = new ArrayList<Child1>();
        final MethodClass test = new MethodClass();
        // test.method(childs); Give compile time error u can't pass child
        // instance in parent. to solve this in method signature we need to use
        // <? extends Parent>
    }
}

class Parent {

}

class Child1 extends Parent {

}

class MethodClass {
    void method(final List<Parent> p) {

    }
}