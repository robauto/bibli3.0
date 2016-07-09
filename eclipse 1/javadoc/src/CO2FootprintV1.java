//purpose-calculate co2 footprint 
//author-Pranav
//version-12/26/15

public class CO2FootprintV1{
    
    //declaring glob var
    double gallonsOfGas;
    
    //default constructor
    CO2FootprintV1(double g){
        
        gallonsOfGas = g;
// this is from vim         
    }
    
    //calculating the pounds of gas
    public double poundsOfGas(){
        
        double temp1 = 0;
        
        temp1 = (8.78 * Math.pow(10, -3));
        temp1 = gallonsOfGas * temp1;
        temp1 = temp1 * 1.10231;
        temp1 = temp1 * 2000;
        
        return temp1;
        
    }
    
    //calculating the tons of gas
    public double tonsOfGas(){
        
        double temp1 = 0;
        
        temp1 = (8.78 * Math.pow(10, -3));
        temp1 = gallonsOfGas * temp1;
        temp1 = temp1 * 1.10231;
        
        return temp1;
        
    }
    
}

