import java.util.Scanner;

public class Dart2 {
	public static double[] calcPi(int d, int t)
	{


	     int hit = 0;
	     int miss = 0;
	     double [] posX = new double[d];
	     double [] posY = new double[d];
	     double[] pi = new double[t];
	    for(int i = 0; i < t; i++)
	    {
	        hit = 0;
	        for(int index = 0; index < d; index++)
	        {   
	            posX[index] = 2 * Math.random() + - 1;
	            posY[index] = 2 * Math.random() + - 1;

	            if((Math.pow(posX[index], 2) + Math.pow(posY[index], 2)) <= 1)
	            {
	                hit++;
	            }

	    }

	    pi[i] = (4 * ((double)hit / d));
	    }
	   return pi;
	}

	  public static double calcPiAverage(double[] p, double t)
	   {
	    double average = 0;
	    double sum = 0;
	    for(int i = 0; i < t; i++)
	    {
	      sum += p[i];
	    }
	    average = sum / t;
	    return average;
	    }

	    public static void printOutput(double [] p, double ave, int t)
	    {
	    for(int i = 0; i < t; i++)
	    {
	        System.out.print("Trial [" + i + "]: pi = ");
	        System.out.printf("%5.5f%n", p[i]);


	    }
	    System.out.printf("Estimate of pi = %5.5f", ave);
	   }

	   public static void main(String[] args)
	  {
	   Scanner in = new Scanner(System.in);

	   System.out.println("How many darts per trial? ");
	   int darts = in.nextInt();
	   System.out.println("How many trials? ");
	   int trials = in.nextInt();

	   double [] pi = new double[trials];
	   pi = calcPi(darts, trials);

	   double piAverage = calcPiAverage(pi, trials);

	   printOutput(pi, piAverage, trials);

	  }
}
