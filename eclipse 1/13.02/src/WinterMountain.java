
public class WinterMountain extends Mountain {

	private double temprature;
	public WinterMountain(int l,int b,int m,double t)
	{
		super(l,b,m);
		temprature=t;
	}
	public double getTemprature()
	{
		return temprature;
	}
}
