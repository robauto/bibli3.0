//use of creating testers 
//author-pranav
//version-12/25/15
package CarV7;

public class CarV7{
    
   double milesStart, milesEnd, gallons, MPG, distance, price, total;
   
   CarV7(double ms, double me, double g, double mpg, double dis, double pri, double tot){
       
       milesStart = ms;
       milesEnd = me;
       gallons = g;
       MPG = mpg;
       distance = dis;
       price = pri;
       total = tot;
       
   }
   
   //calculating the distance
   public double distance(){
   
       total = milesEnd - milesStart;
       
       return total;
   
   }
    
   //calculating the mpg
   public double mpgcalc(){
   
       total = milesEnd - milesStart;
       total = total / gallons;
       
       return total;
       
   }
   
   //calculating the gpm
   public double gpmcalc(){
   
       total = milesEnd - milesStart;
       total = gallons / total;
       
       return total;
       
   }
   
   //calculating cost
   public double cost(){
   
       total = gallons * price;
       
       return total;
       
   }
    
}