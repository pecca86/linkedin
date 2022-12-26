package com.linkedin.generics.mygenerics;

public class BoxUtils {

    // Counts items in box
    // Need to specifie that we will have a generic typed parameter e.g. <T>
    // We will need to do this, since we are not inside the generic class
    public static <T extends Boxable> int getCountOfItems(Box<T> items) {
        return items.getItems().size();
    }

    // This can now be invoked by ANY KIND OF BOX
    // This is safe, since it does not invoke any methods there the Type matters
    public static int getWildCardCountOfItems(Box<?> items) {
        return items.getItems().size();
    }

    // This will be an generic box that can contain Objects that implement Boxable, until it is
    // invoked by calling it with an object implementing Boxable
    public static <T extends Boxable> Box<T> emptyBox() {
        return new Box<>();
    }
}
