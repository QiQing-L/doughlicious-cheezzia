package com.pluralsight.doughliciouscheezzia.models.toppings.premium;

public class Cheese extends PremiumTopping {

    public Cheese(String name, String size) {
        super(name, size);
    }

//    public Cheese(String name) {
//        super(name);
//    }

    @Override
    public double calculatePice(String size) {
        if ((this.extraTopping)=true){
            return switch (size) {
                case "12" -> 1.50 + 0.60;
                case "16" -> 2.25 + 0.90;
                default -> 0.75 + 0.30; // default size 8".
            };
        } else return switch (size) {
            case "12" -> 1.50;
            case "16" -> 2.25;
            default -> 0.75;
        };
    }

}
