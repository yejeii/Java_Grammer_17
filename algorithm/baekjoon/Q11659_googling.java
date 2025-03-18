
import java.util.Scanner;

public class Q11659_googling {
   public static void main(String args[]) {
      Scanner in = new Scanner(System.in);
      String[] firstLine = in.nextLine().split(" ");
      int N = Integer.parseInt(firstLine[0]);
      int M = Integer.parseInt(firstLine[1]);

      StringBuilder sb = new StringBuilder();
      int[] sumArr = new int[N+1];
      sumArr[0] = 0;
      for(int i=0; i<N; i++) {
         sumArr[i+1] = sumArr[i] + in.nextInt();
         // System.out.println("sumArr[" + (i + 1) + "] = " + sumArr[i+1]);
      }

      for(int i=0; i<M; i++) {
         int start = in.nextInt();
         int end = in.nextInt();
         sb.append(sumArr[end]-sumArr[start-1]);
         sb.append("\n");
      }

      System.out.println(sb);

   }
}
