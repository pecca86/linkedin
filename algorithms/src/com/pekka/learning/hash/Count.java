package com.pekka.learning.hash;

import java.util.HashMap;

public class Count {
    public static void main(String[] args) {
        int[] arr1 = {1,1,2,3};
        System.out.println(countElements(arr1));
        int[] arr2 = {32,33, 100, 32, 33, 40, 40};
        countElements(arr2)
                .forEach((k,v) -> System.out.println("Key: " + k + ", Freq: " + v));
    }

    // Display the count fo each element in an unsorted array
    public static HashMap<Integer, Integer> countElements(int[] arr) {
        HashMap<Integer, Integer> counter = new HashMap<>();

        for (int x : arr) {
            if (counter.containsKey(x)) {
                counter.put(x, counter.get(x) + 1);
            } else {
                counter.put(x, 1);
            }
        }

        return counter;
    }
}
