import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Calendar;

public class PLAY_GAME 
{
    static final int INDEX_OF_COUNTRY = 0;
    static final int INDEX_OF_CAPITAL = 1;
    static final int INDEX_OF_GUESSING_COUNT = 3;
    static int lifeCount = 10;
    static int indexOfDraw = 0;
    static long timeBegin;
    static int guessingCount = 0; 
    static Set<String> notInWord = new HashSet<String>();

    public static void main(String[] args) 
    {
        
    }

    public static void initGame()
    {
        clearScreen();
        notInWord.clear();
        lifeCount = 10;
        
        ArrayList<String> countryAndCapital = new ArrayList<String>();
        ArrayList<ArrayList<String>> listCountrysAndCapitals = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> listWin = new ArrayList<ArrayList<String>>();
        String capital = "";
        
        Scanner dataFromFile = FILE_OPERATION.open_file("countries_and_capitals.txt");
        listCountrysAndCapitals = FILE_OPERATION.ScannertoArray(dataFromFile, false);
        countryAndCapital = PREPARE_TO_GAME.randomCapitalsAndCountry(listCountrysAndCapitals);
        capital = countryAndCapital.get(INDEX_OF_CAPITAL);
        char[] capitalDash = PREPARE_TO_GAME.makeDashWord(capital);

        Scanner dataFromListWin = FILE_OPERATION.open_file("win_list.txt");
        listWin = FILE_OPERATION.ScannertoArray(dataFromListWin, true);

        startGame(countryAndCapital, capitalDash, listWin);
    }

    public static void winGame(ArrayList<String> countryAndCapital, ArrayList<ArrayList <String>> listWin)
    {
        Calendar c = Calendar.getInstance();
        System.out.print("Please insert your name: ");
        Scanner inputUser = new Scanner(System.in);  // Create a Scanner object
        String name = inputUser.nextLine();  // Read user input
        String date = String.valueOf(c.getTime());
        String capital = countryAndCapital.get(INDEX_OF_CAPITAL);
        long timeEnd = stopTime(timeBegin);
        String stringTimeEnd = String.valueOf(timeEnd);
        String stringGuessingCount = String.valueOf(guessingCount);
        ArrayList<String> newScoreUser = new ArrayList<String>();
        newScoreUser.add(name);
        newScoreUser.add(date);
        newScoreUser.add(stringTimeEnd);
        newScoreUser.add(stringGuessingCount);
        newScoreUser.add(capital);
        listWin = newHighScore(listWin, newScoreUser);
        FILE_OPERATION.saveToFile(FILE_OPERATION.arrayToString(listWin), "win_list.txt");
        gameWinScreen(timeEnd, guessingCount);
        Scanner dataFromListWin = FILE_OPERATION.open_file("win_list.txt");  
        System.out.print(FILE_OPERATION.arrayToString(FILE_OPERATION.ScannertoArray(dataFromListWin, true)));
    }

    public static void startGame(ArrayList<String> countryAndCapital, char[] capitalDash, ArrayList<ArrayList <String>> listWin)
    {
        timeBegin = startTime();
        boolean gameWin = true;
        while (lifeCount > 0)
        {
            gameWin = play_game(countryAndCapital, capitalDash);
            if (gameWin)
            {
                winGame(countryAndCapital, listWin);
                break;
            }
            
        }
        if (!gameWin)
        {
            Scanner dataFromListWin = FILE_OPERATION.open_file("win_list.txt");  
            System.out.print(FILE_OPERATION.arrayToString(FILE_OPERATION.ScannertoArray(dataFromListWin, true)));
            gameLoseScreen();
        }
        
    }

    public static boolean play_game(ArrayList<String> countryAndCapital, char[] dashedWord)
    {
        ArrayList<String> draws = new ArrayList<String>();
        Scanner drawFile = FILE_OPERATION.open_file("draws.txt");
        draws = FILE_OPERATION.draw(drawFile);
        boolean foundLetter = false;
        boolean foundWord = false;
        String capital = countryAndCapital.get(INDEX_OF_CAPITAL);
        capital = capital.toUpperCase();
        displayUser(countryAndCapital, dashedWord, lifeCount);
        System.out.print("Please insert word or leter: ");
        Scanner inputUser = new Scanner(System.in);  // Create a Scanner object
        String letterOrWord = inputUser.nextLine();  // Read user input
        letterOrWord = letterOrWord.toUpperCase();
        if (letterOrWord.length() > 2)
        {
            foundWord = FIND_LETTER.checkWordInText(capital, letterOrWord);
            foundLetter = true;
            if (!foundWord)
            {
                lifeCount -= 1;
                indexOfDraw++;
            }
            else
            {
                return true;
            }
        }
        else
        {
            foundLetter = FIND_LETTER.checkLetterInText(capital, letterOrWord);
            dashedWord = FIND_LETTER.checkLetterInText(capital, dashedWord, letterOrWord);
            foundWord = hasWord(dashedWord);
            if (foundWord)
            {
                return true;
            }
        }


        if(foundLetter == false)
        {
            if (notInWord.contains(letterOrWord))
            {
                
            }
            else
            {
                System.out.println(draws.get(indexOfDraw));
                lifeCount -= 1;
                indexOfDraw++;
                notInWord.add(letterOrWord);

                
            }
            
            guessingCount += 1;
        }

        return false;


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

    public static ArrayList<ArrayList<String>> newHighScore(ArrayList<ArrayList<String>> listWin, ArrayList <String> newTheBestUser)
    {
        int countGessing = Integer.parseInt(newTheBestUser.get(INDEX_OF_GUESSING_COUNT));
        int checkCount;
        int index = 0;

        for (ArrayList<String> row: listWin)
        {
            checkCount = Integer.parseInt(row.get(INDEX_OF_GUESSING_COUNT));
            if (countGessing < checkCount)
            {
                listWin.add(index, newTheBestUser);
                break;
            }
            index++;
        }
        if (listWin.size() > 10)
        {
            listWin.remove(10); //remove excess element
        }

        
        return listWin;

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

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}