//finding out the carbon emitions 
//Author-pranav
//Version-1/7/16
public class CO2FootPrintTester1
{
   
    public static void main(String[ ] args)
    
    {
    	
        double [] gas = {48000,38400};
        double [] electricity = {16440,16111};
        double [] waste = {1018,3054};
        double [] recycling = {2045,3654};
        double [] bulbs = {5,9};
        //and the main thing is that the main problem is that  i am just tired and tired 
        CO2Footprint1 vars = new CO2Footprint1(gas,electricity,waste,recycling,bulbs);
        //calling Co2Foodprint method
        double [] reduction = vars.emissionReductionBulbs();
        double [] reductionTotal = vars.emissionReduction();
        
        System.out.println("|              Pounds of CO2              |       Pounds of CO2       |                      |");
        System.out.println("|              Emmitted from              |        Reduce from        |                      |");
        System.out.println("|    Gas     |  Electricity  |    Waste   |   Recycling  |  New Bulbs |    CO2 Footprint     |");
        System.out.println("|============|===============|============|==============|============|======================|");
        
        for (int i = 0; i < 2; i++){
            
            System.out.printf("%s%10.2f%3s%12.2f%4s%9.2f%4s%11.2f%4s%9.2f%4s%16.2f%7s\n","|",gas[i],"|",electricity[i],"|",waste[i],"|",recycling[i],"|",reduction[i],"|",reductionTotal[i],"|");
        
        }
        
    }
}
    