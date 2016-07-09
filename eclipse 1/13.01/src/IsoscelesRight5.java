import java.lang.Math;

public class IsoscelesRight extends Triangle {

	public IsoscelesRight(double side)
	{
		super(side,side,Math.sqrt(2)*side);
	}
}
