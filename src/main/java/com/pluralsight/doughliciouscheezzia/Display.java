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

import java.util.*;

import static com.pluralsight.doughliciouscheezzia.pos.Utility.paresInt;

public class Display {


    /* -------------- Shared Data ------------------ */

    private static Map<String,List<MenuItem>> menu = new HashMap<>();
    private static Map<String, List<Topping>> premiumToppingMenu = new HashMap<>();
    private static Map<String, List<Topping>> includedToppingMenu = new HashMap<>();
    private static List<String> pizzaSizes = new ArrayList<>();
    private static List<String> drinkSizes = new ArrayList<>();
    private static List<MenuItem> orderItems = new ArrayList<>();
    private static Order currentOrder = new Order(orderItems);

    /* -------------- text colors ------------------ */
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String RED2 = "\u001B[91m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String YELLOW2 = "\u001B[93m";
    private static final String BLUE = "\u001B[34m";
    private static final String BLUE2 = "\u001B[94m";
    private static final String BOLD = "\u001B[1m";
    /* -------------- Unicode characters/ Emojis ------------------ */
    private static final String ICON_TOMATO = "\uD83C\uDF45"; // 🍅
    private static final String ICON_PIZZA = "\uD83C\uDF55";  // 🍕
    private static final String ICON_CART = "\uD83D\uDED2";   // 🛒
    private static final String ICON_BREAD = "\uD83E\uDD50";  // 🥐
    private static final String ICON_FORK_PLATE_KNIFE = "\uD83C\uDF7D"; // 🍽️
    private static final String ICON_MEAT = "\uD83E\uDD69";   // 🥩
    private static final String ICON_CHEESE = "\uD83E\uDDC0"; // 🧀
    private static final String ICON_DRINK = "\uD83E\uDD64";  // 🥤
    private static final String ICON_LOAF ="\uD83C\uDF5E"; //🍞
    private static final String ICON_BAGUETTE = "\uD83E\uDD56"; //🥖
    private static final String ICON_FLATBREAD ="\uD83E\uDED3"; //🫓
    private static final String ICON_CAULIFLOWER = "\uD83E\uDD66"; //🥦
    private static final String ICON_MEMO = "\uD83D\uDCDD"; //📝

    private static final String welcomeLine = "\n"+ BOLD + YELLOW2 + "       "+ICON_TOMATO+ICON_PIZZA+" Welcome to " + RED2 + "Doughlicious Cheezzia" + YELLOW2 + "! "+ICON_TOMATO+ICON_PIZZA+"       " + RESET;
    private static final String welcomeLine2 = "\n"+ YELLOW + "       "+ICON_TOMATO+ICON_PIZZA+" Welcome to " + RED + "Doughlicious Cheezzia" + YELLOW + "! "+ICON_TOMATO+ICON_PIZZA+"       " + RESET;



//    public Order createOrder(List<MenuItem> orderItems){
//        this.currentOrder = new Order(orderItems);
//        return currentOrder;
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadMenu();
        displayMenu();
        homeScreen(scanner);

        scanner.close();

    }


    /**
     * this method adds all menu items and related sizes, to its perspective menu lists.
     */
    public static void loadMenu(){
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

        includedToppingMenu.put("regularToppings",regularToppings);

        //Sauces List:
        List<Topping> sauces = new ArrayList<>();
        sauces.add(new Sauce("marinara"));
        sauces.add(new Sauce("alfredo"));
        sauces.add(new Sauce("pesto"));
        sauces.add(new Sauce("bbq"));
        sauces.add(new Sauce("buffalo"));
        sauces.add(new Sauce("olive oil"));

        includedToppingMenu.put("sauces",sauces);

        //Sides List:
        List<Topping> sides = new ArrayList<>();
        sides.add(new Side("red pepper"));
        sides.add(new Side("parmesan"));

        includedToppingMenu.put("sides", sides);

        // add lists of premium toppings to premiumToppingMenu list
        // Meats List:
        List<Topping> meats = new ArrayList<>();
        meats.add(new Meat("pepperoni",""));
        meats.add(new Meat("sausage",""));
        meats.add(new Meat("ham",""));
        meats.add(new Meat("bacon",""));
        meats.add(new Meat("chicken",""));
        meats.add(new Meat("meatball",""));

        premiumToppingMenu.put("meats", meats);

        //Cheese List:
        List<Topping> cheeses = new ArrayList<>();
        cheeses.add(new Cheese("Mozzarella",""));
        cheeses.add(new Cheese("Parmesan",""));
        cheeses.add(new Cheese("Ricotta",""));
        cheeses.add(new Cheese("Goat Cheese",""));
        cheeses.add(new Cheese("Buffalo",""));

        premiumToppingMenu.put("cheeses", cheeses);
        // add drinks list and Garlic Knots List to Menu List
        //Garlic Knots List:
        List<MenuItem> garlicKnots = new ArrayList<>();
        garlicKnots.add(new GarlicKnot("Classic Garlic Knots"));
        garlicKnots.add(new GarlicKnot("Cheesy Garlic Knots"));
        garlicKnots.add(new GarlicKnot("Spicy Garlic Knots"));
        garlicKnots.add(new GarlicKnot("Herby Garlic Knots"));

        menu.put("garlicKnots", garlicKnots);

        // Drinks list:
        List<MenuItem> drinks = new ArrayList<>();
        drinks.add(new Drink("Cola",""));
        drinks.add(new Drink("Lemon-Lime soda",""));
        drinks.add(new Drink("Orange soda",""));
        drinks.add(new Drink("Sparkling Water",""));
        drinks.add(new Drink("Water",""));
        drinks.add(new Drink("Iced Tea",""));
        drinks.add(new Drink("Lemonade",""));

        menu.put("drinks", drinks);

    }

    /**
     * this method displays the general menu table of pricing structure for pizzas, drinks, and Garlic Knots.
     */
    public static void displayMenu(){
        System.out.println(welcomeLine);
        System.out.println(BOLD + "\n            =========== "+ICON_FORK_PLATE_KNIFE+" Menu "+ICON_FORK_PLATE_KNIFE+" ===========            \n"+RESET);
        StringBuilder menuDisplay = new StringBuilder();

        // Pricing Table (using String.format for alignment)
        String headerFormat = "%-25s | %-6s | %-6s | %-6s\n";
        String rowFormat    = "%-25s | $%-5.2f | $%-5.2f | $%-5.2f\n";
        String separator    = "--------------------------|--------|--------|--------\n";
        String separator2    = "-----------------------------------------------------\n";

        menuDisplay.append(BOLD + "-----------------"+ICON_PIZZA+" Pizza Pricing "+ICON_PIZZA+"-----------------\n"+ RESET);
        menuDisplay.append(String.format(headerFormat, "Pizza Size", "8”", "12”", "16”"));
        menuDisplay.append(String.format(rowFormat, "Pizza Price", 8.50, 12.00, 16.50));
        menuDisplay.append(separator);
        //Topping Pricing Table
        menuDisplay.append(String.format(headerFormat, "Premium Topping", "8”", "12”", "16”"));
        menuDisplay.append(separator);
        menuDisplay.append(String.format(rowFormat, ICON_MEAT+"Meat Topping (Base)", 1.00, 2.00, 3.00));
        menuDisplay.append(String.format(rowFormat, "Extra Meat ", 0.50, 1.00, 1.50));
        menuDisplay.append(String.format(rowFormat, ICON_CHEESE+"Cheese Topping (Base)", 0.75, 1.50, 2.25));
        menuDisplay.append(String.format(rowFormat, "Extra Cheese ", 0.30, 0.60, 0.90));
        menuDisplay.append(separator);

        menuDisplay.append("Regular Toppings, Sauces, and Sides are included at no extra charge. \n");
        menuDisplay.append("Premium Toppings (Meats and Cheeses) are charged based on pizza size, per topping. \n");
        menuDisplay.append("\n");
        menuDisplay.append(BOLD+ "Other Items\n"+RESET);
        menuDisplay.append(separator2);
        //Drinks Pricing
        menuDisplay.append(String.format(headerFormat,ICON_DRINK+"Drink Size", "Small", "Medium", "large"));
        menuDisplay.append(String.format(rowFormat, ICON_DRINK+ "Drink Price", 2.00, 2.50, 3.00));
        menuDisplay.append(separator2);
        //Garlic Knots Pricing
        menuDisplay.append(ICON_BREAD+ "Garlic Knots (set of 3): $1.50 \n");
        menuDisplay.append(separator2);

        System.out.println(menuDisplay);

    }

    public static void homeScreen(Scanner scanner){
        String choice = "";
        while (!choice.equals("0")) {
            System.out.println(welcomeLine2+"\n");
            System.out.println("1) "+ICON_CART+"New Order ");
            System.out.println("0) ❌Exit ");
            System.out.print("Your choice: ");

            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> orderScreen(scanner);
                case "0" -> System.out.println("Thank you for dining with us!");
                default -> System.out.println("Invalid choice! Please enter 1 or 0.");
            }
        }

    }

    public static void orderScreen(Scanner scanner){

        String choice = "";
        while (!choice.equals("0")) {
            System.out.println("\n"+BOLD+ YELLOW2+"     "+ ICON_CART+ " New Order "+ICON_CART+"     "+RESET+"\n");
            System.out.println("1) " + ICON_PIZZA + " Add Pizza ");
            System.out.println("2) " + ICON_DRINK + " Add Drink  ");
            System.out.println("3) " + ICON_BREAD + " Add Garlic Knots ");
            System.out.println("4) " + ICON_CART + "Checkout");
            System.out.println("0) ❌ Cancel Order  ");
            System.out.print("Your choice: ");

            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addPizza(scanner);
                case "2" -> addDrink(scanner);
                case "3" -> addGarlicKnots(scanner);
                case "4" -> checkOut();
                case "0" -> cancelOrder();
                default -> System.out.println("Invalid choice! Please enter 1 or 0.");
            }
        }

    }

    public static void addPizza(Scanner scanner){
        boolean done = false;
        String crust = "";
        String size = "";
        List<Topping> toppings = new ArrayList<>();

        while (!done) {
            System.out.println("\n"+BOLD+ YELLOW+ "➕ Add Pizza " + ICON_PIZZA + RESET+"\n");
            // prompt user for pizza crust type:
            System.out.println(ICON_LOAF + " Select your crust type: ");
            System.out.println("1) thin" + ICON_FLATBREAD);
            System.out.println("2) regular" + ICON_BAGUETTE);
            System.out.println("3) thick" + ICON_LOAF);
            System.out.println("4) cauliflower" + ICON_CAULIFLOWER);
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    crust = "thin";
                    break;
                case "2":
                    crust = "regular";
                    break;
                case "3":
                    crust = "thick";
                    break;
                case "4":
                    crust = "cauliflower";
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1, 2, 3 or 4.");
                    break;

            }

            // prompt user for pizza size:
            System.out.println(ICON_MEMO+ "Pizza size: ");
            for (int i = 0; i < pizzaSizes.size(); i++) {
                System.out.println((i+1)+ ") "+pizzaSizes.get(i));
            }
            String sizeChoice = scanner.nextLine().trim();

            switch (sizeChoice) {
                case "1" -> size = pizzaSizes.get(0);
                case "2" -> size = pizzaSizes.get(1);
                case "3" -> size = pizzaSizes.get(2);
                default -> System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }

            // prompt user for pizza toppings:
            System.out.println(ICON_TOMATO + "Toppings: ");

            for (int i = 0; i < premiumToppingMenu.get("meats").size(); i++) {
                System.out.println((i+1)+ ") "+premiumToppingMenu.get("meats").get(i).getName());
            }
            String meatChoice = scanner.nextLine().trim();
            int meatInex = paresInt(meatChoice);

            if (meatInex<premiumToppingMenu.get("meats").size())

            switch (meatChoice) {
                case "1" -> toppings.add(premiumToppingMenu.get("meats").get(0));
                case "2" -> toppings.add(premiumToppingMenu.get("meats").get(1));
                case "3" -> toppings.add(premiumToppingMenu.get("meats").get(2));
                default -> System.out.println("Invalid choice! Please enter a number choice.");
            }



            Pizza pizza = new Pizza(size,crust,toppings);
            System.out.println(pizza+pizza.getCrustType()+pizza.getSize()+pizza.getToppings());
            //orderItems.add(new Pizza(size,crust,toppings);
            done = true;


        }

    }

    public static void addDrink(Scanner scanner){
        String choice = "";
        while (!choice.equals("0")) {
            System.out.println(welcomeLine2);
            System.out.println("1) " + ICON_PIZZA + " Add Pizza ");
            System.out.println("2) " + ICON_DRINK + " Add Drink  ");
            System.out.println("0) ❌ Cancel Order  ");
            System.out.print("Your choice: ");

            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> System.out.println(" ");
                case "2" -> System.out.println(" 1");

                case "0" -> System.out.println("Your order has been canceled. Thank you for dining with us!");
                default -> System.out.println("Invalid choice! Please enter 1 or 0.");
            }
        }

    }
    public static void addGarlicKnots(Scanner scanner){
        String choice = "";
        while (!choice.equals("0")) {
            System.out.println(welcomeLine2);
            System.out.println("1) " + ICON_PIZZA + " Add Pizza ");
            System.out.println("2) " + ICON_DRINK + " Add Drink  ");
            System.out.println("0) ❌ Cancel Order  ");
            System.out.print("Your choice: ");

            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> System.out.println(" ");
                case "2" -> System.out.println("1 ");

                case "0" -> System.out.println("Your order has been canceled. Thank you for dining with us!");
                default -> System.out.println("Invalid choice! Please enter 1 or 0.");
            }
        }

    }



    public static void checkOut(){

    }

    public static void cancelOrder(){
        System.out.println("Your order has been canceled. Thank you for dining with us!");

    }
}
