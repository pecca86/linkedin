package com.linkedin.concurrency2.producerconsumer;

/**
 * Producers serving soup for Consumers to eat.
 * As a rule of thumb the produce speed should be less than the consumption speed.
 */

import java.util.concurrent.*;

class SoupProducer extends Thread {

    // Java specific queue that can block and hold a finite amount of elements, it is also thread safe.
    private BlockingQueue servingLine;

    public SoupProducer(BlockingQueue servingLine) {
        this.servingLine = servingLine;
    }

    public void run() {
        for (int i=0; i<20; i++) { // serve 20 bowls of soup
            try {
                servingLine.add("Bowl #" + i);
                System.out.format("Served Bowl #%d - remaining capacity: %d\n", i, servingLine.remainingCapacity());
                Thread.sleep(200); // time to serve a bowl of soup
            } catch (Exception e) { e.printStackTrace(); }
        }
        servingLine.add("no soup for you!"); // This will indicate we have stopped producing soup
        servingLine.add("no soup for you!");
    }
}

class SoupConsumer extends Thread {

    private BlockingQueue servingLine;

    public SoupConsumer(BlockingQueue servingLine) {
        this.servingLine = servingLine;
    }

    public void run() {
        while (true) {
            try {
                String bowl = (String)servingLine.take();
                if (bowl == "no soup for you!")
                    break;
                System.out.format("Ate %s\n", bowl);
                Thread.sleep(300); // time to eat a bowl of soup
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}

public class ProducerConsumerDemo {
    public static void main(String args[]) {
        // Java specific queue that can block and hold a finite amount of elements, it is also thread safe.
        BlockingQueue servingLine = new ArrayBlockingQueue<String>(5);
        new SoupConsumer(servingLine).start(); // ServingLine is the stream of consumable data
        new SoupConsumer(servingLine).start();
        new SoupProducer(servingLine).start();
    }
}