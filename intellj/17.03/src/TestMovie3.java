/**
 * purpose- arrange list in assending and decending order
 * Created by pi on 5/16/16.
 */
public class TestMovie3 {

    public static void main(String[] args)
    {
        Movie3[] myMovies = new Movie3[10];
        myMovies[0] = new Movie3("The Muppets Take Manhattan", 2001, "Columbia Tristar");
        myMovies[1] = new Movie3("Mulan Special Edition", 2004, "Disney");
        myMovies[2] = new Movie3("Shrek 2", 2004, "Dreamworks");
        myMovies[3] = new Movie3("The Incredibles", 2004, "Pixar");
        myMovies[4] = new Movie3("Nanny McPhee", 2006, "Universal");
        myMovies[5] = new Movie3("The Curse of the Were-Rabbit", 2006, "Aardman");
        myMovies[6] = new Movie3("Ice Age", 2002, "20th Century Fox");
        myMovies[7] = new Movie3("Lilo & Stitch", 2002, "Disney");
        myMovies[8] = new Movie3("Robots", 2005, "20th Century Fox");
        myMovies[9] = new Movie3("Monsters Inc.", 2001, "Pixar");

        System.out.println("Before Sorting:");
        printMovies(myMovies);
        TestMovie3 tm3 = new TestMovie3();
        System.out.println("\nSorted by Title - descending");
        printMovies(tm3.sortTitles(myMovies, 2));
        System.out.println("\nSorted by Year - descending:");
        printMovies(tm3.sortYears(myMovies, 2));
        System.out.println("\nSort by studio - ascending");
        printMovies(tm3.sortStudios(myMovies, 1));

    }
    public static void printMovies(Movie3[] movies)
    {
        for(int i = 0; i < movies.length; i++)
            System.out.println(movies[i]);
    }

    public  Movie3[] sortTitles(Movie3[] movies,int choice)
    {
        int i,maxpos,k;
        Movie3 temp;
        if(choice == 1)
        {
            for ( i = movies.length - 1 ; i >= 0 ; i-- ) {
                maxpos = 0;
                for (k = 0; k <= i; k++) {
                    if (movies[k].getTitle().compareTo(movies[maxpos].getTitle()) > 0)
                        maxpos = k;
                }

                temp = movies[i];
                movies[i] = movies[maxpos];
                movies[maxpos] = temp;
            }

        }
        else
         if(choice ==2)
         {
             for ( i = movies.length - 1 ; i >= 0 ; i-- ) {
                 maxpos = 0;
                 for (k = 0; k <= i; k++) {
                     if (movies[k].getTitle().compareTo(movies[maxpos].getTitle()) < 0)
                         maxpos = k;
                 }

                 temp = movies[i];
                 movies[i] = movies[maxpos];
                 movies[maxpos] = temp;
             }

         }
        return movies;

    }
    public Movie3[] sortStudios(Movie3[] movies,int choice)
    {
        int i,maxpos,k;
        Movie3 temp;
        if(choice == 1)
        {
            for ( i = movies.length - 1 ; i >= 0 ; i-- ) {
                maxpos = 0;
                for (k = 0; k <= i; k++) {
                    if (movies[k].getStudio().compareTo(movies[maxpos].getStudio()) > 0)
                        maxpos = k;
                }

                temp = movies[i];
                movies[i] = movies[maxpos];
                movies[maxpos] = temp;
            }

        }
        else
        if(choice ==2)
        {
            for ( i = movies.length - 1 ; i >= 0 ; i-- ) {
                maxpos = 0;
                for (k = 0; k <= i; k++) {
                    if (movies[k].getStudio().compareTo(movies[maxpos].getStudio()) < 0)
                        maxpos = k;
                }

                temp = movies[i];
                movies[i] = movies[maxpos];
                movies[maxpos] = temp;
            }

        }
        return movies;

    }
    public  Movie3[] sortYears(Movie3[] movies, int choice)
    {
        int i;
        int k;
        int maxpos;
        Movie3 temp;

        // ascending sort
        if(choice == 1) {
            for ( i = movies.length - 1 ; i >= 0 ; i-- ) {
                maxpos = 0;
                for (k = 0; k <= i; k++) {
                    if (movies[k].getYear() > movies[maxpos].getYear())
                        maxpos = k;
                }

                temp = movies[i];
                movies[i] = movies[maxpos];
                movies[maxpos] = temp;
            }
        }

        // descending sort
        else if(choice == 2)
        {
            for ( i = movies.length - 1 ; i >= 0 ; i-- ) {
                maxpos = 0;
                for (k = 0; k <= i; k++) {
                    if (movies[k].getYear() < movies[maxpos].getYear())
                        maxpos = k;
                }

                temp = movies[i];
                movies[i] = movies[maxpos];
                movies[maxpos] = temp;
            }
        }
        return movies;
    }

    
}
	

