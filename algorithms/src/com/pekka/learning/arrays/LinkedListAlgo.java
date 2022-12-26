package com.pekka.learning.arrays;

import java.util.LinkedList;

public class LinkedListAlgo {

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Pexi");
        linkedList.add("Är");
        linkedList.add("Sexi");

        System.out.println(linkedList.contains("Pekka"));
        System.out.println(linkedList.size());

        linkedList.removeFirst();

        linkedList.forEach(System.out::println);

    }
}
