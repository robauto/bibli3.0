/**
 * purpose- arrange list in assending and decending order
 * Created by pi on 5/18/16.
 */
public class Movie4 {
	
	
		
		String title;
		int year;
		String studio;
		
		public Movie4(String title, int year, String studio)
		{
			this.title = title;
			this.year = year;
			this.studio = studio;
		}
		
		@Override
		public String toString()
		{
			return title + ", " + year + ", " + studio;
		}
		
		public String getTitle()
		{
			return title;
		}
		
		public void setTitle(String title)
		{
			this.title = title;
		}
		
		public int getYear()
		{
			return year;
		}
		
		public void setYear(int year)
		{
			this.year = year;
		}
		
		public String getStudio()
		{
			return studio;
		}
		
		public void setStudio(String studio)
		{
			this.studio = studio;
		}
		
	}
	
	


