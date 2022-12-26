package test.com.linkedin.collectionsdemo;

import com.linkedin.collectionsdemo.challengeone.Guest;
import com.linkedin.collectionsdemo.challengeone.Room;
import com.linkedin.collectionsdemo.listchallenge.GuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GuestServiceTest {

    GuestService service;

    Room piccadilly = new Room("Piccadilly", "Guest Room", 3, 125.00);
    Room cambridge = new Room("Cambridge", "Premiere Room", 4, 175.00);
    Room westminister = new Room("Westminister", "Premiere Room", 4, 175.00);
    Room oxford = new Room("Oxford", "Suite", 5, 225.0);
    Room victoria = new Room("Victoria", "Suite", 5, 225.0);
    Room manchester = new Room("Manchester", "Suite", 5, 225.0);

    Guest john, maria, sonia, siri, bob;

    @BeforeEach
    void setUp() throws Exception {

        this.service = new GuestService();

        john = new Guest("John", "Doe", false);
        john.getPreferredRooms().addAll(List.of(oxford, victoria, manchester));

        maria = new Guest("Maria", "Doe", true);
        maria.getPreferredRooms().addAll(List.of(cambridge, oxford));

        sonia = new Guest("Sonia", "Doe", true);
        sonia.getPreferredRooms().addAll(List.of(cambridge));

        siri = new Guest("Siri", "Doe", true);

        bob = new Guest("Bob", "Doe", false);

    }

    @Test
    void testFilterByFavoriteRoom() {
        assertTrue(GuestService.filterByFavoriteRoom(List.of(john, maria, sonia, siri, bob), cambridge).containsAll(List.of(maria, sonia)));
        assertFalse(GuestService.filterByFavoriteRoom(List.of(john, maria, sonia, siri, bob), cambridge).containsAll(List.of(john, siri, sonia)));
        assertTrue(GuestService.filterByFavoriteRoom(List.of(john, maria, sonia, siri, bob), oxford).containsAll(List.of(john)));
        assertFalse(GuestService.filterByFavoriteRoom(List.of(john, maria, sonia, siri, bob), oxford).containsAll(List.of(maria,sonia, siri, bob)));
        assertTrue(GuestService.filterByFavoriteRoom(List.of(john, maria, sonia, siri, bob), victoria).isEmpty());
    }

    @Test
    void testSwapPosition() {

        this.service.checkIn(bob);
        this.service.checkIn(maria);
        this.service.checkIn(sonia);
        this.service.checkIn(john);
        this.service.checkIn(siri);

        this.service.swapPosition(maria, john);
        this.service.swapPosition(siri, bob);

        List<Guest> guests = this.service.getCheckInList();
        assertEquals(4, guests.indexOf(maria));
        assertEquals(1, guests.indexOf(sonia));
        assertEquals(3, guests.indexOf(siri));
        assertEquals(2, guests.indexOf(bob));
        assertEquals(0, guests.indexOf(john));

    }


    @Test
    void testCheckIn() {

        this.service.checkIn(bob);
        this.service.checkIn(maria);
        this.service.checkIn(sonia);
        this.service.checkIn(john);
        this.service.checkIn(siri);

        List<Guest> guests = this.service.getCheckInList();
        assertEquals(0, guests.indexOf(maria));
        assertEquals(1, guests.indexOf(sonia));
        assertEquals(2, guests.indexOf(siri));
        assertEquals(3, guests.indexOf(bob));
        assertEquals(4, guests.indexOf(john));

    }

}
