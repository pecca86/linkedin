package com.pekka.learning;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueDemo {

    public static void main(String[] args) {
        // == QUEUE ==
        Queue<String> myQueue = new ArrayDeque<>();
        myQueue.offer("Person one");
        myQueue.offer("Person two");
        myQueue.offer("Person three");

        System.out.println(myQueue);

        // Check first element in queue
        System.out.println(myQueue.peek());
        // Take first element from queue
        System.out.println(myQueue.poll());
        System.out.println(myQueue);

        // Retrieves in ascending order, despite how the elements are ordered inside the queue
        Queue<Integer> integerQueue = new PriorityQueue<>();
        integerQueue.offer(4);
        integerQueue.offer(2);
        integerQueue.offer(1);
        System.out.println(integerQueue);
        System.out.println(integerQueue.poll()); // 1
        System.out.println(integerQueue.poll()); // 2

    }
}
