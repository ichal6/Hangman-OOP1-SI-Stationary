import java.util.Scanner;
import java.util.ArrayList;
public class HANGMAN 
{
    public static void main(String[] args) 
    {
        mainMenu();
    }

    public static void play_game()
    {
        FILE_OPERATION.open_file("countries_and_capitals.txt");
    }

    public static int mainMenu()
    {
        System.out.println("Welcome to Hangman! Save the world from SKYNET!");
        Scanner inputUser = new Scanner(System.in);  // Create a Scanner object
        char choose_user = inputUser.next().charAt(0);  // Read user input
        switch(choose_user)
        {
            case 'S':
                System.out.println("Heya! Nacisnąłeś start!");
                initGame();
                break;
            case 'H':
                break;
            case 'E':
                break;
            default:
                System.out.println("You press wrong input. Please try again");
                mainMenu();
        }
        return 1;
    }

    public static void initGame()
    {
        ArrayList<String> countryAndCapital = new ArrayList<String>();
        int INDEX_OF_CAPITAL = 1;
        Scanner data_from_file = FILE_OPERATION.open_file("countries_and_capitals.txt");
        countryAndCapital = PREPARE_TO_GAME.randomCapitalsAndCountry(FILE_OPERATION.ScannertoArray(data_from_file));
        char[] capitalDash = PREPARE_TO_GAME.makeDashWord(countryAndCapital.get(INDEX_OF_CAPITAL));
        

    }

    public static long startTime()
    {
        long timeStart = System.nanoTime();
        return timeStart;
    }

    public static long StopTime(long timeStart)
    {
        long timeTotal = (System.nanoTime() - timeStart)/1_000_000_000;
        return timeTotal;
    }

    public static void displayUser()
    {
        ;
    }
}