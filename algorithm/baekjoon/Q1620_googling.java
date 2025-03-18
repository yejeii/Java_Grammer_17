
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q1620_googling {
   public static void main(String args[]) {
      Scanner in = new Scanner(System.in);
      String [] strs = in.nextLine().split(" ");
      int dictionaryCnt  = Integer.parseInt(strs[0]);
      int questionCnt = Integer.parseInt(strs[1]);

      Map<Integer, String> dictionaryHashMapNum = new HashMap<>();
      Map<String, Integer> dictionaryHashMapString = new HashMap<>();

      StringBuilder sb = new StringBuilder();

      // 이중 for문 방지를 위해 두 개의 HashMap에 저장
      for(int i=1; i<=dictionaryCnt; i++) {
         String monster = in.nextLine();
         dictionaryHashMapNum.put(i, monster);
         dictionaryHashMapString.put(monster, i);
      }

      for(int i=0; i<questionCnt; i++) {
         String inputQuestion = in.nextLine();
         sb.append(inputQuestion.matches("^[a-zA-Z]*$")
            ? dictionaryHashMapString.get(inputQuestion) 
            : dictionaryHashMapNum.get(Integer.parseInt(inputQuestion)));
         sb.append("\n");
      }

      System.out.println(sb);
      
   }
}