package com.linkedin.collectionsdemo.list;

import com.linkedin.collectionsdemo.challengeone.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestListDemo {
    public static void main(String[] args) {
        Guest guest1 = new Guest("John", "Mayer", false);
        Guest guest2 = new Guest("Elton", "John", true);
        Guest guest3 = new Guest("John", "Forgetty", false);
        Guest guest4 = new Guest("Elvis", "Presley", true);
        Guest guest5 = new Guest("Mike", "Modano", true);

        // Initialize an ArrayList with starting capacity of 100
        List<Guest> checkInList = new ArrayList<>(100);

        checkInList.add(guest1);
        checkInList.add(guest2);
        checkInList.add(guest3);

        printGuestList(checkInList);

        // Bulk add guest 4, 5 in between starting from index 2
        checkInList.addAll(2, List.of(guest4, guest5));
        printGuestList(checkInList);

        for (Guest g : checkInList) {
            if (g.isLoyaltyMember()) {
                continue;
            } else {
                checkInList.remove(g);
                break;
            }
        }

        printGuestList(checkInList);



    }

    public static void printGuestList(List<Guest> guestList) {
        System.out.format("%n-- Guest List Contents ---%n");
        for ( int i = 0 ; i < guestList.size(); i++ ) {
            Guest guest = guestList.get(i);
            System.out.format("%x: %s %n", i, guest.toString());
        }
    }
}
