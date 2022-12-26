package com.linkedin.collectionsdemo.interfaces;

public class InterfaceDemo {
    public static void main(String[] args) {

        Contract contract = new Implementation();
        printContract(contract);

        ExtendedContract extendedContract = new Implementation();
        extendedContract.extendedTerm(); // Now our Implementation only has the extentedTerm method available


    }

    public static void printContract(Contract contract) {
        contract.term1();
        contract.term2();
        contract.extendedTerm();
    }
}
