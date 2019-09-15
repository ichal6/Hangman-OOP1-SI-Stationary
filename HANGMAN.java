public class HANGMAN 
{
    public static void main(String[] args) 
    {
        HANGMAN.play_game();
    }

    public static void play_game()
    {
        FILE_OPERATION.open_file("countries_and_capitals.txt");
    }
}