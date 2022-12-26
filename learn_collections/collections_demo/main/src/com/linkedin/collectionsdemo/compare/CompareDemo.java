package com.linkedin.collectionsdemo.compare;

public class CompareDemo {

    public static void main(String[] args) {
        Car car1 = new Car("Porsche", "911", 2);
        Car car2 = new Car("Porsche", "911", 2);
        Car car3 = new Car("Ferrari", "Marcello", 3);

        // This will return true, since we have overridden the equals method in our car class
        System.out.println(car1.equals(car2));
    }
}
