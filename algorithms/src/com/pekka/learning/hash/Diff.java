package com.pekka.learning.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Diff {

    public static void main(String[] args) {
        // Check which elements in arr1 is not in arr2
        int[] arr1 = {1,2,3,4,5,6};
        int[] arr2 = {1,3,55,6,77,8};
        System.out.println(findMissing(arr1, arr2));

        // test 2
        findMissing(new int[] {55, 66, 77, 88, 100}, new int[] {55, 4, 88, 99})
                .forEach(System.out::println);

    }

    // Check what elements are in first array but not in the second
    public static List<Integer> findMissing(int[] arr1, int[] arr2) {
        List<Integer> result = new ArrayList<>();

        HashSet<Integer> hashSet = new HashSet<>();

        for (int x : arr2) {
            hashSet.add(x);
        }

        for (int x : arr1) {
            if (!hashSet.contains(x)) {
                result.add(x);
            }
        }

        return result;
    }
}
