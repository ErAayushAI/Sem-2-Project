package Validation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    /**
     * To check user input is valid or not.
     *
     * @param scanner Object for user inputs
     * @param message print to console for user
     * @return int
     */
    public static int getValidInt(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    /**
     * To check user input is valid or not.
     *
     * @param scanner Object for user inputs
     * @param message print to console for user
     * @return double
     */
    public static double getValidDouble(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    /**
     * To check user input is valid or not.
     *
     * @param scanner Object for user inputs
     * @param message print to console for user
     * @return boolean
     */
    public static boolean getValidBoolean(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.next().trim().toLowerCase();
            if (input.equals("true") || input.equals("yes") || input.equals("1")) {
                return true;
            } else if (input.equals("false") || input.equals("no") || input.equals("0")) {
                return false;
            } else {
                System.out.println("❌ Invalid input! Please enter true/false (or yes/no).");
            }
        }
    }

    /**
     * Check user input is valid or not.
     *
     * @param scanner Object for user input
     * @param message print to console for user
     * @return String
     */
    public static String getValidString(Scanner scanner, String message) {
        scanner.nextLine();
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("❌ Name cannot be empty. Try again.");
            }
        }
    }

    /**
     * Validate Contact Number of Service.
     *
     * @param number contact number
     * @return true if it's valid
     */
    public static boolean isValidContactNumber(long number) {

        // Convert to String to check length and digits
        String numStr = Long.toString(number);

        // Check if the number has exactly 10 digits or All the numbers are not 0
        return numStr.length() != 10 || numStr.equals("0000000000");
    }

    // Safely get integer input from user
    public static int getChoice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter your choice (integer only): ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }
}
