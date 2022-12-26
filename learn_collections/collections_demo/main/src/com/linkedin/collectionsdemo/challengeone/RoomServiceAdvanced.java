package com.linkedin.collectionsdemo.challengeone;

import java.util.*;
import java.util.stream.Collectors;

public class RoomServiceAdvanced {

    private Collection<Room> inventory;

    public RoomServiceAdvanced() {
        this.inventory = new ArrayDeque<>();
    }

    public boolean hasRoom(Room room) {

        // 1. Returns a boolean that indicates if the Room Inventory contains a Room.
        return this.inventory.contains(room);
    }

    public Room[] asArray() {
        // 2. Returns all Rooms as an Array of Rooms in the **order** they were Added.
        Collection<Room> result = new ArrayDeque<>(this.inventory);
        Room[] rooms = result.toArray(new Room[result.size()]);
        return rooms;
    }

    public Collection<Room> getByType(String type){

	/*
	   3. Return a new Collection of Rooms where Room#type matches the provided String.
		  The original Room Inventory collection MUST NOT BE MODIFIED.
	*/
        Collection<Room> result = this.inventory.stream()
                .filter(room -> room.getType().equals(type))
                .collect(Collectors.toList());

        return result;

    }

    public Collection<Room> getInventory() {
        return new HashSet<>(this.inventory);
    }

    public void createRoom(String name, String type, int capacity, double price) {
        this.inventory.add(new Room(name, type, capacity, price));
    }

    public void createRooms(Room[] rooms) {
        this.inventory.addAll(Arrays.asList(rooms));
    }

    public void removeRoom(Room room) {
        this.inventory.remove(room);
    }
}
