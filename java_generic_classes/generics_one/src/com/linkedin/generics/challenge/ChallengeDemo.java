package com.linkedin.generics.challenge;

import java.util.ArrayList;
import java.util.List;

public class ChallengeDemo {

    public static void main(String[] args) {
        CustomNumberList<Integer> integerCustomNumberList = new CustomNumberList<>();
        CustomNumberList<Long> longCustomNumberList = new CustomNumberList<>();

        integerCustomNumberList.add(10);
        integerCustomNumberList.add(9);
        integerCustomNumberList.add(11);
        System.out.println(integerCustomNumberList.maxNumber());
        System.out.println(integerCustomNumberList.sortedNumbers());

        longCustomNumberList.add(55L);
        longCustomNumberList.add(51010L);
        longCustomNumberList.add(99L);
        System.out.println(longCustomNumberList.sortedNumbers());

        List<Double> myDoubles = List.of(4.5, 4.4, 5.0, 4.5, 34.4);

        CustomNumberList<Double> customNumberList = new CustomNumberList<>(myDoubles);
        System.out.println(customNumberList.sortedNumbers());
    }
}
