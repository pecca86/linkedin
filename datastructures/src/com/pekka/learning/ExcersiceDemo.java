package com.pekka.learning;

import java.util.*;

public class ExcersiceDemo {

    public static void main(String[] args) {
        // Add to end of linked list
        LinkedList<String> stringLinkedList = new LinkedList<>();
        stringLinkedList.add("One");
        stringLinkedList.add("Two");
        stringLinkedList.add("Three");

        // testAddToStartOfLinkedList
        stringLinkedList.add(0, "ZERO");
        stringLinkedList.addFirst("Minus one");
        System.out.println("LINKED LIST: " + stringLinkedList);

        // testRemoveItemFromTopOfStack
        Deque<Integer> integers = new ArrayDeque<>();
        integers.push(1);
        integers.push(2);
        integers.push(3);
        int top = integers.pop();
        System.out.println(top); // should be 3


        // testRemoveItemFromFrontOfQueue
        Queue<Integer> integerQueue = new ArrayDeque<>();
        integerQueue.offer(1);
        integerQueue.offer(2);
        integerQueue.offer(3);
        System.out.println(integerQueue.poll()); // 1

        // testAddItemToTreeSet
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Pekka");
        treeSet.add("Martin");
        treeSet.add("Karkki");
        System.out.println(treeSet);

    }

}
