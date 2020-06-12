//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.tree;

import org.junit.Test;

public class BTreeTest {
    @Test
    public void insert() throws Exception {
        final BTree bTree = new BTree();
        bTree.insert(50, 50);
        bTree.insert(30, 30);
        bTree.insert(60, 60);
        bTree.insert(55, 55);
        bTree.insert(75, 75);
        bTree.display();
    }

    @Test
    public void inOrder() throws Exception {
        final BTree bTree = new BTree();
        bTree.insert(50, 50);
        bTree.insert(30, 30);
        bTree.insert(60, 60);
        bTree.insert(55, 55);
        bTree.insert(75, 75);
        bTree.inOrder(bTree.getRoot());
        System.out.println("");
        bTree.display();
    }

    @Test
    public void preOrder() throws Exception {
        final BTree bTree = new BTree();
        bTree.insert(50, 50);
        bTree.insert(30, 30);
        bTree.insert(60, 60);
        bTree.insert(55, 55);
        bTree.insert(75, 75);
        bTree.preOrder(bTree.getRoot());
        System.out.println("");
        bTree.display();
    }

    @Test
    public void postOrder() throws Exception {
        final BTree bTree = new BTree();
        bTree.insert(50, 50);
        bTree.insert(30, 30);
        bTree.insert(60, 60);
        bTree.insert(55, 55);
        bTree.insert(75, 75);
        bTree.postOrder(bTree.getRoot());
        System.out.println("");
        bTree.display();
    }

    @Test
    public void minimum() throws Exception {
        final BTree bTree = new BTree();
        bTree.insert(50, 50);
        bTree.insert(30, 30);
        bTree.insert(60, 60);
        bTree.insert(55, 55);
        bTree.insert(75, 75);
        System.out.println("Minimum: " + bTree.minimum().iData);
    }

    @Test
    public void maximum() throws Exception {
        final BTree bTree = new BTree();
        bTree.insert(50, 50);
        bTree.insert(30, 30);
        bTree.insert(60, 60);
        bTree.insert(55, 55);
        bTree.insert(75, 75);
        System.out.println("Maximum: " + bTree.maximum().iData);
    }

    @Test
    public void deleteRoot() throws Exception {
        final BTree bTree = new BTree();
        bTree.insert(50, 50);
        bTree.delete(50);
        bTree.display();
    }

    @Test
    public void deleteLeftLeafChildReplace() throws Exception {
        final BTree bTree = new BTree();
        bTree.insert(100, 100);
        bTree.insert(50, 50);
        bTree.insert(150, 150);
        bTree.insert(25, 25);
        bTree.display();
        // Delete key left hand child
        bTree.delete(50);
        bTree.display();
    }

    @Test
    public void deleteRightLeafChildReplace() throws Exception {
        final BTree bTree = new BTree();
        bTree.insert(100, 100);
        bTree.insert(50, 50);
        bTree.insert(150, 150);
        bTree.insert(75, 75);
        bTree.insert(65, 65);
        bTree.insert(85, 85);
        bTree.display();
        // Delete key left hand child
        bTree.delete(50);
        bTree.display();
    }

    @Test
    public void deleteNodeWithTwoChildren() throws Exception {
        final BTree bTree = new BTree();
        bTree.insert(100, 100);
        bTree.insert(50, 50);
        bTree.insert(150, 150);
        bTree.insert(25, 25);
        bTree.insert(75, 75);
        bTree.insert(65, 65);
        bTree.insert(85, 85);
        bTree.insert(61, 61);
        bTree.insert(66, 66);
        bTree.display();
        // Delete key left hand child
        bTree.delete(50);
        bTree.display();
    }
}
