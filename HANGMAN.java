import java.util.Scanner;
import java.util.ArrayList;
public class HANGMAN 
{
    public static void main(String[] args) 
    {
        System.out.println("Welcome to Hangman! Save the world from SKYNET!");
        mainMenu();
    }

    public static void mainMenu()
    {
        System.out.println("(S)tart\n(H)igh scores\n(E)xit");
        Scanner inputUser = new Scanner(System.in);  // Create a Scanner object
        char choose_user = inputUser.next().charAt(0);  // Read user input
        choose_user = Character.toUpperCase(choose_user);
        boolean isRun = true;
        while (isRun)
        {
            switch(choose_user)
            {
                case 'S':
                    PLAY_GAME.initGame();
                    break;
                case 'H':
                    Scanner dataFromListWin = FILE_OPERATION.open_file("win_list.txt");  
                    System.out.print(FILE_OPERATION.arrayToString(FILE_OPERATION.ScannertoArray(dataFromListWin, true)));
                    break;
                case 'E':
                    isRun = false;
                    break;
                default:
                    System.out.println("You press wrong input. Please try again");
            }
        }

    }


}