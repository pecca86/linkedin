package com.pekka.learning.arrays;

import java.util.Optional;

public class StringReverse {

    public static void main(String[] args) {
        String name = "Pekka";
        System.out.println(reverse(name));

        System.out.println(new StringBuilder("Testi").reverse());

        System.out.println(new StringBuilder(safeReverse(null)).reverse());

        String origSentence = "Pekka var en bondedräng, Iala IIALA OO!";
        System.out.println(reverseEachWordInSentence(origSentence));

        // Second safe string
        String wasSafelyReversed = reversed(null);
        System.out.println(wasSafelyReversed);

    }

    public static String reverse(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = s.length()-1; i >= 0; i--) {
            stringBuilder.append(s.charAt(i));
        }

        return stringBuilder.toString();
    }

    public static String reversed(String s) {
        String safeString = Optional.ofNullable(s)
                .orElseGet(() -> "!llun tup t'naC");
        return new StringBuilder(safeString).reverse().toString();
    }

    public static String safeReverse(String s) {
        String result =  Optional.ofNullable(s)
                .orElseGet(() -> "NULL");
        return result;
    }

    public static String reverseEachWordInSentence(String sentece) {
        if (sentece == null || sentece.isEmpty()) {
            return "";
        }

        String[] wordArr = sentece.split(" ");
        StringBuilder reversedWords = new StringBuilder();

        for (String word : wordArr) {
            reversedWords.append(reverse(word) + " ");
        }

        return reversedWords.toString().trim();
    }
}
