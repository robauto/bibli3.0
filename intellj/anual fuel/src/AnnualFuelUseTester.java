//cost of the amount of fuel we use	
//author-pranav
//version-12/25/15
public class AnnualFuelUseTester{
    
    public static void main(String[ ] args){
        
        double [] days = {1,4,8,13};
        double [] startMiles = {45023,45231,45480,45659};
        double [] endMiles = {45231,45480,45659,45961};
        double [] price = {2.95,2.99,3.03,3.01};
        double [] gallonsUsed = {10.00,11.70,9.30,14.90};
        double [] distanceA = {};
        double [] mpgcalcA = {};
        double [] costA = {};
        double distance = 0, distanceMin = 0, distanceMax = 0, MPG = 0, MPGMin = 0, MPGMax = 0, cost = 0, costMin = 0, costMax = 0, gallons = 0, priceMin = 0, priceMax = 0;
        
        AnnualFuelUse vars = new AnnualFuelUse(days,distance,distanceMin,distanceMax,startMiles,endMiles,price,gallonsUsed,MPG,MPGMin,MPGMax
        ,cost,costMin,costMax);
        
        distanceA = vars.distance();
        mpgcalcA = vars.mpgcalc();
        costA = vars.cost();
        
        System.out.printf("%s%8s%15s%13s%13s%16s%7s%11s%11s\n","Fill Up", "Days", "Start Miles", "End Miles", "Distance", "Gallons Used", "MPG", "Price","Cost");
        System.out.println("=======================================================================================================");
        
        for (int i = 0; i < 4; i++){
            
            System.out.printf("%3d%12.2f%14.2f%12.2f%15.2f%12.2f%12.2f%9.2f%13.2f\n",i+1,days[i],startMiles[i],endMiles[i],distanceA[i],gallonsUsed[i],mpgcalcA[i],price[i],gallonsUsed[i] * price[i]);
        
        }
        
        distanceMin = distanceA[0];
        distanceMax = distanceA[0];
        MPGMin = mpgcalcA[0];
        MPGMax = mpgcalcA[0];
        priceMin = price[0];
        priceMax = price[0];
        
        for (int i = 0; i < 4; i++){
            
            if (distanceA[i] < distanceMin){
                
                distanceMin = distanceA[i];
                
            }
            
            if (distanceA[i] > distanceMax){
                
                distanceMax = distanceA[i];
                
            }
            
            if (mpgcalcA[i] < MPGMin){
                
                MPGMin = mpgcalcA[i];
                
            }
            
            if (mpgcalcA[i] > MPGMax){
                
                MPGMax = mpgcalcA[i];
                
            }
            
            if (price[i] < priceMin){
                
                priceMin = price[i];
                
            }
            
            if (price[i] > priceMax){
                
                priceMax = price[i];
                
            }
            
            distance += distanceA[i];
            gallons += gallonsUsed[i];
            cost += price[i] * gallonsUsed[i];
        }
        
        System.out.println();
        System.out.printf("%s%47.0f%35.1f%12.2f\n","Minimum",distanceMin,MPGMin,priceMin);
        System.out.printf("%s%47.0f%35.1f%12.2f\n","Maximum",distanceMax,MPGMax,priceMax);
        System.out.println();
        System.out.printf("%s%48.0f%13.1f%34.2f\n","Totals",distance,gallons,cost);
        System.out.printf("%s%48.0f%13.1f%34.2f\n","Annual",distance * 28,gallons * 28,cost * 28);
        
    }
    
}