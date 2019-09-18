import java.util.Scanner;
import java.util.ArrayList;

public class PLAY_GAME 
{
    public static void main(String[] args) 
    {
        
        String capital = "łobuz";
        char[] dashed = PREPARE_TO_GAME.makeDashWord(capital);
        play_game(capital, dashed);
        System.out.println(dashed);
        play_game(capital, dashed);
        System.out.println(dashed);
    }

    public static void initGame()
    {
        int lifeCount = 10;
        ArrayList<String> countryAndCapital = new ArrayList<String>();
        int INDEX_OF_CAPITAL = 1;
        Scanner data_from_file = FILE_OPERATION.open_file("countries_and_capitals.txt");
        countryAndCapital = PREPARE_TO_GAME.randomCapitalsAndCountry(FILE_OPERATION.ScannertoArray(data_from_file));
        char[] capitalDash = PREPARE_TO_GAME.makeDashWord(countryAndCapital.get(INDEX_OF_CAPITAL));
        while (lifeCount > 0)
        {
            play_game(countryAndCapital.get(1),capitalDash);
            lifeCount -= 1;
        }
    }

    public static boolean play_game(String capital, char[] dashedWord)
    {
        boolean foundLetter = false;
        boolean foundWord = false;
        System.out.print("Please insert word or leter: ");
        Scanner inputUser = new Scanner(System.in);  // Create a Scanner object
        String letterOrWord = inputUser.nextLine();  // Read user input
        if (letterOrWord.length() > 2)
        {
            foundWord = FIND_LETTER.checkWordInText(capital, letterOrWord);
        }
        else
        {
            dashedWord = FIND_LETTER.checkLetterInText(capital, dashedWord, letterOrWord);
            foundWord = hasWord(dashedWord);
        }

        if(foundWord)
        {
            return true;
        }
        else
        {
            return false;
        }
        //inputUser.close();
    }

    public static boolean hasWord(char[] checkWord)
    {
        for (char sign: checkWord)
        {
            if(sign == '_')
            {
                return false;
            }
        }
        return true;
    }

    public static void displayHint(ArrayList<String> arrayCapitalCountry)
    {
        System.out.print("It's the capital of: ");
        System.out.println(arrayCapitalCountry.get(0));
    }
}