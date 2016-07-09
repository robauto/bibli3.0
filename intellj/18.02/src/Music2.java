import java.lang.ref.SoftReference;

/**
 * Created by admin on 5/18/16.
 */
public class Music2 {
    String title;
    int year;
    String singer;
    public Music2(String title, int year, String singer)
    {
        this.title = title;
        this.year = year;
        this.singer = singer;
    }

    public String toString()
    {
        return title+","+year+","+singer;
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
    public String getSinger()
    {
        return singer;
    }
    public void setSinger(String singer)
    {
        this.singer = singer;
    }
}
