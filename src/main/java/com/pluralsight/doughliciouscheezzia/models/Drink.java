package com.pluralsight.doughliciouscheezzia.models;

public class Drink extends MenuItem {
    private String size;

    public Drink(String name, String size) {
        super(name);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public double calculatePrice() {
        return switch (size) {
            case "Medium" -> 2.50;
            case "Large" -> 3.00;
            default -> 2.00;
        };
    }
}
