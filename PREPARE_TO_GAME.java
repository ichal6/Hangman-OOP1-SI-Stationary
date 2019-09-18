import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class PREPARE_TO_GAME 
{
    public static void main(String[] args) 
    {
        ArrayList<ArrayList<String>> Array_countries_and_capitals = new ArrayList<ArrayList<String>>();
        Scanner data_from_file = FILE_OPERATION.open_file("countries_and_capitals.txt");
        System.out.println(randomCapitalsAndCountry(Array_countries_and_capitals = FILE_OPERATION.ScannertoArray(data_from_file)));
        
    }

    public static ArrayList<String> randomCapitalsAndCountry(ArrayList<ArrayList<String>> arrayCountriesAndCapitals)
    {
        ArrayList<String> arrayRow = new ArrayList<String>();
        Random rand = new Random();
        int lengthList = arrayCountriesAndCapitals.size();
        int index = rand.nextInt(lengthList);
        arrayRow = arrayCountriesAndCapitals.get(index);
        return arrayRow;
    }

    public static char[] makeDashWord(String capital)
    {
        int lengthWord = capital.length();
        int iter = 0;
        String capitalDash = "";
        while (iter < lengthWord)
        {
            capitalDash = capitalDash.concat("_");
            iter += 1;
        }
        char[] capitalDashArray = capitalDash.toCharArray();

        return capitalDashArray;
    }
}