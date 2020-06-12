//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.tree;

import java.util.ArrayList;
import java.util.List;

class AVLNode {

    AVLNode leftChild, rightChild;
    int data, height;

    public AVLNode(final int data) {
        this.leftChild = null;
        this.rightChild = null;
        this.data = data;
        this.height = 0;
    }
}

public class AVLTree {

    private AVLNode root;

    public AVLTree() {
        this.root = null;
    }

    public void insert(final int data) {
        root = insert(data, root);
    }

    private AVLNode insert(final int data, final AVLNode node) {
        AVLNode newNode = node;
        if (newNode == null) {
            return new AVLNode(data);
        } else if (data < newNode.data) {
            newNode.leftChild = insert(data, newNode.leftChild);
            final int leftNodeHeight = height(newNode.leftChild);
            final int rightNodeHeight = height(newNode.rightChild);
            final int balance = leftNodeHeight - rightNodeHeight;
            if (balance == 2) {
                if (data < newNode.leftChild.data) {
                    System.out.println("Before Right Rotation");
                    print(newNode);
                    newNode = rightRotation(newNode);
                    System.out.println("After Right Rotation");
                    print(newNode);
                } else {
                    System.out.println("Before Double Right Rotation");
                    print(newNode);
                    newNode = doubleRightRotation(newNode);
                    System.out.println("After Double Right Rotation");
                    print(newNode);
                }
            }
        } else if (data > newNode.data) {
            newNode.rightChild = insert(data, newNode.rightChild);
            final int leftNodeHeight = height(newNode.leftChild);
            final int rightNodeHeight = height(newNode.rightChild);
            final int balance = rightNodeHeight - leftNodeHeight;
            if (balance == 2) {
                if (data > newNode.rightChild.data) {
                    System.out.println("Before Left Rotation");
                    print(newNode);
                    newNode = leftRotation(newNode);
                    System.out.println("After Left Rotation");
                    print(newNode);
                } else {
                    System.out.println("Before Double Left Rotation");
                    print(newNode);
                    newNode = doubleLeftRotation(newNode);
                    System.out.println("After Double Left Rotation");
                    print(newNode);
                }
            }

        }
        newNode.height =
                Math.max(height(newNode.leftChild), height(newNode.rightChild)) + 1;
        return newNode;
    }

    private AVLNode doubleLeftRotation(final AVLNode node) {
        node.rightChild = rightRotation(node.rightChild);
        return leftRotation(node);

    }

    private AVLNode doubleRightRotation(final AVLNode node) {
        node.leftChild = leftRotation(node.leftChild);
        return rightRotation(node);

    }

    private AVLNode leftRotation(final AVLNode node) {
        final AVLNode oldRootNode = node;
        final AVLNode newRootNode = oldRootNode.rightChild;
        oldRootNode.rightChild = newRootNode.leftChild;
        newRootNode.leftChild = oldRootNode;
        final int oldRootLeftChildHeight = height(oldRootNode.leftChild);
        final int oldRootRightChildHeight = height(oldRootNode.rightChild);
        oldRootNode.height =
                Math.max(oldRootLeftChildHeight, oldRootRightChildHeight) + 1;
        newRootNode.height =
                Math.max(height(newRootNode.rightChild), oldRootNode.height) + 1;
        return newRootNode;

    }

    /* Rotate binary tree node with left child */
    private AVLNode rightRotation(final AVLNode node) {
        final AVLNode oldRootNode = node;
        final AVLNode newRootNode = oldRootNode.leftChild;
        oldRootNode.leftChild = newRootNode.rightChild;
        newRootNode.rightChild = oldRootNode;
        final int oldRootLeftChildHeight = height(oldRootNode.leftChild);
        final int oldRootRightChildHeight = height(oldRootNode.rightChild);
        oldRootNode.height =
                Math.max(oldRootLeftChildHeight, oldRootRightChildHeight) + 1;
        final int newRootLeftChildHeight = height(oldRootNode.leftChild);
        newRootNode.height =
                Math.max(newRootLeftChildHeight, oldRootNode.height) + 1;
        return newRootNode;
    }

    /* Function to get height of node */
    private int height(final AVLNode node) {
        return node == null ? -1 : node.height;
    }

    public static void print(final AVLNode root) {
        final List<List<String>> lines = new ArrayList<List<String>>();

        List<AVLNode> level = new ArrayList<AVLNode>();
        List<AVLNode> next = new ArrayList<AVLNode>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            final List<String> line = new ArrayList<String>();

            nn = 0;

            for (final AVLNode n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    final String aa = "" + n.data + "(" + n.height + ")";
                    line.add(aa);
                    if (aa.length() > widest)
                        widest = aa.length();

                    next.add(n.leftChild);
                    next.add(n.rightChild);

                    if (n.leftChild != null)
                        nn++;
                    if (n.rightChild != null)
                        nn++;
                }
            }

            if (widest % 2 == 1)
                widest++;

            lines.add(line);

            final List<AVLNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            final List<String> line = lines.get(i);
            final int hpw = (int)Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split AVLNode
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null)
                                c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null)
                    f = "";
                final int gap1 =
                        (int)Math.ceil(perpiece / 2f - f.length() / 2f);
                final int gap2 =
                        (int)Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }

    public static void main(final String[] args) {
        // final AVLTree singleRightRotation = new AVLTree();
        // singleRightRotation.insert(50);
        // singleRightRotation.insert(40);
        // singleRightRotation.insert(30);

        // final AVLTree singleLeftRotation = new AVLTree();
        // singleLeftRotation.insert(50);
        // singleLeftRotation.insert(60);
        // singleLeftRotation.insert(70);

        // Right-Left Rotation (RL) or "Double right"
        // Perform left rotation make tree left heavy and then right rotation
        // final AVLTree doubleRight = new AVLTree();
        // doubleRight.insert(50);
        // doubleRight.insert(40);
        // doubleRight.insert(45);

        // Left-Right Rotation (LR) or "Double left"
        // Perform right rotation make tree right heavy and then left rotation
        final AVLTree doubleLeft = new AVLTree();
        doubleLeft.insert(50);
        doubleLeft.insert(60);
        doubleLeft.insert(55);

    }
}