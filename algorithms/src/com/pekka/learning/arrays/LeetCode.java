package com.pekka.learning.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode {

    public static void main(String[] args) {
        int[] nums = {2,5,5,11};
        System.out.println(Arrays.toString(twoSum(nums, 10)));
    }

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 1 || nums.length > 100000) {
            return nums;
        }

        if ( target >= Integer.MAX_VALUE || target <= Integer.MIN_VALUE) {
            return nums;
        }

        int[] result = new int[2];
        // Brute force
        for (int i = 0; i < nums.length ; i++) {
            int first = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (first + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

}
