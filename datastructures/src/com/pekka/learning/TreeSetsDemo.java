package com.pekka.learning;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetsDemo {

    public static void main(String[] args) {

        // Sets by default in ascending order
        // Can't store duplicate values!
        Set<Integer> integerSet = new TreeSet<>();
        integerSet.add(1);
        integerSet.add(22);
        integerSet.add(4);
        integerSet.add(7);

        System.out.println(integerSet);

        Set<String> stringSet = new TreeSet<>();
        stringSet.add("kuk");
        stringSet.add("muk");
        stringSet.add("huk");
        stringSet.add("auk");

        System.out.println(stringSet);

        // Order string by length instead of alphabetically
        Set<String> orderedByLen = new TreeSet<>(Comparator.comparing(word -> word.length()));
        orderedByLen.add("aaaaaaa");
        orderedByLen.add("aaaaaaab");
        orderedByLen.add("aaaac");
        orderedByLen.add("aaaax"); // Will not be added, since has same length as above
        orderedByLen.add("aaaaaaaxxxxx");

        System.out.println(orderedByLen);

        // Remove
        orderedByLen.remove("aaaac");
        System.out.println(orderedByLen);
    }
}
