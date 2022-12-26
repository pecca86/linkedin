package com.linkedin.concurrency.deadlocks;

/**
 * Three philosophers, thinking and eating sushi.
 *
 * If there are many threads with different priorities competing for the same resource, the low tier thread
 * might never get access to that resource.
 */

import java.util.concurrent.locks.*;

class StarvingPhilosopher extends Thread {

    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500_000;

    public StarvingPhilosopher(String name, Lock firstChopstick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    public void run() {
        int sushiEaten = 0;
        while(sushiCount > 0) { // eat sushi until it's all gone

            // pick up chopsticks
            firstChopstick.lock();
            secondChopstick.lock();

            try {
                // take a piece of sushi
                if (sushiCount > 0) {
                    sushiCount--;
                    sushiEaten++;
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
        System.out.println(this.getName() + " took " + sushiEaten);
    }
}

public class StarvationDemo {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        // Having this many threads, will lead to some threads never getting access to the shared resource
        for (int i=0; i<5000; i++) {
            new StarvingPhilosopher("Barron", chopstickA, chopstickB).start();
            new StarvingPhilosopher("Olivia", chopstickA, chopstickB).start();
            new StarvingPhilosopher("Steve", chopstickA, chopstickB).start();
        }
    }
}