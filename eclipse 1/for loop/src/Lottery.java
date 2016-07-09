//demonstrate the use of for loop

//Author-Pranav
//version-11/14/15


import java.util.Scanner;
import java.util.Random;
public class Lottery {
	
    public static void main(String[] args)

    {

      //declare and initialized variables and objects
             Scanner input = new Scanner(System.in);
             
             String userGuess="";
             

          
             //Simulate a lottery by drawing one number at a time and 
             //concatenating it to the string
             //Identify the repeated steps and use a for loop structure
             //Generate a 3-digit "lottery" number composed of random numbers
             String lotto = "";
             String temp="";
             int Digit = ((int)(0+Math.random()*10));
             for(int i=0; i < 3; i++) 
             {
            	 
                 
                

                  String lotteryNumberString = Integer.toString(lotteryNumberDigit);
                 System.out.println(lotteryNumberString);
                 String firstNumber = lotteryNumberString.substring(0,0);
                 System.out.println(firstNumber);
                 String secondNumber = lotteryNumberString.substring(1,1);
                 System.out.println(secondNumber);
                 String thirdNumber = lotteryNumberString.substring(2,2);

                 String firstTwoWinner = firstNumber + secondNumber;
                 String lastTwoWinner = secondNumber + thirdNumber;
                 String allNumbersWinner = firstNumber + secondNumber + thirdNumber;

                 System.out.println("Please enter your three numbers (e.g. 123): ");

                 userGuess = input.next();
                  if(userGuess.substring(0,2).equals(firstTwoWinner))

             {
                 System.out.println("Winner: " + allNumbersWinner );

                 System.out.println("Congratulations, the front pair matched.");
             }

             else if (userGuess.substring(1,3).equals(lastTwoWinner))
             {
                  System.out.println("Winner: " + allNumbersWinner );

                  
                  System.out.println("Congratulations, the end pair matched.");
             }

             else if (userGuess.equals(allNumbersWinner))
             {
                  System.out.println("Winner: " + allNumbersWinner );

                  System.out.println("Congratulations, both pairs matched.");
             }

             else
             {
                  System.out.println("Winner: " + allNumbersWinner );

                  System.out.println("Sorry, no matches. You only had one chance out of 100 to win anyway.");
             }



         }
     

	
    }
}
