package com.dsvisualiser.dsvisualiser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Node {
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
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

    int data;
    Node left;
    Node right;

    @JsonIgnore
    int hd;


    public int getHd() {
        return hd;
    }

    public void setHd(int hd) {
        this.hd = hd;
    }



    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.hd= Integer.MAX_VALUE;

    }
}
