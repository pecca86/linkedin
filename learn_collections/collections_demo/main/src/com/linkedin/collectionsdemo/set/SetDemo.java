package com.linkedin.collectionsdemo.set;

import com.linkedin.collectionsdemo.challengeone.Room;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Objects.hash;

/*
    A set does not allow duplicate elements
 */
public class SetDemo {
    public static void main(String[] args) {
        Room room1 = new Room("Knullrumme", "Premiere", 10, 200.50);
        Room room2 = new Room("Fiskällaren", "Guest Room", 1, 10.99);
        Room room3 = new Room("S&M Vinden", "Premiere", 6, 666.66);
        Room room4 = new Room("Köksvägen", "Standard", 20, 2020.50);
        Room room4Dup = new Room("Köksvägen", "Standard", 20, 2020.50);

        // === EXAMPLE 1 ===
        // A LinkedHashSet retains the order of insertion
        Set<Room> roomSet = new LinkedHashSet<>();
        roomSet.addAll(Arrays.asList(room1, room2, room3, room4));
        // This will not be added since it already exists
        roomSet.add(room4);
        // Add a logical equivalent room, our Room class' equals method check for logical
        // equivalences, so this will not be inserted
        roomSet.add(room4Dup);

        roomSet.stream()
                .map(Room::getName)
                .forEach(System.out::println);

        // === EXAMPLE 2 ===
        // This set can't be modified after instantiation
        Set<Room> unmodifiableSet = Set.of(room1, room2);
        // unmodifiableSet.add(room3); <-- Will not work, since the set is immutable

        // === EXAMPLE 3 ===
        // Create an unmodifiable copy of the given set
        // NOTE! If the original set is change, the changes will occur on the copied set as well!
        Set<Room> unmodifiableCopy = Set.copyOf(roomSet);

    }


}
