package com.pluralsight.doughliciouscheezzia.models.toppings;

public abstract class Topping {
    private String name;
    boolean extraTopping;

    public Topping(String name) {
        this.name = name;
        this.extraTopping = false;
    }

    public String getName() {
        return name;
    }

    public abstract double calculatePice(String size);

    public void setExtraTopping() {
        this.extraTopping = true;
    }

    public boolean isExtraTopping() {
        return extraTopping;
    }

    @Override
    public String toString() {
        return "Topping{" +
                "name:'" + name + '\'' +
                ", extraTopping:" + extraTopping +
                '}';
    }
}
