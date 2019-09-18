import java.util.Scanner;
import java.util.ArrayList;

public class PLAY_GAME 
{
    static final int INDEX_OF_COUNTRY = 0;
    static final int INDEX_OF_CAPITAL = 1;
    static int lifeCount = 10;
    static long timeBegin;
    static int guessingCount = 0; 
    static ArrayList<String> notInWord = new ArrayList<String>();

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
        timeBegin = startTime();
        boolean gameWin = true;
        while (lifeCount > 0)
        {
            gameWin = play_game(countryAndCapital, capitalDash);
            if (gameWin)
            {
                long timeEnd = stopTime(timeBegin);
                gameWinScreen(timeEnd, guessingCount);
            }
            
        }
        if (!gameWin)
        {
            gameLoseScreen();
        }
        
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
            notInWord.add(letterOrWord);
            guessingCount += 1;
        }

        if(foundWord)
        {
            return true;
        }
        else
        {
            lifeCount -= 1;
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

    public static long startTime()
    {
        long timeStart = System.nanoTime();
        return timeStart;
    }

    public static long stopTime(long timeStart)
    {
        long timeTotal = (System.nanoTime() - timeStart)/1_000_000_000;
        return timeTotal;
    }

    public static void displayHint(ArrayList<String> arrayCapitalCountry)
    {
        System.out.print("It's the capital of: ");
        System.out.println(arrayCapitalCountry.get(INDEX_OF_COUNTRY));
    }

    public static void displayUser(ArrayList<String> arrayCapitalCountry, char[] dashedWord, int lifeCount)
    {
        String dashedString = new String(dashedWord);
        System.out.print("Wrong word:");
        for(String notIn: notInWord)
        {
            System.out.print(" " + notIn);
        }
        System.out.println("\nCapital - " + dashedString + "\tlife - " + lifeCount);
        if (lifeCount == 1)
        {
            displayHint(arrayCapitalCountry);
        }
    }


    public static void gameWinScreen(long timeEnd, int guessingCount)
    {
        System.out.println("Congratulations! You Win! Your time is " + timeEnd 
                            + " second and you guessed after " + guessingCount + " letters" );
    }

    public static void gameLoseScreen()
    {
        System.out.println("I\'m so sorry, but you died... Try in next life." );
    }
}