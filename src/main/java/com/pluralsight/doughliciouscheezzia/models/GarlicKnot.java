package com.pluralsight.doughliciouscheezzia.models;

public class GarlicKnot extends MenuItem {

    public GarlicKnot(String name) {
        super("Garlic Knots - (set of 3)");
    }

    @Override
    public double calculatePrice() {
        return 1.50;
    }

}
