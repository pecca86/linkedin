package com.linkedin.concurrency.threadsprocesses;

public class ExecutionSchedulingDemo {
    public static void main(String[] args) throws InterruptedException {
        VegetableChopper pekka = new VegetableChopper("Pekka");
        VegetableChopper derek = new VegetableChopper("Derek");

        // Create threads
        pekka.start();
        derek.start();
        Thread.sleep(1000);     // Run for 1 second
        VegetableChopper.chopping = false;  // Stop chopping

        pekka.join(); // Wait for other threads to finish, then return to main thread
        derek.join();

        // The result depends on how the OS Scheduler has scheduled the tasks
        System.out.println("Pekka chopped vegetable amount: " + pekka.vegetable_count);
        System.out.println("Derek chopped vegetable amount: " + derek.vegetable_count);

    }
}

class VegetableChopper extends Thread {
    public int vegetable_count = 0;
    public static boolean chopping = true;

    public VegetableChopper(String name) {
        this.setName(name);
    }

    public void run() {
        while (chopping) {
            System.out.println(this.getName() + " chopped a vegetable");
            vegetable_count++;
        }
    }
}