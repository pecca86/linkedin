package com.linkedin.collectionsdemo.iterators;

import com.linkedin.collectionsdemo.challengeone.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

public class IterablesDemo {
    public static void main(String[] args) {
        Room room1 = new Room("Knullrumme", "Premiere", 10, 200.50);
        Room room2 = new Room("Fiskällaren", "Guest Room", 1, 10.99);
        Room room3 = new Room("S&M Vinden", "Premiere", 6, 666.66);
        Room room4 = new Room("Köksvägen", "Standard", 20, 2020.50);

        Collection<Room> rooms = new ArrayList<>(Arrays.asList(room1, room2, room3, room4));

        // EXAMPLE 1.
        // Generate own Iterator of our collection, iterator() is a factory method that returns an instance
        // of a new iterator
        Iterator<Room> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getName());
        }

        // Same but newer syntax
        for (Room room : rooms) {
            System.out.println(room.getName());
        }

        // EXAMPLE 2.
        // Code that will often break
        for (Room room : rooms) {
            if (room.getCapacity() < 2) {
                // Next line will throw a ConcurrentModificationException error
                // bc we modify the collection while iteration through it!
                //rooms.remove(room);
            }
        }

        System.out.println("=== EXAMPLE 2 ===");
        System.out.println("Rooms collection before Stream: " + rooms.size());
        rooms.stream()
                .map(r -> r.getName())
                .forEach(System.out::println);
        // Same logic done better
        removeRoomsByRoomCapacity(rooms, 16);

        System.out.println("Room collection after stream removal: " + rooms.size());
        rooms.stream()
                .map(r -> r.getName())
                .forEach(System.out::println);
    }

    // Removes all rooms with the capacity given or lower
    private static void removeRoomsByRoomCapacity(Collection<Room> rooms, int capacity) {
        rooms.removeAll(
                rooms.stream()
                        .filter(room -> room.getCapacity() <= capacity)
                        .collect(Collectors.toList())
        );
    }
}
