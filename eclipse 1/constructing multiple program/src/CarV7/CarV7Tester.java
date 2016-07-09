//use of creating testers 
//author-pranav
//version-12/25/15
package CarV7;

public class CarV7Tester{
    
    public static void main(String[ ] args){
       
       //double milesStart = 100, milesEnd = 120, gallons = 15, MPG = 0, distance = 0, price = 3.54, total = 0, GPM = 0, cost = 0;
       //String type = "09 Scion XB";
       
       //10%+ 
       double milesStart1 = 100, milesEnd1 = 120, gallons1 = 15, MPG1 = 0, distance1 = 0, price1 = 3.54, total1 = 0, GPM1 = 0, cost1 = 0;
       
       String type1 = "Saturn vue";
       
       CarV7 vars1 = new CarV7(milesStart1, milesEnd1, gallons1, MPG1, distance1, price1, total1);
       
       //10%-
       double milesStart2 = 100, milesEnd2 = 160, gallons2 = 17, MPG2 = 0, distance2 = 0, price2 = 3.65, total2 = 0, GPM2 = 0, cost2 = 0;
       String type2 = "Ford Escort";
       CarV7 vars2 = new CarV7(milesStart2, milesEnd2, gallons2, MPG2, distance2, price2, total2);
       
       //running the methods
       MPG1 = vars1.mpgcalc();
       GPM1 = vars1.gpmcalc();
       distance1 = vars1.distance();
       cost1 = vars1.cost();
       MPG2 = vars2.mpgcalc();
       GPM2 = vars2.gpmcalc();
       distance2 = vars2.distance();
       cost2 = vars2.cost();
       //printing out into friendly format
       
       System.out.println("                                         Gas Mileage Calculations");
       System.out.println();
       System.out.printf("%13s%15s%15s%15s%15s%15s%15s%15s%15s\n", "Type of Car" , "Start Miles" , "End Miles" , "Distance" , "Gallons", "Price", "Cost", "Mile/Gal", "Gal/Mile");
       System.out.println("======================================================================================================================================");
       System.out.printf("%13s%11.2f%16.2f%17.2f%15.2f%15.2f%17.2f%14.2f%14.2f\n", type1 , milesStart1 , milesEnd1 , distance1 , gallons1, price1, cost1, MPG1, GPM1);
       System.out.printf("%13s%11.2f%16.2f%17.2f%15.2f%15.2f%17.2f%14.2f%14.2f", type2 , milesStart2 , milesEnd2 , distance2 , gallons2, price2, cost2, MPG2, GPM2);
           
   }
   
}