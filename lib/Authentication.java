package lib;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Authentication
{
    static Scanner input = new Scanner(System.in);
    static Console console = System.console();

    public static void createAccount()
    {
        System.out.println(ConsoleColors.BLUE + "-------------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println("Create Account: ");
        System.out.print("Enter username: ");
        String username = input.nextLine();
        for (Customer customer : Main.customers)
        {
            if (customer.getUsername().equals(username))
            {
                System.out.println(ConsoleColors.RED + "Username already exists" + ConsoleColors.RESET);
                return;
            }
        }
        System.out.print("Enter password: ");
        char[] hiddenPassword = console.readPassword();
        String password = new String(hiddenPassword);
        System.out.print("Enter your full name: ");
        String name = input.nextLine();
        Customer customer = new Customer(username, password, name);
        Main.customers.add(customer);
        Main.allUsers.add(customer);
        Main.currentUser = customer;
        Database.saveAccounts();
        Database.saveIdNumbers();
        System.out.println(ConsoleColors.GREEN + "Account created successfully" + ConsoleColors.RESET);
    }

    public static void login()
    {
        System.out.println(ConsoleColors.BLUE + "-------------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println("Login: ");
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        char[] hiddenPassword = console.readPassword();
        String password = new String(hiddenPassword);

        for (User user : Main.allUsers)
        {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                System.out.println(ConsoleColors.GREEN + "logged in successfully" + ConsoleColors.RESET);
                Main.currentUser = user;
                return;
            }
        }

        System.out.println(ConsoleColors.RED + "Invalid username or password" + ConsoleColors.RESET);
    }

    public static void changeInfo(User currentUser)
    {
        System.out.println(ConsoleColors.BLUE + "-------------------------------------------------------------" + ConsoleColors.RESET);
        int choice;
        try
        {
            System.out.println("Change Info: ");
            System.out.println("1. Update username\n2. Update password");
            System.out.print(ConsoleColors.PURPLE + "Enter your choice: " + ConsoleColors.RESET);
            choice = input.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.println(ConsoleColors.RED + "Please Enter a Number!" + ConsoleColors.RESET);
            input.nextLine();
            return;
        }
        input.nextLine();
        if (choice == 1)
        {
            System.out.print("Enter new username: ");
            String username = input.nextLine();
            for (User user : Main.allUsers)
            {
                if (user.getUsername().equals(username))
                {
                    System.out.println(ConsoleColors.RED + "Username already exists" + ConsoleColors.RESET);
                    return;
                }
            }
            Main.currentUser.setUsername(username);
            System.out.println(ConsoleColors.GREEN + "Username updated successfully" + ConsoleColors.RESET);
        }
        else if (choice == 2)
        {
            System.out.print("Enter new password: ");
            char[] hiddenPassword = console.readPassword();
            String password = new String(hiddenPassword);
            Main.currentUser.setPassword(password);
            System.out.println(ConsoleColors.GREEN + "Password updated successfully" + ConsoleColors.RESET);
        }
        Database.saveAccounts();
    }
}
