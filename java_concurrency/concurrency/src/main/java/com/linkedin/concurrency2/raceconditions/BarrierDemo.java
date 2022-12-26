package com.linkedin.concurrency2.raceconditions;

/**
 * A barrier sets a stopping point for a thread, where the thread waits until all the other threads have reached
 * this point.
 */


import java.util.concurrent.locks.*;
import java.util.concurrent.*;

class BarrierShopper extends Thread {

    public static int bagsOfChips = 1; // start with one on the list
    private static Lock pencil = new ReentrantLock();
    // Java built-in Barrier implementation
    // The constructor takes in a value of how many threads to wait on, before the barrier releases
    // Here we will create 10 threads
    // Cyclic, since it can be reused by calling reset()
    private static CyclicBarrier fistBump = new CyclicBarrier(10);

    public BarrierShopper(String name) {
        this.setName(name);
    }

    public void run() {
        // Olivia will add three, before waiting on the barrier
        if (this.getName().contains("Olivia")) {
            pencil.lock();
            try {
                bagsOfChips += 3;
                System.out.println(this.getName() + " ADDED three bags of chips.");
            } finally {
                pencil.unlock();
            }
            try {
                fistBump.await(); // Wait at the Barrier
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            // Barron will go straight to the barrier and wait for olivia to finish her work, after the release
            // of the Barrier, Barron will do his work.
        } else { // "Barron"
            try {
                fistBump.await(); // Waiting at the barrier.
            } catch (InterruptedException | BrokenBarrierException e) {
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

public class BarrierDemo {
    public static void main(String args[]) throws InterruptedException  {
        // create 10 shoppers: Barron-0...4 and Olivia-0...4
        BarrierShopper[] shoppers = new BarrierShopper[10];
        for (int i=0; i<shoppers.length/2; i++) {
            shoppers[2*i] = new BarrierShopper("Barron-"+i);
            shoppers[2*i+1] = new BarrierShopper("Olivia-"+i);
        }
        for (BarrierShopper s : shoppers)
            s.start();
        for (BarrierShopper s : shoppers)
            s.join(); // Main thread wait for all children to finish their execution
        System.out.println("We need to buy " + BarrierShopper.bagsOfChips + " bags of chips.");
    }
}