package Validation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AreaInputValidation
{
    public static int getValidInt(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a valid integer.");
                sc.nextLine(); // clear buffer
            }
        }
    }

    public static double getValidDouble(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a valid number.");
                sc.nextLine(); // clear buffer
            }
        }
    }


    public static boolean getValidBoolean(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.next().trim().toLowerCase();
            if (input.equals("true") || input.equals("yes") || input.equals("1")) {
                return true;
            } else if (input.equals("false") || input.equals("no") || input.equals("0")) {
                return false;
            } else {
                System.out.println("❌ Invalid input! Please enter true/false (or yes/no).");
            }
        }
    }

    public static String getValidString(Scanner sc, String message) {
        sc.nextLine(); // clear buffer before reading string
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("❌ Name cannot be empty. Try again.");
            }
        }
    }
}
