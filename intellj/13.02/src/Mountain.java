//Purpose- use of extends and the parent class
//Version-3/17/16
//AUthor-Pranav
public class Mountain extends Terrain {

	private int numOfMountain;
	
	public Mountain(int l,int b,int m)
	{
		super(l,b);
		numOfMountain=m;
		
	}
	public int getNumOfMountain()
	{
		return numOfMountain;
	}
}
