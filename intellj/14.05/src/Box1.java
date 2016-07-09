//purpose- Learn to extend classes 	
//version-3/18/16
//Author-Pranav
public class Box1 extends Rectangle1 {
	// instance variables 
		private int height;

		/**
		 * Constructor for objects of class box
		 */
		public Box1(int l, int w, int height)
		{
			// call superclass
			super(l, w);
		    // initialise instance variables
			this.height = height;
		}

		// return the height
		public int getHeight()
		{
			return height;
		}


}
