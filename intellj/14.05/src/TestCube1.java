//purpose- Learn to extend classes 	
//version-3/18/16
//Author-Pranav
public class TestCube1 {
public static void main(String[] args) {
	Cube1 c=new Cube1(4);
	Rectangle1 r=new Rectangle1(2,4);

	System.out.println("cube dimentions are "+ c.getLength()+"x"+c.getHeight()+"x"+c.getWidth());
	
	System.out.println("Ones Dimension are "+r.getLength()+"X"+r.getWidth());
}
}
