
/**
 * purpose-learn to use extends
 * Created by Pranav on 3/6/16.
 */import javax.swing.*;

public class Test3
{
	public static void main(String []args)
	{
		Rectangle3 r = new Rectangle3(5, 20);
		Box3 bb=new Box3(4,10,5);
		Cube3 cc=new Cube3(4,4,4);
		Box3 bbb=new Box3(4,4,4);
		System.out.println(r);
		System.out.println(bbb);
		System.out.println(bb);
		System.out.println(cc);
		System.out.println(bbb.equals(cc));
		System.out.println(bb.equals(cc));

	 }
}
