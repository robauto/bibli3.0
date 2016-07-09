//use of constructor 

//version-12/12/15
//author-Pranav
package constructor;

public class CarV5 {
 static String type1;
static double milesStart1;
static double milesEnd1;
static double gallons1;
static double gallonprice1;
	CarV5(String carType1,double endmiles1,double startmiles1,double gallonsused1,double pricepergallon1)
	{
		  type1=carType1;
		  milesStart1=startmiles1;
		  milesEnd1=endmiles1;
		  gallons1=gallonsused1;
		  gallonprice1=pricepergallon1;
	}
	
         public static double calcDistance(double a,double b)
         {
        	 double dist=a-b;
        	 
			return dist;
         }
         public double calcGPM( )
         {
        	double gpm= gallons1 /calcDistance(milesEnd1,milesStart1);
        	 return gpm;
         }
         public double TotalCost()
         {
        	 double cost=gallons1*gallonprice1;
        	 return cost;
         }
         public double calcMPG()
         {
        	 double mpg=calcDistance(milesEnd1,milesStart1)/gallons1;
        	 return mpg;
         }
        public static void main(String[] args) {
        	
        	
        	CarV5 car2=new CarV5("BMW",1500,1230,12,1.72);
        	
        	System.out.printf("%13s%15s%15s%15s%15s%15s%15s%15s%15s\n", "Type of Car" , "Start Miles" , "End Miles" , "Distance" , "Gallons" ,"Price","Cost ","Miles/Gal","Gal/Mile");
            System.out.println("====================================================================================================================================================");
            System.out.printf("%13s%11.2f%16.2f%15.2f%15.2f%15.2f%15.2f%15.2f%15.2f", type1 , milesStart1, milesEnd1 , calcDistance(milesEnd1,milesStart1),gallons1,gallonprice1,gallons1*gallonprice1 ,calcDistance(milesEnd1,milesStart1)/gallons1, gallons1 /calcDistance(milesEnd1,milesStart1) );

		}


}