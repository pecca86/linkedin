package com.linkedin.generics.mygenerics;

public class BoxDemo {
    public static void main(String[] args) {
        Box<Book> bookBox = new Box<>();
        Box<Fruit> fruitBox = new Box<>();

        bookBox.addItem(new Book("Homot homoilemassa", "Gay-Karl", 10));
        bookBox.addItem(new Book("Ilman liukuvoidetta - kohti kivempaa seksiä", "Sexi-Pexi", 20));

        System.out.println(bookBox);
        System.out.println(fruitBox);

        System.out.println(bookBox.getLatestItem());

        System.out.println("Book count: " + BoxUtils.getCountOfItems(bookBox));

        System.out.println(bookBox.getTotalWeight());

    }
}
