//purpose- use of formating output and importing text files
//Author - Pranav
//version-12/10/15


import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;

public class WeightonPlanetsv1
{
	
public static double weightCalc(double weight, double gravity, double weightTotal){
        
        weightTotal = weight * 433.59237;
        weightTotal = weightTotal / gravity;
        weightTotal = weightTotal / 433.59237;
        return weightTotal;
        
    }
    
   
    public static void main(String[] args)
    {
        
        int i = 0;
        Scanner in = new Scanner(System.in);
        double gravity = 0;
        double weightTotal = 0;
        String[] names = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto"};
        
        System.out.print("Enter your weight(lbs): ");
        double weight = in.nextDouble();
        System.out.println();
        System.out.println("          My weight on the Planets");
        System.out.printf("%4s%15s%20s\n","Planet", "Gravity", "Weight(lbs)");
        System.out.println("----------------------------------------------");
        
        for (i = 0; i < names.length; i++){
            
            if (names[i] == names[0]){
                
                gravity = 0.378;
                weightTotal = weightCalc(weight,gravity,weightTotal);
            
            }
            
            if (names[i] == names[1]){
                
                gravity = 0.907;
                weightTotal = weightCalc(weight,gravity,weightTotal);
            
            }
            
            if (names[i] == names[2]){
                
                gravity = 1;
                weightTotal = weightCalc(weight,gravity,weightTotal);
            
            }
            
            if (names[i] == names[3]){
                
                gravity = 0.377;
                weightTotal = weightCalc(weight,gravity,weightTotal);
            
            }
            
            if (names[i] == names[4]){
                
                gravity = 2.36;
                weightTotal = weightCalc(weight,gravity,weightTotal);
            
            }
            
            if (names[i] == names[5]){
                
                gravity = 0.916;
                weightTotal = weightCalc(weight,gravity,weightTotal);
            
            }
            
            if (names[i] == names[6]){
                
                gravity = 0.889;
                weightTotal = weightCalc(weight,gravity,weightTotal);
            
            }
            
            if (names[i] == names[7]){
                
                gravity = 1.12;
                weightTotal = weightCalc(weight,gravity,weightTotal);
            
            }
            
            System.out.printf("%8s%12.2f%17.2f\n", names[i], gravity, weightTotal);
            
        }               

    } //end main
    
}//end class