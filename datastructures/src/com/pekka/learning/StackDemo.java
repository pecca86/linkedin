package com.pekka.learning;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackDemo {

    public static void main(String[] args) {
        // == STACK ==
        // Not thread safe
        Deque<String> myStack = new ArrayDeque<>();
        myStack.push("First in line");
        myStack.push("Second in line");
        myStack.push("Third in line");

        System.out.println(myStack); // Prints in opposite order of input

        System.out.println(myStack.peek()); // Just peeks at the value
        System.out.println(myStack.pop()); // Takes the value and removes it from the stack
        System.out.println(myStack.peek()); // Just peeks at the value
        System.out.println(myStack.poll()); // same as pop(), but returns null if stack is empty, pop() throws an error
    }
}
