package randome;

import java.util.Random;
import java.util.Scanner;

public class randomenumber {
	 public static void main(String[] args) {
         Random r=new Random();
         int  notoguess=r.nextInt(100);
         int tries=0;
         Scanner in = new Scanner(System.in);
          int guess;
          
              System.out.println("enter a guess between 1 and 100");
              guess=  in.nextInt();
               
              tries++;
              if(guess==notoguess)
              {
                  System.out.println("same");
              }
               
              else if(guess<notoguess)
              {
                  System.out.println("No of guess is too low");
                  
              
              }
               
              else if(guess>notoguess)
              {
                  System.out.println("No of guess is too high");
              }
               
               
          }
       
}

