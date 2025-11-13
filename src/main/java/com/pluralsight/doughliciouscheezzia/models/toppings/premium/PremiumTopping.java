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

//    public PremiumTopping(String name) {
//        super(name);
//        this.size = "";
//        this.extraTopping = false;
//    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean getExtraTopping() {
        return extraTopping;
    }

    public abstract double calculatePice(String size);

    public void isExtraTopping(){
        this.extraTopping = true;
    }
}
