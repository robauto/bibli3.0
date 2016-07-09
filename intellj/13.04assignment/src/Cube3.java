/**
 * purpose-learn to use extends
 * Created by Pranav on 3/6/16.
 */
public class Cube3 extends Box3 {



    public Cube3(int l, int w, int h)
    {
        super(l, w,h);

    }


    public String toString()
    {
        return "Cube - " + getLength() + " X " + getWidth() + " X " + getHeight();
    }
    public void showEffectBoth(Box3 b)
    {
        //System.out.println(b);
    }
}
