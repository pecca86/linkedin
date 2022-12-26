package com.pekka.learning.arrays;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.stream.Stream;

public class ArraysFilter {

    public static void main(String[] args) {

        int[] arr = {2, 4342, 4, 99, 3, 43, 42, 57};
        IntPredicate isEvenPredicate = num -> num % 2 == 0;
        int[] evenNumbers = Arrays.stream(arr)
                .filter(isEvenPredicate)
                .toArray();
        System.out.println(Arrays.toString(evenNumbers));

        int[] arr2 = {31,32,43,50,43,22,34,40,52};
        System.out.println(Arrays.toString(filterEvens(arr2)));
    }

    public static int[] filterEvens(int[] numArr) {
        IntPredicate isEvenNumberPredicate = num -> num % 2 == 0;
        return Stream.of(numArr)
                .flatMapToInt(Arrays::stream)
                .filter(isEvenNumberPredicate)
                .toArray();
    }
}
