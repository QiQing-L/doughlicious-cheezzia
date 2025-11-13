package com.pluralsight.doughliciouscheezzia;

import com.pluralsight.doughliciouscheezzia.models.Drink;
import com.pluralsight.doughliciouscheezzia.models.GarlicKnot;
import com.pluralsight.doughliciouscheezzia.models.MenuItem;
import com.pluralsight.doughliciouscheezzia.models.pizza.Pizza;
import com.pluralsight.doughliciouscheezzia.models.toppings.Topping;
import com.pluralsight.doughliciouscheezzia.models.toppings.included.RegularTopping;
import com.pluralsight.doughliciouscheezzia.models.toppings.included.Sauce;
import com.pluralsight.doughliciouscheezzia.models.toppings.included.Side;
import com.pluralsight.doughliciouscheezzia.models.toppings.premium.Cheese;
import com.pluralsight.doughliciouscheezzia.models.toppings.premium.Meat;
import com.pluralsight.doughliciouscheezzia.pos.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Display {
    private Order currentOrder;

    /*
             -------------- Shared Data ------------------
     */
    static List<List<MenuItem>> menu = new ArrayList<>();
    static List<List<Topping>> premiumToppingMenu = new ArrayList<>();
    static List<List<Topping>> includedToppingMenu = new ArrayList<>();
    static List<String> pizzaSizes = new ArrayList<>();
    static List<String> drinkSizes = new ArrayList<>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayMenu();


    }
     /*

        + homeScreen(scanner : Scanner) : void
        + orderScreen(scanner : Scanner) : void
        + createOrder(scanner : Scanner) : Order
        + checkOut(order : Order)
        + addPizza(scanner : Scanner) : void
        + addDrinks(scanner : Scanner) : void
        + addGarlicKnots(scanner : Scanner) : void
         */
    public void loadMenu(){
        // add pizza size to pizzaSizes list
        pizzaSizes.add("8");
        pizzaSizes.add("12");
        pizzaSizes.add("16");

        // add drink size to drinkSizes list
        drinkSizes.add("Small");
        drinkSizes.add("Medium");
        drinkSizes.add("Large");

        // add lists of included toppings to includedToppingMenu list
        //Regular Toppings List:
        List<Topping> regularToppings = new ArrayList<>();
        regularToppings.add(new RegularTopping("onions"));
        regularToppings.add(new RegularTopping("mushrooms"));
        regularToppings.add(new RegularTopping("bell peppers"));
        regularToppings.add(new RegularTopping("olives"));
        regularToppings.add(new RegularTopping("tomatoes"));
        regularToppings.add(new RegularTopping("spinach"));
        regularToppings.add(new RegularTopping("basil"));
        regularToppings.add(new RegularTopping("pineapple"));
        regularToppings.add(new RegularTopping("anchovies"));

        includedToppingMenu.add(regularToppings);

        //Sauces List:
        List<Topping> sauces = new ArrayList<>();
        sauces.add(new Sauce("marinara"));
        sauces.add(new Sauce("alfredo"));
        sauces.add(new Sauce("pesto"));
        sauces.add(new Sauce("bbq"));
        sauces.add(new Sauce("buffalo"));
        sauces.add(new Sauce("olive oil"));

        includedToppingMenu.add(sauces);

        //Sides List:
        List<Topping> sides = new ArrayList<>();
        sides.add(new Side("red pepper"));
        sides.add(new Side("parmesan"));

        includedToppingMenu.add(sides);

        // add lists of premium toppings to premiumToppingMenu list
        // Meats List:
        List<Topping> meats = new ArrayList<>();
        meats.add(new Meat("pepperoni",""));
        meats.add(new Meat("sausage",""));
        meats.add(new Meat("ham",""));
        meats.add(new Meat("bacon",""));
        meats.add(new Meat("chicken",""));
        meats.add(new Meat("meatball",""));

        premiumToppingMenu.add(meats);

        //Cheese List:
        List<Topping> cheeseList = new ArrayList<>();
        cheeseList.add(new Cheese("Mozzarella",""));
        cheeseList.add(new Cheese("Parmesan",""));
        cheeseList.add(new Cheese("Ricotta",""));
        cheeseList.add(new Cheese("Goat Cheese",""));
        cheeseList.add(new Cheese("Buffalo",""));

        premiumToppingMenu.add(cheeseList);
        // add drinks list and Garlic Knots List to Menu List
        //Garlic Knots List:
        List<MenuItem> garlicKnots = new ArrayList<>();
        garlicKnots.add(new GarlicKnot("Classic Garlic Knots"));
        garlicKnots.add(new GarlicKnot("Cheesy Garlic Knots"));
        garlicKnots.add(new GarlicKnot("Spicy Garlic Knots"));
        garlicKnots.add(new GarlicKnot("Herby Garlic Knots"));

        menu.add(garlicKnots);

        // Drinks list:
        List<MenuItem> drinks = new ArrayList<>();
        drinks.add(new Drink("Cola",""));
        drinks.add(new Drink("Lemon-Lime soda",""));
        drinks.add(new Drink("Orange soda",""));
        drinks.add(new Drink("Sparkling Water",""));
        drinks.add(new Drink("Water",""));
        drinks.add(new Drink("Iced Tea",""));
        drinks.add(new Drink("Lemonade",""));

        menu.add(drinks);

    }
    public static void displayMenu(){
        System.out.println("\n            =========== Menu ===========            \n");
        StringBuilder menuDisplay = new StringBuilder();

        // Pricing Table (using String.format for alignment)
        String headerFormat = "%-25s | %-6s | %-6s | %-6s\n";
        String rowFormat    = "%-25s | $%-5.2f | $%-5.2f | $%-5.2f\n";
        String separator    = "--------------------------|--------|--------|--------\n";
        String separator2    = "-----------------------------------------------------\n";

        menuDisplay.append("------------------- Pizza Pricing -------------------\n");
        menuDisplay.append(String.format(headerFormat, "Pizza Size", "8”", "12”", "16”"));
        menuDisplay.append(String.format(rowFormat, "Pizza Price", 8.50, 12.00, 16.50));
        menuDisplay.append(separator);
        //Topping Pricing Table
        menuDisplay.append(String.format(headerFormat, "Premium Topping", "8”", "12”", "16”"));
        menuDisplay.append(separator);
        menuDisplay.append(String.format(rowFormat, "Meat Topping (Base)", 1.00, 2.00, 3.00));
        menuDisplay.append(String.format(rowFormat, "Extra Meat ", 0.50, 1.00, 1.50));
        menuDisplay.append(String.format(rowFormat, "Cheese Topping (Base)", 0.75, 1.50, 2.25));
        menuDisplay.append(String.format(rowFormat, "Extra Cheese ", 0.30, 0.60, 0.90));
        menuDisplay.append(separator);

        menuDisplay.append("Regular Toppings, Sauces, and Sides are included at no extra charge. \n");
        menuDisplay.append("Premium Toppings (Meats and Cheeses) are charged based on pizza size, per topping. \n");
        menuDisplay.append("\n");
        menuDisplay.append("Other Items\n");
        menuDisplay.append(separator2);
        //Drinks Pricing
        menuDisplay.append(String.format(headerFormat,"Drink Size", "Small", "Medium", "large"));
        menuDisplay.append(separator);
        menuDisplay.append(String.format(rowFormat,"Drink Price", 2.00, 2.50, 3.00));
        menuDisplay.append(separator2);
        //Garlic Knots Pricing
        menuDisplay.append("Garlic Knots (set of 3): $1.50 \n");
        menuDisplay.append(separator2);

        System.out.println(menuDisplay);

    }

    public void homeScreen(Scanner scanner){

    }

    public void orderScreen(Scanner scanner){

    }
}
