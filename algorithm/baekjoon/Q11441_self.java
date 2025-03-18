
import java.util.Scanner;

public class Q11441_self {
   public static void main(String args[]) {
      Scanner in = new Scanner(System.in);
      int N = Integer.parseInt(in.nextLine());
      int sumArr[] = new int[N+1];
      sumArr[0] = 0;

      for(int i=0; i<N; i++) {
         sumArr[i+1] = sumArr[i] + in.nextInt();
         System.out.println("sumArr[" + (i + 1) + "] = " + sumArr[i+1]);
      }

      int M = in.nextInt();
      StringBuilder sb = new StringBuilder();
      for(int i=0; i<M; i++) {
         int start = in.nextInt();
         int end = in.nextInt();
         sb.append(sumArr[end] - sumArr[start-1]);
         sb.append("\n");
      }

      System.out.println(sb);
   }
}
