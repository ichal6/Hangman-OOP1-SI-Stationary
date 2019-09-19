import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class PREPARE_TO_GAME 
{
    public static void main(String[] args) 
    {
        Scanner data_from_file = FILE_OPERATION.open_file("countries_and_capitals.txt");
        System.out.println(randomCapitalsAndCountry(FILE_OPERATION.ScannertoArray(data_from_file, false)));
        
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