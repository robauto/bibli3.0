
public class Lab2 {
public static void main(String[] args) {

	String str="my name is pranav1";
	String str1="my name is pranav";
	boolean  f=str.equals(str1);
System.out.println(f);
   
	
	int x=str.indexOf('y');
	System.out.println(x);
	
	String y=str.substring(0, 12);
	System.out.println(y);
	int z=str.length();
	System.out.println(z);
	String a= str.replace('m', 'v');
	System.out.println(a);
    String b=str.replaceAll(str, "Pranav");
     System.out.println(b);
    String g=str.substring(1);
    System.out.println(g);
    String j=str.substring(3,13);
	System.out.println("Substring with two parameter" + j);
     
    // replaceAll(String st1,String st2);
     //will replace St1 with str2.
     

	
}
}
