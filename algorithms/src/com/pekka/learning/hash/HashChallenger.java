package com.pekka.learning.hash;

import com.pekka.learning.ownlinkedlist.Node;
import com.pekka.learning.ownlinkedlist.OwnLinkedList;

public class HashChallenger {

    public static void main(String[] args) {
        // TASK:
        // Detect a cycle within a linked list

        // STARTING POINT
        OwnLinkedList noCycle = new OwnLinkedList();
        Node first = new Node(3);
        Node second = new Node(4);
        Node third = new Node(5);
        Node fourth = new Node(6);

        noCycle.head = first;
        first.next = second;
        second.next = third;
        third.next = fourth;
        System.out.println(noCycle.hasCycle());

        OwnLinkedList cycle = new OwnLinkedList();
        cycle.head = first;
        third.next = second;

        System.out.println(cycle.hasCycle());
        //cycle.displayContents();
    }

    public static boolean hasCycle(OwnLinkedList theList) {
        return false;
    }
}
