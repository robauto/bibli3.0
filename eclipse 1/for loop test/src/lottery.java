/**
 * Description for 5.04 Lottery project
 * 
 * @author (pranav)
 * @version (11/1/15)
 */
 
import java.util.Scanner;
public class lottery
{
    public static void main(String[] args, Scanner in, int count)
    {
        //declare and initialized variables and objects
        Scanner input = new Scanner(System.in);
        int rnumber = (int) (Math.random() * 999);
        int lotteryNum ;
        int userGuess  ;
        
        System.out.println("enter lottery number");
        while((userGuess = in.nextInt()) != rnumber) 
        {
        	if(userGuess < rnumber)
        	{
        		System.out.println("you lost ");
        	}
        	else
        	{
        		System.out.println("you lost ");
        	}
        	
			count++;
			System.out.println("lottery number was"+" "+rnumber );
        }
        //Generate a 3-digit "lottery" number composed of random numbers
        //Simulate a lottery by drawing one number at a time and 
        //concatenating it to the string
        //Identify the repeated steps and use a for loop structure
        
        
        
        
        
        //Input: Ask user to guess 3 digit number
        
        
      
      
      
      
        //Compare the user's guess to the lottery number and report results
        
        
        
        

        
    } //end main
}//end class Lottery