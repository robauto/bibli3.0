
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
