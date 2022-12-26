package com.pekka.learning.queuesandstacks;

import java.util.Stack;

public class StackParenthesise {

    public static void main(String[] args) {
        System.out.println("== SHOULD BE TRUE ==");
        System.out.println(hasMatchingParenthesis("(sodks)", '(', ')'));
        System.out.println("== SHOULD BE FALSE ==");
        System.out.println(hasMatchingParenthesis("()()[]][", '[', ']'));
    }


    // Checks if parentheses match
    // ex. (sodks) = true
    // (((((((d))))))) = true
    // ()(dsd)()()(dasd)((sda)) = true
    // (()()) = true
    // ((dada) = false
    // )(dasd) // false
    // dasd(( = false
    // (()( = false
    public static boolean hasMatchingParenthesis(String s, char opening, char closing) {
        Stack<Character> parenthesis = new Stack<>();

        if (s.charAt(0) == closing) {
            return false;
        }
        for (int i = 0 ; i < s.length(); i++) {
            if (s.charAt(i) == opening) {
                parenthesis.push(s.charAt(i));
            }

            if (s.charAt(i) == closing) {
                if (parenthesis.isEmpty()) {
                    return false;
                }
                parenthesis.pop();
            }
        }

        if (parenthesis.isEmpty()) {
            return true;
        }

        return false;
    }
}
