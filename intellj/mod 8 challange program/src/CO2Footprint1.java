//finding out the carbon emitions 
//Author-pranav
//Version-1/7/16
public class CO2Footprint1
{
    //variable declaration
    double [] gas, electricity, waste, recycling, bulbs;
   
    //Constructor includes gasoline,electricity,waste produced ,waste recycled,replacement of bulbs 
    
    CO2Footprint1(double [] g, double [] e, double [] w, double [] r, double [] b){
        
        gas = g;
        electricity = e;
        waste = w;
        recycling = r;
        bulbs = b;
        
    }
    
    //Method to calculate co2 emmition form electricty
    
    public double [] emissionReductionBulbs(){
       
       double [] total = {0,0};
       
       for (int i = 0; i < total.length; i++){
       
           total[i] = bulbs[i] * 1.37 * 73;
        
       }
        
       return total; 
        
    }
    // methods to calculate co2 emission from recycling 
    public double [] emissionReduction(){
        
        double [] total = {0,0};
        
        for (int i = 0; i < gas.length; i++){
            
            total[i] = (gas[i] + electricity[i] + waste[i]) - (recycling[i] + bulbs[i]);
            
        }
        
        return total;
        
    }
    
}