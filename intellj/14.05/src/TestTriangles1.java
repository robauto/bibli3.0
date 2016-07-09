//purpose- Learn to extend classes 	
//version-3/18/16
//Author-Pranav
public class TestTriangles1 {

	public static void main(String[] args) {
		Triangle1 T=new Triangle1(4,5,6);
		IsoscelesRight1 I=new IsoscelesRight1(1.5);
		Equilateral1 E=new Equilateral1(5);
		System.out.println("Triangle1 has the sides A="+T.getSideA()+", B="+T.getSideB()+", C="+T.getSideC());
		System.out.println("equilateral triangle has sides A="+E.getSideA()+", B="+E.getSideB()+", C="+E.getSideC());
		System.out.println("Isosceles right triangle has sides A="+I.getSideA()+", B="+I.getSideB()+", C="+I.getSideC());
	}

}
