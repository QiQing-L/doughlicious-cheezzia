package com.pluralsight.doughliciouscheezzia.models;

public class GarlicKnot extends MenuItem {

    public GarlicKnot(String name) {
        super(name);
    }

    @Override
    public double calculatePrice() {
        return 1.50;
    }

}
