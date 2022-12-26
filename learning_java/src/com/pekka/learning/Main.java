package com.pekka.learning;

import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.pekka.learning.Pekka.printP;


public class Main {

    public static void main(String[] args) {

        System.out.println(calculateSalary(116, 35));

        System.out.println(calcSal.apply(116, 35));

        Pekka pekka = new Pekka();

        printP();

        System.out.println(Pekka.SEX_GOD);



    }

    private static double calculateSalary(int hoursPerWeek, int amountPerHour) {
        return  hoursPerWeek * amountPerHour;
    }

    private static BiFunction<Integer, Integer, Double> calcSal =
            (hoursPerWeek, amountPerHour) -> Double.valueOf(hoursPerWeek * amountPerHour);



}
