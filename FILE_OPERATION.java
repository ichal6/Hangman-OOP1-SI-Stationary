import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class FILE_OPERATION 
{
    public static void main(String[] args) 
    {
        
        Scanner data_from_file = open_file("countries_and_capitals.txt");
        ScannertoArray(data_from_file, false);
        
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

    public static ArrayList<ArrayList<String>> ScannertoArray(Scanner data_from_file, boolean listWin)
    {
        ArrayList<ArrayList<String>> Array_countries_and_capitals = new ArrayList<ArrayList<String>>();
        String new_row = "";
        String country, capitals;
        while(data_from_file.hasNextLine()) 
        {
            ArrayList<String> Array_row = new ArrayList<String>();
            new_row = data_from_file.nextLine();
            String[] parts = new_row.split("\\|");
            if(listWin)
            {
                Array_row.add(parts[0].substring(0, parts[0].length() - 1)); //remove last char
                Array_row.add(parts[1].substring(1, parts[1].length() - 1)); //remove first and last char
                Array_row.add(parts[2].substring(1, parts[2].length() - 1)); //remove first and last char
                Array_row.add(parts[3].substring(1, parts[3].length() - 1)); //remove first and last char
                Array_row.add(parts[4].substring(1, parts[4].length())); //remove first char
            }
            else
            {
                country = parts[0].substring(0, parts[0].length() - 1); //remove last char
                capitals = parts[1].substring(1, parts[1].length()); //remove first char
                Array_row.add(country);
                Array_row.add(capitals);
                Array_countries_and_capitals.add(Array_row);
            }
            
           
        }
        System.out.println(Array_countries_and_capitals);
        return Array_countries_and_capitals;
    }
}