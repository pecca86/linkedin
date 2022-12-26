package com.pekka.learning.queuesandstacks;

import java.util.Stack;

// LIFO
public class StackDemo {

    public static void main(String[] args) {
        int[] ints = {16,7,2,15};
        printNextGtEl(ints);
    }

    public static void printNextGtEl(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);

        for (int i = 1 ; i < arr.length; i++) {
            int next = arr[i];
            if (!stack.isEmpty()) {
                int popped = stack.pop();
                while (popped < next) {
                    System.out.println(popped + " -> " + next);
                    if (stack.isEmpty()) {
                        break;
                    }
                    popped = stack.pop();
                }
                if (popped > next) {
                    stack.push(popped);
                }
            }
            stack.push(next);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + " -> -1");
        }
    }
}
