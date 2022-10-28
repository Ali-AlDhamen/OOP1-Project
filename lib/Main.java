package lib;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<User> allUsers = new ArrayList<User>();
    static Scanner input = new Scanner(System.in);
    static User admin = new Admin("admin", "admin12345");
    static User currentUser = null;

    public static void main(String[] args)
    {
        Sys.runApp();
        do
        {
            System.out.println("******* Welcome to the " + Restaurant.restaurantName + " ********");
            System.out.println("1. Login\n2. Create Account\n3. Exit");
            System.out.print(ConsoleColors.PURPLE + "Enter your choice: " + ConsoleColors.RESET);
            int inputChoice = input.nextInt();
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
