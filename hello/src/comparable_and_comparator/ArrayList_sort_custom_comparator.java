import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArrayList_sort_custom_comparator {
   public static void main(String args[]) {

      /** 2. Comparator 인터페이스 구현 방식 
       * Comparator 인터페이스를 구현한 사용자 정의 Comparator 클래스를 통해
       * Collections.sort() 또는 List.sort() 의 인자로 전달하여 정렬의 기준으로 만들 수 있다.
       * */
      ArrayList<Fruit> list = new ArrayList<>();
      list.add(new Fruit("Apple", 2000));
      list.add(new Fruit("Orange", 3000));
      list.add(new Fruit("Banana", 1000));
      System.out.println("원본: " + list);

      // price 순 오름차순 정렬
      Collections.sort(list, new FruitPriceComparator());
      System.out.println("price 순 오름차순: " + list);

      // price 순 내림차순 정렬
      Collections.sort(list, new FruitPriceComparator().reversed());
      System.out.println("price 순 내림차순: " + list);

      // name 순 오름차순 정렬
      Collections.sort(list, new FruitNameComparator());
      System.out.println("name 순 오름차순: " + list);

      // name 순 내림차순 정렬
      Collections.sort(list, new FruitNameComparator().reversed());
      System.out.println("name 순 내림차순: " + list);      
   }
}

class FruitPriceComparator implements Comparator<Fruit> {

   /** 기본적으로 숫자를 오름차순으로 정렬하기 위해서는
    * compare() 의
    * '첫번째 파라미터 > 두번째 파라미터' 이면 양수를,
    * '첫번째 파라미터 < 두번째 파라미터' 이면 음수를,
    * 같으면 0을 리턴해야 한다. */
   @Override
   public int compare(Fruit f1, Fruit f2) {
      if(f1.price > f2.price) return 1;
      else if(f1.price < f2.price) return -1;
      return 0;
   }
}

class FruitNameComparator implements Comparator<Fruit> {

   /** 문자열을 비교하기 위해 사용된 compareTo() 가
    * 문자열의 크기 순서에 따라서 양수, 0, 음수를 리턴하기 때문에
    * 다른 조건문없이 compareTo()에서 리턴하는 값을 그대로 리턴 */
   @Override
   public int compare(Fruit f1, Fruit f2) {
      return f1.name.compareTo(f2.name);
   }
   
}

class Fruit{
   String name;
   int price;

   public Fruit(String name, int price) {
      this.name = name;
      this.price = price;
   }

   @Override
   public String toString() {
      return "[" + this.name + ": " + this.price + " ]";
   }
}