package com.pluralsight.doughliciouscheezzia.models.pizza;

import com.pluralsight.doughliciouscheezzia.models.MenuItem;
import com.pluralsight.doughliciouscheezzia.models.toppings.Topping;

import java.util.List;

public class Pizza extends MenuItem {
    private String size;
    private String crustType;
    private List<Topping> toppings;
    private boolean stuffedCrust;

    public Pizza(String size, String crustType, List<Topping> toppings) {
        super("Pizza");
        this.size = size;
        this.crustType = crustType;
        this.toppings = toppings;
        this.stuffedCrust = false;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrustType() {
        return crustType;
    }

    public void setCrustType(String crustType) {
        this.crustType = crustType;
    }

    public List<Topping> getTopping() {
        return topping;
    }

    public void setTopping(List<Topping> topping) {
        this.topping = topping;
    }

    public boolean isStuffedCrust() {
        return stuffedCrust;
    }

    public void setStuffedCrust(boolean stuffedCrust) {
        this.stuffedCrust = stuffedCrust;
    }

    @Override
    public double calculatePrice() {
        double basePrice = 0;
        double toppingPrice = 0;
         switch (size) {
             case "12":
                 basePrice = 12.00;
                 break;
             case "16":
                 basePrice = 16.50;
                 break;
             default:
                 basePrice = 8.50;// default size 8".
                 break;
         }

        for (Topping eachTopping : toppings) {
            toppingPrice += eachTopping.calculatePice(size);
        }

        double pizzaPrice = basePrice + toppingPrice;
        return pizzaPrice;
    }
}
