package lib;

import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant
{
    static String restaurantName = "Restaurant";
    static int numberOfOrders = 0;
    static int numberOfCustomers = 0;
    static ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
    static ArrayList<Order> orders = new ArrayList<Order>();

    static void displayMenu()
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

    static void displayOrders()
    {
        if (orders.size() == 0)
        {
            System.out.println(ConsoleColors.RED + "There`s no orders yet" + ConsoleColors.RESET);
            return;
        }
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

    static void sortOrders(ArrayList<Order> orders)
    {
        for (int i = 0; i < orders.size(); i++)
        {
            for (int j = i + 1; j < orders.size(); j++)
            {
                if (orders.get(i).getTotal() > orders.get(j).getTotal())
                {
                    Order temp = orders.get(i);
                    orders.set(i, orders.get(j));
                    orders.set(j, temp);
                }
            }
        }

    }

    static void displayCustomers()
    {
        if (Main.customers.size() == 0)
        {
            System.out.println(ConsoleColors.RED + "There`s no customers yet" + ConsoleColors.RESET);
            return;
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("| ID | Customer Name | Number of Orders | Total Spent |");
        System.out.println("-------------------------------------------------------");
        for (User user : Main.allUsers)
        {
            if (user instanceof Customer)
            {
                Customer customer = (Customer) user;
                String customerName = Sys.spaces(customer.getName(), 13 - customer.getName().length());
                String numberOfOrders = Sys.spaces(Integer.toString(customer.getNumberOfOrders()),
                        16 - Integer.toString(customer.getNumberOfOrders()).length());
                String totalSpent = Sys.spaces(Double.toString(customer.getTotalSpent()), 11 - Double.toString(customer.getTotalSpent()).length());
                System.out.println("| " + customer.getId() + " | " + customerName + " | " + numberOfOrders + " | " + totalSpent + " |");
            }
        }

        System.out.println("-------------------------------------------------------");
    }

    static void setTestMenu()
    {
        menu.add(new MenuItem("Pizza", 10.0));
        menu.add(new MenuItem("Burger", 5.0));
        menu.add(new MenuItem("Fries", 2.0));
        menu.add(new MenuItem("Coke", 1.0));
    }

}

class MenuItem implements Serializable
{
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

    MenuItem(String Name, double Price)
    {
        this.Name = Name;
        this.Price = Price;
    }

}