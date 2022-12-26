package com.linkedin.concurrency.mutualexclution;

/**
 * Two shoppers adding items to a shared notepad
 */

class SyncShopper extends Thread {

    static int garlicCount = 0;

    // synchronized keyword inside the method name
    // Method needs to be private, aka. an Object method
    private static synchronized void addGarlic() {
        garlicCount++;
    }

    public void run() {
        for (int i=0; i<10_000_000; i++)
            addGarlic(); // The incrementation is now happening inside our private static synchronized method
    }
}

public class SynchronizedMethodDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new SyncShopper();
        Thread olivia = new SyncShopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + SyncShopper.garlicCount + " garlic.");
    }
}