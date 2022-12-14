package lib;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends User
{
    static Scanner input = new Scanner(System.in);

    public Admin(String username, String password)
    {
        super(username, password);
    }

    public void addNewDish()
    {
        System.out.println(ConsoleColors.BLUE + "-------------------------------------------------------------" + ConsoleColors.RESET);
        System.out.print("Enter Dish name: ");
        String name = input.nextLine();
        double price;
        try
        {
            System.out.print("Enter " + name + " price: ");
            price = input.nextDouble();
        }
        catch (InputMismatchException e)
        {
            System.out.println(ConsoleColors.RED + "Please Enter a Number!" + ConsoleColors.RESET);
            input.nextLine();
            return;
        }
        input.nextLine();
        if (price < 0)
        {
            System.out.println("Invalid price");
            return;
        }
        MenuItem dish = new MenuItem(name, price);
        for (MenuItem item : Restaurant.menu)
        {
            if (item.getName().equals(dish.getName()))
            {
                System.out.println(ConsoleColors.RED + "Dish already exists" + ConsoleColors.RESET);
                return;
            }
        }
        Restaurant.menu.add(dish);
        Database.saveMenu();
        System.out.println(ConsoleColors.GREEN + name + " added successfully" + ConsoleColors.RESET);

    }

    public void updateDish()
    {
        System.out.println(ConsoleColors.BLUE + "-------------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println("Update Dish: ");
        System.out.print("Enter Dish name: ");
        String name = input.nextLine();
        int choice;
        double newPrice;
        try
        {
            System.out.println("1. Update name\n2. Update price");
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
            System.out.print("Enter new name: ");
            String newName = input.nextLine();
            for (MenuItem item : Restaurant.menu)
            {
                if (item.getName().equals(newName))
                {
                    System.out.println(ConsoleColors.RED + "Can`t have two dishes of same name in menu" + ConsoleColors.RESET);
                    return;
                }
            }
            for (MenuItem item : Restaurant.menu)
            {
                if (item.getName().equals(name))
                {
                    item.setName(newName);
                    Database.saveMenu();
                    System.out.println(ConsoleColors.GREEN + "Dish name updated successfully" + ConsoleColors.RESET);
                    return;
                }
            }
            System.out.println(ConsoleColors.RED + "Dish Not Found" + ConsoleColors.RESET);
        }
        else if (choice == 2)
        {
            try
            {
                System.out.print("Enter new price: ");
                newPrice = input.nextDouble();
            }
            catch (InputMismatchException e)
            {
                System.out.println(ConsoleColors.RED + "Please Enter a Number!" + ConsoleColors.RESET);
                input.nextLine();
                return;
            }
            input.nextLine();
            if (newPrice < 0)
            {
                System.out.println(ConsoleColors.RED + "Invalid price" + ConsoleColors.RESET);
                return;
            }
            for (MenuItem item : Restaurant.menu)
            {
                if (item.getName().equals(name))
                {
                    item.setPrice(newPrice);
                    Database.saveMenu();
                    System.out.println(ConsoleColors.GREEN + "Dish price updated successfully" + ConsoleColors.RESET);
                    return;
                }
            }
            System.out.println(ConsoleColors.RED + "Dish Not Found" + ConsoleColors.RESET);
        }
        else
        {
            System.out.println(ConsoleColors.RED + "Invalid choice" + ConsoleColors.RESET);
        }

    }

    public void removeDish()
    {
        System.out.println(ConsoleColors.BLUE + "-------------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println("Delete Dish: ");
        System.out.print("Enter Dish name: ");
        String name = input.nextLine();
        for (MenuItem item : Restaurant.menu)
        {
            if (item.getName().equals(name))
            {
                Restaurant.menu.remove(item);
                Database.saveMenu();
                System.out.println(ConsoleColors.GREEN + "Dish deleted successfully" + ConsoleColors.RESET);
                return;
            }
        }
        System.out.println(ConsoleColors.RED + "Dish Not Found" + ConsoleColors.RESET);

    }

    public void changeRestaurantName()
    {
        System.out.println(ConsoleColors.BLUE + "-------------------------------------------------------------" + ConsoleColors.RESET);
        System.out.print("Enter new name: ");
        String newName = input.nextLine();
        Restaurant.restaurantName = newName;
        System.out.println(ConsoleColors.GREEN + "Restaurant name updated successfully" + ConsoleColors.RESET);
    }
}
