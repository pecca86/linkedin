package com.linkedin.concurrency.threadlifecycle;

/**
 * Two threads cooking soup
 * Useful methods:
 * - currentThread() -> returns a reference to currently
 */

class ChefOlivia extends Thread {
    // Override inherited Thread run with own code
    public void run() {
        System.out.println("Olivia started & waiting for sausage to thaw...");
        try {
            Thread.sleep(3000); // Cutting sausage for 3 sex
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Olivia is done cutting sausage.");
    }
}

public class ThreadLifecycleDemo {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Barron started & requesting Olivia's help.");
        Thread olivia = new ChefOlivia();
        System.out.println("  Olivia state: " + olivia.getState()); // NEW

        System.out.println("Barron tells Olivia to start.");
        olivia.start();
        System.out.println("  Olivia state: " + olivia.getState()); // RUNNABLE

        System.out.println("Barron continues cooking soup.");
        Thread.sleep(500);
        System.out.println("  Olivia state: " + olivia.getState()); // TIMED_WAITING

        System.out.println("Barron patiently waits for Olivia to finish and join...");
        olivia.join();
        System.out.println("  Olivia state: " + olivia.getState()); // TERMINATED

        System.out.println("Barron and Olivia are both done!");
    }
}
