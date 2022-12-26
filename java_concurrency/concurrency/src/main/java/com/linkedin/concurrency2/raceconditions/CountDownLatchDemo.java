package com.linkedin.concurrency2.raceconditions;

/**
 * Allows one or more threads to wait, until a set of operations completes.
 *
 * It is initialized with a value, and when the value reaches zero, the other threads can continue their work.
 * The countdown value can't be reset.
 */


import java.util.concurrent.locks.*;
import java.util.concurrent.*;

class CountdownShopper extends Thread {

    public static int bagsOfChips = 1; // start with one on the list
    private static Lock pencil = new ReentrantLock();
    // We construct the CountDownLatch by 5, since there are five threads that need to execute prior to the rest
    // of the threads. If we calculate this value wrongly, the program will never continue, since the countdown value
    // won't reach zero.
    private static CountDownLatch fistBump = new CountDownLatch(5);

    public CountdownShopper(String name) {
        this.setName(name);
    }

    public void run() {
        if (this.getName().contains("Olivia")) {
            pencil.lock();
            try {
                bagsOfChips += 3;
                System.out.println(this.getName() + " ADDED three bags of chips.");
            } finally {
                pencil.unlock();
            }
            // Here we use a count-down instead of a barrier
            fistBump.countDown(); // This reduces the count-down value by one
        } else { // "Barron"
            try {
                fistBump.await(); // Waits for the countdown value to reach zero
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pencil.lock();
            try {
                bagsOfChips *= 2;
                System.out.println(this.getName() + " DOUBLED the bags of chips.");
            } finally {
                pencil.unlock();
            }
        }
    }
}

public class CountDownLatchDemo {
    public static void main(String args[]) throws InterruptedException  {
        // create 10 shoppers: Barron-0...4 and Olivia-0...4
        CountdownShopper[] shoppers = new CountdownShopper[10];
        for (int i=0; i<shoppers.length/2; i++) {
            shoppers[2*i] = new CountdownShopper("Barron-"+i);
            shoppers[2*i+1] = new CountdownShopper("Olivia-"+i);
        }
        for (CountdownShopper s : shoppers)
            s.start();
        for (CountdownShopper s : shoppers)
            s.join();
        System.out.println("We need to buy " + CountdownShopper.bagsOfChips + " bags of chips.");
    }
}