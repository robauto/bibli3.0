/**
 * Prompts user to select a category and tests the menu structure.
 * 
 * @author  -Pranav
 * @version -10/18/15
 */
import java.util.Scanner;
public class WellnessMenu
{
    public static void main(String [] args)
    {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Please select one of the following options from the menu for more information.");
        System.out.println("\nSelect a letter corresponding to a menu option.");
        System.out.println("[A] BMI");
        System.out.println("[B] BMR");
        System.out.println("[C] Healtyy food");
        System.out.println("[D] Food Piramid");
        System.out.println("[E] Fitness Training");
        // prompt user to enter A, B, C, D, or E: ");
        // accept user choice with a Scanner class method
     
       
        System.out.println("choose a food catagory by entering A,B,C,D or E");
        String choice=in.next();
        if(choice.equalsIgnoreCase("A") ) //condition for choice A goes in the parentheses
        {
            // provide print statement to indicate menu item A was chosen
            System.out.println(" You chose A for BMI");
        }
        else if( choice.equalsIgnoreCase("B")) //condition for choice B goes in the parentheses
        {
            // provide print statement to indicate menu item B was chosen
        	System.out.println("you chose B for BMR");
        }
        else if (choice.equalsIgnoreCase("C")) //condition for choice C goes in the parentheses
        {
            // provide print statement to indicate menu item C was chosen
        	System.out.println("you chose C for healthy food ");
        }
       else if (choice.equalsIgnoreCase("E")) //condition for choice D goes in the parentheses
        {
            // provide print statement to indicate menu item D was chosen
       	System.out.println("you chose E for FitnessTrining");
        }
        else if (choice.equalsIgnoreCase("D") ) //condition for choice E goes in the parentheses
        {
            // provide print statement to indicate menu item E was chosen
        	System.out.println("You chose D for FoodPiramid");
        }
        else //default choice for an invalid entry
        {
          //b
        	System.out.println("invalid entry");
        }
    }
}