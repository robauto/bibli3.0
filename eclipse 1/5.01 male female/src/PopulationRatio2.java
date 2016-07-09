/**
 * this program lets the user see how many males and females are there in a population.  
 * 
 * @author  -Pranav
 * @version -10/25/15
 */


import java.util.Scanner;

public class PopulationRatio {
	  public static void main(String[] args)
	    {
	        int male = 0;
	        int female = 0;
	        int counter = 1;
	        double randNum = 0.0;
	        Scanner in = new Scanner(System.in);
	         
	        System.out.println("enter the population of the United States ");
	        int size = in.nextInt();
	         
	        while(counter <= size)
	        {
	            randNum = Math.random();
	            System.out.print(counter + "\t"+"Random no generated"+"\t" + randNum);
	             
	            if(randNum < .47)//probability is 50:50 for Male or Female
	            {
	               female++;
	                System.out.println("\t female");
	            }
	            else
	            {
	                male++;
	                System.out.println("\t male");
	               }
	            counter++;      
	        }
	        System.out.println();
	        System.out.println("Number of male = " + male);
	        System.out.println("Number of female = " + female);
	         
	        int Total=male+female;
	         
	        System.out.println("TotalNumber of Male&female = " + Total);
	        double percentageofmale=(double) (male)/Total*100;
	        System.out.println("percentage of male"+" "+percentageofmale);
	        double percentageoffemale=(double)(female)/Total*100;
	        System.out.println("percentage of female"+" "+percentageoffemale);
	         
	      
	    }

	}

