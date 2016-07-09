//calculate the amount of gas released 
//author-Pranav
//version-12/26/15

public class CO2FootprintV12 {
    
    //declaring glob var
    double gallonsOfGas;
    
    //default constructor

    /**
     *
     * @param g  gas in gallons
     */
    CO2FootprintV12(double g){
        
        gallonsOfGas = g;
        
    }
    
    //calculating the pounds of gas

    /**
     * This method calculates the pounds of gas released into the atmosphere
     *
     * @return temp1  - weight of the gas in pounds
     */

    public double poundsOfGas(){
        
        double temp1 = 0;
        
        temp1 = (8.78 * Math.pow(10, -3));
        temp1 = gallonsOfGas * temp1;
        temp1 = temp1 * 1.10231;
        temp1 = temp1 * 2000;
        
        return temp1;
        
    }

    /**
     *calculating gas releaged in tons
     * @return temp1- volume of the gas
     */
    //
    public double tonsOfGas(){
        
        double temp1 = 0;
        
        temp1 = (8.78 * Math.pow(10, -3));
        temp1 = gallonsOfGas * temp1;
        temp1 = temp1 * 1.10231;
        
        return temp1;
        
    }
    
}