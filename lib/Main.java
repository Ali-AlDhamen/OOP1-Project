package lib;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<User> allUsers = new ArrayList<User>();
    public static Scanner input = new Scanner(System.in);
    public static User currentUser = null;

    public static void main(String[] args)
    {

        Sys.runApp();
        do
        {
            int inputChoice;
            try
            {
                System.out.println(ConsoleColors.BLUE + "******* Welcome to the " + Restaurant.restaurantName + " ********" + ConsoleColors.RESET);
                System.out.println("1. Login\n2. Create Account\n3. Exit");
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
                    Authentication.login();
                    break;
                case 2:
                    Authentication.createAccount();
                    break;
                case 3:
                    System.out.println(ConsoleColors.BLUE + "Thank you for using our app" + ConsoleColors.RESET);
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice" + ConsoleColors.RESET);
            }

            if (currentUser instanceof Admin)
            {
                Sys.adminSystem();
            }
            else if (currentUser instanceof Customer)
            {
                Sys.customerSystem();
            }

        } while (true);
    }
}
