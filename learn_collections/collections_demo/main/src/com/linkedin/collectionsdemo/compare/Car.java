package com.linkedin.collectionsdemo.compare;

import java.util.Objects;

/*
    Creating own equals method
 */
public class Car {

    private String make;
    private String model;
    private double doors;

    public Car(String make, String model, double doors) {
        this.make = make;
        this.model = model;
        this.doors = doors;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getDoors() {
        return doors;
    }

    public void setDoors(double doors) {
        this.doors = doors;
    }

    /*
        By default each new object inherits the Object class' equals method.
        This comparison is as Object a == Object b.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.doors, doors) == 0 && Objects.equals(make, car.make) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, doors);
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", doors=" + doors +
                '}';
    }
}
