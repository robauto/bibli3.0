import java.util.Scanner;

public class TowersOfHanoi {
   public static void main(String[] args) {
	   Scanner in=new Scanner(System.in);
	   System.out.println("enter the amount of discs");
      int nDisks = in.nextInt();
      
      doTowers(nDisks, 'A', 'B', 'C');
   }
   public static void doTowers(int topN, char from,
   char inter, char to) {
      if (topN == 1){
         System.out.println("Disk 1 from "
         + from + " to " + to);
      }else {
         doTowers(topN - 1, from, to, inter);
         System.out.println("Disk "
         + topN + " from " + from + " to " + to);
         doTowers(topN - 1, inter, from, to);
        // System.out.println("moves"+from);
         //System.out.println("moves"+ topN);
      }
   }
}