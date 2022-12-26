package com.linkedin.collectionsdemo.queues;

import com.linkedin.collectionsdemo.challengeone.Guest;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
    QUEUES IMPLEMENT FIFO
 */
public class QueuesDemo {
    public static void main(String[] args) {
        Guest guest1 = new Guest("John", "Mayer", false);
        Guest guest2 = new Guest("Elton", "John", true);
        Guest guest3 = new Guest("John", "Forgetty", false);
        Guest guest4 = new Guest("Elvis", "Presley", true);
        Guest guest5 = new Guest("Mike", "Modano", true);


        // === EXAMPLE 1 ===
        Queue<Guest> guestQueue = new ArrayDeque<>();
        // Using the queue.remove() will throw an error if queue is empty
        Guest g = guestQueue.poll(); // If empty will return null
        System.out.println(g);

        // queue.add() will throw an exception if queue is full
        guestQueue.offer(guest1);
        guestQueue.offer(guest2);

        printQueue(guestQueue);
        System.out.println(guestQueue.poll()); // John, since John was the first person added

        guestQueue.offer(guest3);
        guestQueue.offer(guest4);
        guestQueue.offer(guest5);

        Guest g1 = guestQueue.peek(); // The Guest will be returned, but remain inside the Queue
        System.out.println(g1);
        printQueue(guestQueue);

        // === EXAMPLE 2 ===
        // Priority Queue does not implement FIFO, but implements a comparator
        // This comparator sorts according to the isLoyaltyMember() method
        // And then reverses the order
        Comparator<Guest> guestComparator = Comparator.comparing(Guest::isLoyaltyMember).reversed();
        // Passing the comparator into the PriorityQueue will automatically put LoyaltyMember first
        Queue<Guest> priorityQueue = new PriorityQueue<>(guestComparator);
        priorityQueue.offer(guest1);
        priorityQueue.offer(guest2);
        priorityQueue.offer(guest3);
        priorityQueue.offer(guest4);
        priorityQueue.offer(guest5);
        System.out.println("=== PRIORITY QUEUE ===");
        printQueue(priorityQueue);


    }

    public static void printQueue(Queue<Guest> queue) {
        int x = 0;
        for (Guest g : queue) {
            System.out.format("%x: %s, %s %n", x++, g.toString(), x==1 ? "(HEAD)" : "");
        }
    }
}
