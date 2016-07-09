//This class is to find out you min and max heart rate

//Author:Pranav
//Version:10/10/15




import java.util.Scanner;
public class TargetZone {

//hello this is from vi 	
	
	
	    public static void main(String[] args)
	    {
	    	
	    	System.out.print("Determine your Target heart rate zone for cardiovascular exercise (50% - 85%)");
	    	
	    	System.out.println("\n");
	    	
	        //Initialize and declare variables
	    	   int fixedheartrate=220;
	        String target = "within";
	        Scanner in = new Scanner(System.in);
	    
	        
	        //Prompt user for input
	        System.out.println("Enter your age");
		       int age=in.nextInt();
		       System.out.println("Enter your resting heart rate:");
			     int restingheartrate=in.nextInt();
			     System.out.println("Enter heart rate after exercise:");
			     int heartrateafterexercise=in.nextInt();
			    
	        //Calculate heart rate target zone min and max
			     int maxheartrate=fixedheartrate-age;
			     System.out.println("Your mac heart rate:"+maxheartrate);
			    float minheartrate=(float) (0.6*maxheartrate);
			     
		
	     int heartratereserve=maxheartrate-restingheartrate;
			    
	     int highheartratetargetzone=(int) (heartratereserve*.85);
	     int lowheartratetargetzone=(int) (heartratereserve*.50);
	     System.out.println("your heart rate target zone is between:"+lowheartratetargetzone+"" +"and"+ ""+highheartratetargetzone );
	     if (heartrateafterexercise<maxheartrate)
	     {
	    	 System.out.println("your heart rate is below max heartrate ");
	     }
	     else 
	     {
	    	 System.out.println("Your heart rate is above max heartrate");
	     }
	        //Determine if heart rate after exercise is between the min and max
	        
	        //If the heart rate is below, change the value of target to "below".
	       
	       
	       //If the heart rate is above, change the value of target to "above".
	        
	        
	        //Print two output statements
	        //The first stating the heart rate target zone
	        
	        
	        //The second stating if the heart rate after exercise  was within, above or below
	        //the target zone. The variable "target" will have a value of within, above or below
	        
	         
	    } //end main
}
