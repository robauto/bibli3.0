/**
 * decoding the DNA
 * Created by Pi on 5/13/16.
 */
public class CeaserShiftTester2
{
    public static void main(String args[])
    {
        System.out.println("Decrypting");
        System.out.println("Enter text to decrypt");
        String message="IBQQJOFTTJTBQSPHSBNXJUIPVUFSSPST";
        System.out.println("enter the key value between 0-25");
        for(int i=1;i<25;i+=2) {
            int key = i;
            String decryptedMessgae = CaesarShiftDecryption1.decrypt(message, key);
            System.out.println(decryptedMessgae);
        }
    }

}
