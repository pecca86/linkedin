package com.linkedin.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NoGenerics {
    public static void main(String[] args) {

        // Non-specified List type, all object will be initiated from the Object class
        List languages = new ArrayList(); // RAW type

        languages.add("English");
        Object lang = new String("Swedish");
        languages.add(lang);
        languages.add(10);

        // 1. When referring to the entities inside the list we now need to cast to right type
        // 2. This also lacks type safety

        processLanguages(languages);

        Double one = 1.9;
        Double two = 2.0;
        System.out.println(two + one);
    }

    private static void processLanguages(List languages) {
        String lang = (String) languages.get(0); // Requiers us to know that the retrieved value is of type String
        System.out.println(lang);
    }
}
