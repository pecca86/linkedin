package com.linkedin.generics.wildcards;

import com.linkedin.generics.hierarchies.Apple;
import com.linkedin.generics.mygenerics.Book;
import com.linkedin.generics.mygenerics.Box;
import com.linkedin.generics.mygenerics.BoxUtils;
import com.linkedin.generics.mygenerics.Fruit;
/*
    WILDCARD USE:
    1. Unbounded
        - Specified using only wildcard operator <?>
        - When you don't want a reference a type parameter
    2. Upper bounded
        - List<Number> is not a subTYPE of ArraList<Double>, despite Double being a child of Number
    3. Lower bounded
        - Opposite of lower
        - Specified using the SUPER keyword
 */

// public class TestWildcard extends Box<?> <-- wildcard not allowed!
public class TestWildcard {
    public static void main(String[] args) {
        //new Box<?>(); <-- not allowed to use wildcard here

        //var box = BoxUtils.<?>emptyBox(); <-- wildcard not allowed

        Box<Fruit> fruitBox = new Box<Fruit>();
        fruitBox.addItem(new Fruit("Banana", "Qwait", 4.4));
        fruitBox.addItem(new Fruit("Kiwi", "Australia", 0.4));
        fruitBox.addItem(new Apple("Kiwi", "Australia", 0.4));

        // Unbounded wildcards
        System.out.println("Count of fruits " + BoxUtils.getCountOfItems(fruitBox));
        System.out.println("Count of fruits w/ wildcard " + BoxUtils.getWildCardCountOfItems(fruitBox));

        // Upper bounded wildcard use, this would not accept a Box<Book>
        System.out.println(Box.averageWeightOfFruits(fruitBox));

        // Lower bounded wildcard
        Box.addAppleOrAppleSuperToBox(fruitBox);
        Box<Book> bookBox = new Box<Book>();
        //Box.addAppleOrAppleSuperToBox(bookBox); <-- does not work!




    }
}
