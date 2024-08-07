package thread;

import java.util.ArrayList;

/** ThreadWaitEx2 개선 - wait() 사용
 * 
 * 테이블에 음식이 없을 때뿐 아니라 원하는 음식이 없을 떄에도 손님이 기다리도록 변경함
 * 
 * 또 하나의 문제..
 * 테이블 객체의 waiting pool 에 요리사 스레드와 손님 스레드가 같이 기다린다는 것
 * -> notify() 가 호출되었을 때, 요리사 스레드와 손님 스레드 중에서 누가 통지를 받았는지 알 수 X
 * 
 * 만약 테이블의 음식이 줄어들어서 notify() 가 호출된 경우, 요리사 스레드가 통지를 받아야 함
 * 그러나 notify() 는 그저 waiting pool 에서 대기중인 스레드 중에서 하나를 임의로 선택해 통지할 뿐,
 * 요리사 스레드를 선택해서 통지할 수 없음
 * 운 좋게 요리사 스레드가 통지를 받으면 좋으나, 손님 스레드가 통지를 받으면 락을 얻어도 여전히 자신이
 * 원하는 음식이 없기 때문에 다시 waiting pool 에 들어가게 됨
 * -> wait() 의 장점을 살리지 못하고 있음
 * 
 * -> notifyAll() 을 사용하면 모든 스레드에게 통지를 하므로, 손님 스레드가 다시 waiting pool 에 들어가도
 * 요리사 스레드는 결국 락을 얻어서 작업을 진행할 수 있음
 * 
 * 하지만, 손님 스레드까지 통지를 받아서 불필요하게 요리사 스레드와 락을 얻기 위해 경쟁하게 됨
 * -> 경쟁 상태를 개선하기 위해 요리사 스레드와 손님 스레드를 구별해서 통지하는 것이 필요
 * 
 * 해결 : Lock 과 Condition 사용
 */
public class ThreadWaitEx3 {

    public static void main(String[] args) throws Exception {
        Table3 table = new Table3();  // 여러 스레드가 공유하는 객체

        new Thread(new Cook3(table), "COOK1").start();
        new Thread(new Customer3(table, "donut"), "CUST1").start();
        new Thread(new Customer3(table, "bugger"), "CUST2").start();

        Thread.sleep(5000);  // 3초 후 강제 종료
        System.exit(0); // 프로그램 전체 종료(모든 스레드 종료)
    }
}

class Customer3 implements Runnable {

    private Table3 table;
    private String food;

    
    public Customer3(Table3 table, String food) {
        this.table = table;
        this.food = food;
    }

    @Override
    public void run() {
        while (true) { 
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
            String name = Thread.currentThread().getName();

            table.remove(food);
            System.out.println(name + " ate a " + food);
        } // while
    }
}

class Cook3 implements Runnable {

    private Table3 table;

    public Cook3(Table3 table) {
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

class Table3 {
    String[] dishNames = { "donut", "donut", "bugger"};
    final int MAX_FOOD = 6;
    private ArrayList<String> dishes = new ArrayList<>();

    public synchronized void add(String dish) {

        // 테이블에 음식이 가득찼으면, 테이블에 음식을 추가X
        while(dishes.size() >= MAX_FOOD) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " is waiting...");
            try {
                wait(); // Cook 스레드를 기다리게 함
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
        dishes.add(dish);
        notify();   // 기다리고 있는 CUST 를 깨우기 위함
        System.out.println("Dishes: " + dishes.toString());
    }

    public void remove(String dishName) {
        synchronized(this) {
            String name = Thread.currentThread().getName();
           
            while(dishes.size() == 0) {
                System.out.println(name + " is waiting... ");
                try {
                    wait(); // CUST 스레드를 기다리게 함
                    Thread.sleep(500);  // 0.5초마다 음식이 추가되었는지 확인하면서 기다림
                } catch (InterruptedException e) {}
            }
            
            while(true) {
                // 지정된 요리와 일치하는 요소를 테이블에서 제거
                for (int i = 0; i < dishes.size(); i++) {
                    if(dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        notify();   // 잠자고 있는 COOK 을 깨우기 위함
                        return;
                    }
                }   // end for

                try {
                    System.out.println(name + " is waiting...");
                    wait(); // 원하는 음식이 없는 CUST 스레드를 기다리게 함
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }   // end while
        }   // end synchronized
    }

    public int dishNum() { return dishNames.length; }
}
