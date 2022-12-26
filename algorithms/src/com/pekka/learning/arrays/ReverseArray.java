package com.pekka.learning.arrays;

import java.util.Arrays;

public class ReverseArray {

    public static void main(String[] args) {
        int[] myArr = {1,2,3,4,5};
        reverseArrayInplace(myArr);
        System.out.println(Arrays.toString(myArr));
    }

    public static void reverseArrayInplace(int[] arr) {
        int tmp;
        for ( int i = 0 ; i < arr.length / 2 ; i++ ) {
            tmp = arr[i];
            arr[i] = arr[arr.length - i -1];
            arr[arr.length- i -1] = tmp;
        }

    }
}
