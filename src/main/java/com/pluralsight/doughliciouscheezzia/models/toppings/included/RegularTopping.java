package com.pluralsight.doughliciouscheezzia.models.toppings.included;

import com.pluralsight.doughliciouscheezzia.models.toppings.Topping;

public class RegularTopping extends Topping {
    public RegularTopping(String name) {
        super(name);
    }

    @Override
    public double calculatePice(String size) {
        return 0;
    }


}
