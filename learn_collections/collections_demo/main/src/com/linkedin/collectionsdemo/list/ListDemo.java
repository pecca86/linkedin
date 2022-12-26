package com.linkedin.collectionsdemo.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    - ArrayList is best for random access
    - LinkedList is best for insertion of objects
 */
public class ListDemo {
    public static void main(String[] args) {
        // Create new list
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));
        integerList.add(9);
        System.out.println(integerList);
        // Add new element to index 2
        integerList.add(2, 10);
        System.out.println(integerList);
        // Removes element at index 2 and returns it
        int removed = integerList.remove(2);
        System.out.println(removed);
        System.out.println(integerList.get(0));

    }
}
