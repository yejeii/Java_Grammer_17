import java.util.Scanner;
import java.util.Stack;

public class Q17608_self {
   public static void main(String args[]) {
      Scanner in = new Scanner(System.in);
      int N = in.nextInt();
      Stack<Integer> stickStack = new Stack<>();
      
      // 스택에 막대기 높이 저장
      for(int i=0; i<N; i++) {
         stickStack.push(in.nextInt());
      }

      int stick = stickStack.pop();
      int cnt = 1;
      
      // 막대기 비교
      int compare;
      while(!stickStack.isEmpty()) {
         compare = stickStack.pop();
         if(compare > stick) {
            cnt++;
            stick = compare;
         }
      }

      System.out.println(cnt);
   }
}
