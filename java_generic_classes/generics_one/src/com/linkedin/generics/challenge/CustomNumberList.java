package com.linkedin.generics.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomNumberList<N extends Number & Comparable<N>> {
    private List<N> operands;

    public CustomNumberList() {
        this.operands = new ArrayList<>();
    }

    public CustomNumberList(List<N> operands) {
        this.operands = new ArrayList<>(operands); // This enables us to take in a immutable list as param
    }

    public List<N> sortedNumbers() {
        Collections.sort(operands);
        return operands;
    }

    public N maxNumber() {
        return Collections.max(this.operands);
    }

    public void add(N n) {
        this.operands.add(n);
    }
}
