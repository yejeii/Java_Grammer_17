package thread;

import java.util.ArrayList;

/** ThreadWaitEx1 개선 - synchronized 사용
 * 
 * 여러 스레드가 공유하는 객체인 테이블의 add() 와 remove() 를 동기화하였다.
 * 원활히 진행되고 있지는 않은것 같다...
 * 
 * 손님 스레드가 원하는 음식이 테이블에 없으면 failed to eat 을 출력하고,
 * 테이블에 음식이 하나도 없으면 0.5초마다 음식이 추가되는지 확인하면서 기다리도록 했다.
 * 
 * 문제..
 * 그런데, 요리사 스레드는 왜 음식을 추가하지 않고 손님 스레드를 계속 기다리게 하는 걸까?
 * -> 손님 스레드가 테이블 객체의 lock 을 쥐고 기다리기 때문이다.
 * -> 요리사 스레드가 음식을 새로 추가하려해도 테이블 객체의 락을 얻을 수가 없어서 불가능하다.
 * 
 * 해결 : wait() 과 notify() 사용
 *        wait() 으로 락을 풀고 기다리다가 음식이 추가되면 notify() 로 통보받고 다시 락을 얻게해서 남은 작업 진행하게 하자.
 */
public class ThreadWaitEx2 {

    public static void main(String[] args) throws Exception {
        Table2 table = new Table2();  // 여러 스레드가 공유하는 객체

        new Thread(new Cook2(table), "COOK1").start();
        new Thread(new Customer2(table, "donut"), "CUST1").start();
        new Thread(new Customer2(table, "bugger"), "CUST2").start();

        Thread.sleep(3000);  // 3초 후 강제 종료
        System.exit(0); // 프로그램 전체 종료(모든 스레드 종료)
    }
}

class Customer2 implements Runnable {

    private Table2 table;
    private String food;

    
    public Customer2(Table2 table, String food) {
        this.table = table;
        this.food = food;
    }

    @Override
    public void run() {
        while (true) { 
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            String name = Thread.currentThread().getName();

            if(eatFood()) 
                System.out.println(name + " ate a " + food);
            else 
                System.out.println(name + " failed to eat. :(");
        } // while
    }

    boolean eatFood() { return table.remove(food); }
}

class Cook2 implements Runnable {

    private Table2 table;

    public Cook2(Table2 table) {
        this.table = table;
    }

    @Override
    public void run() {
        while (true) { 
            // 임의의 요리를 하나 선택해서 table 에 추가
            int idx = (int)(Math.random()* table.dishNum());
            table.add(table.dishNames[idx]);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }   // while
    }
}

class Table2 {
    String[] dishNames = { "donut", "donut", "bugger"};
    final int MAX_FOOD = 6;
    private ArrayList<String> dishes = new ArrayList<>();

    public synchronized void add(String dish) {
        // 테이블에 음식이 가득찼으면, 테이블에 음식을 추가X
        if(dishes.size() >= MAX_FOOD)
            return;
        dishes.add(dish);
        System.out.println("Dishes: " + dishes.toString());
    }

    public boolean remove(String dishName) {

        synchronized(this) {
            
            while(dishes.size() == 0) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " is waiting... ");
                try {
                    Thread.sleep(500);  // 0.5초마다 음식이 추가되었는지 확인하면서 기다림
                } catch (InterruptedException e) {
                }
            }
            
            // 지정된 요리와 일치하는 요소를 테이블에서 제거
            for (int i = 0; i < dishes.size(); i++) {
                if(dishName.equals(dishes.get(i))) {
                    dishes.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public int dishNum() { return dishNames.length; }
}