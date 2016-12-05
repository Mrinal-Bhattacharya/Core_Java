//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.tree;

import java.util.ArrayList;
import java.util.List;

public class BTree {
    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public void display() {
        this.print(root);
    }

    public void insert(final int key, final double data) {
        final Node node = new Node();
        node.iData = key;
        node.dData = data;
        if (this.root == null) {
            this.root = node;
        } else {
            Node currentNode = this.root;
            while (true) {
                if (key < currentNode.iData) {
                    if (currentNode.leftChild == null) {
                        currentNode.leftChild = node;
                        return;
                    }
                    currentNode = currentNode.leftChild;
                } else {
                    if (currentNode.rightChild == null) {
                        currentNode.rightChild = node;
                        return;
                    }
                    currentNode = currentNode.rightChild;
                }
            }
        }
    }

    // 1. Call itself to traverse the nodeâ€™s left subtree.
    // 2. Visit the node.
    // 3. Call itself to traverse the nodeâ€™s right subtree.
    void inOrder(final Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    // 1. Visit the node.
    // 2. Call itself to traverse the nodeâ€™s left subtree.
    // 3. Call itself to traverse the nodeâ€™s right subtree.
    void preOrder(final Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    // 1. Call itself to traverse the nodeâ€™s left subtree.
    // 2. Call itself to traverse the nodeâ€™s right subtree.
    // 3. Visit the node.
    void postOrder(final Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }

    public Node minimum() // returns node with minimum key value
    {
        Node current, last = null;
        current = root; // start at root
        while (current != null) // until the bottom,
        {
            last = current; // remember node
            current = current.leftChild; // go to left child
        }
        return last;
    }

    public Node maximum() // returns node with minimum key value
    {
        Node current, last = null;
        current = root; // start at root
        while (current != null) // until the bottom,
        {
            last = current; // remember node
            current = current.rightChild; // go to right child
        }
        return last;
    }

    // The node to be deleted is a leaf (final has no children).
    public void delete(final int key) {
        if (root == null) {
            return;
        }
        Node current = root;
        Node parent = null;
        boolean isLeftHand = false;
        while (current.iData != key) {
            parent = current;
            if (key < current.iData) {
                isLeftHand = true;
                current = current.leftChild;
            } else {
                isLeftHand = false;
                current = current.rightChild;
            }
        }

        // Need to start from here to delete
        // Case 1: The Node to Be Deleted Has No Children
        if (current.rightChild == null && current.leftChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftHand == true)
                parent.leftChild = null;
            else {
                parent.rightChild = null;
            }
        }
        // if no right child, replace with left subtree
        else if (current.rightChild == null) {
            if (current == root)
                root = current.leftChild;
            else if (isLeftHand)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        }
        // if no left child, replace with right subtree
        else if (current.leftChild == null) {
            if (current == this.root)
                this.root = current.rightChild;
            else if (isLeftHand)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        } else {
            // get successor of node to delete (current)
            final Node successor = getSuccessor(current);
            // connect parent of current to successor instead
            if (current == root)
                root = successor;
            else if (isLeftHand)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;
            // connect successor to currentâ€™s left child
            successor.leftChild = current.leftChild;
        }
    }

    private Node getSuccessor(final Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild; // go to right child
        while (current != null) // until no more
        { // left children,
            successorParent = successor;
            successor = current;
            current = current.leftChild; // go to left child
        }
        // if successor not
        if (successor != delNode.rightChild) // right child,
        { // make connections
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    public static void main(final String[] args) {
        final BTree bTree = new BTree();
        bTree.insert(50, 50);
        bTree.insert(30, 30);
        bTree.insert(60, 60);
        bTree.insert(55, 55);
        bTree.insert(75, 75);
        bTree.inOrder(bTree.root);
        System.out.println("");
        bTree.preOrder(bTree.root);
        System.out.println("");
        bTree.postOrder(bTree.root);
        System.out.println("");
        System.out.println(bTree.minimum().iData);
        System.out.println(bTree.maximum().iData);
        System.out.println("Deleting process");
        BTree deleteTree = new BTree();
        deleteTree.insert(50, 50);
        deleteTree.insert(30, 30);
        deleteTree.insert(60, 60);
        deleteTree.delete(30);
        deleteTree.inOrder(deleteTree.root);
        deleteTree.insert(30, 30);
        deleteTree.delete(60);
        deleteTree.inOrder(deleteTree.root);
        System.out.println("Deleting root");
        deleteTree = new BTree();
        deleteTree.insert(50, 50);
        deleteTree.delete(50);
        deleteTree.inOrder(deleteTree.root);

    }

  public static void print(final Node root) {
        final List<List<String>> lines = new ArrayList<List<String>>();

        List<Node> level = new ArrayList<Node>();
        List<Node> next = new ArrayList<Node>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            final List<String> line = new ArrayList<String>();

            nn = 0;

            for (final Node n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    final String aa = "" + n.iData;
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

            final List<Node> tmp = level;
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

                    // split node
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

}

class Node {
    public int iData; // data item (key)
    public double dData; // data item
    public Node leftChild; // this nodeâ€™s left child
    public Node rightChild; // this nodeâ€™s right child

    public void displayNode() // display ourself
    {
        System.out.print('{');
        System.out.print(this.iData);
        System.out.print(", ");
        System.out.print(this.dData);
        System.out.print("} ");
    }

}
