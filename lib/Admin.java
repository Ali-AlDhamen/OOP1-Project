package lib;

import java.util.Scanner;

public class Admin extends User
{
    static Scanner input = new Scanner(System.in);

    Admin(String username, String password)
    {
        super(username, password);
    }

    void addNewDish()
    {
        System.out.println("-------------------------------------------------------------");
        System.out.print("Enter Dish name: ");
        String name = input.nextLine();
        System.out.print("Enter " + name + " price: ");
        double price = input.nextDouble();
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
                System.out.println("Dish already exists");
                return;
            }
        }
        Restaurant.menu.add(dish);
        Database.saveMenu();
        System.out.println(ConsoleColors.GREEN + name + " added successfully" + ConsoleColors.RESET);

    }

    void updateDish()
    {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Update Dish: ");
        System.out.print("Enter Dish name: ");
        String name = input.nextLine();
        System.out.println("1. Update name\n2. Update price");
        System.out.print(ConsoleColors.PURPLE + "Enter your choice: " + ConsoleColors.RESET);
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 1)
        {
            System.out.print("Enter new name: ");
            String newName = input.nextLine();
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
            System.out.print("Enter new price: ");
            double newPrice = input.nextDouble();
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

    void removeDish()
    {
        System.out.println("-------------------------------------------------------------");
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

    void changeRestaurantName()
    {
        System.out.println("-------------------------------------------------------------");
        System.out.print("Enter new name: ");
        String newName = input.nextLine();
        Restaurant.restaurantName = newName;
        System.out.println(ConsoleColors.GREEN + "Restaurant name updated successfully" + ConsoleColors.RESET);
    }
}
