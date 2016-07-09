/**
 * demonstrate use of if and while statements
 * @author (pranav) 
 * @version (12/8/15)
 */

import java.util.Scanner;
import java.util.Random;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
public class Password { 
	public static void main(String [] args)
    {
        Scanner in;  
        in = new Scanner(System.in);
    
    
        System.out.println("                Password Generation Menu                ");
        System.out.println("********************************************************");
        System.out.println("*  [1] Lowercase Letters                               *");
        System.out.println("*  [2] Lowercase & Uppercase Letters                   *");  
        System.out.println("*  [3] Lowercase, Uppercase, and Numbers               *");
        System.out.println("*  [4] Lowercase, Uppercase, Numbers, and Punctuation  *");
        System.out.println("*  [5] Quit                                            *");
        System.out.println("********************************************************");
        System.out.println("Enter Selection (1-5): ");
        int choice = in.nextInt();
        System.out.println("Password Length (1-14): ");
        int passwordLength = in.nextInt();
        int randNum = ((int)(0+ Math.random()* 122));
        String password = "";
        randNum = ((int)(0+ Math.random()* 122));
        
      while (randNum<=47 && randNum>=33) {
    	  randNum = ((int)(0+ Math.random()* 122));
		}
        boolean lowerCase = (randNum>=97) && (randNum<=122);
        boolean upperCase = (randNum>=65) && (randNum<=90);
        boolean numbers = (randNum>=48) && (randNum<=57);
        boolean punctuation = (randNum>=58) && (randNum<=64);
        
        System.out.println("\n");
        
           int counter=0;
          
           if (choice ==1)
           {     
           while( counter < passwordLength)
           { 
               while(!(randNum>=97) && (randNum<=122))
                   
               {        
                   randNum = ((int)(0+ Math.random()* 122));     
               }
               password += (char)randNum;
               counter++;
               randNum = ((int)(0+ Math.random()* 122)); 
           }
               System.out.println(password);
           }
            
           if (choice == 2)

           {
                while( counter < passwordLength)

                {
                     randNum = ((int)(0+ Math.random()* 122));  

                     if((randNum > 65 && randNum <= 90) || (randNum > 96 && randNum <= 122) )

                     {
                           password += (char)randNum;

                           counter++;
                     }

                }

           System.out.println(password);
           }

           if (choice == 3)

           {

                while( counter < passwordLength)

                {

                     randNum = ((int)(0+ Math.random()* 122));  

                     if((randNum > 65 && randNum <= 90) || (randNum > 96 && randNum <= 122) ||(randNum>=48) && (randNum<=57) )

                     {
                           password += (char)randNum;

                           counter++;          
                     }

                }

           System.out.println(password);

           }

           if (choice == 4)

           {

                while( counter < passwordLength)

                {
                     randNum = ((int)(0+ Math.random()* 122));  

                     if((randNum > 65 && randNum <= 90) || (randNum > 96 && randNum <= 122)||(randNum>=48) && (randNum<=57)||(randNum>=58) && (randNum<=64) );

                     {
                           password += (char)randNum;

                           counter++;             

                     }
                }

           System.out.println(password);
           }        
    }
        
    }
	

