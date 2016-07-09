/**
 * purpose- decrypting the program
 * Created by pi on 3/19/16.
 */
public class CaesarShiftDecryption1 {

    final static int INIT = 'a';
    static String decrypt(String message, int key)
    {
        if (key<0 || key>25 )
        {
            System.out.println("invalid key");
        }
        String temp="";
        message=message.toLowerCase();
        for (int i=0;i<message.length();i++)
        {
            int x=message.charAt(i)-INIT;
            int endvalue=x-key;
            if (endvalue<0)
            {
                endvalue=endvalue+26+INIT;
            }
            else
            {
                endvalue=endvalue+INIT;
            }
            temp +=(char)endvalue;

        }
        return temp;

    }
}
