package com.pekka.learning.trees;

// Like linked list, but for non-linear data
//      root
//     /    \
//   child  child
public class BinaryTreeDemo {

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode();
        rootNode.data = 5;

        TreeNode nodeTwo = new TreeNode();
        nodeTwo.data = 2;

        TreeNode nodeFour = new TreeNode();
        nodeFour.data = 4;

        TreeNode nodeNine = new TreeNode();
        nodeNine.data = 9;

        TreeNode nodeTen = new TreeNode();
        nodeTen.data = 10;

        TreeNode nodeSeven = new TreeNode();
        nodeSeven.data = 7;

        rootNode.left = nodeTwo;
        rootNode.right = nodeFour;
        nodeTwo.left = nodeNine;
        nodeTwo.right = nodeTen;
        nodeFour.left = nodeSeven;

         BinaryTree tree = new BinaryTree();
         tree.root = rootNode;

         //tree.preOrderTraversal();
        tree.inOrderTraversal();
        System.out.println("======");
        tree.postOrderTraversal();

    }
}
