//purpose- Learn to extend classes 	
//version-3/18/16
//Author-Pranav
public class TestCube  {
public static void main(String[] args) {
	Cube c=new Cube(4);
	Rectangle r=new Rectangle(2,4);

	System.out.println("cube dimentions are "+ c.getLength()+"x"+c.getHeight()+"x"+c.getWidth());
	
	System.out.println("Ones Dimension are "+r.getLength()+"X"+r.getWidth());
}
}
