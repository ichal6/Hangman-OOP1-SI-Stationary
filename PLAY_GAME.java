import java.util.Scanner;

public class PLAY_GAME 
{
    public static void main(String[] args) 
    {
        String capital = "łobuz";
        char[] dashed = PREPARE_TO_GAME.dashWord(capital);
        play_game(capital, dashed);
        System.out.println(dashed);
        play_game(capital, dashed);
        System.out.println(dashed);
    }

    public static void play_game(String capital, char[] dashedWord)
    {
        boolean foundLetter = false;
        boolean foundWord = false;
        System.out.println("Welcome to Hangman!");
        System.out.print("Please insert word or leter: ");
        Scanner inputUser = new Scanner(System.in);  // Create a Scanner object
        String letterOrWord = inputUser.nextLine();  // Read user input
        if (letterOrWord.length() > 2)
        {
            foundWord = FIND_LETTER.checkWordInText(capital, letterOrWord);
            System.out.println(foundWord);
        }
        else
        {
            foundLetter = FIND_LETTER.checkLetterInText(capital, dashedWord, letterOrWord);
            System.out.println(foundLetter);
        }
        //inputUser.close();
    }
}