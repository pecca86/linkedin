package com.linkedin.generics.mygenerics;

import com.linkedin.generics.hierarchies.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/*
    By extending T with Boxable, we effectively require our passed in objects to Extend this interface as well

    RESTRICTIONS:
        - A Generic object cannot be inizialized as T t = new T();
        - Static fields / method live inside the class, private Static T t is not allowed
        - instanceOf checks only work with unbounded wildcard
        - Method overloading:
            * private static void add (Box<Fruit>)
            * private static void add (Box<Book>)
            * ===> Will at compile after erasure time both be private static void add (Box fruitBox, resp. Box bookBox)
            * Generics can't extend Throwable
            * You can't write a Catch that tries to catch a Generic
            * public class GenericClass<T extends Throwable> DOES work -> inside this class we can now
              add a method like public void process() throws T
            *
 */
public class Box<T extends Boxable> {
    private List<T> items;

    public Box() {
        this.items = new ArrayList<>();
    }

    public List<T> getItems() {
        return items;
    }

    public void addItem(T t) {
        this.items.add(t);
    }

    public T getLatestItem() {
        return this.items.get(this.items.size()-1);
    }

    public double getTotalWeight() {
        return this.items.stream()
                .mapToDouble(item -> item.getWeight()).sum();

    }

    // Before this method is called, it is just returning a generic box of Objects that
    // extend Boxable. Since the method is static, we need to specify the bound: <T extends Boxable>
    public static <T extends Boxable> Box<T> of(T t) {
        Box<T> box = new Box<>();
        box.addItem(t);
        return box;
    }

    // Return average weight of ANY OBJECT that is a child class of fruit
    public static double averageWeightOfFruits(Box<? extends Fruit> box) {
        return box.getItems().stream()
                .mapToDouble(Boxable::getWeight)
                .sum() / box.getItems().size();
    }

    // This can add all parent, grandparent etc. classes, from where apple ascends from to the box
    // If there is another class that is a child of Boxable, it is not included
    public static void addAppleOrAppleSuperToBox(Box<? super Apple> box) {
        box.addItem(new Apple("Test Apple", "Sweden", 0.3));
    }

    @Override
    public String toString() {
        return "Box{" +
                "items=" + items +
                '}';
    }
}
