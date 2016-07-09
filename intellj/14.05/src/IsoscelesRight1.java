//purpose- Learn to extend classes 	
//version-3/18/16
//Author-Pranav
import java.lang.Math;

public class IsoscelesRight1 extends Triangle1 {

	public IsoscelesRight1(double side)
	{
		super(side,side,Math.sqrt(2)*side);
	}
}
