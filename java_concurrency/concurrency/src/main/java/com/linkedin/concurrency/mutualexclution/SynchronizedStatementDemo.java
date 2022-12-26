package com.linkedin.concurrency.mutualexclution;

/**
 * Two shoppers adding items to a shared notepad.
 * Rather than declaring the whole method as synchronized, we can declare the statement that can cause a race condition
 * to be synchronized.
 *
 * Synchronized methods and statements are easier to use, and by using them one can avoid common pitfalls.
 *
 * Locks are more flexible and sometimes required for certain algorithms.
 */

class SyncStatementShopper extends Thread {

    static Integer garlicCount = 0;

    public void run() {
        for (int i=0; i<10_000_000; i++)
            // Specify which object needs to sync, while doing the operation
            // All threads of the class well be acquiring and releasing the same intrinsic lock
            synchronized (Shopper.class) {
                garlicCount++;
            }
    }
}

public class SynchronizedStatementDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new SyncStatementShopper();
        Thread olivia = new SyncStatementShopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + SyncStatementShopper.garlicCount + " garlic.");
    }
}