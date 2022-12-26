package com.linkedin.collectionsdemo.collectionutils;

import com.linkedin.collectionsdemo.challengeone.Room;

import java.util.*;

public class CollectionUtilitiesDemo {
    public static void main(String[] args) {
        // == EXAMPLE 1 - COMPARING ==
        // Two of the rooms have the same name but different type
        ComparableRoom room3 = new ComparableRoom("S&M Vinden", "Premiere", 6, 10.99);
        ComparableRoom room1 = new ComparableRoom("Knullrumme", "Premiere", 10, 200.50);
        ComparableRoom room2 = new ComparableRoom("Fiskällaren", "Guest Room", 1, 10.99);
        ComparableRoom room4 = new ComparableRoom("Fiskällaren", "Standard", 20, 2020.50);

        List<ComparableRoom> rooms = new ArrayList<>(List.of(room1, room2, room3, room4));

        printRoomList(rooms);

        // Now order the rooms as specified in our ComparableRoom class (firstly by name, secondly by type)
        Collections.sort(rooms);

        System.out.println("SORTED:");
        printRoomList(rooms);


        // OVERLOADED METHOD THAT TAKES IN A COMPARATOR
        Collections.sort(rooms, Comparator.naturalOrder());
        // Sort is also in the list interface (preferred when working with a list)
        rooms.sort(Comparator.naturalOrder());


        // == EXAMPLE 2 - USING OWN CUSTOM COMPARATOR COMPARING RATES ==
        rooms.sort(ComparableRoom.RATE_COMPARATOR.reversed()); // Reverses
        System.out.println("COMPARING BY RATES:");
        printRoomList(rooms);

        // == EXAMPLE 3 - FINDING ELEMENTS FROM COLLECTION ==
        // Binary search - requires the collection to be sorted

        // returns index of element
        int index = Collections.binarySearch(rooms, room1, ComparableRoom.RATE_COMPARATOR);

        ComparableRoom notInCollection = new ComparableRoom("Empty Room", "Standard", 20, 2020.50);
        int indexOfNotInCollection = Collections.binarySearch(rooms, notInCollection, ComparableRoom.RATE_COMPARATOR);
        // Since the room does not exist, the method will return a negative number indicating at what
        // index the next room would be inserted
        System.out.println("Result of not found: " + indexOfNotInCollection);
        // Now we can use this information to put the room into its proper location
        rooms.add(Math.abs(++indexOfNotInCollection), notInCollection);
        System.out.println("INSERTED NEW ROOM:");
        printRoomList(rooms);

        System.out.println("MIN VALUE OF ROOMS RATE: " + Collections.min(rooms, ComparableRoom.RATE_COMPARATOR).getRate());
        System.out.println("MAX VALUE OF ROOMS RATE: " + Collections.max(rooms, ComparableRoom.RATE_COMPARATOR).getRate());

    }

    private static void printRoomList(List<ComparableRoom> rooms) {
        rooms.stream()
                .forEach(r -> System.out.format("%-15s %-15s %-10f %n", r.getName(), r.getType(), r.getRate()));
    }
}
