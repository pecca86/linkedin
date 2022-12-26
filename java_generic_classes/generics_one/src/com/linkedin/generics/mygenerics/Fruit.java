package com.linkedin.generics.mygenerics;

public class Fruit implements Boxable {
    private String fruitName;
    private String originCountry;
    private double weight;

    public Fruit(String fruitName, String originCountry, double weight) {
        this.fruitName = fruitName;
        this.originCountry = originCountry;
        this.weight = weight;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "fruitName='" + fruitName + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", weight=" + weight +
                '}';
    }
}
