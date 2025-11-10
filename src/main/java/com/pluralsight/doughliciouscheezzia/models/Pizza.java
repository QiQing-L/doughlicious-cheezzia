package com.pluralsight.doughliciouscheezzia.models;

import com.pluralsight.doughliciouscheezzia.toppings.Topping;

import java.util.List;

public class Pizza extends MenuItem {
    private String size;
    private String crustType;
    private List<Topping> topping;
    private boolean stuffedCrust;

    public Pizza(String size, String crustType, List<Topping> topping) {
        super("Pizza");
        this.size = size;
        this.crustType = crustType;
        this.topping = topping;
        this.stuffedCrust = false;
    }

    @Override
    public double calculatePrice() {
        return 0;
    }
}
