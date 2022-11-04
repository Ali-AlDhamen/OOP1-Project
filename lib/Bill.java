package lib;

public class Bill
{
    public static void displayBill(Order order)
    {
        System.out.println("-------------------------------------------------------");
        System.out.println("| Order ID:  " + order.getOrderId() + "                                        |");
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
        System.out.println("| Total: " + order.getTotal() + "$                                       |");
        System.out.println("-------------------------------------------------------");

    }

    public static double calculateBill(Order order)
    {
        double total = 0;
        for (Dish dish : order.Cart)
        {
            total += dish.getDishPrice() * dish.getDishAmount();
        }
        return total;

    }
}
