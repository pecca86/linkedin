package com.linkedin.generics.hierarchies;

import com.linkedin.generics.mygenerics.Box;
import com.linkedin.generics.mygenerics.Boxable;


// Our new class NEEDS to implement and extend the same type as
// the super class it extends from !!
public class CardBoardBox<T extends Boxable> extends Box<T> {

    @Override
    public double getTotalWeight() {
        return super.getTotalWeight() + 10.0;
    }
}
