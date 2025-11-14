package com.pluralsight.doughliciouscheezzia.models.toppings.premium;

import com.pluralsight.doughliciouscheezzia.models.toppings.Topping;

public abstract class PremiumTopping extends Topping {
    private String size;


    public PremiumTopping(String name, String size) {
        super(name);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public abstract double calculatePice(String size);

}
