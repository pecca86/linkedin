package com.linkedin.collectionsdemo.collectionmethods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CollectionMethodsDemo {
    public static void main(String[] args) {
        Collection collection = new HashSet();

        // It is always better to be more abstract, and not like this:
        Set<String> stringSet = new HashSet<>();

        // Or even worse
        HashSet<String> stringHashSet = new HashSet<>();

        // For demo purposes
        Collection<String> hobbies = new ArrayList<>();
        hobbies.add("Golf");
        hobbies.add("Padel");
        hobbies.add("Padel");
        System.out.println(hobbies);

        // Instantiate a collection using a collection
        Collection<String> uniqueHobbies = new HashSet<>(hobbies);
        System.out.println(uniqueHobbies);
    }
}
