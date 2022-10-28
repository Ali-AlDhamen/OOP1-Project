package lib;

import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.io.*;
import java.util.ArrayList;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;

public class Database
{
    static void saveIdNumbers()
    {
        try
        {
            FileWriter myWriter = new FileWriter("idNumbers.txt");
            myWriter.write(String.valueOf(User.idNumber) + "," + String.valueOf(Order.idNumber));
            myWriter.close();

        }
        catch (IOException e)
        {
            System.out.println(ConsoleColors.RED + "An error occurred." + ConsoleColors.RESET);
            e.printStackTrace();
        }

    }

    static void saveOrders()
    {
        try
        {
            FileOutputStream writeData = new FileOutputStream("orders.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(Restaurant.orders);
            writeStream.flush();
            writeStream.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    static void saveMenu()
    {
        try
        {
            FileOutputStream writeData = new FileOutputStream("menu.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(Restaurant.menu);
            writeStream.flush();
            writeStream.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    static void saveAccounts()
    {
        try
        {
            FileOutputStream writeData = new FileOutputStream("peopledata.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(Main.allUsers);
            writeStream.flush();
            writeStream.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    static void getOrders()
    {
        try
        {
            FileInputStream readData = new FileInputStream("orders.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            Restaurant.orders = (ArrayList<Order>) readStream.readObject();
            readStream.close();

        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    static void getMenu()
    {
        try
        {
            FileInputStream readData = new FileInputStream("menu.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            Restaurant.menu = (ArrayList<MenuItem>) readStream.readObject();
            readStream.close();

        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    static void getAccounts()
    {
        try
        {
            FileInputStream readData = new FileInputStream("peopledata.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            Main.allUsers = (ArrayList<User>) readStream.readObject();
            readStream.close();

        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    static void getIdNumbers()
    {
        String data = "";
        try
        {
            File myObj = new File("idNumbers.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                data += myReader.nextLine();

            }
            myReader.close();
            String[] tokens = data.split(",");
            User.idNumber = Integer.parseInt(tokens[0]);
            Order.idNumber = Integer.parseInt(tokens[1]);

        }
        catch (FileNotFoundException e)
        {
            System.out.println(ConsoleColors.RED + "An error occurred." + ConsoleColors.RESET);
            e.printStackTrace();
        }

    }

}