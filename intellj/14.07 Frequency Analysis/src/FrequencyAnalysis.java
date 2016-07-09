import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Purpose- use the frequency of a message to decrypt
 * Created by admin on 4/30/16.
 */
public class FrequencyAnalysis {

    // open a file and read the data
    //while reading the data count  number of occurances
    // At the end calculate the frequency
    public void doAnalysis(String inputFilename,String outputFilename)
    {
        HashMap<Character,Integer> hm = new HashMap<Character,Integer>();
        for(char alpha = 'a'; alpha <= 'z'; alpha++)
        {
            hm.put(alpha,0);
        }
        int totalCount = 0;
        try {
            Scanner inFile = new Scanner( new FileReader(inputFilename));
            while (inFile.hasNext()) {
                String line = inFile.nextLine().toLowerCase();
                for(int i=0;i < line.length();i++)
                {
                    char alphabet = line.charAt(i);
                    if( alphabet >= 'a' && alphabet  <= 'z')
                    {
                        int lcount = hm.get(line.charAt(i));
                        lcount++;
                        hm.put(line.charAt(i),lcount);
                        totalCount++;
                    }

                }
            }
            PrintWriter outFile = new PrintWriter (new File(outputFilename));
            System.out.println("Alphatbet \t\t Occurance \t\t Frequency");
            outFile.println("Alphatbet \t\t Occurance \t\t Frequency");
            for(char alpha = 'a';alpha<='z';alpha++)
            {
                double freq = (hm.get(alpha)/(double)totalCount)*100;
                System.out.println(alpha+"\t\t"+hm.get(alpha)+"\t\t"+freq);
                outFile.println(alpha+"\t\t"+hm.get(alpha)+"\t\t"+freq);

            }
            outFile.println("totalCount "+ totalCount);

            System.out.println("totalCount "+ totalCount);
            outFile.close();


        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
