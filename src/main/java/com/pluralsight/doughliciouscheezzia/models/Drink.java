package com.pluralsight.doughliciouscheezzia.models;

public class Drink extends MenuItem {
    private String size;

    public Drink(String name, String size) {
        super(name);
        this.size = size;
    }

    @Override
    public double calculatePrice() {
        double drinkPrice = 0;
        switch (size){
            case "small":
                drinkPrice = 2.00;
                break;
            case "medium":
                drinkPrice = 2.50;
                break;
            case "large":
                drinkPrice = 3.00;
                break;
        }

        return drinkPrice;
    }
}
