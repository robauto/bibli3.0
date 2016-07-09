//Purpose- use of extends and the parent class
//Version-3/17/16
//AUthor-Pranav
public class Forest extends Terrain {

	private int numOfTrees;
	
	public Forest(int l, int b, int t)
	{
		super(l,b);
		numOfTrees=t;
	}
	
	public int getNumOfTrees()
	{
		return numOfTrees;
	}
}
