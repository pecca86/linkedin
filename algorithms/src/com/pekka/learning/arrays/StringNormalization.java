package com.pekka.learning.arrays;

public class StringNormalization {

    public static void main(String[] args) {
        String input = "I am a really stupid string, who can't be noRMaLiZed!";
        System.out.println(normalizeString(input));
    }

    // Make lowercase, trim whitespace and replace commas
    public static String normalizeString(String s) {
        return s.toLowerCase().trim().replace(",", "");
    }
}
