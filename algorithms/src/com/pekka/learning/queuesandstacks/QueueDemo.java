package com.pekka.learning.queuesandstacks;

import java.util.LinkedList;
import java.util.Queue;

// FIFO
public class QueueDemo {

    public static void main(String[] args) {
        Queue<Integer> myQueue = new LinkedList<>();
        myQueue.add(2);
        myQueue.add(3);
        myQueue.add(4);
        myQueue.add(5);
        myQueue.add(6);
        System.out.println(myQueue.remove());
        System.out.println(myQueue.remove());
        System.out.println(myQueue.remove());
        System.out.println(myQueue.peek());

        while (!myQueue.isEmpty()) {
            System.out.println(myQueue.remove());
        }

        printBinary(15);
    }

    public static void printBinary(int n) {
        // Check for 0
        if (n <= 0) {
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); // First binary value always 1
        for ( int i = 0 ; i < n ; i++ ) {
            int current = queue.remove(); // take first value in queue
            System.out.println(current);

            queue.add(current * 10); // appends a 0 to the current number = 1 -> 10 and puts it into the queue
            queue.add(current * 10 + 1); // appends 1 to the number = 1 -> 11, and puts in queue
        }
        System.out.println();
    }
}
