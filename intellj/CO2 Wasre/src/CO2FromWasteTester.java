//amount of carbon dioxide released 
//author- pranav
//version-25/12/15

import java.util.ArrayList;

public class CO2FromWasteTester
{
    
    public static void main(String[ ] args){
        
        //declaring vars
        double[] people = {1,3,4,1,1};
        String[] paper = {"true","true","false","true","true"};
        String[] plastic = {"true","false","false","true","true"};
        String[] glass = {"true","true","false","true","true"};
        String[] cans = {"true","true","false","true","true"};

        ArrayList<Double> totalPounds = new ArrayList<Double>(5);
        CO2FromWaste vars = new CO2FromWaste(people,paper,plastic,glass,cans,totalPounds);
        
        //adding data to list
        totalPounds.add(1018.00);
        totalPounds.add(3054.00);
        totalPounds.add(4072.00);
        totalPounds.add(1018.00);
        totalPounds.add(1018.00);
        
        //running methods in outher class
        double totalWaste[] = vars.calcWasteReduction();
        double netWaste[] = vars.calcWasteNet();
        
        //printing it all out in a very friendly way
        System.out.println("|       |        |                                        |            Pounds of CO2               |");
        System.out.println("|       |        |         Household Waste Recycled       |    Total   |             |     Net     |");
        System.out.println("| Index | People |  Paper  |  Plastic  |  Glass  |  Cans  |  Emission  |  Reduction  |   Emission  |");
        System.out.println("|-------|--------|---------|-----------|---------|--------|------------|-------------|-------------|");
        
        for (int i = 0; i < totalPounds.size(); i++){
            
            System.out.printf("%s%4d%4s%4.0f%5s%6s%4s%7s%5s%6s%4s%6s%3s%10.2f%3s%10.2f%4s%10.2f%4s\n","|",i,"|",people[i],"|",paper[i],"|",plastic[i],"|",glass[i],"|",cans[i],"|",totalPounds.get(i),"|",totalWaste[i],"|",netWaste[i],"|");
            
        }
        
    
    }
    
}