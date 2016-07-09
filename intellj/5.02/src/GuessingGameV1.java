//this is a guessing game. Guess the number the computer guesses 

//author-Pranav
//version-10/28/15




import java.util.Scanner;
public class GuessingGameV1 {
	public static void main(String[] args) {
		
		  
		   Scanner in = new Scanner(System.in);
        //random()  method  has value from 0.0(min) to 1.0(max) value ,
		   //AS, It has to take no between 1 to 100 so, multiply with 99 with its max value(ie.1.0=>99*1.0=99.0) and added 1.so Total is 100.0
		   //then tycasted it to get in integer form.
		    int rnumber = (int) (Math.random() * 100)+1;
		    int   guess;
		     int  count = 1;

		   System.out.println("Enter your guess  :");

		   while((guess = in.nextInt()) != rnumber) 
		   {
		     if (guess < rnumber)
		     {  
		       System.out.println("Too low");
		     }
		     else
		     {
		       System.out.println("Too high");
		     }
		     count++;
		   }
		   System.out.println("Random number was "+ rnumber );

		   System.out.println("Number of guesses "+ count );
		  }
	 
	 
	 
	 
	 
	 
	 
		
	 
	 
	 
	 
            
       }
    
