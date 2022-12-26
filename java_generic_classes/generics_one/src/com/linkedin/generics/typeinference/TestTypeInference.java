package com.linkedin.generics.typeinference;

import com.linkedin.generics.hierarchies.Apple;
import com.linkedin.generics.mygenerics.Box;
import com.linkedin.generics.mygenerics.BoxUtils;
import com.linkedin.generics.mygenerics.Boxable;
import com.linkedin.generics.mygenerics.Fruit;

import java.util.Collections;

/*
    Forces the compiler to invoke our type and not trough
    type erasure change it to a type of Object
 */
public class TestTypeInference {

    public static void main(String[] args) {
        // Type witness demo
        // At this point we do not what type of a list is returned
        // putting var infront, the compiler reveals, it's a list of Objects
        var list = Collections.emptyList();

        // If we put at type inside <>, we specify what type of a list we want to invoke
        // <String> is a type witness
        var list2 = Collections.<String>emptyList();

        // Type inference = compiler chooses the most specific type based on input and output type
        // Here we wish a Number type to be returned, and we pass in an integer and a Double
        // Since a int cannot be casted to a double, vice versa, the return type is a Number
        Number number = pickOne(2, 23.0);
        System.out.println(pickOne(4, 5.5).getClass());

        Fruit fruit = new Fruit("Mango", "India", 4.4);
        Apple pinkLady = new Apple("Pink Lady", "Finland", 1.3);


        // Here fruit is the most specific, since apple can be casted to Fruit
        Fruit picked = pickOne(fruit, pinkLady);
        System.out.println(picked.getClass());
        if (picked.getClass().equals(Apple.class)) {
            System.out.println("We go back an Apple!");
        }

        // This will also work with Boxable type, since both objects implement it
        Boxable pickedFruit = pickOne(fruit, pinkLady);

        // Type inference with instantiation:
        Box<Fruit> fruitBox = new Box<>(); // No need to specify Fruit inside new, since it gets invoked using type instantiation

        // Type inference with method argument
        // The compiler can, based on the return type, guess which type the Box will be
        var box = Box.of(fruit);
        var box2 = Box.of(pinkLady);

        // Type inference with target types
        var emptyBox = BoxUtils.emptyBox();
        var emptyAppleBox = BoxUtils.<Apple>emptyBox();
        Box<Fruit> emptyFruitBox = BoxUtils.emptyBox();

    }

    // Takes generic values as param, and returns a generic value
    public static <T> T pickOne(T t1, T t2) {
        if (Math.random() > 0.5) {
            return t1;
        }
        return t2;
    }
}
