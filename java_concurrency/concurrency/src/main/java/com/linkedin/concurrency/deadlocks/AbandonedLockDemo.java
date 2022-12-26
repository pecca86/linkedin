package com.linkedin.concurrency.deadlocks;

/**
 * Three philosophers, thinking and eating sushi.
 * This program will run as expected as long as the thread holding the lock does not crash.
 * If it crashes, it will never release the locks, meaning the waiting threads will wait for ever.
 *
 * The crash is simulated with a divide by zero inside the loop.
 *
 * To prevent a deadlock, we put the critical section inside a try-catch-finally block, where the thread releases
 * its locks.
 */

import java.util.concurrent.locks.*;

class AbandonedPhilosopher extends Thread {

    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500;

    public AbandonedPhilosopher(String name, Lock firstChopstick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    public void run() {
        while(sushiCount > 0) { // eat sushi until it's all gone

            // pick up chopsticks
            firstChopstick.lock();
            secondChopstick.lock();

            try{

            // take a piece of sushi
            if (sushiCount > 0) {
                sushiCount--;
                System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
            }

            if (sushiCount == 10)
                System.out.println(10/0);
            } catch (RuntimeException ex) {
                ex.printStackTrace();
            } finally {
                // This makes sure the thread releases its locks, in case of crashing
                // put down chopsticks
                secondChopstick.unlock();
                firstChopstick.unlock();
            }

        }
    }
}

public class AbandonedLockDemo {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        new AbandonedPhilosopher("Barron", chopstickA, chopstickB).start();
        new AbandonedPhilosopher("Olivia", chopstickB, chopstickC).start();
        new AbandonedPhilosopher("Steve", chopstickA, chopstickC).start();
    }
}