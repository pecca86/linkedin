package com.linkedin.generics.hierarchies;

import com.linkedin.generics.mygenerics.Box;
import com.linkedin.generics.mygenerics.Fruit;

public class TestGenericHierachies {


    public static void main(String[] args) {
        CardBoardBox<Fruit> fruitCardBoardBox = new CardBoardBox<>();

        Box<Fruit> fruitBox = new Box<>();

        Fruit banana = new Fruit("Banana", "Spain", 10.0);
        Fruit apple = new Fruit("Apple", "Italy", 8.0);

        fruitBox.addItem(banana);
        fruitCardBoardBox.addItem(banana);
        fruitCardBoardBox.addItem(apple);

        System.out.println(fruitBox.getTotalWeight());
        System.out.println(fruitCardBoardBox.getTotalWeight());

        // SINCE BOTH ARE OF THE SAME GENERIC TYPE WE CAN CAST ONE TO THE OTHER
        fruitBox = fruitCardBoardBox;
        System.out.println(fruitBox.getItems());

        // TYPE ARGUMENT HIERARCHY
        Box<Apple> appleBox = new Box<>();
        //fruitBox = appleBox; // Doest not work despite apple being a child of fruit b/c there is not an extct match on the TYPE argument


    }
}
