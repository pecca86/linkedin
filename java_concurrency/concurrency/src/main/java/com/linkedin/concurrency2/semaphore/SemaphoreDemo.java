package com.linkedin.concurrency2.semaphore;
/**
 * A Semaphore is like a mutex, but it allows multiple threads to access a resource, and it keeps track of how many
 * semaphores have been acquired.
 *
 * As long as the semaphore value is > 0, it can allow for new threads to acquire it. If zero, new threads will be blocked
 * and placed in a queue, waiting for the semaphore to be available.
 *
 * A thread that has done it's work calls the release() method, which automatically wakes up waiting threads.
 *
 * A pipeline is a chain of producer-consumer pairs.
 **/

import java.util.concurrent.*;

class CellPhone extends Thread {

    // Binary semaphore. A binary semaphore can be acquired and released by different threads
    //private static Semaphore charger = new Semaphore(1);

    // Counting semaphore, simulates a charger with 4 ports
    private static Semaphore charger = new Semaphore(4);

    public CellPhone(String name) {
        this.setName(name);
    }

    public void run() {
        try {
            charger.acquire(); // Try to acquire the semaphore, if value is 0, the thread will wait
            System.out.println(this.getName() + " is charging...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(this.getName() + " is DONE charging!");
            charger.release(); // Releases the semaphore, which will increase the semaphore's value
        }
    }
}

public class SemaphoreDemo {
    public static void main(String args[]) {
        for (int i =0; i < 10; i++)
            new CellPhone("Phone-"+i).start();
    }
}