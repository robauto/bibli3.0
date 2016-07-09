//demonstrating the use of math class 

//Version- 11/7/15
//Author- Pranav
public class PointsOnACircle {
public static void main(String[] args) {
	double[] x1 = { 1.0, 0.90, 0.80, 0.70, 0.60, 0.50, 0.40, 0.30, 0.20,
	           0.10, 0.00, -0.10, -0.20, -0.30, -0.40, -0.50, -0.60, -0.70,
	            -0.80, -0.90, -1.00 };
	double r = 1;
	    System.out.println("      Points on a Circle of Radius 1.0");
	    System.out.println("       x1        y1        x1        y2");
	    System.out.println("---------------------------------------");
	    for (int i = 0; i < x1.length; i++) {
	    	double y1 = Math.sqrt(Math.pow(r, 2) - Math.pow(x1[i], 2));
		    
		    double y2 = Math.sqrt(Math.pow(r, 2) - Math.pow(x1[i], 2));
		    System.out.printf("%10.2f%10.2f%10.2f%10.2f%n", x1[i], y1, x1[i], y2);
	    }
	        System.out.println("");
}
}
