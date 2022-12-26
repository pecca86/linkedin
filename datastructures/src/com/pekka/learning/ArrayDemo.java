package com.pekka.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ArrayDemo {

    public static void main(String[] args) {
        int[] incrementMe = {1,2,3,4,5};
        int[] incremented = incrementArrayByOne(incrementMe);
        System.out.println(Arrays.toString(incremented));

        List<Integer> myIntList = Arrays.asList(1,3,4,5,6);

        // Functional way
        List<Integer> functionIncrement = myIntList.stream()
                .map(num -> num+1)
                .collect(Collectors.toList());

        System.out.println(functionIncrement);
    }

    public static int[] incrementArrayByOne(int[] arr) {
        int[] returnArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            returnArr[i] = arr[i]+1;
        }
        return returnArr;
    }
}
