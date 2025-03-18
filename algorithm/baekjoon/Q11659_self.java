import java.util.Scanner;

public class Q11659_self {
   public static void main(String args[]) {
      Scanner in = new Scanner(System.in);
      String[] firstLine = in.nextLine().split(" ");
      int N = Integer.parseInt(firstLine[0]);
      int M = Integer.parseInt(firstLine[1]);

      String[] secondLine = in.nextLine().split(" ");
      StringBuilder sb = new StringBuilder();

      for(int i=0; i<M; i++) {
         int sum = 0;
         int startIndex = in.nextInt()-1;
         int endIndex = in.nextInt()-1;
         for(int j=startIndex; j<=endIndex; j++) {
            sum += Integer.parseInt(secondLine[j]);
         }
         sb.append(sum);
         sb.append("\n");
      }

      System.out.println(sb);
   }
}
