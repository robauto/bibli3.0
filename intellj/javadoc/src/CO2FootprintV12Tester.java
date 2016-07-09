//calculate the amount of gas released 
//author-Pranav
//version-12/26/15
public class CO2FootprintV12Tester {
    
    public static void main(String[ ] args){
        /**
         * declaring gallonsOfGas variable
         */
        double gallonsOfGas = 1000;
        
        CO2FootprintV12 vars = new CO2FootprintV12(gallonsOfGas);
        /**
         * running the method and storing them in totalPounds and totalTons
         */
        double totalPounds = vars.poundsOfGas();
        double totalTons = vars.tonsOfGas();
        /**
         * printing out the carbon emitions in a table
         */
        //printing in friendly format
        System.out.println("       CO2 Emissions       ");
        System.out.printf("%s%10s%10s\n","Gallons","Pounds","Tons");
        System.out.printf("%s%12s%12s\n","of Gas","from Gas","from Gas");
        System.out.println("=================================");
        System.out.printf("%1.2f%11.2f%10.2f",gallonsOfGas, totalPounds, totalTons);
        
    }
    
}