//Purpose- use of extends and the parent class
//Version-3/17/16
//AUthor-Pranav
public class Terrain {
	// instance variables 
		private int length, width;

		/**
		 * Constructor for objects of class terrain
		 */
		public Terrain(int l, int w)
		{
			// initialise instance variables
			length = l;
			width = w;
		}


		public String terrainSize()
		{
			
			return "Land has dimensions " + length + " X " + width;
		}
}
