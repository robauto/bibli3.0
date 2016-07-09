//reading text files from the computer 
//Author-Pranav
//Version-10/31/15
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Family {
	public static void main(String[] args) throws FileNotFoundException 
	{
	int twoBoys = 0,twoGirls = 0,boyAndGirl = 0,sampleSize = 0,counter=0; 
	float	probabilityOfBoy=0,probabilityOfGirl=0;
	float probabilityOfBoyAndGirl=0;
	String token = ""; 
	//
	Scanner inFile = new Scanner(new FileReader("MaleFemaleFamily.txt")); 
	while (inFile.hasNext()) 
	{ 
	token = inFile.next( ); 
	System.out.println (token); 

	if (token.equals("BB")) 
	{ 
	twoBoys++; 
	counter++; 

	} 
	else if (token.equals("GG")) 
	{ 
	twoGirls++; 
	counter++; 

	} 
	else if (token.equals("BG")) 
	{ 
	boyAndGirl ++; 
	counter++; 
	} 
	else if (token.equals("GB")) 
	{ 
	boyAndGirl++; 
	counter++; 
	} 
	sampleSize=counter; 
	System.out.println("Sample Size: " + sampleSize ); 
	System.out.println("Two Boys: " + twoBoys); 
	System.out.println("One Boy One Girl: " + boyAndGirl); 
	System.out.println("Two Girls: " + twoGirls); 
	} 
	inFile.close(); 
	probabilityOfBoy = (float)twoBoys / (float)sampleSize; 
	probabilityOfGirl = (float)twoGirls / (float)sampleSize; 
	probabilityOfBoyAndGirl = (float)boyAndGirl / (float)sampleSize; 
	System.out.println("Two Boys: "+ probabilityOfBoy); 
	System.out.println("One Boy One Girl: "+ probabilityOfBoyAndGirl); 
	System.out.println("Two Girls: " + probabilityOfGirl); 

	}
}
