package com.linkedin.collectionsdemo.queues;

import com.linkedin.collectionsdemo.challengeone.Guest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

// Allows insertion and removal from head and tail of queue
// Can thus be used as a FIFO or LIFO (stack)
public class DequeDemo {
    public static void main(String[] args) {
        Guest guest1 = new Guest("John", "Mayer", false);
        Guest guest2 = new Guest("Elton", "John", true);
        Guest guest3 = new Guest("John", "Forgetty", false);
        Guest guest4 = new Guest("Elvis", "Presley", true);
        Guest guest5 = new Guest("Mike", "Modano", true);

        // When working with Deque as a stack we use the methods:
        // push(), pop() and peek()
        Deque<String> messageStack = new ArrayDeque<>();
        messageStack.push("Message 1");
        printStack(messageStack);
        messageStack.push("Message 2");
        printStack(messageStack);
        messageStack.push("Message 3");
        printStack(messageStack);
        messageStack.push("Message 4");
        printStack(messageStack);

        messageStack.pop();
        printStack(messageStack);
        messageStack.pop();
        printStack(messageStack);
        messageStack.peek(); // Does not alter the stack



    }

    private static void printStack(Queue<String> queue) {
        System.out.println("================");
        int x = 0;
        for (String s : queue) {
            System.out.format("%x: %s, %s %n", x++, s, x==1 ? "(TOP)" : "");
        }
    }
}
