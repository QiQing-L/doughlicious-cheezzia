package com.pluralsight.doughliciouscheezzia.models.toppings.premium;

import com.pluralsight.doughliciouscheezzia.models.toppings.Topping;

public abstract class PremiumTopping extends Topping {
    private String size;
    boolean extraTopping;

    public PremiumTopping(String name, String size) {
        super(name);
        this.size = size;
        this.extraTopping = false;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isExtraTopping() {
        return extraTopping;
    }

    public abstract double calculatePice(String size);

    public void getExtraTopping(){
        this.extraTopping = true;
    }
}
