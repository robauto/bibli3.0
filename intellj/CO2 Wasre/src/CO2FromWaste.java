//amount of carbon dioxide released 
//author- pranav
//version-25/12/15

import java.util.ArrayList;

public class CO2FromWaste
{
    
    //declaring vars
    double[] people;
    
    String[] paper;
    
    String[] plastic;
    
    String[] glass;
    
    String[] cans;
    
    ArrayList<Double> totalPounds;
    
    //constructing default method
    CO2FromWaste(double[] pe, String[] pa, String[] pl, String[] gl, String[] c, ArrayList<Double> tp){
        
        people = pe;
        paper = pa;
        plastic = pl;
        glass = gl;
        cans = c;
        totalPounds = tp;
        
    }
    
    //calcing waste reduction
    public double[] calcWasteReduction(){
        
        double[] total = {0,0,0,0,0};
        double temp = 0;
        
        for (int i = 0; i < total.length; i++){
            
            temp = 0;
            temp = totalPounds.get(i);
            
            if (paper[i].equals("true")){
                
                 temp -= 184 * people[i];
                
            }
            
            if (plastic[i].equals("true")){

                temp -= 25.6 * people[i];
                
            }
            
            if (glass[i].equals("true")){
                
                temp -= 46.6 * people[i];    
                
            }
            
            if (cans[i].equals("true")){
                
                temp -= 165.8 * people[i];
                
            }
            
            total[i] = totalPounds.get(i) - temp;
            
        }
            
        return total;
        
    }
    
    //calculating new waste
    public double[] calcWasteNet(){
        
        double[] total = {0,0,0,0,0};
        double temp = 0;
        
        for (int i = 0; i < total.length; i++){
            
            temp = 0;
            temp = totalPounds.get(i);
            
            if (paper[i].equals("true")){
                
                 temp -= 184 * people[i];
            }
            
            if (plastic[i].equals("true")){

                temp -= 25.6 * people[i];
                
            }
            
            if (glass[i].equals("true")){
                
                temp -= 46.6 * people[i];    
                
            }
            
            if (cans[i].equals("true")){
                
                temp -= 165.8 * people[i];
                
            }
            
            total[i] = temp;
            
        }
            
        return total;
        
    }
}