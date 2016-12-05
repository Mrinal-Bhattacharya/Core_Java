//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.tree;

import java.util.ArrayList;
import java.util.List;

public class RedBlackTree {
    private RedBlackTreeNode root;

    public void fixRedBlackProperty(RedBlackTreeNode tree) {
        if (tree.parent != null) {
            if (tree.parent.color == Color.BLACK) {
                return;
            }
            if (tree.parent.parent == null) {
                tree.parent.color = Color.BLACK;
                return;
            }
            final RedBlackTreeNode uncle = findUncle(tree);
            if (uncle != null && uncle.color == Color.RED) {
                uncle.color = Color.BLACK;
                tree.parent.color = Color.BLACK;
                tree.parent.parent.color = Color.RED;
                this.fixRedBlackProperty(tree.parent.parent);
            } else {
                if (tree.isLeftChild() && !tree.parent.isLeftChild()) {
                    this.singleRotateRight(tree.parent);
                    tree = tree.rightChild;
                } else if (!tree.isLeftChild() && tree.parent.isLeftChild()) {
                    this.singleRotateLeft(tree.parent);
                    tree = tree.leftChild;
                }
                if (tree.isLeftChild()) {
                    this.singleRotateRight(tree.parent.parent);
                    tree.parent.color = Color.BLACK;
                    tree.parent.rightChild.color = Color.RED;
                } else {

                    this.singleRotateLeft(tree.parent.parent);
                    tree.parent.color = Color.BLACK;
                    tree.parent.leftChild.color = Color.RED;
                }
            }

        } else {
            if (tree.color == Color.RED) {
                tree.color = Color.BLACK;
            }
        }
    }

    private RedBlackTreeNode singleRotateLeft(final RedBlackTreeNode tree) {
        final RedBlackTreeNode A = tree;
        final RedBlackTreeNode B = tree.rightChild;
        final RedBlackTreeNode t2 = B.leftChild;

        if (t2 != null) {
            t2.parent = A;
        }
        B.parent = A.parent;
        if (this.root == A) {
            this.root = B;
        } else {

            if (A.isLeftChild()) {
                A.parent.leftChild = B;
            } else {
                A.parent.rightChild = B;
            }
        }
        B.leftChild = A;
        A.parent = B;
        A.rightChild = t2;
        return B;
    }

    private RedBlackTreeNode singleRotateRight(final RedBlackTreeNode tree) {
        {
            final RedBlackTreeNode B = tree;
            final RedBlackTreeNode A = tree.leftChild;
            final RedBlackTreeNode t2 = A.rightChild;

            // TODO: Change link color

            if (t2 != null) {
                t2.parent = B;
            }

            A.parent = B.parent;
            if (this.root == B) {
                this.root = A;
            } else {
                if (B.isLeftChild()) {
                    B.parent.leftChild = A;
                } else {
                    B.parent.rightChild = A;
                }
            }
            A.rightChild = B;
            B.parent = A;
            B.leftChild = t2;
            return A;
        }
    }

    private RedBlackTreeNode findUncle(final RedBlackTreeNode tree) {
        if (tree.parent == null) {
            return null;
        }
        final RedBlackTreeNode par = tree.parent;
        if (par.parent == null) {
            return null;
        }
        final RedBlackTreeNode grandPar = par.parent;

        if (grandPar.leftChild == par) {
            return grandPar.rightChild;
        } else {
            return grandPar.leftChild;
        }
    }

    public void insert(final int data) {

        if (this.root == null) {
            this.root = new RedBlackTreeNode(data);
            this.root.color = Color.BLACK;
            return;
        }
        RedBlackTreeNode cuurentNode = this.root;
        RedBlackTreeNode parent = null;
        while (cuurentNode != null) {
            parent = cuurentNode;
            if (data < cuurentNode.data) {
                cuurentNode = cuurentNode.leftChild;
            } else if (data > cuurentNode.data) {
                cuurentNode = cuurentNode.rightChild;
            }
        }
        cuurentNode = new RedBlackTreeNode(data);
        cuurentNode.parent = parent;
        if (data < parent.data) {
            parent.leftChild = cuurentNode;
        } else if (data > parent.data) {
            parent.rightChild = cuurentNode;
        }
        fixRedBlackProperty(cuurentNode);

    }

    public static void print(final RedBlackTreeNode root) {
        final List<List<String>> lines = new ArrayList<List<String>>();

        List<RedBlackTreeNode> level = new ArrayList<RedBlackTreeNode>();
        List<RedBlackTreeNode> next = new ArrayList<RedBlackTreeNode>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            final List<String> line = new ArrayList<String>();

            nn = 0;

            for (final RedBlackTreeNode n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    final String aa =
                            "" + n.data + "(" + n.color.getValue() + ")";
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

            final List<RedBlackTreeNode> tmp = level;
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
                            c = (line.get(j) != null) ? '-' : '+';
                        } else {
                            if (j < line.size() && line.get(j) != null)
                                c = '+';
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
                            System.out.print(j % 2 == 0 ? " " : "-");
                        }
                        System.out.print(j % 2 == 0 ? "+" : "+");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "-" : " ");
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
        final RedBlackTree redBlackTree = new RedBlackTree();
        // redBlackTree.insert(13);
        // redBlackTree.insert(8);
        // redBlackTree.insert(17);
        // redBlackTree.insert(1);
        // redBlackTree.insert(11);
        // redBlackTree.insert(6);
        // redBlackTree.insert(15);
        // redBlackTree.insert(25);
        // redBlackTree.insert(22);
        // redBlackTree.insert(27);
        redBlackTree.insert(50);
        redBlackTree.insert(40);
        // redBlackTree.insert(60);
        print(redBlackTree.root);
        redBlackTree.insert(45);
        // redBlackTree.insert(10);
        print(redBlackTree.root);
    }
}

class RedBlackTreeNode {
    int data;
    int height;
    Color color;
    RedBlackTreeNode leftChild, rightChild, parent;

    public RedBlackTreeNode(final int data) {
        this.data = data;
        this.color = Color.RED;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    public boolean isLeftChild() {
        if (this.parent == null) {
            return true;
        }
        return this.parent.leftChild == this;
    }
}

enum Color {
    RED("Red"), BLACK("Black");
    String color;

    Color(final String color) {
        this.color = color;
    }

    String getValue() {
        return this.color;
    }
}