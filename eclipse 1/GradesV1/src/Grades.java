import java.util.Scanner;

public class Grades {
public static void main(String[] args) {
	Scanner in=new Scanner(System.in);
	//declaration of local variable
    //Local Variable
	//Local Variable
		int n=0;        
		System.out.println("enter test grade");
		int testGrade=in.nextInt();
		System.out.println("enter total points");
		int TotalPoint=in.nextInt();
		
		double average=(double)TotalPoint/1;
		n++;
		 System.out.println("n="+  n +" "+ "new Test Grades:"+" "+ testGrade+" "+ "Total Points:"+ " " +" "+ TotalPoint +" "+"Average:"+average);
		
		n++;
		System.out.println("enter the test grade");
		testGrade=in.nextInt();
		TotalPoint+=testGrade;
		average=(double)TotalPoint/2;
		
	 System.out.println("n="+  n +" "+ "new Test Grades:"+" "+ testGrade+" "+ "Total Points:"+ " " +" "+ TotalPoint +" "+"Average:"+average);
		
	n++;
	System.out.println("enter the test grade");
	testGrade=in.nextInt();
	TotalPoint+=testGrade;
	average=(double)TotalPoint/3;
	 System.out.println("n="+  n +" "+ "new Test Grades:"+" "+ testGrade+" "+ "Total Points:"+ " " +" "+ TotalPoint +" "+"Average:"+average);	
		
	
	n++;
	System.out.println("enter the test grade");
	testGrade=88;
	TotalPoint+=in.nextInt();
	average=(double)TotalPoint/4;
	System.out.println("n="+  n +" "+ "new Test Grades:"+" "+ testGrade+" "+ "Total Points:"+ " " +" "+ TotalPoint +" "+"Average:"+average);	
	
	n++;
	System.out.println("enter the test grade");
	testGrade=in.nextInt();
	TotalPoint+=testGrade;
	average=(double)TotalPoint/5;
	System.out.println("n="+  n +" "+ "new Test Grades:"+" "+ testGrade+" "+ "Total Points:"+ " " +" "+ TotalPoint +" "+"Average:"+average);	
	
	n++;
	System.out.println("enter the test grade");
        testGrade=in.nextInt();
        TotalPoint+=testGrade;
        average=(double)TotalPoint/6;
        System.out.println("n="+  n +" "+ "new Test Grades:"+" "+ testGrade+" "+ "Total Points:"+ " " +" "+ TotalPoint +" "+"Average:"+average);

        n++;
        System.out.println("enter the test grade");
       testGrade=in.nextInt();
        TotalPoint+=testGrade;
        average=(double)TotalPoint/7;
        System.out.println("n="+  n +" "+ "new Test Grades:"+" "+ testGrade+" "+ "Total Points:"+ " " +" "+ TotalPoint +" "+"Average:"+average);

        n++;
        System.out.println("enter the test grade");
        testGrade=in.nextInt();
        TotalPoint+=testGrade;
        average=(double)TotalPoint/8;
        System.out.println("n="+  n +" "+ "new Test Grades:"+" "+ testGrade+" "+ "Total Points:"+ " " +" "+ TotalPoint +" "+"Average:"+average);
    
       n++;
       System.out.println("enter the test grade");
       testGrade=in.nextInt();
        TotalPoint+=testGrade;
        average=(double)TotalPoint/9;
        System.out.println("n="+  n +" "+ "new Test Grades:"+" "+ testGrade+" "+ "Total Points:"+ " " +" "+ TotalPoint +" "+"Average:"+average);
	
}
}
