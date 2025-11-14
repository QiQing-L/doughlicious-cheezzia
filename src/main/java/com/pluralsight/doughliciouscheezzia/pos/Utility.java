package com.pluralsight.doughliciouscheezzia.pos;

public class Utility {

    public static int paresInt (String s){
        int choiceNumber = -1;
        try {
            // 2. Attempt to parse the String into an int
            choiceNumber = Integer.parseInt(s);

            System.out.println("You successfully entered the number: " + choiceNumber);

        } catch (NumberFormatException e) {
            // 3. Handle the error if the String can't be parsed
            System.out.println("Error: Invalid choice! Please enter a number choice. ");
        }
        return choiceNumber;
    }

}
