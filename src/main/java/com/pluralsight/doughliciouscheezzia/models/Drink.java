package com.pluralsight.doughliciouscheezzia.models;

public class Drink extends MenuItem {
    private String size;

    public Drink(String name, String size) {
        super(name);
        this.size = size;
    }

    @Override
    public double calculatePrice() {

        return switch (size) {
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 2.00;
        };
    }
}
