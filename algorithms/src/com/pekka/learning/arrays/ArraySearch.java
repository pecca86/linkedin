package com.pekka.learning.arrays;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArraySearch {

    public static void main(String[] args) {
        int[] arr = {1,4,4,5,6,7,8,9,10};

        boolean hasFour = Arrays.stream(arr)
                .anyMatch((num) -> num == 4);
        System.out.println(hasFour);

        int findNumberOrElseGetNegativeOne = Arrays.stream(arr)
                .filter(num -> num == 100)
                .findFirst()
                .orElseGet(() -> -1);
        System.out.println(findNumberOrElseGetNegativeOne);

        // BINARY SEARCH (Divide & Concure)
        int[] t = {2,4,5,6,7,9,20,33,55,66};

        int index = binarySearch(t, 55);
        if (index >= 0) {
            System.out.println(t[index]);
        }
    }

    // Array needs to be ordered
    // If not found, return -1
    public static int binarySearch(int[] arr, int num) {
        int min = 0;
        int max = arr.length - 1;

        while( min <= max ) {
            int mid = (min + max) / 2;
            if (num == arr[mid]) {
                return mid;
            } else if (num < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return -1;
    }
}
