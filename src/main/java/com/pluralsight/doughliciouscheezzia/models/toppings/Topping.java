package com.pluralsight.doughliciouscheezzia.models.toppings;

public abstract class Topping {
    private String name;

    public Topping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculatePice(String size);
}
