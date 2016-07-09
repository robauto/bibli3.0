
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
