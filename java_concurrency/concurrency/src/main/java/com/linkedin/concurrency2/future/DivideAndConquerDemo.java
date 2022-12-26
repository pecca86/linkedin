package com.linkedin.concurrency2.future;

/**
 * Result is a great way of solving divide and conquer problems in parallel, since each sub problem can is independent.
 *
 * This demo will use Java's ForkJoin, which means the class needs to extend RecursiveTask<T> (if we are returning a value)
 */


import java.util.concurrent.*;

class RecursiveSum extends RecursiveTask<Long> {

    private long lo, hi;

    public RecursiveSum(long lo, long hi) {
        this.lo = lo;
        this.hi = hi;
    }

    // Extending the RecursiveTask class means we need to implement this method
    protected Long compute() {
        if (hi-lo <= 100_000) { // base case threshold
            long total = 0;
            for (long i = lo; i <= hi; i++)
                total += i;
            return total;
        } else {
            long mid = (hi+lo)/2; // middle index for split
            RecursiveSum left = new RecursiveSum(lo, mid);
            RecursiveSum right = new RecursiveSum(mid+1, hi);
            left.fork(); // forked thread computes left half
            // current thread computes right half and waits for left fork to finish
            return right.compute() + left.join();
        }
    }
}

public class DivideAndConquerDemo {
    public static void main(String args[]) {
        // A static pool of worker threads
        ForkJoinPool pool = ForkJoinPool.commonPool();
        // Call invoke method, which starts the threads
        Long total = pool.invoke(new RecursiveSum(0, 1_000_000_000));
        pool.shutdown(); // Manually shut down the pool
        System.out.println("Total sum is " + total);
    }
}