package com.pluralsight.doughliciouscheezzia;

import com.pluralsight.doughliciouscheezzia.models.Drink;
import com.pluralsight.doughliciouscheezzia.models.GarlicKnot;
import com.pluralsight.doughliciouscheezzia.models.MenuItem;
import com.pluralsight.doughliciouscheezzia.models.pizza.MargheritaPizza;
import com.pluralsight.doughliciouscheezzia.models.pizza.Pizza;
import com.pluralsight.doughliciouscheezzia.models.pizza.VeggiePizza;
import com.pluralsight.doughliciouscheezzia.models.toppings.Topping;
import com.pluralsight.doughliciouscheezzia.models.toppings.included.RegularTopping;
import com.pluralsight.doughliciouscheezzia.models.toppings.included.Sauce;
import com.pluralsight.doughliciouscheezzia.models.toppings.included.Side;
import com.pluralsight.doughliciouscheezzia.models.toppings.premium.Cheese;
import com.pluralsight.doughliciouscheezzia.models.toppings.premium.Meat;
import com.pluralsight.doughliciouscheezzia.models.toppings.premium.PremiumTopping;
import com.pluralsight.doughliciouscheezzia.pos.FileManager;
import com.pluralsight.doughliciouscheezzia.pos.Order;

import java.util.*;
import java.util.stream.Collectors;

import static com.pluralsight.doughliciouscheezzia.pos.Utility.generateOrderId;
import static com.pluralsight.doughliciouscheezzia.pos.Utility.paresInt;

public class Display {

    /* -------------- Shared Data ------------------ */
    private static int orderCount = 1;
    private static Map<String,List<MenuItem>> menu = new HashMap<>();
    private static Map<String, List<Topping>> premiumToppingMenu = new HashMap<>();
    private static Map<String, List<Topping>> includedToppingMenu = new HashMap<>();
    private static List<String> pizzaSizes = new ArrayList<>();
    private static List<String> drinkSizes = new ArrayList<>();
    private static Order currentOrder = new Order(generateOrderId(orderCount));
    private static int pizzaCount = 1;

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
    private static final String ICON_MEAT = "\uD83C\uDF56";   //🍖
    private static final String ICON_CHEESE = "\uD83E\uDDC0"; // 🧀
    private static final String ICON_DRINK = "\uD83E\uDD64";  // 🥤
    private static final String ICON_LOAF ="\uD83C\uDF5E"; //🍞
    private static final String ICON_BAGUETTE = "\uD83E\uDD56"; //🥖
    private static final String ICON_FLATBREAD ="\uD83E\uDED3"; //🫓
    private static final String ICON_CAULIFLOWER = "\uD83E\uDD66"; //🥦
    private static final String ICON_MEMO = "\uD83D\uDCDD"; //📝
    private static final String ICON_PEPPER = "\uD83C\uDF36\uFE0F";//🌶️
    /* -------------- Headers ------------------ */
    private static final String welcomeLine = "\n"+ BOLD + YELLOW2 + "       "+ICON_TOMATO+ICON_PIZZA+" Welcome to " + RED2 + "Doughlicious Cheezzia" + YELLOW2 + "! "+ICON_TOMATO+ICON_PIZZA+"       " + RESET;
    private static final String welcomeLine2 = "\n"+ YELLOW + "       "+ICON_TOMATO+ICON_PIZZA+" Welcome to " + RED + "Doughlicious Cheezzia" + YELLOW + "! "+ICON_TOMATO+ICON_PIZZA+"       " + RESET;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadMenu();
        displayMenu();
        homeScreen(scanner);
        generateOrderId(orderCount);

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
        drinks.add(new Drink("Lemon-Lime Soda",""));
        drinks.add(new Drink("Orange Soda",""));
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
            System.out.println("1) " + ICON_PIZZA + " Add Custom Pizza ");
            System.out.println("2) "+  ICON_PIZZA + " Add Signature Pizzas ⭐");
            System.out.println("3) " + ICON_DRINK + " Add Drink  ");
            System.out.println("4) " + ICON_BREAD + " Add Garlic Knots ");
            System.out.println("5) " + ICON_CART + "Checkout");
            System.out.println("0) ❌ Cancel Order  ");
            System.out.print("Your choice: ");

            choice = scanner.nextLine().trim();


            switch (choice) {
                case "1" -> addCustomPizza(scanner);
                case "2" -> addSignaturePizza(scanner);
                case "3" -> addDrink(scanner);
                case "4" -> addGarlicKnots(scanner);
                case "5" -> checkOut(scanner);
                case "0" -> cancelOrder();
                default -> System.out.println(RED + "Invalid choice! Please enter 1, 2, 3, 4, 5, or 0." + RESET);
            }
        }

    }

    /*
    --------------- addCustomPizza related methods ----------------
     */
    public static void addCustomPizza(Scanner scanner){
        boolean done = false;
        String crust = "";
        String size = "";
        List<Topping> toppings = new ArrayList<>();

        while (!done) {
            System.out.println("\n"+BOLD+ YELLOW+ "➕ Add Custom Pizza " + ICON_PIZZA + RESET+"\n");
            // prompt user for pizza crust type:
            crust = pizzaCrustPrompt(scanner,crust);

            // prompt user for pizza size:
            size = pizzaSizePrompt(scanner,size);

            // prompt user for pizza toppings:
            pizzaToppingPrompt(scanner, toppings,size);

            // prompt user for sauce selection:
            pizzaSaucePrompt(scanner,toppings);

            // prompt user for optional sides:
            pizzaSideToppingPrompt(scanner,toppings);

            //pizza
            String pizzaName = "pizza " + pizzaCount;
            Pizza pizza = new Pizza(size,crust,toppings);
            pizza.setName(pizzaName);

            // prompt user for optional stuffed crust:
            pizzaStuffedCrustPrompt(scanner,pizza);

            // add pizza to order
            currentOrder.addItem(pizza,1);
            pizzaCount++;
            System.out.println("successfully added " + pizza.getName() + ".");
            done = true;

        }

    }

    public static String pizzaCrustPrompt (Scanner scanner, String crust){
        boolean done = false;
        while (!done){
            System.out.println(BOLD+"\n"+ICON_LOAF + " Select your crust type: "+RESET);
            System.out.println("1) thin" + ICON_FLATBREAD);
            System.out.println("2) regular" + ICON_BAGUETTE);
            System.out.println("3) thick" + ICON_LOAF);
            System.out.println("4) cauliflower" + ICON_CAULIFLOWER);
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    crust = "thin";
                    done= true;
                    break;
                case "2":
                    crust = "regular";
                    done= true;
                    break;
                case "3":
                    crust = "thick";
                    done= true;
                    break;
                case "4":
                    crust = "cauliflower";
                    done= true;
                    break;
                default:
                    System.out.println(RED+"Invalid choice! Please enter 1, 2, 3 or 4." +RESET);
                    break;
            }
        }
        return crust;

    }

    public static String pizzaSizePrompt (Scanner scanner, String size){
        boolean done = false;
        while (!done){
            System.out.println(BOLD+"\n"+ICON_MEMO+ "Pizza size: "+RESET);
            for (int i = 0; i < pizzaSizes.size(); i++) {
                System.out.println((i+1)+ ") "+pizzaSizes.get(i)+"\"");
            }
            String sizeChoice = scanner.nextLine().trim();

            switch (sizeChoice) {
                case "1":
                    size = pizzaSizes.get(0);
                    done = true;
                    break;
                case "2" :
                    size = pizzaSizes.get(1);
                    done = true;
                    break;
                case "3" :
                    size = pizzaSizes.get(2);
                    done = true;
                    break;
                default :
                    System.out.println(RED+"Invalid choice! Please enter 1, 2, or 3."+ RESET);
                    break;
            }
        }
        return size;

    }

    /**
     * this helper method displays the premium toppings in the array list that's in the parameter,
     * line by line with ascending numbers listed on the left of each premium toppings.
     * @param toppingList key of the premium topping array list used in the premiumToppingList HashMap.
     * @param toppingMenu the name of the hashMap that holds the toppling list.
     */
    public static void displayToppingMenu (Map<String, List<Topping>> toppingMenu,String toppingList){
        for (int i = 0; i < toppingMenu.get(toppingList).size(); i++) {
            System.out.println( i + ") "+toppingMenu.get(toppingList).get(i).getName());
        }
    }

    /**
     * this method prompts to the user to add topping to the currently building pizza
     * @param scanner for user input
     * @param toppings List of topping for the currently building pizza
     */
    public static void pizzaToppingPrompt (Scanner scanner, List<Topping> toppings, String size){
        System.out.println(BOLD + "\n"+ICON_TOMATO + "Toppings: " +RESET);
        int index = 0;
        int counter = 0;
        boolean moveOn =false;
        Topping selectedTopping;
        //set limit to 5 toppings max;
        while (counter < 6 && !moveOn){
            System.out.println("Maximum of 5 toppings per pizza.");
            System.out.println("Current number of toppings:"+counter+"\n");
            System.out.println("1)"+ICON_MEAT+"Meat");
            System.out.println("2)"+ICON_CHEESE+"Cheese");
            System.out.println("3)"+ICON_TOMATO+"Regular Topping");
            System.out.println("4)✅ Done Selecting Toppings ");

            String toppingChoice = scanner.nextLine().trim();

            switch (toppingChoice) {
                case "1":
                    System.out.println("\n" + ICON_MEAT + "Meat: \n");

                    displayToppingMenu(premiumToppingMenu, "meats");
                    String meatChoice = scanner.nextLine().trim();
                    index = paresInt(meatChoice);

                    if (index>= 0 && index < premiumToppingMenu.get("meats").size()) {
                        selectedTopping = premiumToppingMenu.get("meats").get(index);
                        ((PremiumTopping) selectedTopping).setSize(size);
                        toppings.add(selectedTopping);
                        System.out.println("successfully added " + selectedTopping.getName() + ".");
                        extraToppingPrompt(premiumToppingMenu, index, "meats", scanner);

                        counter++;
                    } else System.out.println(RED+"Invalid choice! Please enter a number choice from above."+RESET);


                    break;

                case "2":
                    System.out.println("\n" + ICON_CHEESE + "Cheese: \n");

                    displayToppingMenu(premiumToppingMenu, "cheeses");
                    String cheeseChoice = scanner.nextLine().trim();
                    index = paresInt(cheeseChoice);

                    if (index>= 0 && index < premiumToppingMenu.get("cheeses").size()) {
                        selectedTopping =premiumToppingMenu.get("cheeses").get(index);
                        ((PremiumTopping) selectedTopping).setSize(size);
                        toppings.add(selectedTopping);
                        System.out.println("successfully added " + selectedTopping.getName() + ".");
                        extraToppingPrompt(premiumToppingMenu, index, "cheeses", scanner);

                        counter++;
                    } else System.out.println(RED+"Invalid choice! Please enter a number choice from above."+RESET);


                    break;

                case "3":
                    System.out.println("\n" + ICON_TOMATO + "Regular Topping: \n");

                    displayToppingMenu(includedToppingMenu, "regularToppings");
                    String regularToppingsChoice = scanner.nextLine().trim();
                    index = paresInt(regularToppingsChoice);

                    if (index>= 0 && index < includedToppingMenu.get("regularToppings").size()) {
                        toppings.add(includedToppingMenu.get("regularToppings").get(index));
                        System.out.println("successfully added " + includedToppingMenu.get("regularToppings").get(index).getName() + ".");
                        extraToppingPrompt(includedToppingMenu, index, "regularToppings", scanner);

                        counter++;
                    } else System.out.println(RED+"Invalid choice! Please enter a number choice from above."+RESET);


                    break;

                case "4":
                    moveOn = true;
                    break;

                default:
                    System.out.println(RED+"Invalid choice! Please enter 1, 2, 3 or 4."+RESET);
                    break;
            }

        }
    }

    /**
     * his method prompts to the user to add sauce topping to the currently building pizza
     * @param scanner for user input
     * @param toppings List of topping for the currently building pizza
     */
    public static void pizzaSaucePrompt (Scanner scanner, List<Topping> toppings) {
        boolean done = false;
        while (!done) {
            System.out.println("\n🥫Select sauces: \n");

            displayToppingMenu(includedToppingMenu,"sauces");
            String saucesChoice = scanner.nextLine().trim();
            int index = paresInt(saucesChoice);

            if (index>= 0 && index < includedToppingMenu.get("sauces").size()){
                toppings.add(includedToppingMenu.get("sauces").get(index));
                System.out.println("successfully added "+ includedToppingMenu.get("sauces").get(index).getName()+" sauce.");

                done = true;
            }else System.out.println(RED+"Invalid choice! Please enter a number choice from above."+RESET);

        }
    }

    public static void pizzaSideToppingPrompt (Scanner scanner, List<Topping> toppings){
        boolean done = false;
        while (!done){
            System.out.println(BOLD+ "\nAdd any sides (red pepper / Parmesan)?"+RESET);
            System.out.println("1) yes");
            System.out.println("2) no");
            String sideInput = scanner.nextLine().trim();
            switch (sideInput){
                case "1":
                    System.out.println("\n"+ICON_PEPPER+" Sides: \n");
                    displayToppingMenu(includedToppingMenu,"sides");
                    String sideChoice = scanner.nextLine().trim();
                    int index = paresInt(sideChoice);

                    if (index>= 0 && index < includedToppingMenu.get("sides").size()){
                        toppings.add(includedToppingMenu.get("sides").get(index));
                        System.out.println("successfully added "+ includedToppingMenu.get("sides").get(index).getName()+".");
                        extraToppingPrompt(includedToppingMenu, index,"sides", scanner);
                        done = true;
                    }else System.out.println(RED+ "Invalid choice! Please enter a number choice from above."+RESET);
                    break;

                case "2":
                    done = true;
                    break;

                default:
                    System.out.println(RED + "Invalid choice! Please enter a number choice from above." + RESET);
                    break;
            }


        }
    }

    /**
     * this helper method use to prompt the user for extra toppings, when a topping is selected.
     * @param index input from user that match the index number of the topping of the list in display
     * @param toppingList key of the premium topping array list used in the premiumToppingList HashMap.
     * @param toppingMenu the name of the hashMap that holds the toppling list.
     * @param scanner scanner for user input
     */
    public static void extraToppingPrompt (Map<String, List<Topping>> toppingMenu,int index, String toppingList, Scanner scanner){
        boolean moveOn =false;
        while (!moveOn) {
            System.out.println("Add extra " + toppingMenu.get(toppingList).get(index).getName() + "? " );
            System.out.println("1) yes");
            System.out.println("2) no");
            String extraToppingChoice = scanner.nextLine().trim();
            switch (extraToppingChoice){
                case "1":
                    toppingMenu.get(toppingList).get(index).setExtraTopping();
                    System.out.println("Added extra " + toppingMenu.get(toppingList).get(index).getName()+".");

                    moveOn=true;
                    break;
                case "2":
                    moveOn=true;
                    break;
                default:
                    System.out.println(RED+"Invalid choice! Please enter a number choice from above."+RESET);
                    break;
            }

        }

    }

    public static void pizzaStuffedCrustPrompt(Scanner scanner, Pizza pizza){
        boolean done = false;
        while (!done){
            System.out.println(BOLD + "\nWould you like the pizza with stuffed crust?" + RESET);
            System.out.println("1) yes");
            System.out.println("2) no");
            String input = scanner.nextLine().trim();
            switch (input){
                case "1":
                    pizza.setStuffedCrust(true);
                    done =true;
                    break;

                case "2":
                    pizza.setStuffedCrust(false);
                    done=true;
                    break;

                default:
                    System.out.println(RED+ "Invalid choice! Please enter a number choice from above."+RESET);
                    break;
            }

        }

    }

    /*
    -------------------- Signature Pizza related Method ---------------
     */
    public static void addSignaturePizza(Scanner scanner) {
        System.out.println("\n" + BOLD + "⭐ Signature Pizzas " + ICON_PIZZA + RESET + "\n");
        System.out.println("1) Margherita Pizza (12\", Regular Crust)");
        System.out.println("2) Veggie Pizza (8\", Regular Crust)");
        System.out.println("0) Back to Order Menu");
        System.out.print("Your choice: ");

        String choice = scanner.nextLine().trim();
        Pizza pizza = null;

        switch (choice) {
            case "1":
                List<Topping> margheritaToppings = createSignatureToppings("Margherita", "12");
                pizza = new MargheritaPizza(margheritaToppings);
                break;
            case "2":
                List<Topping> veggieToppings = createSignatureToppings("Veggie", "8");
                pizza = new VeggiePizza(veggieToppings);
                break;
            case "0":
                return;
            default:
                System.out.println(RED + "Invalid choice." + RESET);
                return;
        }

        if (pizza != null) {
            // Allows customization and adds to the order
            customizePizza(scanner, pizza);
        }
    }

    /**
     * Searches the menu maps for a specific Topping by name.
     * Used to build the topping list for signature pizzas.
     */
    private static Topping findToppingByName(String name) {
        // Check included toppings
        for (List<Topping> list : includedToppingMenu.values()) {
            for (Topping t : list) {
                if (t.getName().equalsIgnoreCase(name)) {
                    return t;
                }
            }
        }
        // Check premium toppings
        for (List<Topping> list : premiumToppingMenu.values()) {
            for (Topping t : list) {
                if (t.getName().equalsIgnoreCase(name)) {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * Builds the list of specific Topping objects required for a Signature Pizza.
     */
    private static List<Topping> createSignatureToppings(String pizzaType, String size) {
        List<Topping> toppings = new ArrayList<>();

        // Define the required toppings by name
        String[] requiredToppings;
        if (pizzaType.equals("Margherita")) {
            requiredToppings = new String[]{"Mozzarella", "tomatoes", "basil", "marinara", "olive oil"};
        } else if (pizzaType.equals("Veggie")) {
            requiredToppings = new String[]{"bell peppers", "spinach", "olives", "onions", "marinara", "Mozzarella"};
        } else {
            return toppings;
        }

        for (String name : requiredToppings) {
            Topping t = findToppingByName(name);
            if (t != null) {
                // Set size for premium toppings (Mozzarella) to ensure correct pricing.
                if (t instanceof PremiumTopping) {
                    ((PremiumTopping) t).setSize(size);
                }
                toppings.add(t);
            } else {
                System.err.println("Warning: Required topping not found in menu: " + name);
            }
        }
        return toppings;
    }

    /**
     * Creates a new instance of a Topping based on its current type and name.
     * This prevents multiple pizzas from sharing the same static Topping object.
     */
    private static Topping createToppingInstance(Topping template) {
        String name = template.getName();

        if (template instanceof Meat) {
            // Meat requires name and size (size will be set later if needed)
            return new Meat(name, "");
        } else if (template instanceof Cheese) {
            // Cheese requires name and size (size will be set later if needed)
            return new Cheese(name, "");
        } else if (template instanceof Sauce) {
            return new Sauce(name);
        } else if (template instanceof RegularTopping) {
            return new RegularTopping(name);
        } else if (template instanceof Side) {
            return new Side(name);
        }
        // Default or base Topping, although your menu seems to use subclasses.
        return template;
    }

    public static void customizePizza(Scanner scanner, Pizza pizza) {
        System.out.println("\n" + BOLD + "Customizing " + pizza.getName() + " (" + pizza.getSize() + "-inch)" + RESET);
        boolean done = false;

        while (!done) {
            // Display current toppings for context
            List<Topping> currentToppings = pizza.getToppings();
            String toppingList = currentToppings.isEmpty() ? "None" : currentToppings.stream().map(Topping::getName).collect(Collectors.joining(", "));
            System.out.println("\nCurrent Toppings: " + toppingList);
            System.out.println("\n1) Remove Topping");
            System.out.println("2) Add New Topping");
            System.out.println("3) Finish Customization and Add to Order");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    removeToppingPrompt(scanner, pizza);
                    break;
                case "2":
                    addToppingPrompt(scanner, pizza);
                    break;
                case "3":
                    // Finalize the order
                    pizza.setName("pizza " + pizzaCount); // Rename for final receipt
                    currentOrder.addItem(pizza, 1);
                    pizzaCount++;
                    System.out.println("Successfully added " + pizza.getName() + " to the order.");
                    done = true;
                    break;
                default:
                    System.out.println(RED + "Invalid choice." + RESET);
            }
        }
    }

    /**
     * Prompts the user to select and remove a topping from the pizza.
     */
    public static void removeToppingPrompt(Scanner scanner, Pizza pizza) {
        List<Topping> toppings = pizza.getToppings();

        if (toppings.isEmpty()) {
            System.out.println(YELLOW + "This pizza has no toppings to remove." + RESET);
            return;
        }

        boolean done = false;
        while (!done) {
            System.out.println(BOLD + "\n➖ Select a topping to REMOVE:" + RESET);

            // List toppings with indices starting from 1
            for (int i = 0; i < toppings.size(); i++) {
                String extra = toppings.get(i).isExtraTopping() ? " (Extra)" : "";
                System.out.printf("%d) %s%s%n", (i + 1), toppings.get(i).getName(), extra);
            }
            System.out.println("0) Cancel removal");
            System.out.print("Enter choice number: ");

            String choiceInput = scanner.nextLine().trim();
            int choice = paresInt(choiceInput);

            if (choice == 0) {
                return; // Exit removal
            }

            // Adjust index to 0-based
            int indexToRemove = choice - 1;

            if (indexToRemove >= 0 && indexToRemove < toppings.size()) {
                Topping removedTopping = toppings.remove(indexToRemove);
                System.out.println(GREEN + "Removed " + removedTopping.getName() + " from the pizza." + RESET);
                done = true;
            } else {
                System.out.println(RED + "Invalid choice! Please enter a number from the list or 0." + RESET);
            }
        }
    }

    /**
     * Prompts the user to add a single topping (Meat, Cheese, Regular, or Sauce)
     * to an existing pizza object.
     */
    public static void addToppingPrompt(Scanner scanner, Pizza pizza) {
        String size = pizza.getSize();
        List<Topping> toppings = pizza.getToppings();

        boolean done = false;
        while (!done) {
            System.out.println(BOLD + "\n➕ Add a New Topping:" + RESET);
            System.out.println("1)" + ICON_MEAT + " Meat");
            System.out.println("2)" + ICON_CHEESE + " Cheese");
            System.out.println("3)" + ICON_TOMATO + " Regular Topping");
            System.out.println("4) 🥫 Sauce");
            System.out.println("0) 🔙 Back to Customization Menu");
            System.out.print("Your choice: ");

            String toppingCategoryChoice = scanner.nextLine().trim();
            int index;
            Map<String, List<Topping>> menuMap = null;
            String menuKey = null;

            switch (toppingCategoryChoice) {
                case "1":
                    menuMap = premiumToppingMenu;
                    menuKey = "meats";
                    break;
                case "2":
                    menuMap = premiumToppingMenu;
                    menuKey = "cheeses";
                    break;
                case "3":
                    menuMap = includedToppingMenu;
                    menuKey = "regularToppings";
                    break;
                case "4":
                    menuMap = includedToppingMenu;
                    menuKey = "sauces";
                    break;
                case "0":
                    return;
                default:
                    System.out.println(RED + "Invalid choice! Please enter 0-4." + RESET);
                    continue;
            }

            // Display topping menu
            if (menuMap != null && menuKey != null) {
                System.out.println("\nSelect " + menuKey + ":");
                displayToppingMenu(menuMap, menuKey);
                System.out.print("Enter topping number to add: ");
                String toppingChoice = scanner.nextLine().trim();
                index = paresInt(toppingChoice);

                if (index >= 0 && index < menuMap.get(menuKey).size()) {
                    // Get the template topping from the static menu map
                    Topping selectedToppingTemplate = menuMap.get(menuKey).get(index);

                    Topping newToppingInstance = createToppingInstance(selectedToppingTemplate);

                    //If it's Premium, set size for pricing on the new instance
                    if (newToppingInstance instanceof PremiumTopping) {
                        ((PremiumTopping) newToppingInstance).setSize(size);
                    }

                    // Prompt for extra
                    if (menuKey.equals("meats") || menuKey.equals("cheeses") || menuKey.equals("regularToppings")) {

                        if (promptForExtraTopping(scanner, newToppingInstance)) {
                            newToppingInstance.setExtraTopping();
                            System.out.println("Added extra " + newToppingInstance.getName() + ".");
                        }
                    }

                    // Add the new Topping instance to the pizza's list
                    toppings.add(newToppingInstance);
                    System.out.println(GREEN + "Successfully added " + newToppingInstance.getName() + " to the pizza." + RESET);
                    done = true;
                } else {
                    System.out.println(RED + "Invalid topping selection." + RESET);
                }
            }
        }
    }

    /**
     * Prompts the user for extra status on a newly created topping instance.
     */
    private static boolean promptForExtraTopping (Scanner scanner, Topping topping){
        while (true) {
            System.out.println("Add extra " + topping.getName() + "? ");
            System.out.println("1) yes");
            System.out.println("2) no");
            String extraToppingChoice = scanner.nextLine().trim();

            switch (extraToppingChoice){
                case "1":
                    return true;
                case "2":
                    return false;
                default:
                    System.out.println(RED+"Invalid choice! Please enter a number choice from above."+RESET);
                    break;
            }
        }
    }

    /*
    -----------------------  Drink and GarlicKnots methods -----------------
     */
    public static void addDrink(Scanner scanner){
        boolean done = false;
        String size = "";
        String name;
        while (!done) {
            System.out.println("\n" + BOLD + BLUE + "➕ Add drink " + ICON_DRINK + RESET + "\n");
            for (int i = 0; i < menu.get("drinks").size(); i++) {
                System.out.println( i + ") "+menu.get("drinks").get(i).getName());
            }
            String meatChoice = scanner.nextLine().trim();
            int index = paresInt(meatChoice);

            if (index>= 0 && index < menu.get("drinks").size()) {
                size = drinkSizePrompt(scanner, size);
                name = menu.get("drinks").get(index).getName();
                Drink selectDrink = new Drink(name,size);
                currentOrder.addItem(selectDrink,1);

                System.out.println("successfully added a " +selectDrink.getSize()+" "+ selectDrink.getName() + ".");
                done = true;
            } else System.out.println(RED+"Invalid choice! Please enter a number choice from above."+RESET);


        }

    }

    public static String drinkSizePrompt (Scanner scanner,String size){
        boolean done = false;
        while (!done){
            System.out.println(BOLD+"\n"+ICON_MEMO+ "Drink size: "+RESET);
            for (int i = 0; i < drinkSizes.size(); i++) {
                System.out.println((i+1)+ ") "+drinkSizes.get(i));
            }
            String sizeChoice = scanner.nextLine().trim();

            switch (sizeChoice) {
                case "1":
                    size = drinkSizes.get(0);
                    done = true;
                    break;
                case "2" :
                    size = drinkSizes.get(1);
                    done = true;
                    break;
                case "3" :
                    size = drinkSizes.get(2);
                    done = true;
                    break;
                default :
                    System.out.println(RED+"Invalid choice! Please enter 1, 2, or 3."+ RESET);
                    break;
            }
        }
        return size;

    }

    public static void addGarlicKnots(Scanner scanner){
        boolean done = false;
        String name;
        while (!done) {
            System.out.println("\n" + BOLD + YELLOW + "➕ Add Garlic Knots" + ICON_BREAD + RESET + "\n");
            for (int i = 0; i < menu.get("garlicKnots").size(); i++) {
                System.out.println( i + ") "+menu.get("garlicKnots").get(i).getName());
            }
            String meatChoice = scanner.nextLine().trim();
            int index = paresInt(meatChoice);

            if (index>= 0 && index < menu.get("garlicKnots").size()) {
                name = menu.get("garlicKnots").get(index).getName();
                GarlicKnot selectGarlicKnot = new GarlicKnot(name);

                System.out.println("How many "+ selectGarlicKnot.getName() + " do you want to add to the order?");
                String quantityInput = scanner.nextLine().trim();
                int quantity = paresInt(quantityInput);

                if (quantity >=1 && quantity < 30){
                    currentOrder.addItem(selectGarlicKnot,quantity);
                    System.out.println("successfully added "+quantity +" "+ selectGarlicKnot.getName() + ".");
                    done = true;
                } else if (quantity >= 30) {
                    System.out.println("You are ordering more than 29 " + selectGarlicKnot.getName()
                            +". Are you sure you want to proceed with this quantity?\n"+"1) yes \n"+"2) no");
                    String choice = scanner.nextLine().trim();
                    if (choice.equals("1")){
                        currentOrder.addItem(selectGarlicKnot,quantity);
                        System.out.println("successfully added "+quantity +" "+ selectGarlicKnot.getName() + ".");
                        done = true;
                    }else System.out.println("Returning back to '➕ Add Garlic Knots' Screen.");
                } else System.out.println(RED+"Invalid number! Please enter a number quantity of at least 1."+RESET);

            } else System.out.println(RED+"Invalid choice! Please enter a number choice from above."+RESET);

        }
    }

    /*
   ----------------------- checkOut / cancel Related methods -----------------
    */
    public static void checkOut(Scanner scanner) {

        if (!isValidOrder()) {
            System.out.println("\n🚫 ERROR: Invalid Order! You must purchase at least one pizza, OR a drink/garlic knots.");
            // Prompt user to add a required item or cancel
            return;
        }

        if (currentOrder.getOrderItems().isEmpty()) {
            System.out.println("Your cart is empty. Please add items before checking out.");
            return;
        }

        double subTotal = 0.0;

        System.out.println("\n" + BOLD + "--- " + ICON_CART + " ORDER SUMMARY ---" + RESET);

        // Display Individual Items and Calculate Subtotal

        for (int i = 0; i < currentOrder.getOrderItems().size(); i++) {
            MenuItem item = currentOrder.getOrderItems().get(i);
            String name = item.getName();
            double itemPrice = item.calculatePrice();

            // If the item is a Drink, cast and show the size
            String size = "";
            if (item instanceof Drink) {
                size = "(" + ((Drink) item).getSize() + ") ";
            }

            // Display item row
            System.out.printf("%d. %s%s ................... $%.2f%n",
                    (i + 1), size, name, itemPrice);

            if (item instanceof Pizza){
                System.out.println(item);
            }

            // Accumulate the price
            subTotal += itemPrice;
        }

        // Calculate Taxes and Final Total

        // Define your tax rate (e.g., 7% sales tax)
        final double TAX_RATE = 0.07;
        double taxAmount = subTotal * TAX_RATE;
        double finalTotal = subTotal + taxAmount;

        // Display Price Breakdown
        System.out.println("-------------------------------------");
        System.out.printf(BOLD + "Subtotal: ..................... $%.2f%n" + RESET, subTotal);
        System.out.printf("Tax (%.0f%%): .................... $%.2f%n", TAX_RATE * 100, taxAmount);
        System.out.println("-------------------------------------");
        System.out.printf(BOLD + "TOTAL DUE: .................... $%.2f%n" + RESET, finalTotal);
        System.out.println("-------------------------------------");
        System.out.println("\n" + BOLD + "Thank you for your order!" + RESET);

        String choice = "";
        while (!choice.equals("0")) {
            System.out.println(BOLD+"\n"+ICON_CART + "Checkout"+RESET);
            System.out.println("1) "+ICON_CART+"Confirm Order");
            System.out.println("0) ❌ Cancel Order  ");
            System.out.print("Your choice: ");

            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    String receiptText = buildReceiptString();
                // Save the file
                    FileManager.createReceiptFile(receiptText);
                // Clear the order and go back to the home screen
                    currentOrder = new Order(generateOrderId(orderCount++)); // Reset Order
                    System.out.println(GREEN+"Receipt has been successfully created. Returning to Home Screen..."+RESET);
                    return;

                case "0" :
                    cancelOrder();
                    return;

                default:
                    System.out.println("Invalid choice! Please enter 1 or 0.");
                    break;
            }
        }


    }

    /**
     * Builds the complete formatted receipt text for saving to a file.
     * This method is essentially a non-printing version of the order summary.
     * @return The complete receipt as a single String.
     */
    public static String buildReceiptString() {

        // Use StringBuilder to collect all lines of the receipt
        StringBuilder receiptBuilder = new StringBuilder();
        List<MenuItem> orderItems = currentOrder.getOrderItems();

        // Set a constant width for the entire line (Name + Dots + Price)
        final int LINE_WIDTH = 35;
        double subTotal = 0.0;

        //Header
        receiptBuilder.append("\n" + BOLD + "--- " + ICON_CART + " ORDER SUMMARY ---" + RESET + "\n");
        receiptBuilder.append(String.format("Order ID: %s\n", currentOrder.getOrderID()));

        //Item List
        for (int i = 0; i < orderItems.size(); i++) {
            MenuItem item = orderItems.get(i);
            String name = item.getName();
            double itemPrice = item.calculatePrice();

            //Drink size
            String size = "";
            if (item instanceof Drink) {
                size = "(" + ((Drink) item).getSize() + ") ";
            }

            // display name with list number on the left
            String fullItemName = String.format("%d. %s%s", (i + 1), size, name);

            // Format the price string shown 2 decimal place
            String priceString = String.format("$%.2f", itemPrice);

            // Calculate the dots in line
            int usedLength = fullItemName.length() + priceString.length();
            int dotCount = LINE_WIDTH - usedLength;
            if (dotCount < 1) { dotCount = 1; }
            String dots = ".".repeat(dotCount);

            // add Pizza details
            if (item instanceof Pizza){
                receiptBuilder.append(item);
            }

            // Append main item row (Name + Dots + Price)
            receiptBuilder.append(String.format("%s %s %s%n", fullItemName, dots, priceString));

            subTotal += itemPrice;
        }

        // Price Breakdown

        final double TAX_RATE = 0.07;
        double taxAmount = subTotal * TAX_RATE;
        double finalTotal = subTotal + taxAmount;

        receiptBuilder.append("-------------------------------------\n");
        receiptBuilder.append(String.format(BOLD + "Subtotal: %s $%.2f%n" + RESET, ".".repeat(LINE_WIDTH - 12), subTotal));
        receiptBuilder.append(String.format("Tax (%.0f%%): %s $%.2f%n", TAX_RATE * 100, ".".repeat(LINE_WIDTH - 11), taxAmount));
        receiptBuilder.append("-------------------------------------\n");
        receiptBuilder.append(String.format(BOLD + "TOTAL DUE: %s $%.2f%n" + RESET, ".".repeat(LINE_WIDTH - 13), finalTotal));
        receiptBuilder.append("-------------------------------------\n");
        receiptBuilder.append("\n" + BOLD + "Thank you for your order!" + RESET + "\n");

        return receiptBuilder.toString();
    }

    /**
     * Checks if the order meets the minimum purchase requirements:
     * 1. If 1 or more pizzas are present, the order is valid.
     * 2. If 0 pizzas are present, a drink OR garlic knots must be present.
     */
    public static boolean isValidOrder() {
        boolean hasPizza = false;
        boolean hasNonPizzaItem = false;

        for (MenuItem item : currentOrder.getOrderItems()) {
            // Check for Pizza
            if (item instanceof Pizza) {
                hasPizza = true;
            }

            // Check for required non-pizza items (GarlicKnots or Drink)
            if (item instanceof Drink || item instanceof GarlicKnot) {
                hasNonPizzaItem = true;
            }
        }

        // If the order has any pizza, it is valid.
        if (hasPizza) {
            return true;
        }

        //  If the order has NO pizza, it must have at least one required non-pizza item.
        if (!hasPizza && hasNonPizzaItem) {
            return true;
        }

        // If neither of the above conditions are met.
        return false;
    }

    public static void cancelOrder(){
        currentOrder.getOrderItems().clear();
        currentOrder = new Order(generateOrderId(orderCount++));
        System.out.println("Your order has been canceled. Thank you for dining with us!");

    }

}
