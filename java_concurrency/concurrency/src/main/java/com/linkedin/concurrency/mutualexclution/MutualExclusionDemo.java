package com.linkedin.concurrency.mutualexclution;

/**
 * Two shoppers adding items to a shared notepad
 */

import java.util.concurrent.locks.*;

class MutShopper extends Thread {

    static int garlicCount = 0;
    static Lock pencil = new ReentrantLock(); // New lock object

    public void run() {
        for (int i=0; i<5; i++) {
            pencil.lock(); // Lock while writing new data
            garlicCount++;
            pencil.unlock(); // Immediately release the lock, once the operation is done
            System.out.println(Thread.currentThread().getName() + " is thinking.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}

public class MutualExclusionDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new MutShopper();
        Thread olivia = new MutShopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + MutShopper.garlicCount + " garlic.");
    }
}