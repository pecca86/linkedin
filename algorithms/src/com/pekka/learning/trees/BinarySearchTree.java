package com.pekka.learning.trees;

import com.pekka.learning.ownlinkedlist.Node;
import com.sun.source.tree.Tree;

public class BinarySearchTree {
    TreeNode root;

    public void insert(int value) {
        // Calls a helper function
        insert(this.root, value);
    }

    private TreeNode insert(TreeNode root, int value) {
        // Check if root exists
        if (root == null) {
            root = new TreeNode();
            root.data = value;
        } else if (value > root.data){
            // Insert on right
            root.right = insert(root.right, value);
        } else if (value < root.data) {
            // inserrt on left
            root.left = insert(root.left, value);
        }
        // If same value
        return root;
    }

    public boolean isPresent(int value) {
        return isPresent(this.root, value);
    }

    private boolean isPresent(TreeNode root, int value) {
        if (root != null) {
            if (root.data == value) {
                return true;
            } else if (root.data < value) {
                return isPresent(root.right, value);
            } else if (root.data > value) {
                return isPresent(root.left, value);
            }
        }
        return false;
    }

    public boolean contains(int value) {
        return contains(this.root, value);
    }

    private boolean contains(TreeNode root, int value) {
        if (root == null) {
            return false;
        }

        if (value < root.data) {
            contains(root.left, value);
        }

        if ( value > root.data) {
            return contains(root.right, value);
        }

        return true;

    }

}
