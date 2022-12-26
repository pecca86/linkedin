package com.pekka.learning.arrays;

import java.util.*;

public class FindMax {

    public static void main(String[] args) {
	    int[] nums = {1,22,66,100};
        int maxNum = findMax(nums);
        System.out.println(maxNum);

        List<Integer> listNums = Arrays.asList(420,1412,140,4,42014,4214,214,31,2,312,4,125,215,1,25,421);

        // Functional way
        int largest = Arrays.stream(nums)
                .max()
                .getAsInt();
        System.out.println(largest);

        Integer max = listNums.stream()
                .mapToInt(v -> v)
                .max()
                .orElse(-1);
        System.out.println(max);

    }

    public static int findMax(int[] numList) {
        int max = numList[0];
        for (int i = 1; i < numList.length; i++) {
            max = numList[i] > max ? numList[i] : max;
        }

        return max;
    }
}
