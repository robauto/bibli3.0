import java.util.Scanner;
/**
 * purpose- encrypting the program
 * Created by pi on 3/16/16.
 */
public class CaesarShiftTester1 {

    public static void main(String args[])
    {
        Scanner in=new Scanner(System.in);
        System.out.println("enter your choice");
        System.out.println("1.Encrypt\n2.Decrypt\n3.Quit");
        int option = in.nextInt();
        while(option!=3)
        {
            if (option == 1) {
                System.out.println("encrypting");
                System.out.println("Enter text to encrypt");
                String message=in.next();
                System.out.println("enter the key value between 0-25");
                int key=in.nextInt();
                String encryptedMessage = CaesarShiftEncryption1.encrypt(message,key);
                System.out.println(encryptedMessage);
                

            }
            if (option == 2)
            {
                System.out.println("Decrypting");
                System.out.println("Enter text to decrypt");
                String message=in.next();
                System.out.println("enter the key value between 0-25");
                int key=in.nextInt();
                String decryptedMessgae= CaesarShiftDecryption1.decrypt(message,key);
                System.out.println(decryptedMessgae);
            }
            System.out.println("enter your choice");
            System.out.println("1.Encrypt\n2.Decrypt\n3.Quit");
            option = in.nextInt();
        }

    }

}
