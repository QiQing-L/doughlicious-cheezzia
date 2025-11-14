package com.pluralsight.doughliciouscheezzia.pos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

    public static int paresInt (String s){
        int choiceNumber = -1;
        try {
            choiceNumber = Integer.parseInt(s);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid choice! Please enter a number choice. ");
        }
        return choiceNumber;
    }

    public static String currentDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");

        LocalDateTime now = LocalDateTime.now();

        return now.format(formatter);
    }

    public static String currentOrderDateTime(){
        // Pattern: dd (day), MM (month), yyyy (year), h (12-hour hour), mm (minute), a (AM/PM)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a");

        LocalDateTime now = LocalDateTime.now();

        return now.format(formatter);
    }

    public static String generateOrderId(int orderCount) {
        // Date time format for 24-hour clock
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        String prefix = "ORD-";

        // Get the current count and then increment it for the next order
        int currentCount = orderCount++;

        // format current count to be 3 digits long.
        String sequence = String.format("%03d", currentCount);

        return prefix + timestamp + sequence;
    }




}
