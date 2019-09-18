public class FIND_LETTER 
{
    public static void main(String[] args) 
    {
        HANGMAN.play_game();
    }

    public static boolean checkWordInText(String capital, String letterOrWord)
    {
        if (capital.equals(letterOrWord))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean checkLetterInText(String capital, char[] capitalDash, String letterOrWord)
    {
        char[] capital_array = capital.toCharArray();
        boolean letterIsWord = false;
        int index = 0;
        for (char charInCapital: capital_array)
        {
            if (charInCapital == letterOrWord.charAt(0))
            {
                capitalDash[index] = letterOrWord.charAt(0);
                letterIsWord = true;
            }
            index += 1;
        }
        return letterIsWord;
    }
}