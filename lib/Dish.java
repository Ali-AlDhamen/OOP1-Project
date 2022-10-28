package lib;

import java.io.Serializable;

public class Dish implements Serializable
{
    private double dishPrice;
    private String dishName;
    private int dishAmount;

    Dish(String dishName, double dishPrice, int dishAmount)
    {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishAmount = dishAmount;
    }

    public double getDishPrice()
    {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice)
    {
        this.dishPrice = dishPrice;
    }

    public String getDishName()
    {
        return dishName;
    }

    public void setDishName(String dishName)
    {
        this.dishName = dishName;
    }

    public int getDishAmount()
    {
        return dishAmount;
    }

    public void setDishAmount(int dishAmount)
    {
        this.dishAmount = dishAmount;
    }

}
