package com.linkedin.concurrency.mutualexclution;

/**
 * Two shoppers adding items to a shared notepad.
 * In this example two threads are simultaneously reading and writing to the garlicCount,
 * causing a race condition.
 * In the background the program is doing a READ - MODIFY - WRITE process.
 */

class Shopper extends Thread {

    static int garlicCount = 0;

    public void run() {
        for (int i=0; i<10_000_000; i++)
            garlicCount++;
    }
}

public class DataRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper();
        Thread olivia = new Shopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
    }
}