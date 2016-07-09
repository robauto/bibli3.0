//simulate the value of pi
// author-Pranav
//Version-12/20/15
import java.util.*;

public class Dart

{ 

    public static boolean isInside (double xPos, double yPos)  

    {  

        double distance = Math.sqrt((xPos * xPos) + (yPos * yPos));

        return (distance < 1.0);
    }

    public static double computePI (int numThrows)

    { 

        Random randomGen = new Random (System.currentTimeMillis());    

        int hits = 0;

        double PI = 0;   

        for (int i = 1; i <= numThrows; i++)

        { 

            double xPos = (randomGen.nextDouble()) * 2 - 1.0;

            double yPos = (randomGen.nextDouble()) * 2 - 1.0;

            if (isInside(xPos, yPos))

            {

                hits++;

            }
        }
    double thr = numThrows;

 

    PI = (4 * (hits/thr));

        return PI;
    }
 

    public static void main (String[] args)

    {

        Scanner reader = new Scanner (System.in);

        System.out.println("This program approximates PI");

        System.out.println("It simulates throwing darts at a dartboard.");

        System.out.print("Please enter number of throws: ");
        double realpi=0;
       
        
        
      int numThrows = reader.nextInt();
      
      double [] pi = new double[numThrows];

        double PI = computePI(numThrows);

        double Difference = PI - Math.PI;
        
       double sum=0;
       
        for (int k = 0; k < 10; k++) {
        	 realpi=computePI(numThrows);
        	 sum=sum +realpi;
        	
        	 System.out.println("Trial"+"["+k+"]"+realpi);
       
			
		}
       System.out.println("Estimate of pi:"+sum/10);
    }
}
