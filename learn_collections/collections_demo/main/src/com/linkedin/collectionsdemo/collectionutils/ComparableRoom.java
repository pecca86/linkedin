package com.linkedin.collectionsdemo.collectionutils;


import com.linkedin.collectionsdemo.challengeone.Room;

import java.util.Comparator;
import java.util.Objects;
/*
    Implement combarable to naturally order the rooms
    Comparanle<ComparableRoom> = we can compare a room to another
 */

public class ComparableRoom implements Comparable<ComparableRoom>{

    // Our own custom comparator
    // Firstly compares rates, secondly on name, thirdly on type
    public static Comparator<ComparableRoom> RATE_COMPARATOR =
            Comparator.comparing(ComparableRoom::getRate)
                    .thenComparing(ComparableRoom::getName)
                    .thenComparing(ComparableRoom::getType);

    private String name;

    private String type;

    private int capacity;

    private double rate;

    public ComparableRoom(String name, String type, int capacity, double rate) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public ComparableRoom(String name, String type) {
        super();
        this.name = name;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparableRoom room = (ComparableRoom) o;
        return Objects.equals(name, room.name) && Objects.equals(type, room.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "Room [name=" + name + ", type=" + type + ", capacity=" + capacity + ", rate=" + rate + "]";
    }

    // 0 = equal, 1 = first is larger, -1 = first is smaller
    @Override
    public int compareTo(ComparableRoom o) {
        int result = this.getName().compareTo(o.getName());
        // The logic is as follows:
        // If the result is 0 (meaning equal on name) -> compare the room type
        // this should break the tie
        return result != 0 ? result : this.getType().compareTo(o.getType());
    }
}
