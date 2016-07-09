import java.util.Scanner;

public class CurrencyConversion {
	public static void main(String [ ] args)
    {
	 Scanner in=new Scanner(System.in);

	 

        
    
        
        
     
  
        // local variable for US Dollars
        // local variable for Mexican pesos spent
        
        
        
       // local variable for exchange rate of US Dollars to Pesos
   	 System.out.println("enter the amount of pesos spent");
       double pesosSpent=in.nextDouble(); 
       double pesoExchangeRage=0.0623;
  	 System.out.println("enter a starting value in US Dollars");
       double startingUsDollars=in.nextDouble();
       
        double dollarsSpentInMexico =pesosSpent*pesoExchangeRage;      // local variable for dollars spent in Mexico
        double remainingUsDollars = startingUsDollars-dollarsSpentInMexico;        // local variable for US Dollars remaining
        //remaining variables below here
        System.out.println("enter amount of yen spent");
        double availabledollarsinjapan = startingUsDollars-dollarsSpentInMexico;
        double yenspent=in.nextDouble();
        double yenExchangeRate=0.00886;
        double dollarsspentinjapan=yenspent*yenExchangeRate;
        double remainingusDollars=availabledollarsinjapan-dollarsspentinjapan;
        System.out.println("enter amount of euros spent");
        double availableusdollarsinfrance=remainingusDollars;
        double eurosspent=in.nextDouble();
        double euroexchangerate=1.1758;
        double dollarsspentinfrance=eurosspent*euroexchangerate;
        double remainingusdollars=availableusdollarsinfrance-dollarsspentinfrance;
        
        
        
        
      
        // purpose of program
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("This program converts an amount of money");
        System.out.println("from a specific country into the equivalent");
        System.out.println("currency of another country given the current");
        System.out.println("exchange rate.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Starting US dollars:"+  startingUsDollars);

        // convertion for Mexican pesos
        System.out.println( "US dollars spent in mexico:" +dollarsSpentInMexico);

        // convertion for Japanese yen
        System.out.println("US dollars spent japan:" +dollarsspentinjapan);

        // convertion for Euros
        System.out.println("US dollars spent in france:" +dollarsspentinfrance);


        //print output to the screen
        


        // Complete the code below. Replace th 0's for totalItem and fundsRemaining
        // with the proper calculations. Casting, integer division and the modulus
        // operator needed. Do not worry about extra decimal places for the dollar amounts.

 		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Souvenir Purchases");
		System.out.println(" (all values in US Dollars) ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		//Calculations for Souvenir #1
	   
		int costItem1 =4;  						//cost per item of first souvenir
		
		int budget1 = 50;   						//budget for first item
		int totalItem1 = budget1/costItem1; 		//how many items can be purchased
	    int  fundsRemaining1 =budget1%totalItem1; //how much of the budget is left

		System.out.println("Item 1");
		System.out.println("   Cost per item: $" + costItem1 );
		System.out.println("   Budget: $"+ budget1);
		System.out.println("   Total items purchased: " +  totalItem1);
		System.out.println("   Funds remaining: $"  +  fundsRemaining1);

		//Calculations for Souvenir #2
		
		double costItem2 = 32.55;  						//cost per item of second souvenir
		
		int budget2 = 713;   							//budget for second item
		int totalItem2 = budget2/(int)costItem2; 	//how many items can be purchased
	     int fundsRemaining2 = budget2% totalItem2;  	//how much of the budget is left

		System.out.println("Item 2");
		System.out.println("   Cost per item: $" + costItem2 );
		System.out.println("   Budget: $"+ budget2);
		System.out.println("   Total items purchased: " +  totalItem2);
		System.out.println("   Funds remaining: $"  +  fundsRemaining2);
}

}
