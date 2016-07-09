
import java.util.ArrayList;
/**
 * purpose- arrange list and get a list from a category
 * Created by pi on 5/19/16.
 */
public class TestMusic
{
    public static void main(String args[])
    {
        Music [] myMusic = new Music[10];
        myMusic[0]=new Music("Pieces of You",1994,"Jewel");
        myMusic[1]=new Music("Jagged Little pill",1995,"Alanis Morissette");
        myMusic[2]=new Music("What If It’s You",1995,"Reba McEntire");
        myMusic[3]=new Music("Misunderstood",2001,"pink");
        myMusic[4]=new Music("Laundry Service",2001,"Shakira");
        myMusic[5]=new Music("Taking the long way", 2006,"Dixie Chicks");
        myMusic[6]=new Music("Under my skin",2004,"Avril Lavigne");
        myMusic[7]=new Music("Let go",2002,"Avril Lavigne");
        myMusic[8]=new Music("Let it go",2007,"Tim McGraw");
        myMusic[9]=new Music("white Flag",2004,"dido");
        printMusic(myMusic);
        ArrayList<Music> temp = searchTitle(myMusic,"Let go");
        printSearchTitleResults(temp,"Let go");
        temp = searchTitle(myMusic,"Some Day");
        printSearchTitleResults(temp,"Some Day");
        temp = searchYear(myMusic,2001);
        printSearchYearResults(temp,2001);
        temp = searchYear(myMusic,2003);
        printSearchYearResults(temp,2003);

        temp = searchSinger(myMusic,"Avril Lavigne");
        printSearchSingerResults(temp,"Avril Lavigne");

        temp = searchSinger(myMusic,"Tony Curtis");
        printSearchSingerResults(temp,"Tony Curtis");


    }
    public  static void printSearchTitleResults(ArrayList<Music> temp,String title)
    {
        System.out.println("\n\nSearch - Title - "+title);
        if(temp.size()!=0)
        {
            System.out.println("We found the "+ title+" in the library");
            for(int i=0;i<temp.size();i++)
            {
                System.out.println(temp.get(i));
            }


        }
        else
        {
            System.out.println(title+" is not in the library");
        }
    }
    public static void printSearchYearResults(ArrayList<Music> temp,int year)
    {
        System.out.println("\n\nSearch - Year - "+year);
        System.out.println("Find results:");
        if(temp.size()!=0)
        {

            for(int i=0;i<temp.size();i++)
            {
                System.out.println(temp.get(i));
            }
            System.out.println("There are "+temp.size()+" listings for "+year);

        }
        else
        {
            System.out.println("There are no listings for "+year);
        }
    }
    public static void printSearchSingerResults(ArrayList<Music> temp,String singer)
    {
        System.out.println("\n\nSearch - Singer - "+singer);
        System.out.println("Find results:");
        if(temp.size()!=0)
        {

            for(int i=0;i<temp.size();i++)
            {
                System.out.println(temp.get(i));
            }
            System.out.println("There are "+temp.size()+" listings for "+singer);

        }
        else
        {
            System.out.println("There are no listings for "+singer);
        }
    }
    public static void printMusic(Music[] myMusic)
    {
        System.out.println("Music Library");
        System.out.println("-------------");
        for(int i=0;i<myMusic.length;i++)
        {
            System.out.println(myMusic[i]);
        }

    }
    public static ArrayList<Music> searchTitle(Music[] myMusic,String title)
    {
        ArrayList<Music> retMusic = new ArrayList<Music>();

        for(int i=0;i< myMusic.length;i++)
        {
            if(myMusic[i].getTitle().compareTo(title) == 0)
            {
                retMusic.add(myMusic[i]);
            }
        }
       return retMusic;
    }
    public static ArrayList<Music> searchSinger(Music[] myMusic,String name)
    {

        ArrayList<Music> retMusic = new ArrayList<Music>();

        for(int i=0;i< myMusic.length;i++)
        {
            if(myMusic[i].getSinger().compareTo(name) == 0)
            {
                retMusic.add(myMusic[i]);
            }
        }
        return retMusic;

    }
    public static ArrayList<Music> searchYear(Music[] myMusic,int year)
    {
        ArrayList<Music> retMusic = new ArrayList<Music>();
        for(int i=0;i< myMusic.length;i++)
        {
            if(myMusic[i].getYear() == year)
            {
                retMusic.add(myMusic[i]);
            }
        }
        return  retMusic;

    }

}
