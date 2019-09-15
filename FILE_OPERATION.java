import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class FILE_OPERATION 
{
    public static void main(String[] args) 
    {
        
        open_file("countries_and_capitals.txt");
        
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

}