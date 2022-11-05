package lib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable
{
    private static final long serialVersionUID = 203L;
    private String customerId;
    private String orderId;
    private Date orderDate;
    private double total;
    public static int idNumber = 1;
    public ArrayList<Dish> Cart;

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public Order(String customerId)
    {
        this.customerId = customerId;
        this.orderDate = new Date();
        this.Cart = new ArrayList<Dish>();
        generateId();
        Restaurant.numberOfOrders++;
    }

    public void generateId()
    {
        this.orderId = String.valueOf(idNumber);
        idNumber += 1;
    }
}
