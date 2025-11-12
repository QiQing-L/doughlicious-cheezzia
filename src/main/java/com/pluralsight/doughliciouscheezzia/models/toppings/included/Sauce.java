package com.pluralsight.doughliciouscheezzia.models.toppings.included;

import com.pluralsight.doughliciouscheezzia.models.toppings.Topping;

public class Sauce extends Topping {

    public Sauce(String name) {
        super(name);
    }

    @Override
    public double calculatePice() {
        return 0;
    }


}
