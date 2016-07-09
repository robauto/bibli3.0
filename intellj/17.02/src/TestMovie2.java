/**
 * purpose- arrange list in assending and decending order
 * Created by pi on 5/12/16.
 */
public class TestMovie2 {
	
	  public static void main(String[] args)
	    {
	        Movie2[] myMovies = new Movie2[10];
	        myMovies[0] = new Movie2("The Muppets Take Manhattan", 2001, "Columbia Tristar");
	        myMovies[1] = new Movie2("Mulan Special Edition", 2004, "Disney");
	        myMovies[2] = new Movie2("Shrek 2", 2004, "Dreamworks");
	        myMovies[3] = new Movie2("The Incredibles", 2004, "Pixar");
	        myMovies[4] = new Movie2("Nanny McPhee", 2006, "Universal");
	        myMovies[5] = new Movie2("The Curse of the Were-Rabbit", 2006, "Aardman");
	        myMovies[6] = new Movie2("Ice Age", 2002, "20th Century Fox");
	        myMovies[7] = new Movie2("Lilo & Stitch", 2002, "Disney");
	        myMovies[8] = new Movie2("Robots", 2005, "20th Century Fox");
	        myMovies[9] = new Movie2("Monsters Inc.", 2001, "Pixar");

	        System.out.println("Before Sorting:");
	        printMovies(myMovies);

	        System.out.println("\nSorted by Title - ascending:");
	        printMovies(sortTitles(myMovies, 1));

	        System.out.println("\nSorted by Year - descending:");
	        printMovies(sortYears(myMovies, 2));

	        System.out.println("\nSorted by Studio - ascending:");
	        printMovies(sortStudios(myMovies, 1));
	    }
	
	    public static void printMovies(Movie2[] movies)
	    {
	        for(int i = 0; i < movies.length; i++)
	            System.out.println(movies[i]);
	    }

	   
	    public static Movie2[] sortStudios(Movie2[] movies, int x)
	    {
	        Movie2[] dest = new Movie2[ movies.length ];

	        // ascending sort
	        if(x == 1) {
	            for (int i = 0; i < movies.length; i++) {
	                Movie2 next = movies[i];
	                int insertindex = 0;
	                int k = i;
	                while (k > 0 && insertindex == 0) {
	                    if (next.getStudio().compareTo(dest[k - 1].getStudio()) > 0) {
	                        insertindex = k;
	                    }
	                    else {
	                        dest[k] = dest[k - 1];
	                    }
	                    k--;
	                }

	                dest[insertindex] = next;
	            }
	        }

	        // descending sort
	        else if(x == 2)
	        {
	            for (int i = 0; i < movies.length; i++) {
	                Movie2 next = movies[i];
	                int insertindex = 0;
	                int k = i;
	                while (k > 0 && insertindex == 0) {
	                    if (next.getStudio().compareTo(dest[k - 1].getStudio()) < 0) {
	                        insertindex = k;
	                    }
	                    else {
	                        dest[k] = dest[k - 1];
	                    }
	                    k--;
	                }

	                dest[insertindex] = next;
	            }
	        }
	        return dest;
	    }

	   
	    public static Movie2[] sortYears(Movie2[] movies, int y)
	    {
	        Movie2[] dest = new Movie2[ movies.length ];

	        // ascending sort
	        if(y == 1) {
	            for (int i = 0; i < movies.length; i++) {
	                Movie2 next = movies[i];
	                int insertindex = 0;
	                int j = i;
	                while (j > 0 && insertindex == 0) {
	                    if (next.getYear() > dest[j - 1].getYear()) {
	                        insertindex = j;
	                    }
	                    else {
	                        dest[j] = dest[j - 1];
	                    }
	                    j--;
	                }

	                dest[insertindex] = next;
	            }
	        }

	        // descending sort
	        else if(y == 2)
	        {
	            for (int i = 0; i < movies.length; i++) {
	                Movie2 next = movies[i];
	                int insertindex = 0;
	                int k = i;
	                while (k > 0 && insertindex == 0) {
	                    if (next.getYear() < dest[k - 1].getYear()) {
	                        insertindex = k;
	                    }
	                    else {
	                        dest[k] = dest[k - 1];
	                    }
	                    k--;
	                }

	                dest[insertindex] = next;
	            }
	        }
	        return dest;
	    }

	
	    public static Movie2[] sortTitles(Movie2[] movies, int z)
	    {
	        Movie2[] dest = new Movie2[ movies.length ];

	        // ascending sort
	        if(z == 1) {
	            for (int i = 0; i < movies.length; i++) {
	                Movie2 next = movies[i];
	                int insertindex = 0;
	                int k = i;
	                while (k > 0 && insertindex == 0) {
	                    if (next.getTitle().compareTo(dest[k - 1].getTitle()) > 0) {
	                        insertindex = k;
	                    }
	                    else {
	                        dest[k] = dest[k - 1];
	                    }
	                    k--;
	                }

	                dest[insertindex] = next;
	            }
	        }

	        // descending sort
	        else if(z == 2)
	        {
	            for (int i = 0; i < movies.length; i++) {
	                Movie2 next = movies[i];
	                int insertindex = 0;
	                int k = i;
	                while (k > 0 && insertindex == 0) {
	                    if (next.getTitle().compareTo(dest[k - 1].getTitle()) < 0) {
	                        insertindex = k;
	                    }
	                    else {
	                        dest[k] = dest[k - 1];
	                    }
	                    k--;
	                }

	                dest[insertindex] = next;
	            }
	        }
	        return dest;
	    }
}