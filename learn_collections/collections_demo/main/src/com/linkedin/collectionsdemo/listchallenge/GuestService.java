package com.linkedin.collectionsdemo.listchallenge;

import com.linkedin.collectionsdemo.challengeone.Guest;
import com.linkedin.collectionsdemo.challengeone.Room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GuestService {

    private List<Guest> checkinList = new ArrayList<>(100);

    public static List<Guest> filterByFavoriteRoom(List<Guest> guests, Room room) {

        /*
         *  1. Returns a new collection that contains guests from the provided collection
         *  who have indicated the provided room as the first preference in their preferred
         *  room list.
         */
        List<Guest> result = guests.stream()
                .filter(g -> g.getPreferredRooms().indexOf(room) == 0)
                .collect(Collectors.toList());
        return result;
    }

    public void checkIn(Guest guest) {

        /*
         *  2. Adds a guest to the checkinList, placing members of the loyalty program
         *  ahead of those guests not in the program. Otherwise, guests are arranged in the
         *  order they were inserted.
         */
        if (this.checkinList.isEmpty()) {
            this.checkinList.add(guest);
            return;
        }

        List<Guest> copyOf = new ArrayList<>(this.checkinList);

        for (Guest g : copyOf) {
            if (!g.isLoyaltyMember() && guest.isLoyaltyMember()) {
                this.checkinList.add(this.checkinList.indexOf(g), guest);
                break;
            } else if (g.isLoyaltyMember()){
                continue;
            } else {
                this.checkinList.add(guest);
                break;
            }
        }
    }

    public void swapPosition(Guest guest1, Guest guest2) {

        /*
         *  3.  Swaps the position of the two provided guests within the checkinList.
         *  If guests are not currently in the list no action is required.
         */
        if (!this.checkinList.contains(guest1) || !this.checkinList.contains(guest2)) {
            return;
        }

        int guest1Index = this.checkinList.indexOf(guest1);
        int guest2Index = this.checkinList.indexOf(guest2);
        Guest tmp1 = this.checkinList.get(guest1Index);
        Guest tmp2 = this.checkinList.get(guest2Index);
        this.checkinList.remove(guest1);
        this.checkinList.remove(guest2);
        this.checkinList.add(guest1Index, tmp2);
        this.checkinList.add(guest2Index, tmp1);

    }

    public List<Guest> getCheckInList() {
        return List.copyOf(this.checkinList);
    }

}
