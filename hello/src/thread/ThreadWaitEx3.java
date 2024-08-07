package thread;

import java.util.ArrayList;

public class ThreadWaitEx3 {

    public static void main(String[] args) throws Exception {
        Table3 table = new Table3();  // 여러 스레드가 공유하는 객체

        new Thread(new Cook3(table), "COOK1").start();
        new Thread(new Customer3(table, "donut"), "CUST1").start();
        new Thread(new Customer3(table, "burger"), "CUST2").start();

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
