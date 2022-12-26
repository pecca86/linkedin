package com.linkedin.generics.mygenerics;

public class Book implements Boxable {

    private String bookName;
    private String author;
    private double bookWeight;

    public Book(String bookName, String author, double bookWeight) {
        this.bookName = bookName;
        this.author = author;
        this.bookWeight = bookWeight;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getWeight() {
        return bookWeight;
    }

    public void setBookWeight(double bookWeight) {
        this.bookWeight = bookWeight;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", bookWeight=" + bookWeight +
                '}';
    }
}
