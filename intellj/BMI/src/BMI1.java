
//Author-Pranav
//Version-10/13/15
//Description-calculating BMI of a person with the help of if else statement


import java.util.Scanner;



public class BMI1 {
public static void main(String[] args) {
	Scanner in=new Scanner(System.in);
	double converttokg=0.453592;
	double converttom=0.3048;
	
	
	System.out.print("Enter you name:");
	String name=in.next();
	System.out.print("\n");
	System.out.print("enter your weight in pounds:");
	double weight=in.nextDouble();
	System.out.print("\n");
	
	System.out.print("enter your height in feet:");
	double height=in.nextDouble();
	System.out.print("\n");
	System.out.println();
	System.out.println("Body mass index calculator");
	System.out.println("-------------------------------");
	System.out.println("Your name:"+name);
	double heightinm=height*converttom;
	System.out.println("Height in (M):"+heightinm);
	double weightinkg=(weight*converttokg);
	System.out.println("weight in (Kg):"+weightinkg);
	double BMI=weightinkg/(heightinm*heightinm);
	System.out.println("Your BMI IS:"+BMI);
	
	if(BMI>18.5 && BMI<24.9)
	{
		System.out.println("catagory:normal");
		
		
	}
	
	else if (BMI>25 && BMI<29.9)
			{
		System.out.println("catagory:overweight)");
			}
	else if (BMI>30)
	{
		System.out.println("catagory:Obese");
	}
	
}
}
