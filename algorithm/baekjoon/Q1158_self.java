import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q1158_self {
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

      // [K-1] 위치의 사람(K번째) 사람 제거
      if(k==1) {
         while(!queue.isEmpty()) {
            sb.append(queue.poll());
            sb.append(", ");
         }
      }
      
      else {
         while(queue.size() >= k) {

            // [K-2] 위치까지 차례로 빼서 뒤에 첨가
            for (int i = 0; i < k-1; i++) {
               queue.add(queue.poll());
            }
            sb.append(queue.poll());
            sb.append(", ");
         } 
      
         if(queue.size() >= 1 && queue.size() < k) {
            for (int i = 0; i < k; i++) {
               if(i == k-1) break;
               sb.append(queue.poll());
               sb.append(", ");
            }
         }
      }

      System.out.println(sb.substring(0, (sb.lastIndexOf(", "))) + ">");
   }
}
