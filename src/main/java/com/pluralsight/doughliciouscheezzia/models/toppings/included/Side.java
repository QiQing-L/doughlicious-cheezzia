package com.pluralsight.doughliciouscheezzia.models.toppings.included;

import com.pluralsight.doughliciouscheezzia.models.toppings.Topping;

public class Side extends Topping {

    public Side(String name) {
        super(name);
    }

    @Override
    public double calculatePice() {
        return 0;
    }
}
