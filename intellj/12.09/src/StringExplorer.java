/**
 * A program to allow students to try out different 
 * String methods. 
 * @author Laurie White
 * @version April 2012
 */
public class StringExplorer
{

	public static void main(String[] args)
	{
		String sample = "The quick brown fox jumped over the lazy dog.";
		
		String Statement1="My mother has dog but no cat";
		int x=Statement1.indexOf("no") ;
		System.out.println(x);
		
		//  Demonstrate the indexOf method.
		int position = sample.indexOf("quick");
		System.out.println ("sample.indexOf(\"quick\") = " + position);
		
		//  Demonstrate the toLowerCase method.
		String lowerCase = sample.toLowerCase();
		System.out.println ("sample.toLowerCase() = " + lowerCase);
		System.out.println ("After toLowerCase(), sample = " + sample);
		
		//  Try other methods here:
		
		//trim method
		//It returns a copy of this string with leading and trailing white space removed, 
		//or this string if it has no leading or trailing white space.
		String str="  My name is Pi  ";
		
		System.out.println("String value before trim method"+"***"+str);
		String str1=str.trim();

		System.out.println("String value after trim method"+"***"+str1);
		
		
		
		
		
		
	}

}