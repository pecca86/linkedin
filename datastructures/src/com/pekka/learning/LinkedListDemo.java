package com.pekka.learning;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {

    public static void main(String[] args) {
        // == LINKED LIST ==
        // Linked list with next and prev
        // Linked list keeps items in order, fast for adding new item, slow for retrieving
        // Linked list is not thread safe
        LinkedList<String> shoppingList = new LinkedList<>();
        shoppingList.add("banana");
        shoppingList.add("apple");
        shoppingList.add("pizza");

        System.out.println(shoppingList);

        shoppingList.add(1, "dildo");
        System.out.println(shoppingList);

        shoppingList.removeFirst();
        System.out.println(shoppingList);

        // Thread safe linked list
        List<String> syncShoppingList = Collections.synchronizedList(shoppingList);
        System.out.println(syncShoppingList);


    }
}
