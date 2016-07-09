//use of printf
//version-12/19/15
//author- Pranav
import java.util.Scanner;

class AnnualClimate2
{

    public static void main (String [ ] args)
    {

        //Declare and intialize variables - programmer to provide initial values
        Scanner in = new Scanner(System.in);
        String city = "Alpalachicola";
        String state = "Florida";
        double totalTemp = 0;
        double totalPrecip = 0;

        String month [] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        double temperature [] = {52.7, 55.3, 60.7, 66.8, 74.1, 80.0, 81.9, 81.7, 79.1, 70.2, 62.0, 55.2};      
        double precipitation [] = {4.9, 3.8, 5.0, 3.0, 2.6, 4.3, 7.3, 7.3, 7.1, 4.2, 3.6, 3.5}; 
        
       
        String tempLabel = "F";  
        String precipLabel = "Inches"; 

        System.out.print("Choose the temperature scale (F = Fahrenheit, C = Celsius): ");
        String tempChoice = in.next();
        System.out.print("Choose the precipitation scale (i = inches, c = centimeteres): ");
        String precipChoice = in.next();
        
        System.out.println();
        System.out.println("Climate Data");
        System.out.println("Location: " + city +", " + state);

        //Convert in values to cm; replace the current values in precipitation
        
        
        System.out.printf("%5s %18s %s %18s %s","Month","Temperature",tempLabel,"Precipitation",precipLabel);
        System.out.println();
        System.out.printf("***************************************************");
        System.out.println();

        if(tempChoice.equalsIgnoreCase("F")){   
            
            tempLabel = "(F)";
            
            for(int i = 0; i < temperature.length; i++)
            {
             
                totalTemp += (double)temperature[i];
                System.out.println(month[i] + "\t\t" + (double)temperature[i] + "\t\t\t" + (double)precipitation[i]);
                
            }
            
            if (precipChoice.equalsIgnoreCase("c")){
                
                for( int i = 0; i < temperature.length; i++)
                {
                    
                    totalTemp += (double)(temperature[i] - 32) * (5.0/9.0);
                    System.out.println(month[i] + "\t\t" + (double)temperature[i] + "\t\t\t" + (double)(precipitation[i] * 2.54));
                
                }
            
            }
            
            totalTemp = totalTemp / temperature.length;

        }
        
        if(tempChoice.equalsIgnoreCase("C")){
            
            tempLabel="(C)";
            
            for( int i = 0; i < temperature.length; i++)
            {
                
                totalTemp += (double)(temperature[i] - 32) * (5.0/9.0);
                System.out.println(month[i] + "\t\t" + (double)(temperature[i] - 32) * (5.0/9.0) + "\t\t\t" + (double)precipitation[i]);
            
            }
            
            if (precipChoice.equalsIgnoreCase("c")){
                
                for( int i = 0; i < temperature.length; i++)
                {
                    
                    totalTemp += (double)(temperature[i] - 32) * (5.0/9.0);
                    System.out.println(month[i] + "\t\t" + (double)(temperature[i] - 32) * (5.0/9.0) + "\t\t\t" + (double)(precipitation[i] * 2.54));
                
                }
            
            }
            
            totalTemp = totalTemp / temperature.length;
            
        }
        
        if (precipChoice.equalsIgnoreCase("i")){
        
            precipLabel = "(in.)";
            
            for(int i = 0; i < precipitation.length; i++){
                
                totalPrecip += (double)precipitation[i];
                
            }
            
        }
        
        if(precipChoice.equalsIgnoreCase("c")){
            
            precipLabel = "(cm)";
            
            for(int i = 0; i < precipitation.length; i++){
                
                totalPrecip += (double)(precipitation[i] * 2.54);
                
            }

        }
        
        System.out.println("***************************************************");
        System.out.println("Average: " + totalTemp + "\t\t" + "Annual: " + totalPrecip);
        
    }//end main

}//end Annual Climate