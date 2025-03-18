import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ArrayList_sort {
   public static void main(String args[]) {

      /** ArrayList 정렬 
       * 1. Collections.sort()            - list1
       * 2. List.sort() - Java 8 이후     - list2
       * 3. 사용자 정의                   - ArrayList_sort_custom_* 클래스 참고 */
      ArrayList<String> list1 = new ArrayList<>(Arrays.asList("miku", "yumm", "sammo", "Lulu", "lilly"));
      ArrayList<String> list2 = new ArrayList<>(Arrays.asList("yumm", "kiley", "pukeu", "Amy", "ammley"));
      System.out.println("원본 : " + list1);
      System.out.println("원본 : " + list2);

      // 오름차순 정렬
      Collections.sort(list1);
      list2.sort(Comparator.naturalOrder());
      System.out.println("오름차순 : " + list1);
      System.out.println("오름차순 : " + list2);

      // 내림차순 정렬
      Collections.sort(list1, Collections.reverseOrder());
      list2.sort(Comparator.reverseOrder());
      System.out.println("내림차순 : " + list1);
      System.out.println("내림차순 : " + list2);

      // 대소문자 구분없이 오름차순 정렬
      Collections.sort(list1, String.CASE_INSENSITIVE_ORDER);
      list2.sort(String.CASE_INSENSITIVE_ORDER);
      System.out.println("대소문자 구분없이 오름차순 : " + list1);
      System.out.println("대소문자 구분없이 오름차순 : " + list2);

      // 대소문자 구분없이 내림차순 정렬
      Collections.sort(list1, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
      list2.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
      System.out.println("대소문자 구분없이 내림차순 : " + list1);
      System.out.println("대소문자 구분없이 내림차순 : " + list2);
   }
}
