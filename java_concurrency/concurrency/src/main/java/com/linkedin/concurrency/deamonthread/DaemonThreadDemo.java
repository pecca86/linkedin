package com.linkedin.concurrency.deamonthread;

/**
 * Barron finishes cooking while Olivia cleans.
 * Daemon threads get detached from the spawning thread / process.
 * Daemon threads should only be used, if the sudden shutdown of the thread can't cause any negative
 * side effects. Daemon threads do not gracefully exit, that is why they should not be used for e.g., I/O operations.
 * A thread created from the Daemon thread will inherit the daemon status from its parent thread.
 * The reason for using daemon threads, is so the parent thread can exit, despite the daemon thread still running.
 */

class KitchenCleaner implements Runnable {
    public void run() {
        while (true) {
            System.out.println("Olivia cleaned the kitchen.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class DaemonThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread olivia = new Thread(new KitchenCleaner());
        olivia.setDaemon(true); // make the thread a daemon thread, default = not a daemon
        olivia.start();

        System.out.println("Barron is cooking...");
        Thread.sleep(600);
        System.out.println("Barron is cooking...");
        Thread.sleep(600);
        System.out.println("Barron is cooking...");
        Thread.sleep(600);
        System.out.println("Barron is done!");
    }
}