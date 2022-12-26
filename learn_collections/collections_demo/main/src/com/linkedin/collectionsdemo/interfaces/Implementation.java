package com.linkedin.collectionsdemo.interfaces;

public class Implementation implements Contract {
    @Override
    public void term1() {
        System.out.println("Term 1");
    }

    @Override
    public void term2() {
        System.out.println("Term 2");
    }

    // Since the class we implement, extends another class, we also need to implement
    // that class' contracts
    @Override
    public void extendedTerm() {
        System.out.println("Extented term");
    }
}
