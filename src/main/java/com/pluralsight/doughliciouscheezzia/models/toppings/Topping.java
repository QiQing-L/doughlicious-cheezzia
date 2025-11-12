package com.pluralsight.doughliciouscheezzia.models.toppings;

public abstract class Topping {
    private String name;

    public Topping(String name) {
        this.name = name;
    }

    public abstract double calculatePice();
}
