package com.pluralsight.doughliciouscheezzia.pos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import static com.pluralsight.doughliciouscheezzia.pos.Utility.currentDateTime;

public class FileManager {

    // Constant for the directory where receipts will be saved
    private static final String RECEIPTS_FOLDER = "receipts";

    // Constant for the required filename format
    private static final String FILENAME_FORMAT = "yyyyMMdd-HHmmss";

    /**
     * Generates a filename based on the current date and time.
     * @return The formatted filename string (yyyyMMdd-hhmmss.txt - i.e. 20230329-121523.txt).
     */
    private static String generateFilename() {
        String timestamp = currentDateTime();
        return timestamp + ".txt";
    }

    /**
     * Creates a receipt file with the order details and saves it to the 'receipts' folder.
     * * @param orderDetails A string containing all the formatted order information (the output of checkOut).
     * @return true if the file was successfully created, false otherwise.
     */
    public static boolean createReceiptFile(String orderDetails) {

        //check if receipts file exists
        File filePath = new File(RECEIPTS_FOLDER);
        if (!filePath.exists()) {
            filePath.mkdirs(); // Create the file path
        }

        // Generate file name
        String filename = generateFilename();
        File receiptFile = new File(filePath, filename);

        // Write the order details to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile))) {
            writer.write(orderDetails);
            System.out.println("✅ Receipt successfully saved to: " + receiptFile.getAbsolutePath());
            return true;
        } catch (IOException e) {
            System.err.println("❌ ERROR: Could not write receipt file: " + e.getMessage());
            return false;
        }
    }
}
