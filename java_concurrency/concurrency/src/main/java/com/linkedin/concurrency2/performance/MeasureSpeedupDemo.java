package com.linkedin.concurrency2.performance;

/**
 * Weak scaling:        Bringing in more processors for the same task, more work - same time
 * Strong scaling:      Breaking up the problem, to do more work in a set amount of time, more work - less time
 * Throughput:          N tasks / time (actions per unit)
 * Latency:             time / task (time)
 *
 * Speedup:             (sequential execution time) / (parallel execution time with N workers)
 * Amdahl's law:        P = portion that can be made parallel, S = Speedup of the parallelized portion
 *                      Overall Speedup = 1 / (1-P) + P/S
 *                      This shows that adding more and more processors will at some point not have any effect on how
 *                      fast the program executes.
 * Efficiency:          Calculates how well additional resources are used.
 *                      speedup / N processors
 */


import java.util.concurrent.*;

// parallel implementation
class RecursiveSum extends RecursiveTask<Long> {

    private long lo, hi;

    // constructor for recursive instantiations
    public RecursiveSum(long lo, long hi) {
        this.lo = lo;
        this.hi = hi;
    }

    // returns sum of numbers between lo and hi
    protected Long compute() {
        if (hi-lo < 100) { // base case threshold
            long total = 0;
            for (long i=lo; i<=hi; i++)
                total += i;
            return total;
        } else {
            long mid = (hi+lo)/2; // middle index for split
            RecursiveSum left = new RecursiveSum(lo, mid);
            RecursiveSum right = new RecursiveSum(mid+1, hi);
            left.fork(); // forked thread computes left half
            return right.compute() + left.join(); // current thread computes right half
        }
    }
}

public class MeasureSpeedupDemo {

    // sequential implementation
    private static long sequentialSum(long lo, long hi) {
        long total = 0;
        for (long i=lo; i<=hi; i++)
            total += i;
        return total;
    }

    public static void main(String args[]) {
        final int NUM_EVAL_RUNS = 10;
        final long SUM_VALUE = 1_000_000_000L;

        System.out.println("Evaluating Sequential Implementation...");
        long sequentialResult = sequentialSum(0, SUM_VALUE); // "warm up"
        double sequentialTime = 0;
        // Evaluate the time it takes to perform the calculation sequentially
        for(int i=0; i<NUM_EVAL_RUNS; i++) {
            long start = System.currentTimeMillis();
            sequentialSum(0, SUM_VALUE);
            sequentialTime += System.currentTimeMillis() - start;
        }
        sequentialTime /= NUM_EVAL_RUNS;

        System.out.println("Evaluating Parallel Implementation...");
        ForkJoinPool pool = ForkJoinPool.commonPool();
        long parallelResult = pool.invoke(new RecursiveSum(0, SUM_VALUE)); // "warm up"
        pool.shutdown();
        double parallelTime = 0;
        for(int i=0; i<NUM_EVAL_RUNS; i++) {
            long start = System.currentTimeMillis();
            pool = ForkJoinPool.commonPool();
            pool.invoke(new RecursiveSum(0, SUM_VALUE));
            pool.shutdown();
            parallelTime += System.currentTimeMillis() - start;
        }
        parallelTime /= NUM_EVAL_RUNS;

        // display sequential and parallel results for comparison
        if (sequentialResult != parallelResult)
            throw new Error("ERROR: sequentialResult and parallelResult do not match!");
        System.out.format("Average Sequential Time: %.1f ms\n", sequentialTime);
        System.out.format("Average Parallel Time: %.1f ms\n", parallelTime);
        System.out.format("Speedup: %.2f \n", sequentialTime/parallelTime);
        System.out.format("Efficiency: %.2f%%\n", 100*(sequentialTime/parallelTime)/Runtime.getRuntime().availableProcessors());
    }
}