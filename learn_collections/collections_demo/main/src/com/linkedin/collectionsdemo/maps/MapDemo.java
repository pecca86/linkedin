package com.linkedin.collectionsdemo.maps;

import com.linkedin.collectionsdemo.challengeone.Guest;
import com.linkedin.collectionsdemo.challengeone.Room;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
    The key value inside a Map must be a type that implements
    Equals and HashCode

    Methods (sub set):
    - put(k,v) if value exists, it will be overwritten
    - get(k), returns null if not exists
    - map.keySet(), returns all keys, any changes to these, will change the underlying keys in the actual map
    - Set<Map.Entry<K,V> entrySet() --> returns a set view of the entries contained in the map

    Implementations:
    - TreeMap: Sorted order in tree structure
    - HashMap: Unordered
    - LinkedHashMap: Stores in ordered fashion

    - HashMap handles Hash collisions by creating a linked list of the values with the same hashCode.
    This list is then traversed when retrieving a value. HashMap now uses the equals method to decide if it
    has found the object we are searching for, thus the object needs to implement both hashCode and equals methods.

 */
public class MapDemo {
    public static void main(String[] args) {

        Room room1 = new Room("Knullrumme", "Premiere", 10, 200.50);
        Room room2 = new Room("Fiskällaren", "Guest Room", 1, 10.99);
        Room room3 = new Room("Vinden", "Guest Room", 1, 10.99);

        Guest guest1 = new Guest("John", "Mayer", false);
        Guest guest2 = new Guest("Elton", "John", true);

        Map<Room, Guest> roomAssignments = new HashMap<>();
        roomAssignments.put(room1, guest1);
        roomAssignments.put(room2, guest2);

        // === EXAMPLE 1 ===
        System.out.println(roomAssignments.get(room1));

        // Trying to retrieved room info by creating a room logically equivalent of an existing key
        // This will work, since Room has implemented hashCode and equals properly
        System.out.println(roomAssignments.get(new Room("Fiskällaren", "Guest Room", 1, 10.99)));

        // remove method return the k,v pair
        // Since it has this functionality, we can re-assign a room like this
        Guest g = roomAssignments.put(room1, roomAssignments.remove(room2));
        System.out.println("Added? : " + roomAssignments.putIfAbsent(room2, guest1)); // putIfAbsent does nothing if key has a value
        System.out.println("KAKKA: " + roomAssignments.put(room2, guest1));

        if (roomAssignments.get(room3) == null ) {
            System.out.println("Room is empty!");
        }

        System.out.println(roomAssignments);

        // === EXAMPLE 2 - ITERATING ===
        Set<Map.Entry<Room, Guest>> cv = roomAssignments.entrySet();

        for (Map.Entry<Room, Guest> entry : roomAssignments.entrySet()) {
            Room r = entry.getKey();
            Guest gst = entry.getValue();
            System.out.format("%s : %s%n", r.getName(), gst.getFirstName());
        }


    }
}
