package com.linkedin.concurrency.livelock;

/**
 * A polite philosopher makes sure their friends eat first.
 * A live lock can occur, when two threads try to release a lock continuously, creating an infinite loop.
 */


import java.util.concurrent.locks.*;
import java.util.Random;

class Philosopher extends Thread {

    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500_000;
    private Random rps = new Random();

    public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    public void run() {
        while(sushiCount > 0) { // eat sushi until it's all gone

            // pick up chopsticks, if the second chopstick is locked, this thread will release the first chopstick
            firstChopstick.lock();
            if (! secondChopstick.tryLock()) {
                System.out.println(this.getName() + " released their first chopstick.");
                firstChopstick.unlock();
                try {
                    // Make the thread sleep before trying again, so that another thread has a chance to get BOTH chopsticks
                    Thread.sleep(rps.nextInt(3));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    // take a piece of sushi
                    if (sushiCount > 0) {
                        sushiCount--;
                        System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // put down chopsticks
                    secondChopstick.unlock();
                    firstChopstick.unlock();
                }
            }
        }
    }
}

public class LivelockDemo {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        new Philosopher("Barron", chopstickA, chopstickB).start();
        new Philosopher("Olivia", chopstickB, chopstickC).start();
        new Philosopher("Steve", chopstickC, chopstickA).start();
    }
}