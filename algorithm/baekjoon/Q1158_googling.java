import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q1158_googling {
   public static void main(String args[]) {
      Scanner in = new Scanner(System.in);
      StringBuilder sb = new StringBuilder();

      int n = in.nextInt();
      int k = in.nextInt();
      Queue<Integer> queue = new LinkedList<>();

      for (int i = 0; i < n; i++) {
         queue.add(i+1);
      }

      sb.append("<");

      // 사이즈가 1개일 때까지 반복
      while(queue.size() != 1) {

         // K-1 번째까지 모든 값을 차례로 뒤로 보낸다
         for (int i = 0; i < k-1; i++) {
            queue.add(queue.poll());
         }

         // K 번째는 꺼내서 출력
         sb.append(queue.poll());
         sb.append(", ");
      }

      // 1개일 땐 바로 꺼내서 출력
      sb.append(queue.poll() + ">");

      System.out.println(sb);
   }
}
