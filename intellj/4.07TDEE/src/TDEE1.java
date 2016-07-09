/**
 * Program to calculate the Total Daily Energy Expenditure
 *
 * @author-Pranav
 * @version-10/17/15
 */
import java.util.Scanner;
public class TDEE1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Input: Gather information from user
        System.out.print("Please enter your name: ");
        String name = in.nextLine();
        System.out.print("Please enter your BMR:");
        double basalMR = in.nextDouble();
        System.out.print("Please enter your gender (M/F): ");
        String gender = in.next();
        System.out.println();
        // Activity Level Menu
        System.out.println("Select Your Activity Level");
        System.out.println("[A] Resting (Sleeping, Reclining)");
        System.out.println("[B] Sedentary (Minimal Movement)");
        System.out.println("[C] Light (Sitting, Standing)");
        System.out.println("[D] Moderate (Light Manual Labor, Dancing, Riding Bike)");
        System.out.println("[E] Very Active (Team Sports, Hard Manual Labor)");
        System.out.println("[F] Extremely Active (Full-time Athelete, Heavy Manual Labor)");
        System.out.println();
        System.out.print("Enter the LETTER corresponding to your activity level: ");
        String choice = in.next();
        System.out.println();
        //Processing:
        // Activity Factor
        double activityFactor;
        System.out.print("");
        if (choice.equalsIgnoreCase("A") && ((gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")))) {
            //calculate tdee
            System.out.println("you entered activity level as sleeping, resting");
            activityFactor = 1.0;
            double tDEE = basalMR * activityFactor;
            // Output: Print Results
            System.out.println("Name: " + name + "\t\tGender: " + gender);
            System.out.println("BMR: " + basalMR + " calories " + "\t\tActivity Factor: " + activityFactor);
            System.out.println("TDEE1: " + tDEE + " calories/day ");

        }
        else if (choice.equalsIgnoreCase("B") && ((gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")))) {
            System.out.println("you entered activity level as sedentary");
            activityFactor = 1.3;
            // Output: Print Results
            double tDEE = basalMR * activityFactor;
            System.out.println("Name: " + name + "\t\tGender: " + gender);
            System.out.println("BMR: " + basalMR + " calories " + "\t\tActivity Factor: " + activityFactor);
            System.out.println("TDEE1: " + tDEE + " calories/day ");

        }
        else if (choice.equalsIgnoreCase("C") && (gender.equalsIgnoreCase("M"))) {
            System.out.println("you entered activity level as light");
            activityFactor = 1.6;
            double tDEE = basalMR * activityFactor;
            System.out.println("Name: " + name + "\t\tGender: " + gender);
            System.out.println("BMR: " + basalMR + " calories " + "\t\tActivity Factor: " + activityFactor);
            System.out.println("TDEE1: " + tDEE + " calories/day ");

        }
        else if (choice.equalsIgnoreCase("C") && (gender.equalsIgnoreCase("F"))) {
            System.out.println("you entered activity level as light");
            activityFactor = 1.5;

            double tDEE = basalMR * activityFactor;
            System.out.println("Name: " + name + "\t\tGender: " + gender);
            System.out.println("BMR: " + basalMR + " calories " + "\t\tActivity Factor: " + activityFactor);
            System.out.println("TDEE1: " + tDEE + " calories/day ");
        }
        else if (choice.equalsIgnoreCase("D") && gender.equalsIgnoreCase("M")) {

            System.out.println("you entered activity level as moderate");
            activityFactor = 1.7;
            double tDEE = basalMR * activityFactor;
            System.out.println("Name: " + name + "\t\tGender: " + gender);
            System.out.println("BMR: " + basalMR + " calories " + "\t\tActivity Factor: " + activityFactor);
            System.out.println("TDEE1: " + tDEE + " calories/day ");

        }
        else if (choice.equalsIgnoreCase("D") && (gender.equalsIgnoreCase("F"))) {

            System.out.println("you entered activity level as light");
            activityFactor = 1.6;

            double tDEE = basalMR * activityFactor;
            System.out.println("Name: " + name + "\t\tGender: " + gender);
            System.out.println("BMR: " + basalMR + " calories " + "\t\tActivity Factor: " + activityFactor);
            System.out.println("TDEE1: " + tDEE + " calories/day ");

        }
        else if (choice.equalsIgnoreCase("E") && (gender.equalsIgnoreCase("M"))) {

            System.out.println("you entered activity level as very active");
            activityFactor = 2.1;
            double tDEE = basalMR * activityFactor;
            System.out.println("Name: " + name + "\t\tGender: " + gender);
            System.out.println("BMR: " + basalMR + " calories " + "\t\tActivity Factor: " + activityFactor);
            System.out.println("TDEE1: " + tDEE + " calories/day ");

        }
        else if (choice.equalsIgnoreCase("E") && (gender.equalsIgnoreCase("F"))) {

            System.out.println("you entered activity level as very active");
            activityFactor = 1.9;

            double tDEE = basalMR * activityFactor;
            System.out.println("Name: " + name + "\t\tGender: " + gender);
            System.out.println("BMR: " + basalMR + " calories " + "\t\tActivity Factor: " + activityFactor);
            System.out.println("TDEE1: " + tDEE + " calories/day ");

        }
        else if (choice.equalsIgnoreCase("F") && (gender.equalsIgnoreCase("M"))) {

            System.out.println("you entered activity level as extremely active");
            activityFactor = 2.4;

            double tDEE = basalMR * activityFactor;
            System.out.println("Name: " + name + "\t\tGender: " + gender);
            System.out.println("BMR: " + basalMR + " calories " + "\t\tActivity Factor: " + activityFactor);
            System.out.println("TDEE1: " + tDEE + " calories/day ");

        }
        else if (choice.equalsIgnoreCase("F") && (gender.equalsIgnoreCase("F"))) {

            System.out.println("you entered activity level as extremely active");
            activityFactor = 2.2;
            double tDEE = basalMR * activityFactor;
            System.out.println("Name: " + name + "\t\tGender: " + gender);
            System.out.println("BMR: " + basalMR + " calories " + "\t\tActivity Factor: " + activityFactor);
            System.out.println("TDEE1: " + tDEE + " calories/day ");


        }
    }
}
