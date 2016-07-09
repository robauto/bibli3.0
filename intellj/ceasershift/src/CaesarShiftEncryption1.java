/**
 * purpose- encrypting the program
 * Created by pi on 3/16/16.
 */
public class CaesarShiftEncryption1 {

    final static int INIT = 'a';
    static String encrypt(String message, int key)
    {
        if(key<0 || key>25) {
            System.out.println("invalid key value");
        }

        message = message.toLowerCase();
        String temp = "";
        for(int i=0;i<message.length();i++)
        {
            int x = message.charAt(i) - INIT;
            int endValue = x+key;
            if(endValue < 26)
            {
                endValue = endValue + INIT;
            }
            else
            {
                endValue = endValue - 26;
                endValue = endValue + INIT;
            }
            temp += (char) endValue;

        }

        return temp;
    }

}
