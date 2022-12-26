package com.linkedin.concurrency.locks;

/**
 * Two shoppers adding garlic and potatoes to a shared notepad.
 * Reentrant lock means that a class can lock a resource many times.
 * This also means the lock need to be released as many times as it has been locked, in order for other
 * threads to acquire the lock.
 */

import java.util.concurrent.locks.*;

class Shopper extends Thread {

    static int garlicCount, potatoCount = 0;
    // By instantiating the lock as the specific type (instead of lock), we can log how many locks
    // The object has at a given moment.
    static ReentrantLock pencil = new ReentrantLock();
    private void addGarlic() {
        pencil.lock();
        System.out.println("Hold count: " + pencil.getHoldCount());
        garlicCount++;
        pencil.unlock();
    }

    private void addPotato() {
        pencil.lock();
        potatoCount++;
        addGarlic(); // This will cause the object to create another lock
        pencil.unlock();
    }

    public void run() {
        for (int i=0; i<10_000; i++) {
            addGarlic();
            addPotato();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper();
        Thread olivia = new Shopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
        System.out.println("We should buy " + Shopper.potatoCount + " potatoes.");
    }
}