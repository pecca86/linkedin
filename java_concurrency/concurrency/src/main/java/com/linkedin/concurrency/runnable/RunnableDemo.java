package com.linkedin.concurrency.runnable;

/**
 * Two threads cooking soup
 * Another way of creating threads, is by creating a new class that implements Runnable.
 * This class needs to override the run() method.
 * Runnable enables us to extend more interfaces.
 */

class ChefOlivia implements Runnable {
    public void run() {
        System.out.println("Olivia started & waiting for sausage to thaw...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Olivia is done cutting sausage.");
    }
}

public class RunnableDemo {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Barron started & requesting Olivia's help.");
        Thread olivia = new Thread(new ChefOlivia());

        System.out.println("Barron tells Olivia to start.");
        olivia.start();

        System.out.println("Barron continues cooking soup.");
        Thread.sleep(500);

        System.out.println("Barron patiently waits for Olivia to finish and join...");
        olivia.join();

        System.out.println("Barron and Olivia are both done!");
    }
}