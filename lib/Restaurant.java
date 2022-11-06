package lib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Restaurant
{
    public static String restaurantName = "Restaurant";
    public static int numberOfOrders = 0;
    public static int numberOfCustomers = 0;
    public static ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
    public static ArrayList<Order> orders = new ArrayList<Order>();

    public static void displayMenu()
    {
        if (menu.size() == 0)
        {
            System.out.println(ConsoleColors.RED + "There`s no items in the menu" + ConsoleColors.RESET);
            return;
        }
        int counter = 1;
        System.out.println("--------------------------------");
        System.out.println("| No. | Dish Name | Dish Price |");
        System.out.println("--------------------------------");
        for (MenuItem item : menu)
        {
            String dishNumber = Sys.spaces(String.valueOf(counter), 3 - String.valueOf(counter).length());
            String dishName = Sys.spaces(item.getName(), 9 - item.getName().length());
            String dishPrice = Sys.spaces(String.valueOf(item.getPrice()), 9 - String.valueOf(item.getPrice()).length());
            System.out.println("| " + dishNumber + " | " + dishName + " | " + dishPrice + " |");
            counter++;
        }
        System.out.println("--------------------------------");
    }

    public static void displayOrders()
    {
        if (orders.size() == 0)
        {
            System.out.println(ConsoleColors.RED + "There`s no orders yet" + ConsoleColors.RESET);
            return;
        }
        sortOrders(orders);
        String customerName = "";
        for (Order order : Restaurant.orders)
        {
            for (Customer customer : Main.customers)
            {

                if (customer.getId().equals(order.getCustomerId()))
                {
                    customerName = customer.getName();
                    break;
                }
            }
            System.out.println("-------------------------------------------------------");
            System.out.printf("| Order ID: %s | Customer Name: %s                    |\n", order.getOrderId(), customerName);
            System.out.println("-------------------------------------------------------");
            System.out.println("| Dish Name | Unit Price | Unit Quantity | Line Total |");
            for (Dish dish : order.Cart)
            {
                String dishName = Sys.spaces(dish.getDishName(), 9 - dish.getDishName().length());
                String unitPrice = Sys.spaces(Double.toString(dish.getDishPrice()), 10 - Double.toString(dish.getDishPrice()).length());
                String unitQuantity = Sys.spaces(Integer.toString(dish.getDishAmount()), 13 - Integer.toString(dish.getDishAmount()).length());
                String lineTotal = Sys.spaces(Double.toString(dish.getDishPrice() * dish.getDishAmount()),
                        10 - Double.toString(dish.getDishPrice() * dish.getDishAmount()).length());
                System.out.println("-------------------------------------------------------");
                System.out.printf("| %s | %s | %s | %s |\n", dishName, unitPrice, unitQuantity, lineTotal);
                System.out.println("-------------------------------------------------------");
            }
            System.out.println("| Total: " + order.getTotal() + "$                                        |");
            System.out.println("-------------------------------------------------------");
        }

    }

    public static void sortOrders(ArrayList<Order> orders)
    {
        int sortChoice = 0;
        do
        {
            try
            {
                System.out.println("1. Sort by Order ID\n2. Sort by Customer Name\n3. Sort by Total");
                System.out.print(ConsoleColors.PURPLE + "Enter your choice: " + ConsoleColors.RESET);
                sortChoice = Main.input.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println(ConsoleColors.RED + "Please Enter a Number!" + ConsoleColors.RESET);
                Main.input.nextLine();
                continue;
            }
            switch (sortChoice)
            {
                case 1:
                    orders.sort((o1, o2) -> o1.getOrderId().compareTo(o2.getOrderId()));
                    break;
                case 2:
                    orders.sort((o1, o2) -> o1.getCustomerId().compareTo(o2.getCustomerId()));
                    break;
                case 3:
                    orders.sort((o1, o2) -> Double.compare(o1.getTotal(), o2.getTotal()));
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice" + ConsoleColors.RESET);
                    break;

            }
        } while (sortChoice != 1 && sortChoice != 2 && sortChoice != 3);

    }

    public static void displayCustomers()
    {
        if (Main.customers.size() == 0)
        {
            System.out.println(ConsoleColors.RED + "There`s no customers yet" + ConsoleColors.RESET);
            return;
        }
        sortCustomers(Main.customers);
        System.out.println("-------------------------------------------------------");
        System.out.println("| ID | Customer Name | Number of Orders | Total Spent |");
        System.out.println("-------------------------------------------------------");
        for (Customer customer : Main.customers)
        {

            String customerName = Sys.spaces(customer.getName(), 13 - customer.getName().length());
            String numberOfOrders = Sys.spaces(Integer.toString(customer.getNumberOfOrders()),
                    16 - Integer.toString(customer.getNumberOfOrders()).length());
            String totalSpent = Sys.spaces(Double.toString(customer.getTotalSpent()), 11 - Double.toString(customer.getTotalSpent()).length());
            System.out.println("| " + customer.getId() + " | " + customerName + " | " + numberOfOrders + " | " + totalSpent + " |");

        }

        System.out.println("-------------------------------------------------------");
    }

    public static void sortCustomers(ArrayList<Customer> customer)
    {
        int sortChoice = 0;
        do
        {
            try
            {
                System.out.println("1. Sort by ID\n2. Sort by Name\n3. Sort by Number of Orders\n4. Sort by Total Spent");
                System.out.print(ConsoleColors.PURPLE + "Enter your choice: " + ConsoleColors.RESET);
                sortChoice = Main.input.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println(ConsoleColors.RED + "Please Enter a Number!" + ConsoleColors.RESET);
                Main.input.nextLine();
                continue;
            }
            switch (sortChoice)
            {
                case 1:
                    Main.customers.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
                    break;
                case 2:
                    Main.customers.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                    break;
                case 3:
                    Main.customers.sort((o1, o2) -> Integer.compare(o2.getNumberOfOrders(), o1.getNumberOfOrders()));
                    break;
                case 4:
                    Main.customers.sort((o1, o2) -> Double.compare(o2.getTotalSpent(), o1.getTotalSpent()));
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice" + ConsoleColors.RESET);
                    break;

            }
        } while (sortChoice != 1 && sortChoice != 2 && sortChoice != 3 && sortChoice != 4);

    }
}

class MenuItem implements Serializable
{
    private static final long serialVersionUID = 201L;
    private double Price;
    private String Name;

    public double getPrice()
    {
        return Price;
    }

    public void setPrice(double price)
    {
        Price = price;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public MenuItem(String Name, double Price)
    {
        this.Name = Name;
        this.Price = Price;
    }

}