import java.util.Scanner;

/**
 * purpose- program to convert to morse code
 * Created by Pranav on 3/3/16.
 */
public class MorseCodeTester1
{
    public static void main(String args[])
    {
        Scanner in=new Scanner(System.in);
        System.out.println("enter the string that you want to convert to morse code with out punctuation ");
        String encryptMessage = MorseCode1.messageToMorse(in.nextLine());
        System.out.println(encryptMessage);
    }
}
