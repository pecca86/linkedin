package com.pekka.learning.arrays;

import java.util.Arrays;

/*
    1,2,3,4 -> 2,3,4,1
 */
public class ArrayChallenge {

    public static void main(String[] args) {
        int[] myArr = {1,2,3,4,5};
        rotateLeftInPlace(myArr);
        System.out.println(Arrays.toString(myArr));

        int[] toRight = {1,2,3,4};
        rotateRightInPlace(toRight);
        System.out.println(Arrays.toString(toRight));

        rotateRightInPlace(myArr);
        System.out.println(Arrays.toString(myArr));
    }

    public static void rotateLeftInPlace(int[] arr) {
        int first = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            arr[i] = first;
            arr[i-1] = tmp;
        }
    }

    public static void rotateRightInPlace(int[] arr) {
        int first = arr[arr.length-1];
        for (int i = arr.length-2; i >= 0; i--) {
            int tmp = arr[i];
            arr[i] = first;
            arr[i+1] = tmp;
        }
    }
}
