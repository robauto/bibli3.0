package tails;

import java.util.Scanner;

public class Lab1 {

	
	{
	    int heads = 0;
	    int tails = 0;
	    int counter = 1;
	    double randNum = 0.0;
	    Scanner in = new Scanner(System.in);
	    
	    System.out.println("How many times will the coin be flipped? ");
	    int flips = in.nextInt();
	    
	    while(counter <= flips)
	    {
	        randNum = Math.random();
	        System.out.print(counter + "\t" + randNum);
	        
	        if(randNum < .5)
	        {
	            heads++;
	            System.out.println("\t heads");
	        }
	     //   else
	        {
	       //10
	        	
	         
	           }
	     //  counter++;      
	    }
	    System.out.println();
	    System.out.println("Number of Heads = " + heads);
	   // System.out.println("Number of Tails = " + tails);
	   // int numberofheads=heads;
	   // numberoftails=tails;
	   // System.out.println("heads"+" "+numberofheads);
	    //System.out.println("tails"+" "+numberoftails);
	    
	
	    }
        }


