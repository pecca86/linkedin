package com.pekka.learning.ownlinkedlist;

public class LinkedListDemo {
    public static void main(String[] args) {
        OwnLinkedList ownLinkedList = new OwnLinkedList();

        Node firstNode = new Node(3);
        Node secondNode = new Node(4);
        Node thirdNode = new Node(5);
        Node fourthNode = new Node(6);

        // Set first node as head
        ownLinkedList.head = firstNode;
        firstNode.next = secondNode;
        secondNode.prev = firstNode;

        secondNode.next = thirdNode;
        thirdNode.prev = secondNode;

        thirdNode.next = fourthNode;
        fourthNode.prev = thirdNode;

        ownLinkedList.displayContents();
        ownLinkedList.deleteBackHalf();
        ownLinkedList.displayContents();

        // TESTING DELETION OF NTH NODE
        OwnLinkedList customLinkedList = new OwnLinkedList();
        customLinkedList.head = firstNode;
        firstNode.next = secondNode;
        secondNode.prev = firstNode;

        secondNode.next = thirdNode;
        thirdNode.prev = secondNode;

        thirdNode.next = fourthNode;
        fourthNode.prev = thirdNode;
        customLinkedList.displayContents();
        customLinkedList.deleteKthNodeFromEndOfList(3);
        customLinkedList.displayContents();

        OwnLinkedList newMethodTest = createLinkedListData();
        System.out.println("B4: ");
        newMethodTest.displayContents();
        newMethodTest.deleteNodeFromList( 0 );
        System.out.println("AFTER: ");
        newMethodTest.displayContents();


    }

    public static OwnLinkedList createLinkedListData() {
        OwnLinkedList ll = new OwnLinkedList();

        Node firstNode = new Node(3);
        Node secondNode = new Node(4);
        Node thirdNode = new Node(5);
        Node fourthNode = new Node(6);
        Node fifthNode = new Node(7);

        ll.head = firstNode;

        firstNode.next = secondNode;
        secondNode.prev = firstNode;

        secondNode.next = thirdNode;
        thirdNode.prev = secondNode;

        thirdNode.next = fourthNode;
        fourthNode.prev = thirdNode;

        fourthNode.next = fifthNode;
        fifthNode.prev = fourthNode;

        return ll;
    }
}
