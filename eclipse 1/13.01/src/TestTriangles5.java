
public class TestTriangles {

	public static void main(String[] args) {
		Triangle T=new Triangle(4,5,6);
		IsoscelesRight I=new IsoscelesRight(1.5);
		Equilateral E=new Equilateral(5);
		System.out.println("Triangle has the sides A="+T.getSideA()+", B="+T.getSideB()+", C="+T.getSideC());
		System.out.println("equilateral triangle has sides A="+E.getSideA()+", B="+E.getSideB()+", C="+E.getSideC());
		System.out.println("Isosceles right triangle has sides A="+I.getSideA()+", B="+I.getSideB()+", C="+I.getSideC());
	}

}
