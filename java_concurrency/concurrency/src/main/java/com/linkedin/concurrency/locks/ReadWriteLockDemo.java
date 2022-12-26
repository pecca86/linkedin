package com.linkedin.concurrency.locks;

/**
 * Several users reading a calendar, but only a few users updating it.
 * Read-Write lock is effective, when there are more read operations and a sparse amount of write operations.
 * This allows for many readers to hold the lock at the same time.
 * The write thread still needs to wait for the readers to release the lock before acquiring it. The write thread will
 * also block new readers from getting the lock, while it is writing new data. Only ONE thread can hold the write lock
 * at a time.
 */

import java.util.concurrent.locks.*;

class CalendarUser extends Thread {

    private static final String[] WEEKDAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private static int today = 0;
    private static ReentrantReadWriteLock marker = new ReentrantReadWriteLock();
    private static Lock readMarker = marker.readLock(); // read lock
    private static Lock writeMarker = marker.writeLock(); // write lock

    public CalendarUser(String name) {
        this.setName(name);
    }

    public void run() {
        while (today < WEEKDAYS.length-1){
            // Checks if the thread wants to write data
            if (this.getName().contains("Writer")) { // update the shared calendar
                writeMarker.lock();
                try {
                    today = (today+1) % 7;
                    System.out.println(this.getName() + " updated date to " + WEEKDAYS[today]);
                } catch (Exception e)
                {e.printStackTrace(); }
                {
                    writeMarker.unlock();
                }
            } else { // Reader - check to see what today is
                readMarker.lock();
                try {
                    System.out.println(this.getName() + " sees that today is " + WEEKDAYS[today] + "; total readers: " + marker.getReadLockCount());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    readMarker.unlock();
                }
            }
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        // create ten reader threads
        for (int i=0; i<10; i++)
            new CalendarUser("Reader-"+i).start();

        // ...but only two writer threads
        for (int i=0; i<2; i++)
            new CalendarUser("Writer-"+i).start();
    }
}