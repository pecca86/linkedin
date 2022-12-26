package com.linkedin.collectionsdemo.set;

import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(400, 500, 100, 600, 1200, 4000);

        NavigableSet<Integer> numberTree = new TreeSet<>(numbers);

        // The TreeSet automatically orders the elements
        numberTree.stream()
                .forEach(System.out::println);

        // Print in descending order
        numberTree.descendingSet()
                .stream()
                .forEach(System.out::println);

        System.out.println("===============");
        // Get all numbers lower than given number
        numberTree.headSet(500)
                .stream()
                .forEach(System.out::println);
        System.out.println("=== HIGHER THAN 500 ===");
        // Number higher than given value
        numberTree.tailSet(500)
                .stream()
                .forEach(System.out::println);

        System.out.println("=== NUMBER BETWEEN 400 AND 1500 ===");
        numberTree.subSet(400, 1500)
                .stream()
                .forEach(System.out::println);

        System.out.println("=== NEXT NUMBER HIGHER / LOWER THAN 500 ===");
        System.out.println(numberTree.higher(500));
        System.out.println(numberTree.lower(500));
    }
}
