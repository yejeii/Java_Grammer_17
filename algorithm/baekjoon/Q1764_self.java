import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Q1764_self {
   public static void main(String args[]) {
      Scanner in = new Scanner(System.in);
      String arr[] = in.nextLine().split(" ");
      int everHeard = Integer.parseInt(arr[0]);
      int everSaw = Integer.parseInt(arr[1]);

      List<String> bothList = new ArrayList<>();
      Set<String> uniqueSet = new HashSet<>();
      for (int i = 0; i < everHeard+everSaw; i++) {
         String nameString = in.nextLine();
         if(uniqueSet.contains(nameString)) {
            bothList.add(nameString);
         } else {
            uniqueSet.add(nameString);
         }
      }
      
      bothList.sort(Comparator.naturalOrder());
      StringBuffer sb = new StringBuffer();
      sb.append(bothList.size());
      sb.append("\n");
      for (String name : bothList) {
         sb.append(name);
         sb.append("\n");
      }

      System.out.println(sb);
   }
}
