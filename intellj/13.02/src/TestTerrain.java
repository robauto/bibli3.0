//Purpose- use of extends and the parent class
//Version-3/17/16
//AUthor-Pranav
public class TestTerrain {

	public static void main(String[] args) {

		Forest fst = new Forest(100,200,100);
		Mountain m=new Mountain(300,400,25);
		WinterMountain wm=new WinterMountain(500,500,15,10);
		System.out.println("Forest " + fst.terrainSize() + " and has " + fst.getNumOfTrees() + " trees");
		System.out.println("mountain"+m.terrainSize()+" and has "+m.getNumOfMountain()+" mountains ");
		System.out.println("Winter Mountain "+ wm.terrainSize()+ " and has temprature "+ wm.getTemprature()+" and "+ wm.getNumOfMountain()+ " mountains");
	}

}
