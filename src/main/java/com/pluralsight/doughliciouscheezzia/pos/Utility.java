package com.pluralsight.doughliciouscheezzia.pos;

public class Utility {

    public static int paresInt (String s){
        int choiceNumber = -1;
        try {
            // Attempt to parse the String into an int
            choiceNumber = Integer.parseInt(s);

        } catch (NumberFormatException e) {
            //  Handle the error if the String can't be parsed
            System.out.println("Error: Invalid choice! Please enter a number choice. ");
        }
        return choiceNumber;
    }

}
