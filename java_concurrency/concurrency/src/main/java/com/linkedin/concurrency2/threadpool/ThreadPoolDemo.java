package com.linkedin.concurrency2.threadpool;

/**
 * A thread pool Creates and maintains a collection of worker threads.
 *
 * It reuses existing threads to execute new tasks.
 *
 * This addresses the overhead of spawning new threads. This is beneficial if the time it takes to execute a task is
 * lower than the time it takes to spawn a new thread.
 *
 * Java has the ExecutorService interface for handling thread pools:
 * - it holds a queue of work waiting to be done
 * - it then uses a pool of threads, to which it allocates new work
 * - has methods like:
 *      - newSingleThreadExecutor() = single thread that executes tasks
 *      - newFixedThreadPool(int nThreads) = fixed amount of threads to execute tasks
 */

import java.util.concurrent.*;

class VegetableChopper extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " chopped a vegetable!");
    }
}

public class ThreadPoolDemo {
    public static void main(String args[]) {
        // Check available processors
        int numProcs = Runtime.getRuntime().availableProcessors();
        System.out.println("Processor available: " + numProcs);
        // Create N worker threads
        ExecutorService pool = Executors.newFixedThreadPool(numProcs);
        for (int i=0; i<100; i++)
            pool.submit(new VegetableChopper());
        pool.shutdown(); // The thread pool needs to explicitly be shut down.
    }
}