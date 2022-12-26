package com.linkedin.concurrency2.future;

/**
 * One thread gets a promise of a result sometime in the FUTURE (read only).
 * Uses the Callable<V> interface, which will return a result - Runnable does not do this.
 */

import java.util.concurrent.*;

class HowManyVegetables implements Callable {
    public Integer call() throws Exception {
        System.out.println("Olivia is counting vegetables...");
        Thread.sleep(3000);
        return 42;
    }
}

public class FutureDemo {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        System.out.println("Barron asks Olivia how many vegetables are in the pantry.");
        // Create a new single threaded executor
        ExecutorService executor = Executors.newSingleThreadExecutor();
        // initialize a Future with the executor as argument, the class needs to implement Callable
        Future result = executor.submit(new HowManyVegetables());
        System.out.println("Barron can do other things while he waits for the result...");
        System.out.println("Olivia responded with " + result.get()); // get() blocks the caller and has it waiting for the result
        executor.shutdown(); // executor needs to be shut down manually
    }
}