import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Q1620_self {
   public static void main(String args[]) {
      Scanner in = new Scanner(System.in);
      String [] strs = in.nextLine().split(" ");
      int dictionaryCnt  = Integer.parseInt(strs[0]);
      int questionCnt = Integer.parseInt(strs[1]);

      List<String> questionList = new ArrayList<>();
      Map<Integer, String> dictionHashMap = new HashMap<>();

      StringBuilder sb = new StringBuilder();

      for(int i=1; i<=dictionaryCnt; i++) {
         dictionHashMap.put(i, in.nextLine());
      }

      for (int i = 0; i < questionCnt; i++) {
         questionList.add(in.nextLine());
      }

      for(String q: questionList) {
         if(q.matches("^[a-zA-Z]*$")) {
            for(Integer key: dictionHashMap.keySet()) {
               if(q.equals(dictionHashMap.get(key))) {
                  sb.append(key);
               }
            }
         } else {
            int inputNumber = Integer.parseInt(q);
            sb.append(dictionHashMap.get(inputNumber));
         }
         sb.append("\n");
      }

      System.out.println(sb);
      
   }
}


/* ------------- 이중 for문으로 인한 시간 초과 ----------------- */