package com.linkedin.collectionsdemo.mapschallenge;

import com.linkedin.collectionsdemo.challengeone.Guest;
import com.linkedin.collectionsdemo.challengeone.Room;

import java.util.HashMap;
import java.util.Map;

public class BookingService {
    private Map<Room, Guest> bookings = new HashMap<>();

    public boolean book(Room room, Guest guest) {

        /*
         * 1. The provided Guest is placed in the bookings Map and
         * associated with the provided room, only if no other guest
         * is associated with the room.
         *
         * Returns a boolean that indicates if the Guest was
         * successfully placed in the room.
         */
        if (this.bookings.get(room) == null) {
            this.bookings.put(room, guest);
            return true;
        }

        return false;
    }

    public double totalRevenue() {

        /*
         * 2. Returns a double that totals the rate of each Room booked
         * in the bookings Map.
         */
        double totalRate = 0;

        for (Map.Entry<Room, Guest> entry : this.bookings.entrySet()) {
            if (entry.getValue() != null) {
                totalRate += entry.getKey().getRate();
            }
        }

        // CHECK streamedRevenue for stream version

        return totalRate;
    }

    public double streamedRevenue() {
        return this.bookings.keySet().stream()
                .mapToDouble(Room::getRate)
                .sum();
    }

    public Map<Room, Guest> getBookings() {
        return bookings;
    }
}
