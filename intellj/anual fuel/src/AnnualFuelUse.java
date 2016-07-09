//cost of the amount of fuel we use	
//author-pranav
//version-12/25/15
public class AnnualFuelUse{
    
    double [] days;
    double [] startMiles;
    double [] endMiles;
    double [] price;
    double [] gallonsUsed;
    double distance, distanceMin, distanceMax, MPG, MPGMin, MPGMax, cost, costMin, costMax;
        
    AnnualFuelUse(double [] d, double dis, double disMin, double disMax, double [] s, double [] e, 
    double [] p, double [] g, double m, double mMin, double mMax, double c, double cMin, double cMax){
            
        days = d;
        startMiles = s;
        endMiles = e;
        price = p;
        gallonsUsed = g;
        distance = dis;
        distanceMin = disMin;
        distanceMax = disMax;
        cost = c;
        costMin = cMin;
        costMax = cMax;
        MPG = m;
        MPGMin = mMin;
        MPGMax = mMax;
            
    }
        
    public double [] distance(){
            
        double distance [] = {0,0,0,0};
        double total = 0;
            
        for (int i = 0; i < endMiles.length; i++){
           
            distance[i] = endMiles[i] - startMiles[i];
                
        }
                
        return distance;
            
    }
        
    public double [] mpgcalc(){
            
        double mpgcalc [] = {0,0,0,0};
        double total = 0;
            
        for (int i = 0; i < mpgcalc.length; i++){
                
            total = endMiles[i] - startMiles[i];
            mpgcalc[i] = total / gallonsUsed[i];
                
        }
            
        return mpgcalc;
            
    }
        
    public double [] cost(){
            
        double cost [] = {0,0,0,0};
            
        for (int i = 0; i < cost.length; i++){
                
            cost[i] = gallonsUsed[i] * price[i]; 
                
        }
            
        return cost;
            
    }
        
}