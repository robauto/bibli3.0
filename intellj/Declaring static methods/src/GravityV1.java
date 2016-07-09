//use of printf command
//author-Pranav
//version-11/13/15


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
 
public class GravityV1 {
    public static double[] calcGravity(double[] radius, double[] mass)
    
    {
    	
    	
    	
        double[] gravity = new double[8];
          for(int i = 0; i < 8; i++)
        {
          gravity[i] = (6.67E17 * mass[i]) / (radius[i] * radius[i]);            
          
        }
          
        
        return gravity;
    }
 
    public static void printResults(String[] name, double[] radius, double[] mass, double       gravity[])
    {
    	    DecimalFormat df=new  DecimalFormat("0.00E0");
            System.out.printf("%9s %8s %6s %7s","Planet", "Diameter (km)", "Mass (kg)", "g  (m/s^2)\n"); 
            System.out.println("----------------------------------------------------");
            for(int i = 0; i < 8; i++)
            {
               System.out.printf("%9s %8s %6s ", name[i],     radius[i]*2 ,      mass[i]); 
               System.out.printf(df.format(gravity[i]));
               System.out.printf("\n");
               
            }
 
    }
 
    //print the gravity values to text file
    public static void printToFile(double[] gravity)throws IOException
    {
        PrintWriter outFile = new PrintWriter (new File("gravity data.txt"));
        for(int a = 0; a < 8; a++)
        {
         outFile.println(gravity[a]);
       
        
        }
        outFile.close();
    }
    
    public static void main(String[] args)throws IOException
    {
        // Initialize variables
        String[] names = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto"};
        double[] radii = {2439.7, 6051.9, 6378, 3402.5, 71492, 60270, 25562, 24774, 1195};
        double[] mass = {3.30E23, 4.87E24, 5.97E24, 6.42E23, 1.90E27, 5.68E26, 8.68E25, 1.02E26, 1.27E22}; 
 
        // Processing
        double[] gravities = calcGravity(radii, mass);
       
       
       
        
        printResults(names   , radii    , mass, gravities);
        
        
        printToFile(gravities);
 
 
    } //end main
     
 

}