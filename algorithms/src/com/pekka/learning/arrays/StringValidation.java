package com.pekka.learning.arrays;

import java.util.function.Function;

public class StringValidation {


    public static void main(String[] args) {
        String onlyUppers = "IMAUPPE";

        // Functional
        boolean allUpper = onlyUppers.chars()
                .allMatch(Character::isUpperCase);
        System.out.println(allUpper);

        String shouldBeOnlyLower = "iadoksdoak";
        System.out.println(hasOnlyLowerCase(shouldBeOnlyLower));

        System.out.println(isGoodPassword("dosaLLkfok0909"));

    }

    public static boolean hasOnlyLowerCase(String s) {
        if (s.equals(s.toLowerCase())) return true;
        return false;
    }

    // Check if password contains: 1 lower, 1 upper, 1 number
    public static boolean isGoodPassword(String s) {
        return s.chars().anyMatch(Character::isUpperCase) &&
                s.chars().anyMatch(Character::isLowerCase) &&
                s.chars().anyMatch(Character::isDigit);
    }

}
