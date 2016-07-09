import java.util.Scanner;

public class AnnualClimate1 {
	public static void main (String [ ] args)
	{

		//Declare and intialize variables - programmer to provide initial values
		Scanner in = new Scanner(System.in);
		String city = "Key West";
		String state = "Florida";

		String month [] ={"Jan", "Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec" };
		double temperature [] ={70.3, 70.8,73.8,77.0,80.7, 83.4, 84.5, 84.5, 83.4,80.2, 76.3,72.0 }; 		//initialize with Fahrenheit values
		double precipitation [] ={2.2, 1.5, 1.9, 2.1, 3.5, 4.6, 3.3, 5.4, 5.5,4.3, 2.6, 2.1 }; 	//initialize with inches values

		String tempLabel = "F";   //initialize to F
		String precipLabel = "cm"; //initialize to cm


		//INPUT - ask user for temp and preciptation scale choice
		System.out.print("Choose the temperature scale (F = Fahrenheit, C = Celsius): ");
		String tempChoice = in.next();
		System.out.print("Choose the precipitation scale (i = inches, c = centimeteres): ");
		String precipChoice = in.next();


		//PROCESSING - convert from F to C and in to cm based on user's choices
		// remember 5/9 = 0, 5.0/9 = .5555

		if(tempChoice.equalsIgnoreCase("C"))
		{
			tempLabel="(C)";
			for( int index = 0; index < temperature.length; index++)
			{
				
			}

		}

		//Convert in values to cm; replace the current values in precipitation
		if(precipChoice.equalsIgnoreCase("c"))
		{
			precipLabel="(cm)";


		}

		//OUTPUT - print table using printf to format and align data

		System.out.println();
		System.out.println("Climate Data");
		System.out.println("Location: " + city +", " + state);
		System.out.printf("%5s %18s %s %18s %s","Month","Temperature",tempLabel,"Precipitation",precipLabel);
		System.out.println();
		System.out.printf("***************************************************");
		System.out.println();




	}//end main
}
