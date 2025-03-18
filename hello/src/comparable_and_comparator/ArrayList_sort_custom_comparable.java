import java.util.ArrayList;
import java.util.Collections;

public class ArrayList_sort_custom_comparable {
   public static void main(String args[]) {

      /** 1. Comparable 인터페이스 구현 방식 
       * Collections.sort() 가 객체를 정렬할 때, 해당 객체의 Comparable 을 구현한 compareTo() 를 참조해 정렬 순서를 결정하는데, 이 방식을 이용한 것.
       * 정렬 대상이 되는 클래스가 Comparable 인터페이스를 구현하게 하고, compareTo() 를 오버라이드한다면,
       * Collections.sort() 를 이용해 객체 정렬이 가능하다. 
       * 그래서 만약 정렬 대상이 되는 배열이 Comparable 을 구현 안했다면, Comparator 를 구현한 사용자 정의 클래스를 이용해 sort() 를 사용해야 한다.
       * */
      ArrayList<Fruit> list = new ArrayList<>();
      list.add(new Fruit("Apple", 2000));
      list.add(new Fruit("Orange", 3000));
      list.add(new Fruit("Banana", 1000));
      System.out.println("원본: " + list);

      // price 순 오름차순 정렬
      Collections.sort(list);
      System.out.println("오름차순: " + list);

      // price 순 내림차순 정렬
      Collections.sort(list, Collections.reverseOrder());
      System.out.println("내림차순: " + list);
      
   }
}

class Fruit implements Comparable<Fruit> {
   private String name;
   private int price;

   public Fruit(String name, int price) {
      this.name = name;
      this.price = price;
   }

   @Override
   public int compareTo(Fruit f) {
      if(f.price < price) return 1;
      else if(f.price > price) return -1;
      return 0;
   }

   @Override
   public String toString() {
      return "[" + this.name + ": " + this.price + " ]";
   }

   
   
}