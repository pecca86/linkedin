package com.pekka.learning.trees;

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        TreeNode rootNode = new TreeNode();
        rootNode.data = 5;
        tree.root = rootNode;

        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        tree.insert(6);
        tree.insert(7);
        tree.insert(9);

        //System.out.println(tree.isPresent(4));
        System.out.println(tree.isPresent(9));
        System.out.println(tree.isPresent(10));

        System.out.println(tree.contains(9));
        System.out.println(tree.contains(10));
    }
}
