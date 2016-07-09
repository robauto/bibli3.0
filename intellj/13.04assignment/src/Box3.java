/**
 * Purpose-learn to overide a method
 * Created by Pranav on 3/6/16.
 *
 */
public class Box3 extends Rectangle3 {
    private int height;

    public Box3(int l, int w, int h)
    {
        super(l, w);
        height=h;

    }
    public int getHeight()
    {

        return height;
    }

    public String toString()
    {
        return "Box - " + getLength() + " X " + getWidth() + " X " + height;
    }
    public String equals(Box3 box3)
    {
        if (box3.getLength() == getLength() && box3.getWidth() == getWidth() && box3.getHeight() == getHeight())
        {
            return this.toString()+" is same size as  "+ box3.toString();
        }
        return this.toString()+" is not same size as "+ box3.toString();

    }
}
