package com.linkedin.generics;

import java.util.ArrayList;
import java.util.List;

public class Rules {
    public static void main(String[] args) {
        // Generics requires Reference types and NOT primitives, int -> Integer
        List<Integer> integerList = new ArrayList<>();
        integerList.add(10);
        int num = integerList.get(0); // Autoboxes from Integer to int


        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        System.out.println(fruits.add("Gurka"));
        // fruits = integerList --> won't work, since invoked different parameters
    }
}
