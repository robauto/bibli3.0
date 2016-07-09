//calculate the amount of co2 from the amount of electricty used	
// author- Pranav
//version-12/28/15
import java.util.ArrayList;

public class CO2FromElectricity
{
    
    //declaring vars
    ArrayList<Double> monthlyBill = new ArrayList<Double>(3);
    ArrayList<Double> monthlyPrice = new ArrayList<Double>(3);
    double avgBill, avgPrice;
    
    //default constructor
    CO2FromElectricity(ArrayList<Double> mb, ArrayList<Double> mp){
        
        monthlyBill = mb;
        monthlyPrice = mp;
        
    }
    
    //calcs the av gof a bill
    public double calcAverageBill(){
        
        double total = 0;
        
        for (int i = 0; i < monthlyBill.size(); i++){
            
            total += monthlyBill.get(i);
            
        }
        
        total = total / monthlyBill.size();
        
        return total;
        
    }
    
    //calcs the avg price
    public double calcAveragePrice(){
        
        double total = 0;
        
        for (int i = 0; i < monthlyPrice.size(); i++){
            
            total += monthlyPrice.get(i);
            
        }
        
        total = total / monthlyPrice.size();
        
        return total;
        
    }
    
    //calcs the co2 emissions
    public double calcElectricityCO2(double avgBill,double avgPrice){
        
        double total = 0;
        
        total = avgBill/avgPrice * 1.37 * 12;
        
        return total;
        
    }
    
}