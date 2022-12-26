package com.linkedin.collectionsdemo.challengeone;

import java.util.ArrayList;
import java.util.List;

public class Guest {
    private String firstName;
    private String lastName;
    private boolean isLoyaltyMember;
    private List<Room> preferredRooms = new ArrayList<>();

    public Guest(String firstName, String lastName, boolean isLoyaltyMember) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isLoyaltyMember = isLoyaltyMember;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isLoyaltyMember() {
        return isLoyaltyMember;
    }

    public void setLoyaltyMember(boolean loyaltyMember) {
        isLoyaltyMember = loyaltyMember;
    }

    public List<Room> getPreferredRooms() {
        return preferredRooms;
    }

    public void setPreferredRooms(List<Room> preferredRooms) {
        this.preferredRooms = preferredRooms;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isLoyaltyMember=" + isLoyaltyMember +
                ", preferredRooms=" + preferredRooms +
                '}';
    }
}
