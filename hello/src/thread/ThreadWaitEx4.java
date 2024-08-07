package thread;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/** ThreadWaitEx3 개선 - Lock 과 Condition 사용
 * 
 * wait(), notify() 로 스레드 종류를 구분하지 않고 공유 객체의 waiting pool 에 같이 몰아넣는 대신,
 * 손님 스레드를 위한 Condition 과 요리사 스레드를 위한 Condition 을 만들어
 * 각각의 waiting pool 에서 따로 기다리도록 한다.
 * 
 * 이전과는 달리 요리사 스레드가 통지를 받아야 하는 상황에서 손님 스레드가 통지를 받는 경우가 없어졌다.
 * 기아 현상과 경쟁 상태가 개선된 것이다.
 * 
 * 또 하나의 문제..
 * 그러나, 스레드의 종류에 따라 구분하여 통지를 할 수 있게 된 것일 뿐,
 * 여전히 특정 스레드를 선택할 수 없기 때문에 같은 종류의 스레드간의 기아 현상이나 경쟁 상태가 발생할 가능성은 남아있다.
 * 
 * 손님이 원하는 음식의 종류로 Condition 을 더 세분화하면,
 * 통지를 받고도 원하는 음식이 없어서 다시 기다리는 일이 없도록 할 수 있다.
 * 
 * < 직접 해보기 >
 * 
 */
public class ThreadWaitEx4 {

   public static void main(String[] args) throws Exception {
      Table4 table = new Table4();  // 여러 스레드가 공유하는 객체

      new Thread(new Cook4(table), "COOK1").start();
      new Thread(new Customer4(table, "donut"), "CUST1").start();
      new Thread(new Customer4(table, "bugger"), "CUST2").start();

      Thread.sleep(5000);  // 5초 후 강제 종료
      System.exit(0); // 프로그램 전체 종료(모든 스레드 종료)
  }
}

class Customer4 implements Runnable {

   private Table4 table;
   private String food;

   
   public Customer4(Table4 table, String food) {
      this.table = table;
      this.food = food;
   }

   @Override
   public void run() {
      while (true) { 
         try {
            Thread.sleep(100);
         } catch (InterruptedException e) {}
         String name = Thread.currentThread().getName();

         table.remove(food);
         System.out.println(name + " ate a " + food);
      } // while
   }
}

class Cook4 implements Runnable {

   private Table4 table;

   public Cook4(Table4 table) {
      this.table = table;
   }

   @Override
   public void run() {
      while (true) { 
         // 임의의 요리를 하나 선택해서 table 에 추가
         int idx = (int)(Math.random()* table.dishNum());
         table.add(table.dishNames[idx]);

         try {
            Thread.sleep(10);
         } catch (InterruptedException e) {}
      }   // while
   }
}

class Table4 {
   String[] dishNames = { "donut", "donut", "bugger"};
   final int MAX_FOOD = 6;
   private ArrayList<String> dishes = new ArrayList<>();

   private ReentrantLock lock = new ReentrantLock();  // lock 생성
   // lock 으로 condition 생성
   private Condition forCook = lock.newCondition();
   private Condition forCust = lock.newCondition();

   // 테이블에 음식 추가 (요리사가 갖다놓음)
   public void add(String dish) {
      lock.lock();

      try {
         // 테이블에 음식이 가득찼으면, 테이블에 음식을 추가X
         while(dishes.size() >= MAX_FOOD) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " is waiting...");
            try {
               forCook.await(); // Cook 스레드를 기다리게 함
               Thread.sleep(500);
            } catch (InterruptedException e) {}
         }
         dishes.add(dish);
         forCust.signal();   // 기다리고 있는 CUST 를 깨우기 위함
         System.out.println("Dishes: " + dishes.toString());
      } finally {
         lock.unlock();
      }
   }

   // 테이블에 음식 제거 (손님이 먹음)
   public void remove(String dishName) {
      lock.lock();
      
      try {
         String name = Thread.currentThread().getName();
         
         while(dishes.size() == 0) {
            System.out.println(name + " is waiting... ");
            try {
                  forCust.await(); // CUST 스레드를 기다리게 함
                  Thread.sleep(500);  // 0.5초마다 음식이 추가되었는지 확인하면서 기다림
            } catch (InterruptedException e) {}
         }
         
         while(true) {
            // 지정된 요리와 일치하는 요소를 테이블에서 제거
            for (int i = 0; i < dishes.size(); i++) {
                  if(dishName.equals(dishes.get(i))) {
                     dishes.remove(i);
                     forCook.signal();   // 잠자고 있는 COOK 을 깨우기 위함
                     return;
                  }
            }   // end for

            try {
                  System.out.println(name + " is waiting...");
                  forCust.await(); // 원하는 음식이 없는 CUST 스레드를 기다리게 함
                  Thread.sleep(500);
            } catch (InterruptedException e) {
            }
         }   // end while
      } finally {
         lock.unlock();
      }
   }

   public int dishNum() { return dishNames.length; }
}