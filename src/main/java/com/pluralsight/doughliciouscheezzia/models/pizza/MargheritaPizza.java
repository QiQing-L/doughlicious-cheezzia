package com.pluralsight.doughliciouscheezzia.models.pizza;

import com.pluralsight.doughliciouscheezzia.models.toppings.Topping;
import java.util.List;

public class MargheritaPizza extends Pizza {

    // Fixed for Margherita Pizza
    private static final String PIZZA_SIZE = "12";
    private static final String CRUST_TYPE = "regular";

    /**
     * Constructs a Margherita Pizza with fixed size and crust type, and a pre-defined
     * list of toppings provided by the Display class.
     * @param toppingsList The list of required Toppings.
     */
    public MargheritaPizza(List<Topping> toppingsList) {
        super(PIZZA_SIZE, CRUST_TYPE, toppingsList);
        this.setName("Margherita Pizza");
    }

    // The calculatePrice() and toString() methods are inherited from the Pizza class.
}
