
public class TestMusic2 {
	public static void main(String[] args)
	{
		//create an array with name music2
		Music2[] Music2 = {
				new Music2("Pieces of You", 1994, "Jewel"),	
				new Music2("Jagged Little Pill", 1995, "Alanis Morissette"),
				new Music2("What If It’s You", 1995, "Reba McEntire"),
				new Music2("Misunderstood", 2001, "Pink"),
				new Music2("Laundry Service", 2001, "Shakira"),
				new Music2("Taking the Long Way", 2006, "Dixie Chicks"),
				new Music2("Under My Skin", 2004, "Avril Lavigne"),
				new Music2("Let Go", 2002, "Avril Lavigne"),
				new Music2("Let It Go", 2007, "Tim McGraw"),
				new Music2("White Flag", 2004, "Dido")
		};

		// call printMusic()by passing  array with the name music2
		System.out.println("Music Library");
		System.out.println("--------------");
		printMusic(Music2);
      
		//AS PER Instruction title for Year by passing array and keyword to be search
		System.out.println("\n");
		searchTitle(Music2, "Misunderstood");

		//AS PER Instruction title for Year by passing array and keyword to be search
		System.out.println("\n");
		searchTitle(Music2, "Under Paid");
		

		//AS PER Instruction search for Year by passing array and keyword to be search
		System.out.println("\n");
		searchYear(Music2, 2005);

		//AS PER Instruction search for Year by passing array and keyword to be search
		System.out.println("\n");
		searchYear(Music2, 2006);

		//search for singer
		System.out.println("\n");
		searchSinger(Music2, "Darth Maul");
		
		//search for singer
		System.out.println("\n");
		searchSinger(Music2, "Dido");

	}

	//print out a list of music
	public static void printMusic(Music2[] music)
	{
		for(Music2 music2 : music)
		{
			System.out.println(music2);
		}
	}


	//SORTING array using mergesort 
	
	public static void mergeSortTitles(Music2[] a, int low, int high)
	{
		
		if(low == high)
		{
			return;
		}
       //find the mid element
		int mid = (low + high) / 2;
     
		mergeSortTitles(a, low, mid);
		
	
		mergeSortTitles(a, mid + 1, high);
		
       //merge the sorted part
		mergeTitles(a, low, mid, high);
	}

	
	public static void mergeTitles(Music2[] a, int low, int mid, int high)
	{
		//get the temp array for sorting
		Music2[] temp = new Music2[high - low + 1];

		int i = low;
		int j = mid + 1;
		int n = 0;

		//sort the elements into temp
		while(i <= mid || j <= high)
		{
			
			if(i > mid)
			{
				temp[n] = a[j];
				j++;
			}
			
			else if(j > high)
			{
				temp[n] = a[i];
				i++;
			}
			//compare
			else if(a[i].getTitle().compareTo(a[j].getTitle()) < 0 )
			{
				temp[n] = a[i];
				i++;
			}
			else if(a[i].getTitle().compareTo(a[j].getTitle()) > 0 )
			{
				temp[n] = a[i];
				i++;
			}
			// put smallest in j position
			else
			{
				temp[n] = a[j];
				j++;
			}
			n++;
		}

		for(int k = low; k<= high; k++)
		{
			a[k] = temp[k - low];
		}
	}
	//binary search for a given title
	public static Music2 searchTitle(Music2[] music, String searchTitle)
	{
		System.out.println("Search - Title - " + searchTitle);

		//sort the music array
		mergeSortTitles(music, 0, music.length - 1);

		//serach for high ,low,mid element
		int high = music.length;
		int low = -1;
		int mid;

		//as long as the search  is greater than one
		while(high - low > 1)
		{
			//get the middle element
			mid = (high + low) / 2;

			//if middle element is after the element to be  search
			if(music[mid].getTitle().compareTo(searchTitle) > 0)
			{
				//put high value to the middle of the current search
				high = mid;
			}
			else
			{
				//else, set the low value to the middle of the current search
				low = mid;
			}
		}

		
		if((low >= 0) && (music[low].getTitle().compareTo(searchTitle) == 0))
		{
			String S = music[low].getTitle() + ", " + music[low].getYear() + ", " + music[low].getSinger();
			System.out.println("Found: " + S);
			return music[low];
		}
		else
		{
			System.out.println("Not Found.");
			return null;
		}
	}
	
	
	
	//start binary search
	//get the music for the keyword to be search
	public static void searchYear(Music2[] music, int year)
	
	{
		System.out.println("Search - Year - " + year);
		
		//sort the music array
		mergeSortTitles(music, 0, music.length - 1);
		
		int high = music.length;
		int low = -1;
		int probe;
		
		while (high - low > 1)
		{
			probe = (high + low) / 2;
			if (music[probe].getYear() > year)
				high = probe;
			else
				low = probe;
		}
		if ((low >= 0) && (music[low].getYear() == year))
		{
			System.out.println("Search - Year - " + year);
			System.out.println("Found: " + music[low].toString());
			System.out.print("\n\n");
		}
		else
		{
			System.out.println("Search - Year - " + year);
			System.out.println("Not Found ");
			System.out.print("\n\n");
		}
	}

	//get the music for the keyword to be search
	public static Music2 searchSinger(Music2[] music, String singer)
	{
		System.out.println("Search - Singer - " + singer); 
		mergeSortTitles(music, 0, music.length - 1);

		//serach for high ,low,mid element
		int high = music.length;
		int low = -1;
		int mid;

		//as long as the search  is greater than one
		while(high - low > 1)
		{
			//get the middle element
			mid = (high + low) / 2;

			//if middle element is after the element to be  search
			if(music[mid].getTitle().compareTo(singer) > 0)
			{
				//put high value to the middle of the current search
				high = mid;
			}
			else
			{
				//else, set the low value to the middle of the current search
				low = mid;
			}
		}

		
		if((low >= 0) && (music[low].getTitle().compareTo(singer) == 0))
		{
			String S = music[low].getTitle() + ", " + music[low].getYear() + ", " + music[low].getSinger();
			System.out.println("Found: " + S);
			return music[low];
		}
		else
		{
			System.out.println("Not Found.");
			return null;
		}
	}

}