package com.linkedin.concurrency.locks;

/**
 * Two shoppers adding items to a shared notepad.
 * Try lock = If a thread fails the get the locks, it does not stand idle (BLOCKED state) but can instead
 * go on doing other tasks, and then later try to acquire the lock again.
 */

import java.util.concurrent.locks.*;

class TryLockShopper extends Thread {

    private int itemsToAdd = 0; // items this shopper is waiting to add
    private static int itemsOnNotepad = 0; // total items on shared notepad
    private static Lock pencil = new ReentrantLock();

    public TryLockShopper(String name) {
        this.setName(name);
    }

    public void run() {
        while (itemsOnNotepad <= 20){
            // if there are items to add AND lock is free
            if ((itemsToAdd > 0) && pencil.tryLock()) { // add item(s) to shared notepad
                try {
                    itemsOnNotepad += itemsToAdd;
                    System.out.println(this.getName() + " added " + itemsToAdd + " item(s) to notepad.");
                    itemsToAdd = 0;
                    Thread.sleep(300); // time spent writing
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    pencil.unlock();
                }
            } else { // look for other things to buy
                try {
                    Thread.sleep(100); // time spent searching
                    itemsToAdd++;
                    System.out.println(this.getName() + " found something to buy.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class TryLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new TryLockShopper("Barron");
        Thread olivia = new TryLockShopper("Olivia");
        long start = System.currentTimeMillis();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        long finish = System.currentTimeMillis();
        System.out.println("Elapsed Time: " + (float)(finish - start)/1000 + " seconds");
    }
}
