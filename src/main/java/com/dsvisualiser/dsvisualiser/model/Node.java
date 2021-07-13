package com.dsvisualiser.dsvisualiser.model;

public class Node {
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    int key;
    Node left, right;

    // constructor
    public Node(int key)
    {
        this.key = key;
        left = null;
        right = null;
    }
}
