package com.pekka.learning.ownlinkedlist;

import java.util.HashSet;

public class OwnLinkedList {
    public Node head;
    Node tail;

    // Iterates trough our linked list
    public void displayContents() {
        Node current = head;
        // Null = linkedlist tail
        while (current != null) {
            System.out.print(current.data + "->");
            // Get value of next node
            current = current.next;
        }
        System.out.println();
    }

    public void deleteKthNodeFromEndOfList(int k) {

        Node tail = head;

        // Find end node
        while ( tail.next != null) {
            tail = tail.next;
        }

        // If it is the last node, just delete it and adjust pointers
        if (k == 0) {
            tail.prev.next = null;
            return;
        }

        // Find target node
        for (int i = 0; i < k; i++) {
            if (tail.prev != null) {
                tail = tail.prev;
            }
        }

        // Reset pointers
        if (tail.next != null)  {
            tail.next.prev = tail.prev;
        }

        if (tail.prev != null) {
            tail.prev.next = tail.next;
        } else {
            // Case first node
            head = tail.next;
        }
    }

    public void deleteNodeFromList(int k) {
        Node tail = head;
        Node target = head;
        Node start = head;

        while ( tail.next != null ) {
            tail = tail.next;
            // Skip tail node k time forward and target one time
            for (int i = 0; i < k ; i++) {
                if (tail.next != null){
                    tail = tail.next;
                    // If we reach the end
                } else if ( tail.next == null ) {
                    // Check if start and end node is the same
                    if (target.prev == null ) {
                        head = target.next;
                    } else {
                        // Point the pointer two steps forward
                        target.next = target.next.next;
                    }
                    return;
                }
            }
            target = target.next;
        }

        if (target.next != null) {
            target.prev.next = target.next;
        } else {
            target.prev.next = null;
        }
    }

    // Deletes the back half of the linked list
    public void deleteBackHalf() {
        // Check if linked list has just one or less elements
        // If so, empty the second element or delete whole linked list
        if (head == null || head.next == null) {
            head = null;
        }
        // When we reach the middle node, we set its next value to null
        // The fast pointer will go twice the step, hence the slow pointer should
        // be one step after the middle, when the fast pointer has reach the end of the linked list
        Node slow = head;
        Node fast = head;
        // This will be one step behind our slow = in the middle
        Node prev = null;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = null;
    }

    // Check if linked list has a cycle
    public boolean hasCycle() {
        HashSet<Integer> uniqueValues = new HashSet<>();

        Node current = head;

        while(current != null) {
            if (uniqueValues.contains(current.data)) {
                return true;
            } else {
                uniqueValues.add(current.data);
            }
            current = current.next;
        }

        return false;
    }
}
