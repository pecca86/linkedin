package com.linkedin.collectionsdemo.challengeone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class RoomService {


    // 1. Declare a Collection to store Room Inventory
    private Collection<Room> rooms;

    public RoomService() {
        // 2. Initialize Collection and assign it to the Room Inventory
        this.rooms = new ArrayList<>();
    }


    public RoomService(Collection<Room> rooms) {
        this.rooms = new ArrayList<>(rooms);
    }

    public Collection<Room> getInventory(){
        // 3. Return the Room Inventory
        // Returns a copy of the inventory, since it is mutable
        return new ArrayList<>(this.rooms);
    }

    public void createRoom(String name, String type, int capacity, double rate) {
        // 4. Add a new Room to the Room Inventory using the provided parameters
        this.rooms.add(new Room(name, type, capacity, rate));
    }

    public void createRooms(Room[] rooms) {
        // 5. Add the Rooms provided in the Array to the Room Inventory
        this.rooms.addAll(Arrays.asList(rooms));
    }

    public void removeRoom(Room room) {
        // 6. Remove the provided Room from the Room Inventory
        this.rooms.remove(room);

    }
}
