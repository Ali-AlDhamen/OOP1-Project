package lib;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Sys
{
    static Scanner input = new Scanner(System.in);

    static public void runApp()
    {
        Audio.playAudio("data/AOT.wav");
        Database.getAccounts();
        Database.getIdNumbers();
        Database.getMenu();
        Database.getOrders();
        for (User user : Main.allUsers)
        {
            if (user instanceof Customer)
            {
                Main.customers.add((Customer) user);
            }
        }
    }

    static public void customerSystem()
    {

        System.out.println(ConsoleColors.BLUE + "-------------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println("Welcome to the customer system!");
        while (true)
        {
            int inputChoice;
            try
            {
                System.out.println("1. Order\n2. View Orders\n3. View Profile\n4. Update Profile\n5. Logout");
                System.out.print(ConsoleColors.PURPLE + "Enter your choice: " + ConsoleColors.RESET);
                inputChoice = input.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println(ConsoleColors.RED + "Please Enter a Number!" + ConsoleColors.RESET);
                input.nextLine();
                continue;
            }
            switch (inputChoice)
            {
                case 1:
                    ((Customer) Main.currentUser).order();
                    break;
                case 2:
                    ((Customer) Main.currentUser).displayMyOrders();
                    break;
                case 3:
                    ((Customer) Main.currentUser).accountInfo();
                    break;
                case 4:
                    Authentication.changeInfo(Main.currentUser);
                    break;
                case 5:
                    Main.currentUser = null;
                    System.out.println(ConsoleColors.GREEN + "Logged out successfully" + ConsoleColors.RESET);
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice" + ConsoleColors.RESET);
            }
        }

    }

    static public void adminSystem()
    {
        System.out.println(ConsoleColors.BLUE + "-------------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println("Welcome to the admin system!");
        while (true)
        {
            int choice;
            try
            {
                System.out.println(
                        "1. Add Food\n2. Remove Food\n3. Update Food\n4. Update Profile\n5. Update Restaurant Name\n6. View Orders\n7. View Customers\n8. Display menu\n9. Account Info\n10. Logout");
                System.out.print(ConsoleColors.PURPLE + "Enter your choice: " + ConsoleColors.RESET);
                choice = input.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println(ConsoleColors.RED + "Please Enter a Number!" + ConsoleColors.RESET);
                input.nextLine();
                continue;
            }
            input.nextLine();
            switch (choice)
            {
                case 1:
                    ((Admin) Main.currentUser).addNewDish();
                    break;
                case 2:
                    ((Admin) Main.currentUser).removeDish();
                    break;
                case 3:
                    ((Admin) Main.currentUser).updateDish();
                    break;
                case 4:
                    Authentication.changeInfo(Main.currentUser);
                    break;
                case 5:
                    ((Admin) Main.currentUser).changeRestaurantName();
                    break;
                case 6:
                    Restaurant.displayOrders();
                    break;
                case 7:
                    Restaurant.displayCustomers();
                    break;
                case 8:
                    Restaurant.displayMenu();
                    break;
                case 9:
                    ((Admin) Main.currentUser).accountInfo();
                    break;
                case 10:
                    Main.currentUser = null;
                    System.out.println(ConsoleColors.GREEN + "Logged out successfully" + ConsoleColors.RESET);
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice" + ConsoleColors.RESET);
                    break;
            }
        }
    }

    public static String spaces(String s, int spaces)
    {

        String newString = "";
        if (spaces % 2 != 0)
        {
            spaces += 1;
        }
        for (int i = 0; i < spaces / 2; i++)
        {
            newString += " ";
        }
        newString += s;
        for (int i = 0; i < spaces / 2; i++)
        {
            newString += " ";
        }

        return newString;

    }
}
