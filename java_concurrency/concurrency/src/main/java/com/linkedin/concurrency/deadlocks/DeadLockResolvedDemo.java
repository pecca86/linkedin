package com.linkedin.concurrency.deadlocks;

/**
 * Three philosophers, thinking and eating sushi.
 * To avoid a deadlock we set priorities to the chopstick, and require the new thread to try to acquire the
 * lock with the highest priority first.
 *
 * A better technique is to put a timeout on how long a thread waits for the second lock, if it doesn't get it in
 * the specified time, it will release all its locks and try again later.
 */

import java.util.concurrent.locks.*;

class ResolvedPhilosopher extends Thread {

    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500_000;

    public ResolvedPhilosopher(String name, Lock firstChopstick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    public void run() {
        while(sushiCount > 0) { // eat sushi until it's all gone

            // pick up chopsticks
            firstChopstick.lock();
            secondChopstick.lock();

            // take a piece of sushi
            if (sushiCount > 0) {
                sushiCount--;
                System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
            }

            // put down chopsticks
            secondChopstick.unlock();
            firstChopstick.unlock();
        }
    }
}

public class DeadLockResolvedDemo {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        new ResolvedPhilosopher("Barron", chopstickA, chopstickB).start();
        new ResolvedPhilosopher("Olivia", chopstickB, chopstickC).start();
        // Here we switched the order of which chopstick is taken first
        new ResolvedPhilosopher("Steve", chopstickA, chopstickC).start();
    }
}