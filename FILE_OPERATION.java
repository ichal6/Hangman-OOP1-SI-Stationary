import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class FILE_OPERATION 
{
    public static void main(String[] args) 
    {
        
        Scanner data_from_file = open_file("countries_and_capitals.txt");
        ScannertoArray(data_from_file);
        
    }

    public static Scanner open_file(String filename)
    {
        Scanner data = null;

        try 
        {
            data = new Scanner(new File(filename));
        }
        catch ( IOException e) 
        {
            System.out.println("Sorry but I was unable to open your data file");
            e.printStackTrace();
            System.exit(0);
        }

        return data;
    }

    public static ArrayList<ArrayList<String>> ScannertoArray(Scanner data_from_file)
    {
        ArrayList<ArrayList<String>> Array_countries_and_capitals = new ArrayList<ArrayList<String>>();
        String new_row = "";
        String country, capitals;
        while(data_from_file.hasNextLine()) 
        {
            ArrayList<String> Array_row = new ArrayList<String>();
            new_row = data_from_file.nextLine();
            String[] parts = new_row.split("\\|");
            country = parts[0].split(" ")[0];
            capitals = parts[1].split(" ")[1];
            Array_row.add(country);
            Array_row.add(capitals);
            Array_countries_and_capitals.add(Array_row);
           
        }
        return Array_countries_and_capitals;
    }
}