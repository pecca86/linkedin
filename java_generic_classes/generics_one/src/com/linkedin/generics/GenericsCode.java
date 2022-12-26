package com.linkedin.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsCode {

    public static void main(String[] args) {
        List<String> langs = new ArrayList<String>();

        langs.add("German");

        processLanguages(langs);
    }

    private static void processLanguages(List<String> langs) {
        String language = langs.get(0);
        System.out.println(language);
    }
}
