package lib;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer extends User
{
    static Scanner input = new Scanner(System.in);
    private int numberOfOrders = 0;
    private double totalSpent = 0;
    private String name;

    public double getTotalSpent()
    {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent)
    {
        this.totalSpent = totalSpent;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getNumberOfOrders()
    {
        return numberOfOrders;
    }

    public Customer(String username, String password, String name)
    {
        super(username, password);
        this.name = name;
    }

    public void order()
    {
        System.out.println(ConsoleColors.BLUE + "-------------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println("Order: ");
        Restaurant.displayMenu();
        int numberOfDishes, dishNumber, quantity;
        try
        {
            System.out.print("Enter the number of dishes you want to order: ");
            numberOfDishes = input.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.println(ConsoleColors.RED + "Please Enter a Number!" + ConsoleColors.RESET);
            input.nextLine();
            return;
        }
        input.nextLine();
        Order order = new Order(this.getId());
        for (int i = 0; i < numberOfDishes; i++)
        {
            try
            {
                System.out.print("Enter the number of the dish: ");
                dishNumber = input.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println(ConsoleColors.RED + "Please Enter a Number!" + ConsoleColors.RESET);
                input.nextLine();
                i--;
                continue;
            }

            if (dishNumber > Restaurant.menu.size())
            {
                System.out.println(ConsoleColors.RED + "Invalid dish number" + ConsoleColors.RESET);
                i--;
                continue;
            }
            input.nextLine();
            String dishName = Restaurant.menu.get(dishNumber - 1).getName();
            double dishPrice = Restaurant.menu.get(dishNumber - 1).getPrice();
            try
            {
                System.out.print("Enter the quantity of " + dishName + ": ");
                quantity = input.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println(ConsoleColors.RED + "Please Enter a Number!" + ConsoleColors.RESET);
                input.nextLine();
                i--;
                continue;
            }
            if (quantity < 0)
            {
                System.out.println(ConsoleColors.RED + "Invalid quantity" + ConsoleColors.RESET);
                i--;
                continue;
            }
            order.Cart.add(new Dish(dishName, dishPrice, quantity));
        }
        Restaurant.orders.add(order);
        this.numberOfOrders++;
        order.setTotal(Bill.calculateBill(order));
        this.totalSpent += order.getTotal();
        Database.saveIdNumbers();
        Database.saveOrders();
        Database.saveAccounts();
        System.out.println(ConsoleColors.GREEN + "Order placed successfully" + ConsoleColors.RESET);
        Bill.displayBill(order);
    }

    public void displayMyOrders()
    {
        if (this.totalSpent == 0)
        {
            System.out.println(ConsoleColors.RED + "You have not placed any orders yet" + ConsoleColors.RESET);
            return;
        }
        for (Order order : Restaurant.orders)
        {
            if (order.getCustomerId().equals(this.getId()))
            {
                System.out.println("-------------------------------------------------------");
                System.out.printf("| Order ID : %s,    %s       |\n", order.getOrderId(), order.getOrderDate());
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
    }

}
