//****************************************************************
//* Copyright (c) 2015 . All Rights Reserved.
//****************************************************************
package com.ds.stack;

import org.junit.Assert;
import org.junit.Test;

public class StackTest {
    @Test
    public void testForInsertingDataInStack() throws Exception {
        final Stack stack = new Stack(100);
        stack.push(10);
        Assert.assertFalse(stack.isEmpty());
    }

    @Test
    public void testForPopingDataFromStack() throws Exception {
        final Stack stack = new Stack(100);
        stack.push(10);
        stack.pop();
        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void testForRetrivingDataFromStackWithoutDelete() throws Exception {
        final Stack stack = new Stack(100);
        stack.push(10);
        final long result = stack.peek();
        Assert.assertEquals(10, result);
        Assert.assertFalse(stack.isEmpty());
    }

    @Test
    public void testForCheckingStackIsFull() throws Exception {
        final Stack stack = new Stack(1);
        stack.push(10);
        Assert.assertTrue(stack.isFull());
    }
}
