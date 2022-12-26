package com.pekka.learning.arrays;

public class StringSearch {

    public static void main(String[] args) {
        String hello = "Hello, World";
        System.out.println(hello.contains("ll"));
        //parse(hello);
        System.out.println(stringContainsLetterBAtEven("ABba"));

    }

    public static void parse(String s) {
        for (char c : s.toCharArray()) {
            System.out.println(c);
        }
    }

    public static boolean stringContainsLetterBAtEven(String s) {
        for (int i = 1; i < s.length(); i+=2) {
            if (s.charAt(i) == 'B') {
                return true;
            }
        }
        return false;
    }
}
