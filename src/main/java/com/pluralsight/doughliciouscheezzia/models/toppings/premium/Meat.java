package com.pluralsight.doughliciouscheezzia.models.toppings.premium;

public class Meat extends PremiumTopping {

    public Meat(String name, String size) {
        super(name, size);
    }

    @Override
    public double calculatePice(String size) {
        if ((this.extraTopping)=true){
            return switch (size) {
                case "12\"" -> 3.00;
                case "16\"" -> 4.50;
                default -> 1.50;
            };
        } else return switch (size) {
            case "12\"" -> 2.00;
            case "16\"" -> 3.00;
            default -> 1.00;
        };
    }

}
