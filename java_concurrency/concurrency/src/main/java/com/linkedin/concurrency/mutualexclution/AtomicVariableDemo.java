package com.linkedin.concurrency.mutualexclution;

/**
 * Two shoppers adding items to a shared notepad.
 * Using an atomic lock, makes simple processes, like adding a single value (operation), much faster.
 */

import java.util.concurrent.atomic.*;

class AtomicShopper extends Thread {

    static AtomicInteger garlicCount = new AtomicInteger(0); // More Atomic locks available on Java docs

    public void run() {
        for (int i=0; i<10_000_000; i++)
            garlicCount.incrementAndGet();
    }
}

public class AtomicVariableDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new AtomicShopper();
        Thread olivia = new AtomicShopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + AtomicShopper.garlicCount + " garlic.");
    }
}