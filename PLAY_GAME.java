import java.util.Scanner;
import java.util.ArrayList;

public class PLAY_GAME 
{
    static final int INDEX_OF_CAPITAL = 1;
    static int lifeCount = 10;
    public static void main(String[] args) 
    {
        
        String capital = "łobuz";
        char[] dashed = PREPARE_TO_GAME.makeDashWord(capital);
    }

    public static void initGame()
    {
        lifeCount = 10;
        ArrayList<String> countryAndCapital = new ArrayList<String>();
        
        Scanner data_from_file = FILE_OPERATION.open_file("countries_and_capitals.txt");
        countryAndCapital = PREPARE_TO_GAME.randomCapitalsAndCountry(FILE_OPERATION.ScannertoArray(data_from_file));
        char[] capitalDash = PREPARE_TO_GAME.makeDashWord(countryAndCapital.get(INDEX_OF_CAPITAL));
        startGame(countryAndCapital, capitalDash);
    }

    public static void startGame(ArrayList<String> countryAndCapital ,char[] capitalDash)
    {
        boolean gameWin = true;
        while (lifeCount > 0)
        {
            play_game(countryAndCapital, capitalDash);
            if (gameWin)
            {
                //gameWinScreen();
            }
            
        }
        //gameLoseScreen();
    }

    public static boolean play_game(ArrayList<String> countryAndCapital, char[] dashedWord)
    {
        boolean foundLetter = false;
        boolean foundWord = false;
        String capital = countryAndCapital.get(INDEX_OF_CAPITAL);
        displayUser(countryAndCapital, dashedWord, lifeCount);
        System.out.print("Please insert word or leter: ");
        Scanner inputUser = new Scanner(System.in);  // Create a Scanner object
        String letterOrWord = inputUser.nextLine();  // Read user input
        if (letterOrWord.length() > 2)
        {
            foundWord = FIND_LETTER.checkWordInText(capital, letterOrWord);
        }
        else
        {
            foundLetter = FIND_LETTER.checkLetterInText(capital, letterOrWord);
            dashedWord = FIND_LETTER.checkLetterInText(capital, dashedWord, letterOrWord);
            foundWord = hasWord(dashedWord);
        }


        if(foundLetter == false)
        {
            lifeCount -= 1;
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

    public static void displayUser(ArrayList<String> arrayCapitalCountry, char[] dashedWord, int lifeCount)
    {
        String dashedString = new String(dashedWord);
        System.out.println("Capital - " + dashedString + "\tlife - " + lifeCount);
        if (lifeCount == 1)
        {
            displayHint(arrayCapitalCountry);
        }
    }
}